package Filter;

import java.io.IOException;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class loginFilter implements Filter {
	 
    public void destroy() {
           //TODO
    }
 
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
         
        HttpServletRequest hrequest = (HttpServletRequest) request;
        HttpSession session=hrequest.getSession();
         
        //�����ж�session���Ƿ����û���Ϣ�������ж��û���
        if(session == null){
        	hrequest.getRequestDispatcher("index.jsp").forward(request, response);
        }
//        else if (session.getAttribute("unitid") == null) {
//            hrequest.getRequestDispatcher("index.jsp").forward(request, response);
// 
//        } else if(session.getAttribute("adminid") == null){
//        	hrequest.getRequestDispatcher("index.jsp").forward(request, response);
//        }
        else {
            // �����¼�ˣ���������ִ�� */
            chain.doFilter(request, response);
        }
    }
 
    public void init(FilterConfig arg0) throws ServletException {
 
    }

	
}