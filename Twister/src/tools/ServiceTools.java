package tools;

import org.json.JSONException;
import org.json.JSONObject;

public class ServiceTools {

	public static JSONObject serviceRefused(String message , int codeErreur) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("Message",message);
			obj.put("ErrorCode", codeErreur);
		} catch (JSONException j) {
			j.printStackTrace();
			return serviceRefused("JSONException", 100);
		} 
		return obj;
	}
	
	public static JSONObject serviceAccepted() {
		JSONObject obj = new JSONObject();
		try {
			obj.put("Output", new String("Service Accepted"));
		} catch (JSONException j) {
			j.printStackTrace();
			return serviceRefused("JSONException", 100);
		} 
		return obj;
	}



}
