<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
    HttpSession ss = request.getSession();
    String unitid = (String) ss.getAttribute("adminid");
    String adminname = (String) ss.getAttribute("adminusername");
    String type = "admin";
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
table {
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
.middletitle{
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>管理员管理界面</title>
</head>
<body class="body">
<jsp:useBean id="getinfo" class="Bean.getinfo" scope="page"></jsp:useBean>
    
    <div class="head">
    <h1>管理 </h1>
    </div>
    <div class="userlogout">
    <p class="welcome">欢迎您,<%=adminname%></p>
    <form class="logoutbutton" action = "login.manager" method="post" name="button">
    <input type="hidden" name="type" value="button" />
    <input type="submit" value="登出">
    </form>
    </div>
    
    <div class="middletitle">
    <p>可以编辑的项目(点击项目编号可以修改项目信息)</p>
    </div>
    
    <div class="biaoge">
    <form action = "login.manager" method="post" name="unitapplyform" class="form">
    <input type="hidden" name="type" value="deletepro" />
    <table class="table">
    <tr>
    <th>项目编号</th>
    <th>项目名称</th>
    <th>项目性质</th>
    <th>项目类型</th>
    <th>项目总费用</th>
    <th>项目状态</th>
    <th>开始日期</th>
    <th>结束日期</th>
    <th>剩余费用</th>
    </tr>
    <%
    String[] proinfo = new String[9];
    Vector<String[]> vec = new Vector<String[]>();
    vec = getinfo.getproinfo();
    for(int i = 0;i < vec.size();i ++){
    	proinfo = (String[]) vec.get(i); %>
    	<tr>
    	<td><input name="deleteproid" type="checkbox" value="<%=proinfo[0]%>" /><a href="itdoredit.jsp?type1=<%=proinfo[0]%>&usertype=<%=type%>"><%=proinfo[0]%></a></td>
    	<td><%=proinfo[1]%></td>
    	<td><%=proinfo[2]%></td>
    	<td><%=proinfo[3]%></td>
    	<td><%=proinfo[4]%>千元</td>
    	<td><%=proinfo[5]%></td>
    	<td><%=proinfo[6]%></td>
    	<td><%=proinfo[7]%></td>
    	<td><%=proinfo[8]%>千元</td>
    	</tr>
    	
    <% 
    }
    %>
    </table>
    <input type="submit" value="删除项目" <%if(vec.size()==0){%>disabled="disabled"<%}%>/>
    </form>
    </div>
    
    <div class="middletitle">
    <button onclick="window.location.href='addpro.jsp'">点击添加项目</button>
    </div>
    
    
    <div class="middletitle">
    <p>确定的合同(点击合同编号可以删除)</p>
    </div>
    
    <div class="biaoge">
    <form action = "login.manager" method="post" name="unitapplyform" class="form">
    <input type="hidden" name="type" value="deleteproman" />
    <table class="table">
    <tr>
    <th>项目编号</th>
    <th>单位编号</th>
    <th>合同编号</th>
    <th>总经费</th>
    </tr>
    <%
    String[] proman = new String[5];
    Vector<String[]> promanvec = new Vector<String[]>();
    promanvec = getinfo.getpromaninfo();
    for(int i = 0;i < promanvec.size();i ++){
    	proman = (String[]) promanvec.get(i); %>
    	<tr>
    	<td><input name="deletepromanid" type="checkbox" value="<%=proman[0]%>" /><%=proman[1]%></td>
    	<td><%=proman[2]%></td>
    	<td><%=proman[3]%></td>
    	<td><%=proman[4]%></td>
    	
    	</tr>
    	
    <% 
    }
    %>
    </table>
    <input type="submit" value="删除合同" <%if(vec.size()==0){%>disabled="disabled"<%}%>/>
    </form>
    </div>
    
    
    <div class="middletitle">
    <p>可以确认的申请(勾选经过审批的若干申请并填写合同编号和经费)</p>
    </div>
    
    <div class="biaoge">
    <form action = "login.manager" method="post" name="accapply" class="form">
    <input type="hidden" name="type" value="accapply" />
    <table class="talbe">
    <tr>
    <th>单位编号</th>
    <th>项目编号</th>
    <th>申请编号</th>
    </tr>
    <% 
    String[] applyinfo = new String[3];
    Vector<String[]> appvec = new Vector<String[]>();
    appvec = getinfo.getapplyinfo();
    for(int i = 0;i < appvec.size(); i++){
    	applyinfo = (String[]) appvec.get(i); 
    	System.out.println("unitmanage\n");
    	System.out.println("unitmanage i="+i);
    	System.out.println(applyinfo[0]+","+applyinfo[1]+","+applyinfo[2]);
    	
    	%>
    	<tr>
    	<td><input name="applyid" type="checkbox" value="<%=applyinfo[2]%>" /><%=applyinfo[0]%></td>
    	<td><%=applyinfo[1]%></td>
    	<td><%=applyinfo[2]%></td>
    	</tr>
    	
    <% 
    }
    %>
    </table>
    <label>合同编号:</label>
    <input name="connum" type="text" value="请填写数字" onfocus="this.value='';"/>
    <label>经费:</label>
    <input name="expense" type="text" value="请填写数字" onfocus="this.value='';"/>
    <label>千元</label>
    <input type="submit" value="接受申请" <%if(appvec.size()==0){%>disabled="disabled"<%}%>/>
    </form>
    </div>
    
    
    
    
    
    <div class="middletitle">
    <p>管理申报单位(点击单位编号可以修改单位信息)</p>
    </div>
    
    <div class="biaoge">
    <form action = "login.manager" method="post" name="uniteditform" class="form">
    <input type="hidden" name="type" value="deleteunitid" />
    <table class="table">
    <tr>
    <th>单位编号</th>
    <th>单位名称</th>
    <th>单位类型</th>
    <th>单位联系人</th>
    <th>单位电话</th>
    <th>单位电邮</th>
    <th>单位用户名</th>
    </tr>
    <%
    String[] unitinfo = new String[7];
    Vector<String[]> unitvec = new Vector<String[]>();
    unitvec = getinfo.getunitinfo();
    for(int i = 0;i < unitvec.size();i ++){
    	unitinfo = (String[]) unitvec.get(i); %>
    	<tr>
    	<td><input name="deleteunitid" type="checkbox" value="<%=unitinfo[0]%>" /><a href="unitedit.jsp?type1=<%=unitinfo[0]%>&usertype=<%=type%>"><%=unitinfo[0]%></a></td>
    	<td><%=unitinfo[1]%></td>
    	<td><%=unitinfo[2]%></td>
    	<td><%=unitinfo[3]%></td>
    	<td><%=unitinfo[4]%></td>
    	<td><%=unitinfo[5]%></td>
    	<td><%=unitinfo[6]%></td>
    	
    	
    	</tr>
    	
    <% 
    }
    %>
    </table>
    <input type="submit" value="删除单位信息" <%if(unitvec.size()==0){%>disabled="disabled"<%}%>/>
    </form>
    </div>
    
    <div class="foot">
    <p>国家科研项目部</p>
    </div>
    
</body>
</html>