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
    <form action="/Hospital/yyghservlet" name="login" method="get"  >
   <table>
   <tr>
   <td> yyghrq：</td>
   <td><input type="text" name="yyghrq" ></td>
   </tr>
   <tr>
   <td>brxm：</td>
   <td><input type="text" name="brxm" ></td>
   </tr>
   <tr>
   <td> brxb：</td>
   <td><input type="text" name="brxb" ></td>
   </tr>
   <tr>
   <td> brnldw：</td>
   <td><input type="text" name="brnldw" ></td>
   </tr>
   <tr>
   <td> sfzh：</td>
   <td><input type="text" name="sfzh" ></td>
   </tr>
   <tr>
   <td> jtzz：</td>
   <td><input type="text" name="jtzz" ></td>
   </tr>
   <tr>
   <td> nl：</td>
   <td><input type="text" name="nl" ></td>
   </tr>
   <tr>
   <td> sj：</td>
   <td><input type="text" name="sj" ></td>
   </tr>
   <tr>
   <td> ysbm：</td>
   <td><input type="text" name="ysbm" ></td>
   </tr>
     <tr>
   <td> yyks：</td>
   <td><input type="text" name="yyks" ></td>
   </tr>
     <tr>
   <td> yydjrq：</td>
   <td><input type="text" name="yydjrq" ></td>
   </tr>
     <tr>
   <td> yyyxrq：</td>
   <td><input type="text" name="yyyxrq" ></td>
   </tr>
  <tr>
   <td> ylkh：</td>
   <td><input type="text" name="ylkh" ></td>
   </tr>
   <tr>
    <tr>
   <td> yyys：</td>
   <td><input type="text" name="yyys" ></td>
   </tr>
   <tr>
   <td  >提交：</td><td><input type="submit" name="提交"></td>
   </tr>
   </table>
</form>
  </body>
</html>
