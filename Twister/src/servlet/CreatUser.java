package servlet;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.user.CreatUserS;;

/**
 * @author LAOUER Walid
 *
 */
public class CreatUser extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nom = request.getParameter("nom");
		String prenom =  request.getParameter("prenom");
		String mail = request.getParameter("mail");
		String login =  request.getParameter("login");
		String mdp = request.getParameter("mdp");

		response.setContentType("text/json");
		PrintWriter out = response.getWriter();

		try {
			out.println(CreatUserS.createUser(nom, prenom, mail, login, mdp));
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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


