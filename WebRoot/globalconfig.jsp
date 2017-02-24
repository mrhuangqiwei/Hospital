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
    <h1> 全局参数设置</h1>
    <hr>
    <form action="/Hospital/globalconfigservlet" name="config" method="get"  >
   <table>
   <tr>
   <td>ghfsbm:</td>
   <td><input type="text" name="ghfsbm" ></td>
   </tr>
 <tr>
   <td>ghyks:</td>
   <td><input type="text" name="ghyks" ></td>
   </tr>
    <tr>
   <td>ghybm:</td>
   <td><input type="text" name="ghybm" ></td>
   </tr>
    <tr>
   <td>ywckbm:</td>
   <td><input type="text" name="ywckbm" ></td>
   </tr>
    <tr>
   <td>ghybm:</td>
   <td><input type="text" name="ghybm" ></td>
   </tr>
   
    <tr>
   <td>jdbcUrl:</td>
   <td><input type="text" name="jdbcUrl" ></td>
   </tr> 
   <tr>
   <td>jdbcUSERNAME1:</td>
   <td><input type="text" name="jdbcUSERNAME1" ></td>
   </tr>
   <tr>
   <td>PASSWORD:</td>
   <td><input type="text" name="PASSWORD" ></td>
   </tr>
   <tr>
   <td>fbbm:</td>
   <td><input type="text" name="fbbm" ></td>
   </tr>
   <tr>
   <td>ghxq:</td>
   <td><input type="text" name="ghxq" ></td>
   </tr>
    <tr>
   <td>jscs:</td>
   <td><input type="text" name="jscs" ></td>
   </tr>
   <tr>
   <td  >提交</td><td><input type="submit" name="提交"></td>
   </tr>
   </table>
</form>
  </body>
</html>
