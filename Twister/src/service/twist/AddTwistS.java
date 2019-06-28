/**
 * 
 */
package service.twist;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.client.MongoCollection;

import org.json.JSONArray;
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
public class AddTwistS {
	public static JSONObject AddTwist(String key, String text) throws InstantiationException, IllegalAccessException{
		if(key == null || text==null ) {
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
			return MessageTools.postTwist(id_user, text , m);
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

	public static JSONObject listTwists(String key, String orderAsc, String nbTwist) throws InstantiationException, IllegalAccessException {
		if(key==null || nbTwist == null)
			return ServiceTools.serviceRefused(Data.MESSAGE_MISSING_PARAMETERS, Data.CODE_MISSING_PARAMETERS);
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
			//Si il pr�cise pas d'ordre, ordre croissant
			boolean ord;
			if (orderAsc == null)
				ord = false;
			else
				ord = Boolean.parseBoolean(orderAsc);
			int nb_twist = Integer.parseInt(nbTwist);

			JSONArray array = MessageTools.listTwist(UserTools.getLogin(id_user), nb_twist, ord, m);
			return ServiceTools.serviceAccepted().put("My_twists",array);
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
	public static JSONArray listFollowersTwist(String login, String nb_twist, String ord) throws SQLException, JSONException, InstantiationException, IllegalAccessException {
		JSONObject followings = null;
		int twist_nb= Integer.parseInt(nb_twist);
		boolean order = Boolean.parseBoolean(ord);
		JSONArray listFollowers = new JSONArray();

		MongoCollection<Document> m = Database.getMongoMessage();
		Connection co=null;
		
		try {
			co = Database.getMySQLConnection();
			int user_id = tools.UserTools.getIdUser(login, co);
			followings = tools.FriendTools.listFollowings(user_id, co);
			followings.put(login, tools.UserTools.getIdUser(login, co));
			JSONArray array= followings.getJSONArray("Followings");
			Iterator<String> k = followings.keys();
			JSONArray listTwist =null;
			//System.out.println(array);
			for(int i = 0 ; i < array.length() ; i++){
				Iterator<String> keys = array.getJSONObject(i).keys();
				String log = keys.next();
				listTwist = MessageTools.listTwist(log, twist_nb, order, m);
				if(listTwist.length()==0)
					continue;
				//System.out.println(listTwist);
				listFollowers.put(listTwist);
			}
			
		}catch(JSONException e) {
			return new JSONArray().put("ERROR JSON");
		}finally {
			if ((!DBStatic.is_pooling) && (co != null))
				try {
					co.close();
				} catch (SQLException e) {
					System.err.println("Error closing connexion : " + e.getMessage());

					e.printStackTrace();
				}
		}
		return listFollowers;
	}
}
