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
 
    <hr>
    <form action="/Hospital/FriendInfo" name="login" method="get"  >
   <table>
   <tr>
   <td> sfzh:</td>
   <td><input type="text" name="sfzh" ></td>
   </tr>
  <tr>
   <td> ylkh:</td>
   <td><input type="text" name="ylkh" ></td>
   </tr>
   <tr>
   <td  >提交：</td><td><input type="submit" name="submit"></td>
   </tr>
   </table>
</form>
  </body>
</html>
