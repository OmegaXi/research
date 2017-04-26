package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DBoprt.DBoprt;

public class unitedit extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public unitedit() {
		super();
	}

	/**
		 * Destruction of the servlet. <br>
		 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
		 * The doGet method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to get.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	/**
		 * The doPost method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try{
			DBoprt dbo = new DBoprt();
			String adminusername = null;
			HttpServletRequest hrequest = (HttpServletRequest) request;
	        HttpSession session=hrequest.getSession();
	        adminusername = (String) session.getAttribute("adminusername");
	        System.out.println(adminusername);
	        String check = "select * from AdminInfo where AD_USERNAME = '"+adminusername+"'";
	        if(dbo.check(check) != 111){
	        	response.sendRedirect("error.jsp");
	        }else{
			int result = 0;
			String unitid = request.getParameter("unit_id");
			String unitname = request.getParameter("unit_name");
			String unittype = request.getParameter("unit_type");
			String unitcontact = request.getParameter("unit_contact");
			String unittellnumber = request.getParameter("unit_tellnumber");
			String unitemail = request.getParameter("unit_email");
			String unitusername = request.getParameter("unit_username");
			String unituserpasswd = request.getParameter("unit_password");
			

			String unitinsert = "update UnitInfo set UNIT_NAME ='"+unitname+"',UNIT_TYPE ='"+unittype+"',UNIT_CONTACT ='"+unitcontact+"',UNIT_TELLNUMBER ="+unittellnumber+",UNIT_EMAIL ='"+unitemail
					+"',UNIT_USERNAME = '"+unitusername+"',UNIT_USERPASSWD = '"+unituserpasswd+"' where UNIT_ID ="+unitid;
			result = dbo.insert(unitinsert);
			System.out.print(unitinsert);
			if(result == 111){
				response.sendRedirect("filter_manager.jsp");
			}else if(result == 0){
				response.sendRedirect("filter_manager.jsp");
			}
			else {
				response.sendRedirect("error.jsp");
			}
	        }
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
		 * Initialization of the servlet. <br>
		 *
		 * @throws ServletException if an error occurs
		 */
	public void init() throws ServletException {
		// Put your code here
	}

}
