/**
 * 
 */
package test;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import service.twist.RemoveTwistS;

/**
 * @author LAOUER Walid
 *
 */
public class RemoveTwistTest {

	/**
	 * @param args
	 * @throws JSONException 
	 * @throws SQLException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void main(String[] args) throws SQLException, JSONException, InstantiationException, IllegalAccessException {

		JSONObject obj = RemoveTwistS.RemoveTwist("h64kGbLCayfMG9ttsPSVWNzCP6cAtFpn", "5caa34726b1ec349516832b9");
		System.out.println( obj);
	}

}
