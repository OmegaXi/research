package Bean;

import DBoprt.DBoprt;
import java.util.*;

public class getinfo {
	DBoprt db = new DBoprt();
	
	public Vector<String[]> getproinfoadmin(String proid){
		String sql = "select * from ProInfo where PRO_ID ="+proid;
		Vector<String[]> vec = new Vector<String[]>();
		vec = db.getProInfo(sql);
		return vec;
	}
	public Vector<String[]> getproinfo(String unitid){
		String sql = "select * from ProInfo where PRO_ID not in (select PRO_ID from Apply where UNIT_ID ="+unitid+" UNION select PRO_ID from ProMan where UNIT_ID="+unitid+") and PRO_STATE='applying'";
		Vector<String[]> vec = new Vector<String[]>();
		vec = db.getProInfo(sql);
		return vec;
	}
	public Vector<String[]> getproinfo(){
		String sql = "select * from ProInfo";
		Vector<String[]> vec = new Vector<String[]>();
		vec = db.getProInfo(sql);
		return vec;
	}
	public Vector<String[]> getapplyinfo(String unitid){
		String sql = "select * from Apply where UNIT_ID ="+unitid;
		Vector<String[]> vec = new Vector<String[]>();
		vec = db.getApplyInfo(sql);
		for( int i = 0;i < vec.size(); i ++){
			String[] applyinfo = new String[3];
			applyinfo = vec.get(i);
			System.out.println("getinfo:\n");
			System.out.println(applyinfo[0]+","+applyinfo[1]+","+applyinfo[2]);
		}
		return vec;
	}
	public Vector<String[]> getapplyinfo(){
		String sql = "select * from Apply";
		Vector<String[]> vec = new Vector<String[]>();
		vec = db.getApplyInfo(sql);
		for( int i = 0;i < vec.size(); i ++){
			String[] applyinfo = new String[3];
			applyinfo = vec.get(i);
			System.out.println("getinfo:\n");
			System.out.println(applyinfo[0]+","+applyinfo[1]+","+applyinfo[2]);
		}
		return vec;
	}
	public Vector<String[]> getunitinfo(){
		Vector<String[]> vec = new Vector<String[]>();
		vec = db.getUnitInfo();
		for( int i = 0;i < vec.size(); i ++){
			String[] unitinfo = new String[7];
			unitinfo = vec.get(i);
			System.out.print("unitinfo:\n");
			System.out.print(unitinfo[0]+","+unitinfo[1]+","+unitinfo[2]);
		}
		return vec;
	}
	public String[] getunitinfo(String unitid){
		String [] str = new String[8];
		String sql = "select * from UnitInfo where UNIT_ID ="+unitid;
		str = db.getUnitInfo(sql);
		return str;
	}
	public Vector<String[]> getpromaninfo(){
		String sql = "select * from ProMan";
		Vector<String[]> vec = new Vector<String[]>();
		vec = db.getProManInfo(sql);
		for( int i = 0;i < vec.size(); i ++){
			String[] promaninfo = new String[5];
			promaninfo = vec.get(i);
			System.out.print("promaninfo:\n");
			System.out.print(promaninfo[0]+","+promaninfo[1]+","+promaninfo[2]);
		}
		return vec;
	}
	public Vector<String[]> getpromaninfo(String unitid){
		Vector<String[]> vec = new Vector<String[]>();
		String sql = "select * from ProMan where UNIT_ID ="+unitid;
		vec = db.getProManInfo(sql);
		for( int i = 0;i < vec.size(); i ++){
			String[] promaninfo = new String[5];
			promaninfo = vec.get(i);
			System.out.print("promaninfo:\n");
			System.out.print(promaninfo[0]+","+promaninfo[1]+","+promaninfo[2]);
		}
		return vec;
		
	}
}
