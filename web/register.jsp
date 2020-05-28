<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/5/10
  Time: 8:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <form action="/experiment/UserSerlvet" method="post">
        用户名:<input type="text" name="username" required><br>
        密 码:<input type="password" name="password" required><br>
        <input type="submit" name="submitType" value="注册">
    </form>
</body>
</html>
