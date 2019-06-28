/**
 * 
 */
package tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

import db.Database;

/**
 * @author LAOUER Walid
 *
 */
public class SessionTools {

	/**
	 * @param log
	 * @param mdp
	 * @param co 
	 * @return
	 * @throws JSONException 
	 * @throws SQLException 
	 */
	public static JSONObject insertConnexion(String log, String mdp, Connection co) throws JSONException, SQLException {
		Statement st = null;
		String key = generatekey();


		st = co.createStatement();
		String query = "INSERT INTO sessions (`user_id`, `session_key`) VALUES('" + UserTools.getIdUser(log, co) + "','" + key + "')";
		st.executeUpdate(query);


		st.close();
	
		
		return ServiceTools.serviceAccepted().put(log+" signed in", 001).put("key", key);
	}

	public static String generatekey() {
		String chars = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ0123456789";
		String key = "";
		for (int i=0; i<32; i++) {
			int r = new Random().nextInt(chars.length());
			key += chars.charAt(r);
		}
		return key;
	}


	/**
	 * @param key
	 * @return
	 */
	public static int getIdFromKey(String key,Connection co) throws SQLException {
		Statement st = null;
		ResultSet res = null;
		int id_user = 0;

		st = co.createStatement();
		String query = "SELECT user_id FROM sessions WHERE session_key= \""+key+"\";";
		res = st.executeQuery(query);

		if (res.next()) {
			//System.out.println("il existe bien un resultat");
			id_user = res.getInt("user_id");
		}
		res.close();
		st.close();
		if(id_user==0)
			System.out.println("Utilisateur non connecte ");
		return id_user;
	}
	
	public static String getKeyFromId(int user_id, Connection co) throws SQLException {
		String key_session=null;
		Statement st = null;
		ResultSet res = null;
		
		st = co.createStatement();
		String query = "SELECT session_key FROM sessions WHERE user_id= '"+user_id+"';";
		res = st.executeQuery(query);
		if(res.next()) {
			key_session=res.getString("session_key");
		}
		return key_session;
	}

	public static boolean isConnected(String key)
	{
		Connection c = null;
		try 
		{
			c = Database.getMySQLConnection();
			Statement st = c.createStatement();
			String query = "SELECT session_start FROM sessions WHERE session_key= \""+key+"\";";
			ResultSet rs = st.executeQuery(query);
			if(rs.next())
			{
				Timestamp tempsSession = rs.getTimestamp(1);
				Timestamp maintenant = new Timestamp(new GregorianCalendar().getTimeInMillis());
//				System.out.println(maintenant.getTime());
//				System.out.println(tempsSession.getTime());
				if((maintenant.getTime() - tempsSession.getTime()) >= 360000)
				{
					if (!root(key))
					{
						//System.out.println(root(key));
						removeConnection(key,c);
						return false;
					}
					else
						return true;
				}
				else if(!root(key))					
					return updateSession(key);
				else
					return true;
			}
			else
				return false;
		} 
		catch (Exception e) 
		{
			System.err.println(e.getMessage());
			return false;
		}


	}

	/**
	 * @param key
	 * @throws JSONException 
	 */
	public static JSONObject removeConnection(String key, Connection co) throws JSONException, SQLException {
		// TODO Auto-generated method stub
		int id_user = SessionTools.getIdFromKey(key, co);
		if(id_user==0)
			return ServiceTools.serviceRefused(Data.MESSAGE_USER_NOT_CONNECTED, Data.CODE_USER_NOT_CONNECTED);
		Statement st = null;
		//int res = null;
		st = co.createStatement();
		String query = "DELETE FROM sessions WHERE user_id = '"+id_user+"'";
		st.executeUpdate(query);

		return ServiceTools.serviceAccepted().put(UserTools.getLogin(id_user)+" Disconnected", 001);

	}

	public static boolean updateSession(String key)
	{
		Connection c = null;
		try {
			c = Database.getMySQLConnection();
			Statement st = c.createStatement();
			String query = "UPDATE sessions SET session_start = NOW() WHERE session_key = \""+key+"\";";
			if(st.executeUpdate(query) == 0)
				return false;
			return true;
		} 
		catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	/**
	 * @param key
	 * @return
	 */
	private static boolean root(String key){
		Connection c = null;
		try 
		{
			c = Database.getMySQLConnection();
			Statement st = c.createStatement();
			String query = "SELECT session_root FROM sessions WHERE session_key= \""+key+"\";";
			ResultSet rs = st.executeQuery(query);
			if(rs.next())
			{
				int root = rs.getInt(1);
				if(root == 0)
					return false;
				else
					return true;
			}
			else
				return false;
		} 
		catch (Exception e) 
		{
			System.err.println(e.getMessage());
			return false;
		}

	} 
}
