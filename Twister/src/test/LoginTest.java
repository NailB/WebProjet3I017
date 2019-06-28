package test;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import service.user.LoginS;
import tools.SessionTools;

public class LoginTest {
	public static void main(String args[]) throws SQLException, JSONException, InstantiationException, IllegalAccessException, ClassNotFoundException {

		JSONObject neel = LoginS.login("neel", "123");	
		JSONObject delpax11 = LoginS.login("Delpaxll", "1234");
		JSONObject chikow = LoginS.login("chikow", "mdp");
		System.out.println(neel);
		System.out.println(delpax11);
		System.out.println(chikow);

		//System.out.println("L'utilisateur est-il toujours connecté ? "+SessionTools.isConnected("236Y3kSj2apuTFYgDKkLY1Ch5ao3Hicg"));
	}
}
