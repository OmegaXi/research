package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DBoprt.DBoprt;

public class unitapply extends HttpServlet {
	DBoprt db = new DBoprt();
	
	/**
		 * Constructor of the object.
		 */
	public unitapply() {
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

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
		HttpSession session = request.getSession();
		String strunitid = (String) session.getAttribute("unitid");
		for( int i = 0; i < strunitid.length(); i ++){
			char c = strunitid.charAt(i);
			if(c == ' '){
				strunitid = strunitid.substring(0,i);
				
				break;
			}
		}
		int intunitid = Integer.parseInt(strunitid);
		System.out.println("sad");
		if(request.getParameter("type").equals("apply"))
			apply(request,response,intunitid);
		else if(request.getParameter("type").equals("cancelapply"))
			cancelapply(request,response);
		else if(request.getParameter("type").equals("button"))
			logout(request,response);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void apply(HttpServletRequest request, HttpServletResponse response,Integer intunitid) throws ServletException, IOException {
		String[] apply = request.getParameterValues("applyproid");
		int result = 0;
		for(String proid:apply){
			
			for( int i = 0; i < proid.length(); i ++){
				char c = proid.charAt(i);
				if(c == ' '){
					proid = proid.substring(0,i);
					
					break;
				}
			}
			int intproid = Integer.parseInt(proid);
			
			
			String applypro = "insert into Apply (UNIT_ID,PRO_ID) values ("+String.valueOf(intunitid)+","+proid+")";
			System.out.println(applypro);
			result = db.insert(applypro);
			System.out.println("apply result="+result+"\n");
			if(result == 777){
				result = 0;
				break;
			}
			
		}
		if(result == 0){
			response.sendRedirect("error.jsp");
		}
		else{
			System.out.println("result="+result);
			response.sendRedirect("filter_unitmanage.jsp");
		}
	}
	public void cancelapply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] cancelapplyid = request.getParameterValues("applyid");
		int result = 0;
		for(String applyid:cancelapplyid){
			
			for( int i = 0; i < applyid.length(); i ++){
				char c = applyid.charAt(i);
				if(c == ' '){
					applyid = applyid.substring(0,i);
					
					break;
				}
			}
			
			String cancelapply = "delete from Apply where APPLY_ID ="+applyid;
			result = db.insert(cancelapply);
			System.out.println("cancel result="+result+"\n");
			if(result == 777){
				result = 0;
				break;
			}
			
		}
		if(result == 0){
			response.sendRedirect("error.jsp");
		}
		else{
			System.out.println("result="+result);
			response.sendRedirect("filter_unitmanage.jsp");
		}
	}
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("index.jsp");
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
