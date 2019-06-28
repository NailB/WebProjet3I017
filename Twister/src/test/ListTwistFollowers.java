package test;

import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ListTwistFollowers {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, SQLException, JSONException {
		JSONArray json = new JSONArray();
		
		json = service.twist.AddTwistS.listFollowersTwist("neel", "3", "false");
		System.out.println(json);
	}

}
