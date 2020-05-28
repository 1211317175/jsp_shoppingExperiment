<%@ page import="java.util.List" %>
<%@ page import="com.study.entity.Item" %>
<%@ page import="com.study.entity.ShoppingTrolley" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/5/12
  Time: 19:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品页面</title>
    <script>
        function addItem() {
            var item = document.getElementById("tab");
            var rows = item.rows;

            //获取表格的所有td
            // for(var i = 0; i<rows.length; i++ ){
            //     for(var j = 0; j<rows[i].cells.length; j++ ){    // 遍历该行的 td
            //         alert("第"+(i+1)+"行，第"+(j+1)+"个td的值："+rows[i].cells[j].innerHTML+"。");           // 输出每个td的内容
            //     }
            // }

            // var clickedItemId = this.document.getElementById("item_id").innerHTML;
            document.onmouseup = function(e) { //点击获取表格中的值
                var a = e.target.innerHTML;
                // alert(a);
                var itemList = new Array();
                itemList.push(a);
                itemList.push(123);
                alert(itemList);
            }


            // for(var i = 0; i<rows.length; i++ ){
            //     if (rows[i].cells[0].innerHTML==clickedItemId){
            //         alert(rows[i].cells[0].innerHTML);
            //         break;
            //     }
            // }

            // alert(itemId+1);



            // sessionStorage.setItem("itemId",itemId);
        }
    </script>
</head>
<body onload="selectIndex()">

    <%
        Integer totalCount = (Integer) request.getAttribute("totalCount");
        Integer currentPage = (Integer) request.getAttribute("currentPage");
        Integer pageSize = (Integer) request.getAttribute("pageSize");
        Integer totalPage = totalCount%pageSize==0?totalCount/pageSize-1:totalCount/pageSize;
        String username = (String) request.getSession().getAttribute("username");
//        String itemId = request.getParameter("itemId");
//        if (itemId!=null){
//            session.setAttribute("itemId",itemId);
//            System.out.println(itemId);
//        }
    %>
    <h1>商品列表</h1>
    <h3>尊敬的<%=username%>,我们在这等您很久啦！！！</h3>
    <a href="/experiment/CheckShoppingTrolleyServlet">查看您的购物车</a>

    <table id="tab" border="1" cellspacing="0" cellpadding="0">
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Price</th>
            <th>Portrait</th>
            <th>单个添加至购物车</th>
            <th>多个添加至购物车</th>
        </tr>

        <%
            List<Item> items = (List<Item>) request.getAttribute("items");
            if (items!=null){
                for (Item item : items) {
        %>
            <tr>
                <td width="5"><%=item.getId()%></td>
                <td width="200"><%=item.getName()%></td>
                <td width="20"><%=item.getPrice()%></td>
                <td><img src="image/item/<%=item.getPortrait()%>" height="200" width="200"></td>
                <form action="/experiment/CUDShoppingTrolleyServlet" method="post" >
                    <td hidden><input type="text" name="itemId" value="<%=item.getId()%>" hidden></td>
                    <td><input type="submit" value="add" name="submitType"></td>
                </form>
                <form  action="/experiment/CUDShoppingTrolleyServlet" method="post">
                    <td hidden><input type="text" name="itemId" value="<%=item.getId()%>" hidden></td>
                    <td>
                        <input type="number" min="1" max="100" name="itemNum">
                        <input type="submit" value="addMore" name="submitType">
                    </td>
                </form>

            </tr>
        <%
                }
            }
        %>
    </table>


    <%
        if (currentPage==0){ //首页只显示 下一页 和 尾页
            %>
                <a href="ItemServlet?currentPage=<%=currentPage+1%>">下一页</a>
                <a href="ItemServlet?currentPage=<%=totalPage%>">尾页</a><br>
            <%
        }
        else if (currentPage==totalPage){ //尾页只显示 上一页 和 首页
            %>
                <a href="ItemServlet?currentPage=0">首页</a>
                <a href="ItemServlet?currentPage=<%=currentPage-1%>">上一页</a><br>
            <%
        }else {
            %>
                <a href="ItemServlet?currentPage=0">首页</a>
                <a href="ItemServlet?currentPage=<%=currentPage-1%>">上一页</a>
                <a href="ItemServlet?currentPage=<%=currentPage+1%>">下一页</a>
                <a href="ItemServlet?currentPage=<%=totalPage+1%>">尾页</a><br>
            <%
        }
    %>

    共<%=totalCount%>条记录   <%=currentPage+1%>/<%=totalPage+1%>

    <form action="/experiment/ItemServlet">
        选择大小:<select name="pageSize" id="pageSize" onchange="saveSelect()">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
            <option value="10">10</option>
        </select>
        <input type="submit" value="确定">
    </form>
    <script>
        function saveSelect(){
            var typeId=document.getElementById("pageSize");
            var typeIdText=typeId.options[typeId.selectedIndex].value;
            document.cookie="typeIdText="+typeIdText;
        }
        function selectIndex(){
            var typeIdText=0;
            var coosStr=document.cookie;
            var coos=coosStr.split("; ");
            for(var i=0;i<coos.length;i++){
                var coo=coos[i].split("=");
                if("typeIdText"==coo[0]){
                    typeIdText=coo[1];
                }
            }
            var typeId=document.getElementById("pageSize");
            if(typeIdText==0){
                typeId.selectedIndex=0;
            }else{
                var length=typeId.options.length;
                for(var i=0;i<length;i++){
                    if(typeId.options[i].value==typeIdText){
                        typeId.selectedIndex=i;
                        break;
                    }
                }
            }

        }
    </script>
</body>
</html>
