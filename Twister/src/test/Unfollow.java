package test;

import org.json.JSONObject;

import service.follows.RemoveFriendS;

public class Unfollow {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub

		JSONObject o = RemoveFriendS.Unfollow("xnM1N35kkpus65CdlWbK4BNYcSQa49aA", 17);
		System.out.println(o);

	}
}