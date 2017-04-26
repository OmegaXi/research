<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	HttpSession ss = request.getSession();
    String manage = (String) ss.getAttribute("adminid");
    String unitid = request.getParameter("type1");
    System.out.print("type1="+unitid);
    String[] unitinfo = new String[8];
    
%>
<jsp:useBean id="getinfo" class="Bean.getinfo" scope="page"></jsp:useBean>
<%unitinfo = getinfo.getunitinfo(unitid); %>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>单位信息修改</title>
    <style>input.al{padding:3px 15px 3px 3px;width:100%;border:1px solid #fff;text-align:left;outline:none;font-size:18px;margin-top:6%;font-weight: 300;font-family: 'Muli', sans-serif;border-radius: 4px;}</style>
<style>select.one{padding:3px 15px 3px 3px;width:100%;border:1px solid #fff;text-align:left;outline:none;font-size:18px;margin-top:6%;font-weight: 300;font-family: 'Muli', sans-serif;border-radius: 4px;}</style>
<style>span.fo{font-size:20px;color:white;}</style>
<style>input.reg:hover{background-color:#376D29;color:white;}</style>
<style>h1.st{color:white;text-align:center;font-size:45px;padding:12px 0;text-transform:uppercase;font-weight:200;}</style>
<style>input.reg{padding:10px;background-color:#4C9A39;width:78%;border:none;cursor:pointer;color:#fff;font-weight: 300;font-family: 'Nunito', sans-serif;font-size:20px;margin-top:7%;text-transform:uppercase;border-radius:4px;-webkit-transition: all 0.5s ease-in-out;-moz-transition: all 0.5s ease-in-out;-o-transition: all 0.5s ease-in-out;}</style>
<style>form.cl{width:35%;text-align:center;background-color:rgba(153,202,204,0.2);padding:50px 50px 50px;border-radius:100px;}</style>
<style>p.footer{margin-top:3%;padding:10px 0;text-align:center;font-size:15px;font-family: 'Muli', sans-serif;font-weight: 300;line-height:25px;}</style>
<style>body.ba{margin:0;padding:0;background:url(images/banner.jpg);background-attachment:fixed;background-position:center;background-size:cover;}</style>
<style>a.sty:hover{color:white;}</style>
<style>a.sty:link{color:blue;}</style>
<style>a.sty:visited{color:blue;}</style>
<style>a.sty:active{color:blue}</style>
<script type="text/javascript">
     function myfunction(){
     var unittype = "<%=unitinfo[2]%>";
     obj = document.getElementById("nima");
     for(i = 0;i < obj.length;i ++){
     	if(obj[i].value==unittype)
            obj[i].selected = true;
     	}
     }
    function checkandsub(){
	var username = document.form.unit_username.value.length;
	var password = document.form.unit_password.value.length;
	var tellnumber = document.form.unit_tellnumber.value.length;
	var email = document.form.unit_email.value;
	var check = /^([0-9A-Za-z\-_\.]+)@([0-9a-z]+\.[a-z]{2,3}(\.[a-z]{2})?)$/g;
	if(check.test(email)&&(username <= 30)&&(password <= 30)&&(tellnumber == 11)){
		form.action="login.unitedit";
		form.method="post";
		form.submit();
		}
	else{
		alert("输入有误");
		}
	}
</script>
  </head>
  
  <body class="ba" onload="myfunction()">
  <h1 align="center" class="st"><strong>单位信息修改</strong></h1>
	<center>
		<form class="cl" name="form">
		<input type="hidden" name="unit_id" value="<%=unitinfo[0] %>">
			<table align="center" style="padding-left: 5px; padding-right: 5px; padding-top: 5px; width: 364px; padding-bottom: 5px; word-spacing: normal">
				<tr>
					<td><span class="fo">单位名称</span></td>
					<td><input class="al" name="unit_name" value="<%=unitinfo[1]%>"></td>
				</tr>

				<tr>
					<td style="width: 160px; "><span class="fo">单位性质</span></td>
					<td><select class="one" name="unit_type" id="nima">
							<option value="college">高校</option>
							<option value="lab">研究所</option>
							<option value="company">企业</option>
							<option value="others">其它</option>
					</select></td>
				</tr>

				<tr>
					<td style="width: 103px; "><span class="fo">联系人姓名</span></td>
					<td><input class="al" name="unit_contact" type="text" value="<%=unitinfo[3]%>" ></td>
				</tr>

				<tr>
					<td><span class="fo">手机号码</span></td>
					<td><input class="al" name="unit_tellnumber" type="text" value="<%=unitinfo[4]%>"></td>
				</tr>

				<tr>
					<td style="width: 71px; "><span class="fo">电子邮箱</span></td>
					<td><input class="al" name="unit_email" type="text" value="<%=unitinfo[5]%>"></td>
				</tr>

				<tr>
					<td><span class="fo">用户名</span></td>
					<td><input class="al" name="unit_username" value="<%=unitinfo[6]%>" ></td>
				</tr>

				<tr>
					<td><span class="fo">密码</span></td>
					<td><input class="al" name="unit_password" type="text" value="<%=unitinfo[7]%>"></td>
				</tr>

				<tr align="center">
					<td colspan="2"><input type="button" class="reg" value="确认" onclick="checkandsub()">
						
				</tr>
			</table>
		</form>
	</center>
	<p align="center" class="footer">国家科研项目部</p>
</body>
</html>
