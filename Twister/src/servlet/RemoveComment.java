
package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.comments.RemoveCommentS;

/**
 * @author LAOUER Walid
 *
 */
public class RemoveComment extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String key = request.getParameter("id_user");
		String id_message =  request.getParameter("id_message");
		String id_comment =  request.getParameter("id_comment");

		response.setContentType("text/json");
		PrintWriter out = response.getWriter();

		try {
			out.println(RemoveCommentS.DeleteComment(key, id_message, id_comment));
		} catch (InstantiationException | IllegalAccessException e) {
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
