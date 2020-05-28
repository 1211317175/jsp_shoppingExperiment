<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/5/10
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Welcome</title>
  </head>
  <body>
    <h1>欢迎来到本商城系统</h1>

    <%
      String userSession = (String) request.getSession().getAttribute("login");
      if (!"successful".equals(userSession)){
    %>
    <a href="register.jsp">注册</a><br>
    <a href="login.jsp">登录</a><br>
    <%
      }else {
    %>
    <a href="/experiment/ItemServlet">查看商品</a>
    <%
      }
    %>



  </body>
</html>
