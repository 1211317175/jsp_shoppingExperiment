<%@ page import="com.study.entity.ShoppingTrolley" %>
<%@ page import="java.util.List" %>
<%@ page import="com.study.servlet.ItemServlet" %>
<%@ page import="com.study.dao.impl.ItemDAOImpl" %>
<%@ page import="com.study.entity.Item" %>
<%@ page import="com.study.dao.IItemDAO" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/5/26
  Time: 7:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购物车详情</title>
</head>
<body>
    <%
        String username = (String) request.getSession().getAttribute("username");
    %>
    <h2><%=username%>的购物车详情:</h2>
    <a href="/experiment/ItemServlet">返回商品页面</a>
    <table id="tab" border="1" cellspacing="0" cellpadding="0">
        <tr>
            <th>Id</th>
            <th>item_id</th>
            <th>item_name</th>
            <th>item_singlePrice</th>
            <th>item_num</th>
            <th>item_totalPrice</th>
            <th>操作1</th>
            <th>操作2</th>
        </tr>

    <%
        List<ShoppingTrolley> shoppingTrolleyList = (List<ShoppingTrolley>) request.getSession().getAttribute("shoppingTrolleyList");
        if (shoppingTrolleyList!=null){
            for (ShoppingTrolley shoppingTrolley : shoppingTrolleyList) {
        %>
                <tr>
                    <td><%=shoppingTrolley.getId()%></td>
                    <td><%=shoppingTrolley.getItem_id()%></td>
                    <td width="400"><%=shoppingTrolley.getItem_name()%></td>
                    <td><%=shoppingTrolley.getItem_singlePrice()%></td>
                    <td><%=shoppingTrolley.getItem_num()%></td>
                    <td><%=shoppingTrolley.getItem_singlePrice()*shoppingTrolley.getItem_num()%></td>
                    <form action="/experiment/CheckShoppingTrolleyServlet" method="post">
                        <td hidden><input type="text" name="itemId" value="<%=shoppingTrolley.getItem_id()%>" hidden></td>
                        <td><input type="submit" value="delete" name="submitType"></td>
                    </form>
                    <form  action="/experiment/CUDShoppingTrolleyServlet" method="post">
                        <td hidden><input type="text" name="itemId" value="<%=shoppingTrolley.getItem_id()%>" hidden></td>
                        <td><input type="submit" value="update" name="submitType"></td>
                    </form>
                </tr>
        <%
            }
        }
    %>
    </table>
    <form action="/experiment/OrderFormServlet" method="post">
        <input type="submit" value="结算">
    </form>

</body>
</html>
