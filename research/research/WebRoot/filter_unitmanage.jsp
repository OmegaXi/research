<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>   
    <% 
    HttpSession ss = request.getSession();
    String unitid = (String) ss.getAttribute("unitid");
    String unitname = (String) ss.getAttribute("unitname");
    String usertype = "unit";
    %>


<html>
<style type="text/css">
.body {
	margin:0;
	padding:0;
	background:url(images/banner.jpg);
	background-attachment:fixed;
	background-position:center;
	background-size:cover;
}
.head {
	width: 100%;
	height: 100px;
	padding: 0;
	line-height:100px;
	text-align:center;
}
h1 {
	font-family: 'Muli';
	color:#FFF;
	font-size:45px;
	font-weight:200;
}
.userlogout {
	width: 100%;
	height: 30px;
	padding:0;
	line-height:30px;
	position:relative;
}
.welcome {
	margin:0;
	position:absolute;
	right:150px;
}
.logoutbutton {
	margin:0;
	position:absolute;
	right:10px;
	margin-top:5px;
}
.biaoge {
	width: 80%;
	text-align:center;
	margin:auto;
	min-height:100px;
}
.table {
	width:100%;
	height:auto;
	border: 3px solid white;
	border-collapse:collapse;
	table-layout:fixed;

}
th,td{
	border: 3px solid white;
	text-align:center; 
	width:auto;
	height:20px;line-height:20px;
	overflow:hidden;
	 word-break:keep-all;
	text-overflow:ellipsis;
}
.apply{
	width:100%;
	height:30px;
	line-height:30px;
	text-align:center;
}
.cancelapply {
	width:100%;
	height:30px;
	line-height:30px;
	text-align:center;
}
.foot {
	width:100%;
	height:50px;
	line-height:30px;
	text-align:center;
}
</style>
  <head>
    <base href="<%=basePath%>">
    <title>单位管理界面</title>
  </head>
  
  <body class="body">
  <jsp:useBean id="getinfo" class="Bean.getinfo" scope="page"></jsp:useBean>
    
    <div class="head">
    <h1>单位项目申请</h1>
    </div>
    <div class="userlogout">
    <p class="welcome">欢迎您,<%=unitname%></p>
    <form class="logoutbutton" action = "login.unitapply" method="post" name="button">
    <input type="hidden" name="type" value="button" />
    <input type="submit" value="登出">
    </form>
    </div>
    
    
    
    <div class="apply">
    <p>可以申请的项目</p>
    </div>
    
    
    <div class="biaoge">
    <form action = "login.unitapply" method="post" name="unitapplyform" class="form">
    <input type="hidden" name="type" value="apply" />
    
    <%
    String[] proinfo = new String[9];
    Vector<String[]> vec = new Vector<String[]>();
    vec = getinfo.getproinfo(unitid);
    if(vec.size() == 0){
    %>
    <p>目前没有可供申请的项目</p>
    <%
    }
    else{ 
    %>
    <table class="table">
    <tr>
    <th>项目编号</th>
    <th>项目名称</th>
    <th>项目类型</th>
    <th>项目类型</th>
    <th>项目总预算</th>
    <th>项目状态</th>
    <th>项目开始日期</th>
    <th>项目结束日期</th>
    <th>项目剩余资金</th>
    </tr>
    <%
    for(int i = 0;i < vec.size();i ++){
    	proinfo = (String[]) vec.get(i); %>
    	<tr>
    	<td><input name="applyproid" type="checkbox" value="<%=proinfo[0]%>" /><a href="itdoredit.jsp?type1=<%=proinfo[0]%>&usertype=<%=usertype%>"><%=proinfo[0]%></a></td>
    	<td><%=proinfo[1]%></td>
    	<td><%=proinfo[2]%></td>
    	<td><%=proinfo[3]%></td>
    	<td><%=proinfo[4]%></td>
    	<td><%=proinfo[5]%></td>
    	<td><%=proinfo[6]%></td>
    	<td><%=proinfo[7]%></td>
    	<td><%=proinfo[8]%>千元</td>
    	</tr>
    	
    <% 
    }
    }
    %>
    </table>
    <input type="submit" value="申请" <%if(vec.size()==0){%>disabled="disabled"<%}%> />
    </form>
    </div>
    
    
    <div class="cancelapply">
    <p>已经申请的项目</p>
    </div>
    
    <div class="biaoge">
    <form action = "login.unitapply" method="post" name="unitapplyform" style="text-align:center">
    <input type="hidden" name="type" value="cancelapply" />
    
    <% 
    String[] applyinfo = new String[3];
    Vector<String[]> appvec = new Vector<String[]>();
    appvec = getinfo.getapplyinfo(unitid);
    if(appvec.size() == 0){%>
    
          <p>目前没有可供选择的项目</p>
    <%
    }else{
    %>
    <table class="table">
    <tr>
    <th>单位编号</th>
    <th>项目编号</th>
    <th>申请编号</th>
    </tr>
    <%
    for(int i = 0;i < appvec.size(); i++){
    	applyinfo = (String[]) appvec.get(i); 
    	System.out.println("unitmanage\n");
    	System.out.println("unitmanage i="+i);
    	System.out.println(applyinfo[0]+","+applyinfo[1]+","+applyinfo[2]);
    	
    	%>
    	<tr>
    	<td><input name="applyid" type="checkbox" value="<%=applyinfo[2]%>" /><%=applyinfo[0]%></td>
    	<td><a href="itdoredit.jsp?type1=<%=applyinfo[1]%>"><%=applyinfo[1]%></td>
    	<td><%=applyinfo[2]%></td>
    	</tr>
    	
    <% 
    }
    }
    %>
    </table>
    <input type="submit" value="取消申请" <%if(appvec.size()==0){%>disabled="disabled"<%}%>/>
    </form>
    </div>
    
    <div class="apply">
    <p>确立合同的项目</p>
    </div>
    
    <div class="biaoge">
    <form action = "login.unitapply" method="post" name="unitapplyform" style="text-align:center">
    <input type="hidden" name="type" value="cancelapply" />
    
    <% 
    String[] promaninfo = new String[5];
    Vector<String[]> promanvec = new Vector<String[]>();
    promanvec = getinfo.getpromaninfo(unitid);
    if(promanvec.size() == 0){%>
    
          <p>目前没有可供选择的项目</p>
    <%
    }else{
    %>
    <table class="table">
    <tr>
    
    <th>项目编号</th>
    <th>单位编号</th>
    <th>合同编号</th>
    <th>经费</th>
    </tr>
    <%
    for(int i = 0;i < promanvec.size(); i++){
    	promaninfo = (String[]) promanvec.get(i); 
    	System.out.println("proman\n");
    	System.out.println(promaninfo[0]+","+promaninfo[1]+","+promaninfo[2]);
    	
    	%>
    	<tr>
    	<td><%=promaninfo[1]%></td>
    	<td><%=promaninfo[2]%></td>
    	<td><%=promaninfo[3]%></td>
    	<td><%=promaninfo[4]%></td>
    	</tr>
    	
    <% 
    }
    }
    %>
    </table>
    
    </form>
    </div>
    
    
    
    <div class="foot">
    <p>国家科研项目部</p>
    </div>
   
   
   
   </body>
</html>
