package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DBoprt.DBoprt;

public class edit extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public edit() {
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
			String proid = request.getParameter("pro_id");
			String protype = request.getParameter("pro_type");
			String proname = request.getParameter("pro_name");
			String proprop = request.getParameter("pro_prop");
			String protolexp = request.getParameter("pro_tolexp");
			String prostate = request.getParameter("pro_state");
			String prostartdate = request.getParameter("pro_startdate");
			String proenddate = request.getParameter("pro_enddate");
			String proremain = request.getParameter("pro_remain");
			

			String proinsert = "update ProInfo set PRO_NAME ='"+proname+"',PRO_PROP ='"+proprop+"',PRO_TYPE ='"+protype+"',PRO_TOLEXP ="+protolexp+",PRO_STATE ='"+prostate
					+"',PRO_STARTDATE = TO_DATE('"+prostartdate+"','yyyy-mm-dd HH24:MI:SS'),PRO_ENDDATE = TO_DATE('"+proenddate+"','yyyy-mm-dd HH24:MI:SS'),PRO_REMAIN = '"+proremain+"' where PRO_ID ="+proid;
			result = dbo.insert(proinsert);
			System.out.print(proinsert);
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

	
	public void init() throws ServletException {
		// Put your code here
	}

}
