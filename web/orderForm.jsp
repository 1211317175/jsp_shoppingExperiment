<%@ page import="com.study.entity.OrderForm" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/5/27
  Time: 9:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单页面</title>
</head>
<body>
    <%
        String username = (String) request.getSession().getAttribute("username");
        String serial_number = (String) request.getSession().getAttribute("serial_number");
        Date create_time = (Date) request.getSession().getAttribute("create_time");
        List<OrderForm> orderFormList = (List<OrderForm>) request.getSession().getAttribute("orderFormList");
        if (orderFormList!=null){
            Double finalPrice = 0.0;
    %>
    <h2><%=username%>的订单详情:</h2>

    订单号:<%=serial_number%><br>
    创建时间:<%=create_time%><br>
    <table id="tab" border="1" cellspacing="0" cellpadding="0">
        <tr>
            <th>item_name</th>
            <th>item_singlePrice</th>
            <th>item_num</th>
            <th>item_totalPrice</th>
        </tr>
        <%
            for (OrderForm orderForm : orderFormList) {
        %>
        <tr>
<%--            <td><%=orderForm.getSerial_number()%></td>--%>
            <td width="400"><%=orderForm.getItem_name()%></td>
            <td><%=orderForm.getItem_singlePrice()%></td>
            <td><%=orderForm.getItem_num()%></td>
            <td><%=orderForm.getItem_totalPrice()%></td>
<%--            <td><%=orderForm.getCreate_time()%></td>--%>
        </tr>
        <%
            finalPrice+=orderForm.getItem_totalPrice();
        }
        %>
    </table>
    您一共需要支付￥<%=finalPrice%>
<%--    <form>--%>
<%--        <input type="submit" value="确认支付">--%>
<%--    </form>--%>
    <button>确认支付</button>
    <%
        }else {
    %>
            请您先将商品加购至购物车
    <%
        }
    %>
</body>
</html>
