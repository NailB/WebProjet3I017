package service;
import java.sql.Connection;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import db.DBStatic;
import db.Database;
import tools.Data;
import tools.ServiceTools;
import tools.UserTools;

/**
 * @author BELAREF Nail
 *
 */
public class SearchS {
	/**
	 * 
	 * @param login Le login de l'utilisateur
	 * @param key
	 * @return
	 * @throws SQLException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static JSONObject searchUserByLogin(String login, String key) throws InstantiationException, IllegalAccessException, SQLException
	{
		if(login == null || key == null)
			return ServiceTools.serviceRefused(Data.MESSAGE_ERROR_JSON,Data.CODE_ERROR_JSON);

		Connection co=null;
		try {
			co = Database.getMySQLConnection();
			if(tools.SessionTools.isConnected(key))
				return ServiceTools.serviceRefused(Data.MESSAGE_USER_NOT_CONNECTED, Data.CODE_USER_NOT_CONNECTED);
			JSONArray liste = UserTools.searchUserByLogin(login, co);
			if(liste == null)
				return ServiceTools.serviceRefused(Data.MESSAGE_ERROR_MongoDB, Data.CODE_ERROR_MongoDB);

			try
			{
				return ServiceTools.serviceAccepted().put("list_search_user", liste);
			}
			catch (JSONException e)
			{
				System.err.println("Error search User By Login : " + e.getMessage());
				return ServiceTools.serviceRefused(Data.MESSAGE_ERROR_JSON,	Data.CODE_ERROR_JSON);
			}
		}finally {
			if ((!DBStatic.is_pooling) && (co != null))
				try {
					co.close();
				} catch (SQLException e) {
					System.err.println("Error closing connexion : " + e.getMessage());

					e.printStackTrace();
				}
		}
	}
}
