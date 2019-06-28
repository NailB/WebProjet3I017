package test;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import service.comments.AddCommentS;

public class AddComment {

	public static void main(String[] args) throws SQLException, JSONException, InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub

		
	JSONObject o = AddCommentS.postComment("Sg0uWq4omDj3LoMvdrcG0nkgIg0O4sWE", "5c7539a801d8a61f12cd724c", "still here !");
	System.out.println(o);

		
		
	}

}
