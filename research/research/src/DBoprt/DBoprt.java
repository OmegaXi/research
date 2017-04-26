package DBoprt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.*;

public class DBoprt {

	
	
	public Connection getconn() throws SQLException,ClassNotFoundException{
		
		String driverName="oracle.jdbc.driver.OracleDriver"; 
		Class.forName(driverName);
	    String url="jdbc:oracle:thin:@localhost:1521:ORCL";   
	    String user="research";   
	    String password="123456";  
	   
	    Connection conn = null;
	    conn = DriverManager.getConnection(url, user, password);
	    return conn;
	}
	
	public int check (String sql){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		int result = 0;
		try{
			conn = getconn();
			pstmt = conn.prepareStatement(sql);
			set = pstmt.executeQuery();
			set.next();
			int rows = set.getRow();
			System.out.print("rows ="+rows);
	        if(rows != 0){
	        	result = 111;
	        }
	        else{
	        	result = 777;
	        }
		
		}catch (SQLException e) {  
	        e.printStackTrace();  
	    }catch (Exception e) {
	    	e.printStackTrace();
	    }finally{  
	        try{  
	            if(pstmt != null){  
	                pstmt.close();  
	            }
	            if(conn != null){  
	                conn.close();  
	            }
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        }     
	    }
		return result;
	}
	public int insert(String sql){
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		int judge = 0;
		try{
			conn = getconn();
			pstmt = conn.prepareStatement(sql);
			judge = pstmt.executeUpdate();
	        if(judge == 1){
	        	System.out.println("ok");
	        	result = 111;
	        }
	        else{
	        	System.out.println("erro");
	        	result = judge;
	        }
		
		}catch (SQLException e) {  
	        e.printStackTrace();  
	    }catch (Exception e) {
	    	e.printStackTrace();
	    }finally{  
	        try{  
	            if(pstmt != null){  
	                pstmt.close();  
	            }
	            if(conn != null){  
	                conn.close();  
	            }
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        }     
	    }
		return result;
	}
	public String[] getUnitInfo(String sql){
		String[] string = new String[8];
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = getconn();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
	        while(rs.next()){
	        	string[0] = rs.getString("UNIT_ID");
	        	string[1] = rs.getString("UNIT_NAME");
	        	string[2] = rs.getString("UNIT_TYPE");
	        	string[3] = rs.getString("UNIT_CONTACT");
	        	string[4] = rs.getString("UNIT_TELLNUMBER");
	        	string[5] = rs.getString("UNIT_EMAIL");
	        	string[6] = rs.getString("UNIT_USERNAME");
	        	string[7] = rs.getString("UNIT_USERPASSWD");
	        }
		
		}catch (SQLException e) {  
	        e.printStackTrace();  
	    }catch (Exception e) {
	    	e.printStackTrace();
	    }finally{  
	        try{
	        	if(rs != null){
	        		rs.close();
	        	}
	            if(pstmt != null){  
	                pstmt.close();  
	            }
	            if(conn != null){  
	                conn.close();  
	            }
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        }     
	    }
		return string;
	}
	public String[] getAdminInfo(String sql){
		String[] string = new String[3];
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = getconn();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
	        while(rs.next()){
	        	string[0] = rs.getString("AD_ID");
	        	string[1] = rs.getString("AD_USERNAME");
	        	string[2] = rs.getString("AD_USERPASSWD");
	        }
		
		}catch (SQLException e) {  
	        e.printStackTrace();  
	    }catch (Exception e) {
	    	e.printStackTrace();
	    }finally{  
	        try{
	        	if(rs != null){
	        		rs.close();
	        	}
	            if(pstmt != null){  
	                pstmt.close();  
	            }
	            if(conn != null){  
	                conn.close();  
	            }
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        }     
	    }
		return string;
	}
	public Vector<String[]> getProInfo(String sql){
		 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Vector<String[]> vector = new Vector<String[]>();
		try{
			conn = getconn();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
	        while(rs.next()){
	        	String[] str = new String[9];
	        	str[0] = rs.getString("PRO_ID");
	        	str[1] = rs.getString("PRO_NAME");
	        	str[2] = rs.getString("PRO_PROP");
	        	str[3] = rs.getString("PRO_TYPE");
	        	str[4] = rs.getString("PRO_TOLEXP");
	        	str[5] = rs.getString("PRO_STATE");
	        	str[6] = rs.getString("PRO_STARTDATE");
	        	str[7] = rs.getString("PRO_ENDDATE");
	        	str[8] = rs.getString("PRO_REMAIN");
	        	vector.add(str);
	        }
		
		}catch (SQLException e) {  
	        e.printStackTrace();  
	    }catch (Exception e) {
	    	e.printStackTrace();
	    }finally{  
	        try{
	        	if(rs != null){
	        		rs.close();
	        	}
	            if(pstmt != null){  
	                pstmt.close();  
	            }
	            if(conn != null){  
	                conn.close();  
	            }
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        }     
	    }
		return vector;
	}
	public Vector<String[]> getApplyInfo(String sql){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		Vector<String[]> vector = new Vector<String[]>();
		ResultSet rs = null;
		try{
			conn = getconn();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
	        while(rs.next()){
	        	String[] string = new String[3];
	        	string[0] = rs.getString("UNIT_ID");
	        	string[1] = rs.getString("PRO_ID");
	        	string[2] = rs.getString("APPLY_ID");
	        	vector.add(string);
	        	System.out.println(string[0]+","+string[1]+","+string[2]);
	        }
		
		}catch (SQLException e) {  
	        e.printStackTrace();  
	    }catch (Exception e) {
	    	e.printStackTrace();
	    }finally{  
	        try{
	        	if(rs != null){
	        		rs.close();
	        	}
	            if(pstmt != null){  
	                pstmt.close();  
	            }
	            if(conn != null){  
	                conn.close();  
	            }
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        }     
	    }
		return vector;
	}
public Vector<String[]> getUnitInfo(){
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		Vector<String[]> vector = new Vector<String[]>();
		ResultSet rs = null;
		String sql = "select * from UnitInfo";
		try{
			conn = getconn();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
	        while(rs.next()){
	        	String[] string = new String[7];
	        	string[0] = rs.getString("UNIT_ID");
	        	string[1] = rs.getString("UNIT_NAME");
	        	string[2] = rs.getString("UNIT_TYPE");
	        	string[3] = rs.getString("UNIT_CONTACT");
	        	string[4] = rs.getString("UNIT_TELLNUMBER");
	        	string[5] = rs.getString("UNIT_EMAIL");
	        	string[6] = rs.getString("UNIT_USERNAME");
	        	vector.add(string);
	        	System.out.println(string[0]+","+string[1]+","+string[2]);
	        }
		
		}catch (SQLException e) {  
	        e.printStackTrace();  
	    }catch (Exception e) {
	    	e.printStackTrace();
	    }finally{  
	        try{
	        	if(rs != null){
	        		rs.close();
	        	}
	            if(pstmt != null){  
	                pstmt.close();  
	            }
	            if(conn != null){  
	                conn.close();  
	            }
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        }     
	    }
		return vector;
	}
public Vector<String[]> getProManInfo(String sql){
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	Vector<String[]> vector = new Vector<String[]>();
	ResultSet rs = null;
	try{
		conn = getconn();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
        while(rs.next()){
        	String[] string = new String[5];
        	string[0] = rs.getString("PROMAN_ID");
        	string[1] = rs.getString("PRO_ID");
        	string[2] = rs.getString("UNIT_ID");
        	string[3] = rs.getString("CONNUM");
        	string[4] = rs.getString("EXPENSE");
        	vector.add(string);
        	System.out.println(string[0]+","+string[1]+","+string[2]);
        }
	
	}catch (SQLException e) {  
        e.printStackTrace();  
    }catch (Exception e) {
    	e.printStackTrace();
    }finally{  
        try{
        	if(rs != null){
        		rs.close();
        	}
            if(pstmt != null){  
                pstmt.close();  
            }
            if(conn != null){  
                conn.close();  
            }
        } catch (SQLException e) {  
            e.printStackTrace();  
        }     
    }
	return vector;
}
}
