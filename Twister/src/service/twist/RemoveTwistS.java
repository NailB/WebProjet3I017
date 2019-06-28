/**
 * 
 */
package service.twist;

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

/**
 * @author LAOUER Walid
 *
 */
public class RemoveTwistS {
	public static JSONObject RemoveTwist(String key,String tweet_id) throws InstantiationException, IllegalAccessException  {
		if(key == null || tweet_id==null ) {
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
				return ServiceTools.serviceRefused(Data.MESSAGE_USER_NOT_CONNECTED, Data.CODE_USER_NOT_CONNECTED);
			}
			return MessageTools.RemoveTwist(id_user, tweet_id ,co , m);
		}catch(SQLException s) {
			return ServiceTools.serviceRefused(Data.MESSAGE_ERROR_SQL, Data.CODE_ERROR_SQL);
		}

		catch(JSONException e) {
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
