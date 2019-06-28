package test;



import org.json.JSONObject;

import service.user.CreatUserS;



/**
 * @author LAOUER Walid
 *
 */
public class CreateUserTest {


	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

		System.out.println("test");
		JSONObject obj = CreatUserS.createUser("EFE","FEF", "FEFE", "FEF@gmail.com", "123");
		System.out.println(obj.toString());
		

		
	}


}
