package login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBoprt.DBoprt;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
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
			String username = request.getParameter("unit_username");
			String password = request.getParameter("unit_password");
			
			String unittype = request.getParameter("unit_type");
			String unitname = request.getParameter("unit_name");
			String unitcontact = request.getParameter("unit_contact");
			String unittellnumber = request.getParameter("unit_tellnumber");
			String unitemail = request.getParameter("unit_email");
			String unitid = unittellnumber.substring(7,11);
			System.out.print("unitid="+unitid);
			
			DBoprt dbo = new DBoprt();

			String unitinsert = "insert into UnitInfo (UNIT_NAME,UNIT_TYPE,UNIT_CONTACT,UNIT_TELLNUMBER,UNIT_EMAIL,UNIT_USERNAME,UNIT_USERPASSWD) values ('"+unitname+"','"+unittype+"','"+unitcontact+"',"+unittellnumber+",'"+unitemail+"','"+username+"','"+password+"')";
			result = dbo.insert(unitinsert);
			if(result == 111){
				response.sendRedirect("index.jsp");
			} else {
				response.sendRedirect("error.jsp");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
