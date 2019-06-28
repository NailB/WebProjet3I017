package service;

import java.sql.Connection;
import java.sql.SQLException;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.client.MongoCollection;

import db.DBStatic;
import db.Database;
import tools.Data;
import tools.ServiceTools;

public class AdvancedSearch {
	/**
	 * 
	 * @param from, to et key de l'utilisateur // from to des dates
	 * @param key
	 * @return
	 * @throws JSONException 
	 * @throws SQLException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static JSONObject AdvencedSearchByDate(String login , String from, String to, String key) throws JSONException, InstantiationException, IllegalAccessException, SQLException{
		{
			if(from == null || to == null|| key == null )
				return ServiceTools.serviceRefused(Data.MESSAGE_ERROR_JSON,Data.CODE_ERROR_JSON);
			Connection co=null;//connection vers ma table sql pour checker la connection
			MongoCollection<Document> m = Database.getMongoMessage();
			try {
				co = Database.getMySQLConnection();
				
				if(!tools.SessionTools.isConnected(key))//check si l'utilisateur est connecté 
					return ServiceTools.serviceRefused(Data.MESSAGE_USER_NOT_CONNECTED, Data.CODE_USER_NOT_CONNECTED);
				JSONArray list = tools.MessageTools.getTwistByDate(login, from, to, m, co);
				return ServiceTools.serviceAccepted().put("twists", list);
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
	}
