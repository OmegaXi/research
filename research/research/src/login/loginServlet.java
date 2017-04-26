package login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import DBoprt.DBoprt;
/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			int result = 0;
			HttpSession session = request.getSession();
			String username = request.getParameter("username");
			System.out.print("username"+username);
			String password = request.getParameter("password");
			String logintype = request.getParameter("logintype");
			System.out.print("logintype="+logintype);
			String check = null;
			
			DBoprt dbo = new DBoprt();
			switch (logintype){
				case "unit":
				System.out.print("logintype="+logintype);
				check = "select * from UnitInfo where UNIT_USERNAME = '"+username+"' and UNIT_USERPASSWD = '"+password+"'";
				System.out.print("check information="+check);
				result = dbo.check(check);
				if(result == 111){
					String[] info = new String[7];
					info = dbo.getUnitInfo(check);
					session.setAttribute("unitid",info[0]);
					session.setAttribute("unitname",info[1]);
					session.setAttribute("unittype",info[2]);
					session.setAttribute("unitcontact",info[3]);
					session.setAttribute("unittellnumber",info[4]);
					session.setAttribute("unitemail",info[5]);
					session.setAttribute("unitusername",info[6]);
					session.setAttribute("type","unit");
					
					response.sendRedirect("filter_unitmanage.jsp");
				} else {
					response.sendRedirect("error.jsp");
				}
				break;
				case "admin":
				System.out.print("logintype="+logintype);
				check = "select * from AdminInfo where AD_USERNAME = '"+username+"' and AD_USERPASSWD = '"+password+"'";
				System.out.print("check information="+check);
				result = dbo.check(check);
				if(result == 111){
					String[] info = new String[3];
					info = dbo.getAdminInfo(check);
					session.setAttribute("adminid",info[0]);
					session.setAttribute("adminusername",info[1]);
					session.setAttribute("adminpasswd",info[2]);
					session.setAttribute("type","admin");
					response.sendRedirect("filter_manager.jsp");
					System.out.print("adminid ="+info[0]);
					System.out.print("adminusername ="+info[1]);
				} else {
					response.sendRedirect("error.jsp");
				}
				break;
				default:
				break;
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
