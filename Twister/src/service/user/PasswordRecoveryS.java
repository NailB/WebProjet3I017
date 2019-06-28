package service.user;

import java.sql.Connection;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import db.Database;
import tools.Data;
import tools.ServiceTools;
import tools.UserTools;

public class PasswordRecoveryS {

	public static JSONObject passwordRecovery(String mail) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (mail == null || mail.isEmpty())
			return ServiceTools.serviceRefused(Data.MESSAGE_MISSING_PARAMETERS, Data.CODE_MISSING_PARAMETERS);

		Connection co=null;
		try {
			co = Database.getMySQLConnection();
			boolean b =UserTools.mailExists(mail, co);
			if(!b) {
				return ServiceTools.serviceRefused(Data.MESSAGE_MAIL_DOES_NOT_EXIST, Data.CODE_MAIL_DOES_NOT_EXIST);
			}
			return UserTools.sendPasswordRecovery(mail, co);
		}catch (JSONException | SQLException s) {
			s.printStackTrace();
			return ServiceTools.serviceRefused(Data.MESSAGE_ERROR_SQL, Data.CODE_ERROR_SQL);
		}finally {
			try {
				co.close();
			} catch (SQLException e) {
				System.err.println("Error closing connexion : " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
}