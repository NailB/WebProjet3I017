/**
 * 
 */
package service.follows;

import java.sql.Connection;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import db.DBStatic;
import db.Database;
import tools.Data;
import tools.FriendTools;
import tools.ServiceTools;
import tools.SessionTools;
import tools.UserTools;

/**
 * @author LAOUER Walid
 *
 */
public class RemoveFriendS {
	public static JSONObject Unfollow(String key, int id_friend) throws InstantiationException, IllegalAccessException {
		if (key==null)
			return ServiceTools.serviceRefused("Paramètre(s) vide(s)", -1);
		
		Connection co=null;
		try {
			co = Database.getMySQLConnection();
			int id_user = SessionTools.getIdFromKey(key, co);
			boolean b = SessionTools.isConnected(key);
			System.out.println(b);
			if(!b) {
				return ServiceTools.serviceRefused(Data.MESSAGE_USER_NOT_CONNECTED, Data.CODE_USER_NOT_CONNECTED);
			}
		boolean is_friend = FriendTools.alreadyFriend(id_user, id_friend, co);
		if (!is_friend) {
			co.close();
			return ServiceTools.serviceRefused("Can't unfollow", -2);
		}
		return FriendTools.unfollow(id_user, id_friend, co);
		}catch(SQLException s){
			return ServiceTools.serviceRefused(Data.MESSAGE_ERROR_SQL, Data.CODE_ERROR_SQL);
		}catch(JSONException e){
			return ServiceTools.serviceRefused(Data.MESSAGE_ERROR_JSON, Data.CODE_ERROR_JSON);
		}finally {
			try {
				if ((!DBStatic.is_pooling) && (co != null))
					co.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Error closing connexion : " + e.getMessage());
			}
		}
	}
}
