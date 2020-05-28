<%@ page import="com.study.entity.ShoppingTrolley" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/5/26
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        request.setCharacterEncoding("UTF-8");
        ShoppingTrolley shoppingTrolley = (ShoppingTrolley) request.getSession().getAttribute("shoppingTrolley");
    %>
    <form action="/experiment/CUDShoppingTrolleyServlet" method="post">
        商品Id:<input name="itemId" value="<%=shoppingTrolley.getItem_id()%>" readonly><br>
        商品名称:<%=shoppingTrolley.getItem_name()%><br>
        商品单价:<%=shoppingTrolley.getItem_singlePrice()%><br>
        商品数量:<input type="number" min="1" max="100" name="itemNum"><br>
        <input type="submit" value="confirmUpdate" name="submitType">
    </form>
</body>
</html>
