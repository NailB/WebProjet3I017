package test;

import org.json.JSONObject;

import service.twist.AddTwistS;

public class ListTwists {
	public static void main(String[ ] args) throws InstantiationException, IllegalAccessException {
	JSONObject obj = AddTwistS.listTwists("Sg0uWq4omDj3LoMvdrcG0nkgIg0O4sWE", "false", "1");

	System.out.println(obj);
	}
}