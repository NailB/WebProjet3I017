/**
 * 
 */
package service.comments;

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
import tools.MessageTools;
import tools.ServiceTools;
import tools.SessionTools;
import tools.UserTools;

/**
 * @author LAOUER Walid
 *
 */
public class AddCommentS {
	public static JSONObject postComment(String key, String id_message, String text) throws InstantiationException, IllegalAccessException {
		if(key == null || text==null || id_message==null) {
			return ServiceTools.serviceRefused(Data.MESSAGE_MISSING_PARAMETERS, Data.CODE_MISSING_PARAMETERS);
		}
		MongoCollection<Document> m = Database.getMongoMessage();
		Connection co=null;
		try {
			co = Database.getMySQLConnection();
			int id =SessionTools.getIdFromKey(key, co);
			boolean b = SessionTools.isConnected(key);
			if(!b) {
				co.close();
				return ServiceTools.serviceRefused(Data.MESSAGE_USER_NOT_CONNECTED, Data.CODE_USER_NOT_CONNECTED);
			}
			try {
				return MessageTools.postComment(id,id_message, text, m, co);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return ServiceTools.serviceRefused(Data.MESSAGE_ERROR_JSON, Data.CODE_ERROR_JSON);
			}
			//
		}catch(SQLException s) {
			s.printStackTrace();
			return ServiceTools.serviceRefused(Data.MESSAGE_ERROR_SQL, Data.CODE_ERROR_SQL);
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

	public static JSONObject listComments(String key, String id_twist) throws InstantiationException, IllegalAccessException
	{
		if(key == null || id_twist == null) {
			return ServiceTools.serviceRefused(Data.MESSAGE_MISSING_PARAMETERS, Data.CODE_MISSING_PARAMETERS);
		}
		MongoCollection<Document> m = Database.getMongoMessage();
		Connection co=null;
		try {
			co = Database.getMySQLConnection();
			boolean b = SessionTools.isConnected(key);
			if(!b) {
				co.close();
				return ServiceTools.serviceRefused(Data.MESSAGE_USER_NOT_CONNECTED, Data.CODE_USER_NOT_CONNECTED);
			}
			JSONArray array = MessageTools.listComment(id_twist, m);
			return ServiceTools.serviceAccepted().put("Comments", array);		
		}catch(SQLException s) {
			return ServiceTools.serviceRefused(Data.MESSAGE_ERROR_SQL, Data.CODE_ERROR_SQL);
		}catch(JSONException e) {
			return ServiceTools.serviceRefused(Data.MESSAGE_ERROR_JSON, Data.CODE_ERROR_JSON);
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