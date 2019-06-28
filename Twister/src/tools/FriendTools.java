/**
 * 
 */
package tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author LAOUER Walid
 *
 */
public class FriendTools {

	/**
	 * Verifie si id_friend est deja ami avec id_user
	 * @param id_user
	 * @param id_friend
	 * @return
	 * @throws SQLException 
	 */
	public static boolean alreadyFriend(int id_user, int id_friend, Connection co) throws SQLException {
		Statement st = null;
		ResultSet res = null;
		st = co.createStatement();
		String query = "SELECT * FROM follow WHERE user_id1= \""+id_user+"\"AND user_id2 = \""+id_friend+"\";";
		//System.out.println("ici passe par la requete");
		res = st.executeQuery(query);
		if (res.next()) {
			return true;
		}

		res.close();
		st.close();

		return false;
	}

	/**
	 * @param key
	 * @param id_friend
	 * @return
	 * @throws JSONException 
	 * @throws SQLException 
	 */
	public static JSONObject follow(String key, int id_friend, Connection co) throws JSONException, SQLException {
		int id_user = SessionTools.getIdFromKey(key, co);
		System.out.println(id_user);
		if( id_user==0)
			return ServiceTools.serviceRefused(Data.MESSAGE_USER_NOT_CONNECTED, Data.CODE_USER_NOT_CONNECTED);
		Statement st = null;
		st = co.createStatement();
		
		String query = "INSERT INTO follow (`follow_date`,`user_id1`, `user_id2`) VALUES( NOW(),'"+ id_user + "','" + id_friend + "')";
		st.executeUpdate(query);

		st.close();


		return ServiceTools.serviceAccepted().put(UserTools.getLogin(id_user)+": just followed "+UserTools.getLogin(id_friend), 001);
	}
	
	public static JSONObject listFollowers(int id_user, Connection co) throws SQLException, JSONException {
		Statement st = null;
		ResultSet res = null;
		st = co.createStatement();
		String query = "SELECT user_id1 FROM follow where user_id2 = '"+ id_user+"'";
		res = st.executeQuery(query);
//		System.out.println(res.next());
  		List<JSONObject> array = new ArrayList<JSONObject>();

		while(res.next()) {
			int follower = res.getInt("user_id1");
			JSONObject j = new JSONObject().put("user", UserTools.getLogin(follower));
			array.add(j);
		}
		JSONObject friends = new JSONObject().put("Followers", array); 
		return friends;
	}
	public static JSONObject listAbonnement(int id_user, Connection co) throws SQLException, JSONException {
		Statement st = null;
		ResultSet res = null;
		st = co.createStatement();
		String query = "SELECT user_id2 FROM follow where user_id1 = '"+ id_user+"'";
		res = st.executeQuery(query);
//		System.out.println(res.next());
  		List<JSONObject> array = new ArrayList<JSONObject>();

		while(res.next()) {
			int follower = res.getInt("user_id2");
			JSONObject j = new JSONObject().put("user", UserTools.getLogin(follower)).put("id", follower);
			array.add(j);
		}
		JSONObject friends = new JSONObject().put("Followers", array); 
		return friends;
	}


	public static JSONObject listFollowings(int id_user, Connection co) throws SQLException, JSONException {
		Statement st = null;
		ResultSet res = null;
		st = co.createStatement();
		String query = "SELECT user_id2 FROM follow where user_id1 = '"+ id_user+"'";
		res = st.executeQuery(query);
//		System.out.println(res.next());
  		List<JSONObject> array = new ArrayList<JSONObject>();
//		int follower = res.getInt("user_id1");
//		JSONObject j = new JSONObject().put(UserTools.getLogin(follower), follower);
//		array.add(j);
		while(res.next()) {
			int follower = res.getInt("user_id2");
			JSONObject j = new JSONObject().put(UserTools.getLogin(follower), follower);
			array.add(j);
		}
		JSONObject friends = new JSONObject().put("Followings", array); 
		return friends;
	}
	
	
	/**
	 * @param key
	 * @param id_friend
	 * @return
	 * @throws SQLException 
	 * @throws JSONException 
	 */
	public static JSONObject unfollow(int id_user, int id_friend, Connection co) throws SQLException, JSONException {
		Statement st = null;
		st = co.createStatement();
		String query = "DELETE FROM follow WHERE user_id1 = '"+id_user+"' AND user_id2 = '"+id_friend+"'";
		st.executeUpdate(query);
		st.close();
		return ServiceTools.serviceAccepted().put(UserTools.getLogin(id_user)+" has unfollowed "+UserTools.getLogin(id_friend), 1);
	}

}
