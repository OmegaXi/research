<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:useBean id="getinfo" class="Bean.getinfo" scope="page"></jsp:useBean>
<% 
	String chaxun = null;
	int usertype = 0;
    HttpSession ss = request.getSession();
    String type=request.getParameter("usertype");
    String unitid = null;
    String manage = null;
    if(type.equals("unit")){
    unitid = (String) ss.getAttribute("unitid");
    }
    else{
    manage = (String) ss.getAttribute("adminid");
    }
    System.out.print("unitid="+unitid);
    System.out.print("manage="+manage);
    if(unitid == null){
    	if(manage == null){
    	usertype = 1;
    	chaxun="查询";
    	}
    	else{
    	usertype = 3;
    	chaxun="修改";
    	}
    }
    else{
    	usertype = 2;
    	chaxun="查询";
    }
    String proid=request.getParameter("type1");
    System.out.print("type1="+proid);
    String[] proinfo = new String[9];
    Vector<String[]> vec = new Vector<String[]>();
    vec = getinfo.getproinfoadmin(proid);
    proinfo = vec.get(0);
%>
<html>
  <head>
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
    <base href="<%=basePath%>">
    <title>项目信息<%=chaxun%></title>
    <script type="text/javascript">
     function myfunction(){
     var proprop = "<%=proinfo[2]%>";
     obj = document.getElementById("nima");
     for(i = 0;i < obj.length;i ++){
     	if(obj[i].value==proprop)
            obj[i].selected = true;
     	}
     var protype = "<%=proinfo[3]%>";
     obj = document.getElementById("nima2");
     for(i = 0;i < obj.length;i ++){
     	if(obj[i].value==protype)
     		obj[i].selected = true;
     	}
     
     var prostate = "<%=proinfo[5]%>";
     obj = document.getElementById("nima3");
     for(i = 0;i <obj.length;i ++){
     	if(obj[i].value==prostate)
     		obj[i].selected = true;
     }
     }
     </script>
  </head>
  
  <body class="ba" onload="myfunction()">
  
  <h1 align="center" class="st"><strong>项目信息<%=chaxun%></strong></h1>
  <%vec = getinfo.getproinfoadmin(proid);
    proinfo = vec.get(0);
   %>
   <center>  
    <form class="cl" action = "login.edit" method="post" name="form" >
    <input type="hidden" name="pro_id" value="<%=proinfo[0]%>">
    <%
    switch (usertype){
    case 3://为管理员用户
    %>
    <table align="center" style="padding-left: 5px; padding-right: 5px; padding-top: 5px; width: 364px; padding-bottom: 5px; word-spacing: normal">
				<tr>
					<td><span class="fo">项目名称</span></td>
					<td><input class="al" name="pro_name" value="<%=proinfo[1]%>"></td>
				</tr>

				<tr>
					<td style="width: 160px; "><span class="fo">项目性质</span></td>
					<td><select class="one" name="pro_prop" id="nima">
							<option value="863">国家863</option>
							<option value="973">国家973</option>
							<option value="nature">国家自然科学基金</option>
							<option value="others">横向</option>
					</select></td>
				</tr>

				<tr>
					<td style="width: 103px; "><span class="fo">项目类型</span></td>
					<td><select class="one" name="pro_type" id="nima2">
							<option value="software">软件技术</option>
							<option value="design">规范设计</option>
							<option value="application">计算机应用</option>
					</select></td>
				</tr>

				<tr>
					<td><span class="fo">项目费用</span></td>
					<td><input class="al" name="pro_tolexp" type="text" value="<%=proinfo[4]%>"></td>
				</tr>

				<tr>
					<td style="width: 71px; "><span class="fo">项目状态</span></td>
					<td><select class="one" name="pro_state" id="nima3">
							<option value="applying">申请中</option>
							<option value="ongoing">进行中</option>
							<option value="ending">结束中</option>
					</select></td>
				</tr>

				<tr>
					<td><span class="fo">开始日期</span></td>
					<td><input class="al" name="pro_startdate" value="<%=proinfo[6]%>" ></td>
				</tr>

				<tr>
					<td><span class="fo">结题日期</span></td>
					<td><input class="al" name="pro_enddate" type="text" value="<%=proinfo[7]%>"></td>
				</tr>
				<tr>
					<td><span class="fo">费用余额</span></td>
					<td><input class="al" name="pro_remain" type="text" value="<%=proinfo[8]%>"></td>
				</tr>

				<tr align="center">
					<td colspan="2"><input type="submit" class="reg" value="确认" >
						
				</tr>
			
    	
    	</table>
    	</form>
    <% 
    
    break;
    case 2://2为单位用户
    
    %>
    <table align="center" style="padding-left: 5px; padding-right: 5px; padding-top: 5px; width: 364px; padding-bottom: 5px; word-spacing: normal">
				<tr>
					<td><span class="fo">项目名称</span></td>
					<td><input class="al" name="unit_name" value="<%=proinfo[1]%>"></td>
				</tr>

				<tr>
					<td style="width: 160px; "><span class="fo">项目性质</span></td>
					<td><select class="one" name="pro_prop" id="nima">
							<option value="863">国家863</option>
							<option value="973">国家973</option>
							<option value="nature">国家自然科学基金</option>
							<option value="others">横向</option>
					</select></td>
				</tr>

				<tr>
					<td style="width: 103px; "><span class="fo">项目类型</span></td>
					<td><select class="one" name="pro_type" id="nima2">
							<option value="software">软件技术</option>
							<option value="design">规范设计</option>
							<option value="application">计算机应用</option>
					</select></td>
				</tr>

				<tr>
					<td><span class="fo">项目费用</span></td>
					<td><input class="al" name="pro_tolexp" type="text" value="<%=proinfo[4]%>"></td>
				</tr>

				<tr>
					<td style="width: 71px; "><span class="fo">项目状态</span></td>
					<td><select class="one" name="pro_type" id="nima3">
							<option value="applying">申请中</option>
							<option value="ongoing">进行中</option>
							<option value="ending">结束中</option>
					</select></td>
				</tr>

				<tr>
					<td><span class="fo">开始日期</span></td>
					<td><input class="al" name="pro_startdate" value="<%=proinfo[6]%>" ></td>
				</tr>

				<tr>
					<td><span class="fo">结题日期</span></td>
					<td><input class="al" name="pro_enddate" type="text" value="<%=proinfo[7]%>"></td>
				</tr>
				<tr>
					<td><span class="fo">费用余额</span></td>
					<td><input class="al" name="pro_remain" type="text" value="<%=proinfo[8]%>"></td>
				</tr>

				<tr align="center">
					<td colspan="2"><input type="button" class="reg" value="返回" onclick="javascript:history.back(-1);">
						
				</tr>
			</table>
	<% 
    break;
    case 1://为普通用户
    %>
    <table align="center" style="padding-left: 5px; padding-right: 5px; padding-top: 5px; width: 364px; padding-bottom: 5px; word-spacing: normal">
				<tr>
					<td><span class="fo">项目名称</span></td>
					<td><input class="al" name="unit_name" value="<%=proinfo[1]%>"></td>
				</tr>

				<tr>
					<td style="width: 160px; "><span class="fo">项目性质</span></td>
					<td><select class="one" name="pro_prop" id="nima">
							<option value="863">国家863</option>
							<option value="973">国家973</option>
							<option value="nature">国家自然科学基金</option>
							<option value="others">横向</option>
					</select></td>
				</tr>

				<tr>
					<td style="width: 103px; "><span class="fo">项目类型</span></td>
					<td><select class="one" name="pro_type" id="nima2">
							<option value="software">软件技术</option>
							<option value="design">规范设计</option>
							<option value="application">计算机应用</option>
					</select></td>
				</tr>

				<tr>
					<td><span class="fo">项目费用</span></td>
					<td><input class="al" name="pro_tolexp" type="text" value="<%=proinfo[4]%>"></td>
				</tr>

				<tr>
					<td style="width: 71px; "><span class="fo">项目状态</span></td>
					<td><select class="one" name="pro_type" id="nima3">
							<option value="applying">申请中</option>
							<option value="ongoing">进行中</option>
							<option value="ending">结束中</option>
					</select></td>
				</tr>

				<tr>
					<td><span class="fo">开始日期</span></td>
					<td><input class="al" name="pro_startdate" value="<%=proinfo[6]%>" ></td>
				</tr>

				<tr>
					<td><span class="fo">结题日期</span></td>
					<td><input class="al" name="pro_enddate" type="text" value="<%=proinfo[7]%>"></td>
				</tr>
				<tr>
					<td><span class="fo">费用余额</span></td>
					<td><input class="al" name="pro_remain" type="text" value="<%=proinfo[8]%>"></td>
				</tr>

				<tr align="center">
					<td colspan="2"><input type="button" class="reg" value="返回" onclick="javascript:history.back(-1);">
						
				</tr>
			</table>
    
    
    
    <% 
    break;
    default:
    break;
    }
    %>
    
    </form>
    </center>
  </body>
</html>
