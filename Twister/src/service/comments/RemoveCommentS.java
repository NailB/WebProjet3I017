package service.comments;

import java.sql.Connection;
import java.sql.SQLException;

import org.bson.Document;
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

public class RemoveCommentS {
	
	public RemoveCommentS() {
		
	}
	
	public static JSONObject DeleteComment(String key, String id_message, String id_comment) throws InstantiationException, IllegalAccessException {
		if(key==null || id_message==null) {
			return ServiceTools.serviceRefused(Data.MESSAGE_MISSING_PARAMETERS, Data.CODE_MISSING_PARAMETERS);
		}
		MongoCollection<Document> m = Database.getMongoMessage();
		Connection co=null;
		try {
			co = Database.getMySQLConnection();
			int id_user = SessionTools.getIdFromKey(key, co);
			boolean b = SessionTools.isConnected(key);
			if(!b) {
				co.close();
				return ServiceTools.serviceRefused(Data.MESSAGE_USER_NOT_CONNECTED,Data.CODE_USER_NOT_CONNECTED );
			}
			return MessageTools.removeComment(id_message, id_comment, m);
		}catch(SQLException | JSONException s) {
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
}
