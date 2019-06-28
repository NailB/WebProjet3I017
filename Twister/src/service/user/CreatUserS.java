package service.user;

import java.sql.Connection;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import db.DBStatic;
import db.Database;
import tools.Data;
import tools.ServiceTools;
import tools.UserTools;
/**
 * @author LAOUER Walid
 *
 */
public class CreatUserS {
	
	public static JSONObject createUser(String login, String nom, String prenom, String mail, String mdp) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (nom == null || prenom == null || mail == null || login == null || mdp == null) {
			return ServiceTools.serviceRefused(Data.MESSAGE_MISSING_PARAMETERS, Data.CODE_MISSING_PARAMETERS);
		}
		
		Connection co=null;
		try {
			co = Database.getMySQLConnection();
		
		
		if (UserTools.userExist(login, co)) {
			co.close();
			return ServiceTools.serviceRefused(Data.MESSAGE_USER_ALREADY_EXISTS, Data.CODE_USER_ALREADY_EXISTS);
		}
		
		if (UserTools.mailExists(mail, co)) {
			co.close();
			return ServiceTools.serviceRefused(Data.MESSAGE_MAIL_ALREADY_EXISTS, Data.CODE_MAIL_ALREADY_EXISTS);
		}
		if(mdp.isEmpty())
			return ServiceTools.serviceRefused(Data.MESSAGE_MISSING_PARAMETERS, Data.CODE_MISSING_PARAMETERS);
		return UserTools.insertUser(login,nom, prenom, mail, mdp, co);
		
		}catch(JSONException e) {
			e.printStackTrace();
			ServiceTools.serviceRefused(Data.MESSAGE_ERROR_JSON,Data.CODE_ERROR_JSON);}
		catch(SQLException s) {
			s.printStackTrace();
			return ServiceTools.serviceRefused(Data.MESSAGE_ERROR_SQL, Data.CODE_ERROR_SQL);}
			
		finally {
			if ((!DBStatic.is_pooling) && (co != null)) {
				try {
					co.close();
				} catch (SQLException e) {
					System.err.println("Error closing connexion : " + e.getMessage());

					e.printStackTrace();
				}
		}
		}
		
		return ServiceTools.serviceRefused(Data.MESSAGE_ERROR_JSON, Data.CODE_ERROR_JSON);
	}

}
