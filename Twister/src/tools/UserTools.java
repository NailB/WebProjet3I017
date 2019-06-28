package tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Address;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import db.DBStatic;
import db.Database;
import service.user.LoginS;

/**
 * @author LAOUER Walid
 *
 */
public class UserTools {


	public static boolean userExist(String log, Connection co) throws SQLException {
		Statement st = null;
		ResultSet res = null;

		st = co.createStatement();
		String query = "SELECT user_login from users where user_login = '"+log+"'";
		res = st.executeQuery(query);

		if (res.next()) {
			return true;
		}
		res.close();
		st.close();

		return false;

	}



	public static boolean mailExists(String mail, Connection co) throws SQLException {
		Statement st = null;
		ResultSet res = null;

		st = co.createStatement();
		String query = "SELECT * from users where user_mail = '"+ mail + "'";
		res = st.executeQuery(query);
		if (res.next()) {
			return true;
		}
		res.close();
		st.close();



		return false;
	}

	public static JSONObject insertUser(String login, String nom, String prenom, String mail, String mdp, Connection co) throws  SQLException, JSONException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Statement st = null;

		st = co.createStatement();
		String query = "INSERT INTO users (`user_id`, `user_login`, `user_nom`, `user_prenom`, `user_mail`, `user_password`) VALUES(null,'" + login + "','" + nom + "','" + prenom + "','" + mail + "','" + mdp + "')";
		st.executeUpdate(query);

		st.close();
		co.close();

		System.out.println("insert user");
		//JSONObject json= LoginS.login(login, mdp);
		//String key= json.getString("key");
		return ServiceTools.serviceAccepted().put(login+" has been added successfully", 1).put("success", 1);

	}

	public static boolean checkPassword(String log, String mdp, Connection co) throws SQLException {
		Statement st = null;
		ResultSet res = null;

		st = co.createStatement();
		String query = "SELECT * from users where user_login = '" + log + "' and user_password= '"+mdp+"'";
		res = st.executeQuery(query);
		if (res.next()) {
			return true;
		}

		res.close();
		st.close();

		return false;

	}

	public static int getIdUser(String log, Connection co) throws SQLException {
		Statement st = null;
		ResultSet res = null;
		int id_user = 0;
		st = co.createStatement();
		String query = "SELECT user_id FROM users WHERE user_login= \""+log+"\";";
		//System.out.println("ici passe par la requete");
		res = st.executeQuery(query);
		//System.out.println(res.getInt("user_id"));
		if (res.next()) {
			System.out.println("il existe bien un resultat");
			id_user = res.getInt("user_id");
		}


		res.close();
		st.close();



		return id_user;
	}

	public static boolean userConnected(int id_user, Connection co) throws SQLException {
		Statement st = null;
		ResultSet res = null;
		st = co.createStatement();
		String query = "SELECT * FROM sessions WHERE user_id = '"+id_user+"'";
		res = st.executeQuery(query);

		if (res.next()) {
			return true;
		}
		

		res.close();
		st.close();


		return false;
	}

	public static String getLogin(int id) {
		Connection co = null;
		Statement st = null;
		ResultSet res = null;
		try {
			co = Database.getMySQLConnection();
			st = co.createStatement();
			String query = "SELECT user_login FROM users WHERE user_id = "+id+";";
			res = st.executeQuery(query);
			if (res.next()) {
				return res.getString(1);
			}
			return null;
		}catch (Exception e)
		{
			System.err.println("Error getLogin : " + e.getMessage());
			return null;
		}finally
		{
			try
			{
				res.close();
				st.close();
				if ((!DBStatic.is_pooling) && (co != null))
					co.close();
			}
			catch(SQLException e)
			{
				System.err.println("Error closing connexion : " + e.getMessage());
			}
		}
	}



	public static JSONObject sendPasswordRecovery(String mail, Connection co) throws SQLException, JSONException {
		Statement st = null;
		ResultSet res = null;

		st = co.createStatement();
		String query = "SELECT user_password from users where user_mail = '"+ mail + "'";
		st= co.createStatement();
		res = st.executeQuery(query);

		//JSONObject o = new JSONObject();
		if(res.next()) {
			try {
				String password =res.getString("user_password");
				String host ="smtp.gmail.com" ;
				String user = Data.MAIL_ADDRESS;
				String pass = Data.MAIL_PASSWORD;
				String to = mail;
				String from = Data.MAIL_ADDRESS;
				String subject = "This is confirmation email for your Twister account. Please insert this password to activate your account.";
				String messageText = "Twister account password :"+password;
				boolean sessionDebug = false;

				Properties props = System.getProperties();

				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.host", host);
				props.put("mail.smtp.port", "587");
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.required", "true");

				
				Session mailSession = Session.getDefaultInstance(props, null);
				mailSession.setDebug(sessionDebug);
				Message msg = new MimeMessage(mailSession);
				msg.setFrom(new InternetAddress(from));
				InternetAddress[] address = {new InternetAddress(to)};
				msg.setRecipients(Message.RecipientType.TO, address);
				msg.setSubject(subject); msg.setSentDate(new Date());
				msg.setText(messageText);

				Transport transport=mailSession.getTransport("smtp");
				transport.connect(host, user, pass);
				transport.sendMessage(msg, msg.getAllRecipients());
				transport.close();
				System.out.println("message sent successfully");
			}catch(Exception ex)
			{
				System.out.println(ex);
			}

		}
		st.close();
		return ServiceTools.serviceAccepted().put("message send successfully", 1);
	}


	public static JSONArray searchUserByLogin(String login, Connection c)
	{
		try
		{
			Statement st = c.createStatement();
			String query = "SELECT * FROM users WHERE login LIKE \"%"+login+"%\";";
			ResultSet cursor = st.executeQuery(query);
			JSONArray userArray = new JSONArray();
			while(cursor.next())
			{
				JSONObject json = new JSONObject();
				json.put("user_id",cursor.getString("id"));
				json.put("login",cursor.getString("login"));
				userArray.put(json);
			}
			
			return userArray;
		}
		catch (Exception e)
		{
			System.err.println("Error User_Search : " + e.getMessage());
			return null;
		}
		
		
	}


}






