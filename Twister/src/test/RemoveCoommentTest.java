package test;

import org.json.JSONObject;

import service.comments.RemoveCommentS;

public class RemoveCoommentTest {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		JSONObject o = RemoveCommentS.DeleteComment("sYmqrumBJ5O4L2wjOz8ugyi9EuOR9iL3", "5c751d64d131316d05c87603", "5c751db0119c071c98e8a133");
		System.out.println(o);
	}
}
