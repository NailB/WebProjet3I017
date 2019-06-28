package test;

import org.json.JSONObject;

import service.follows.AddFriendS;

public class ListFollowers {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		JSONObject o = AddFriendS.listFollowers("lmqAcKPgiyijQJtXRmYOXjXU5NMJsa8J");
		System.out.println(o);
		
	}
}

