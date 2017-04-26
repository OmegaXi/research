package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import DBoprt.DBoprt;
import java.util.*;

public class manager extends HttpServlet {

	DBoprt db = new DBoprt();
	public manager() {
		super();
	}

	
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try{
			HttpSession session = request.getSession();
			if(request.getParameter("type").equals("deletepro"))
				deletepro(request,response);
			else if(request.getParameter("type").equals("accapply"))
				accapply(request,response);
			else if(request.getParameter("type").equals("button"))
				logout(request,response);
			else if(request.getParameter("type").equals("deleteunitid"))
				deleteunitid(request,response);
			else if(request.getParameter("type").equals("deleteproman"))
				deleteproman(request,response);
			}
		
		catch(Exception e) {
				e.printStackTrace();
			}
	}
	public void deletepro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] deleteproid = request.getParameterValues("deleteproid");
		int result = 0;
		for(String proid:deleteproid){
			System.out.println("deletid="+proid);
			String delete3 = "delete from ProInfo where PRO_ID ="+proid;
			result = db.insert(delete3);
			System.out.println("delete result="+result+"\n");
			if(result == 777){
				result = 0;
				break;
			}
			
		}
		if(result == 0){
			response.sendRedirect("error.jsp");
		}
		else{
			response.sendRedirect("filter_manager.jsp");
		}
	}
	public void accapply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] accapplyid = request.getParameterValues("applyid");
		int result = 0;
		int judge = 0;
		for(String applyid:accapplyid){
			String expense = request.getParameter("expense");  //得到expense的值
			System.out.println("expense="+expense+"\n");       
			String connum = request.getParameter("connum");    //得到connum的值
			System.out.println("cnnum="+connum+"\n");
			Vector<String[]> info = new Vector<String[]>();
			String sql = "select * from Apply where APPLY_ID ="+applyid;
			String middle[] = new String[3];
			info = db.getApplyInfo(sql);
			middle = (String[]) info.get(0);
			String unitid = middle[0];
			String proid = middle[1];
			String deletepro = "insert into ProMan (PRO_ID,UNIT_ID,CONNUM,EXPENSE)values("+proid+","+unitid+","+connum+","+expense+")";
			result = db.insert(deletepro);
			System.out.println("delete result="+result+"\n");
			if(result == 777){
				result = 0;
				break;
			}
			else{
				String deletesql = "delete from Apply where PRO_ID ="+proid;
				String update = "update ProInfo set PRO_STATE ='ongoing' where PRO_ID ="+proid;
				judge = db.insert(deletesql);
				judge = db.insert(update);
			}
			
		}
		if(result == 0||judge == 0){
			response.sendRedirect("error.jsp");
		}
		else{
			response.sendRedirect("filter_manager.jsp");
		}
	}
	public void deleteunitid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] deleteunitid = request.getParameterValues("deleteunitid");
		int result = 0;
		for(String unitid:deleteunitid){
			System.out.print("delete unitid = "+unitid);
			String delete3 = "delete from UnitInfo where UNIT_ID ="+unitid;
			result = db.insert(delete3);
			System.out.println("delete result="+result+"\n");
			if(result == 777){
				result = 0;
				break;
			}
			
		}
		if(result == 0){
			response.sendRedirect("error.jsp");
		}
		else{
			response.sendRedirect("filter_manager.jsp");
		}
			
		
	}
	public void deleteproman(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] deletepromanid = request.getParameterValues("deletepromanid");
		int result = 0;
		for(String promanid:deletepromanid){
			System.out.println("deletpromanid="+promanid);
			String delete1 = "delete from ProMan where PROMAN_ID ="+promanid;
			
			result = db.insert(delete1);
			
			System.out.println("delete result="+result+"\n");
			if(result == 777){
				result = 0;
				break;
			}
			
		}
		if(result == 0){
			response.sendRedirect("error.jsp");
		}
		else{
			response.sendRedirect("filter_manager.jsp");
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
