<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
   

  </head>
  
  <body>
    <h1> 用户登录</h1>
    <hr>
    <form action="/Hospital/updatefriend" name="login" method="get"  >
   <table>
   <tr>
   <td> Openid：</td>
   <td><input type="text" name="openid" ></td>
   </tr>
   <tr>
   <td> 身份证号：</td>
   <td><input type="text" name="sfzh" ></td>
   </tr>
   <tr>
   <td> sfzhold：</td>
   <td><input type="text" name="sfzhold" ></td>
   </tr>
   <tr>
   <td> 医疗卡号：</td>
   <td><input type="text" name="ylkh" ></td>
   </tr>
   <tr>
   <td> 病人姓名：</td>
   <td><input type="text" name="brxm" ></td>
   </tr>
   <tr>
   <td> 病人性别：</td>
   <td><input type="text" name="brxb" ></td>
   </tr>
   <tr>
   <td> 病人家庭住址：</td>
   <td><input type="text" name="brjtzz" ></td>
   </tr>
   <tr>
   <td> 联系电话：</td>
   <td><input type="text" name="brdh" ></td>
   </tr>
   <tr>
   <td> 病人年龄：</td>
   <td><input type="text" name="nl" ></td>
   </tr>
   
   <tr>
   <td  >提交：</td><td><input type="submit" name="提交"></td>
   </tr>
   </table>
</form>
  </body>
</html>
