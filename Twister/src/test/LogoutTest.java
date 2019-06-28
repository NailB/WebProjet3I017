package test;

import org.json.JSONObject;

import service.user.LogoutS;

public class LogoutTest {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		JSONObject neel = LogoutS.logout("fXN6Khf3NEYVGgJEx8LjC0vorfvCwop7");	
		JSONObject delpax = LogoutS.logout("r7uN3gPJLn1khrBRlNoVvBUMj32WHJiX");
		JSONObject chikow = LogoutS.logout("ylOxMwc48ZDshob25QHXCbYpEue9APvb");
		System.out.println(neel);
		System.out.println(delpax);
		System.out.println(chikow);
	}

}
