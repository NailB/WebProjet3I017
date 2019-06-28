
package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.twist.RemoveTwistS;

/**
 * @author LAOUER Walid
 *
 */
public class RemoveTwist extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String key = request.getParameter("key");
		String id_message =  request.getParameter("id_message");

		response.setContentType("text/json");
		PrintWriter out = response.getWriter();

		try {
			out.println(RemoveTwistS.RemoveTwist(key, id_message));
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(
			HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		doGet(request, response);
	}
}
