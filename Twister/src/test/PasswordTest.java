package test;

import org.json.JSONObject;

import service.user.PasswordRecoveryS;

public class PasswordTest {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		JSONObject neel = null;
		try {
			neel = PasswordRecoveryS.passwordRecovery("bfafaslref@gmail.com");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(neel);
	}
}
