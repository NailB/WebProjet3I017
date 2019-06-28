package test;

import org.json.JSONObject;

import service.comments.AddCommentS;

public class ListComment {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		JSONObject obj = AddCommentS.listComments("LzhiNROlUcD6WAokBAyDjXzjtInCVeAN", "5c728510fbf6f506cb98d4f3");
		System.out.println(obj);
		

	}

}
