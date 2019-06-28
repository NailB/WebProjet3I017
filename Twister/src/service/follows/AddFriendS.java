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

/**
 * @author LAOUER Walid
 *
 */
public class AddFriendS {
	public static JSONObject addFriend(String key, int id_friend) throws InstantiationException, IllegalAccessException {
		if (key == null || id_friend==0)
			return ServiceTools.serviceRefused(Data.MESSAGE_MISSING_PARAMETERS, Data.CODE_MISSING_PARAMETERS);
		Connection co=null;
		try {
			co = Database.getMySQLConnection();
			int id_user = SessionTools.getIdFromKey(key, co);
			boolean b = SessionTools.isConnected(key);
			
			if(b==false) {
				System.out.println(b);
				co.close();
				return ServiceTools.serviceRefused(Data.MESSAGE_USER_NOT_CONNECTED, Data.CODE_USER_NOT_CONNECTED);
			}

			boolean is_friend = FriendTools.alreadyFriend(id_user, id_friend, co);
			if (is_friend) {
				co.close();
				return ServiceTools.serviceRefused(Data.MESSAGE_USER_ALREADY_FRIEND, Data.CODE_USER_ALREADY_FRIEND);
			}
			return FriendTools.follow(key, id_friend, co);
		} catch (JSONException | SQLException s) {
			// TODO Auto-generated catch block
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

	public static JSONObject listFollowers(String key) throws InstantiationException, IllegalAccessException {
		if(key ==null)
			return ServiceTools.serviceRefused(Data.MESSAGE_MISSING_PARAMETERS, Data.CODE_MISSING_PARAMETERS);
		Connection co=null;
		try {
			co = Database.getMySQLConnection();

			int id_user = SessionTools.getIdFromKey(key, co);
			boolean b = SessionTools.isConnected(key);
			System.out.println(b);
			if(!b) {
				
				co.close();
				return ServiceTools.serviceRefused(Data.MESSAGE_USER_NOT_CONNECTED, Data.CODE_USER_NOT_CONNECTED);
			}
			return FriendTools.listAbonnement(id_user, co);
		}catch(SQLException s) {
			s.printStackTrace();
			return ServiceTools.serviceRefused(Data.MESSAGE_ERROR_SQL, Data.CODE_ERROR_SQL);

		}catch(JSONException e) {
			e.printStackTrace();
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
