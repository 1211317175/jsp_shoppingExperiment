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
    <title>Login</title>
    <script>
        function checkPasswords() {
            let pwd = document.getElementById("password").value;
            let confirmPwd = document.getElementById("confirmPassword").value;
            if (pwd!=confirmPwd){
                alert("两次密码不一致，请重新输入！！")
            }
        }
    </script>
</head>
<body>
    <form action="/experiment/UserSerlvet" method="post">
        用户名&nbsp;:<input type="text" name="username" required><br>
        密&nbsp;&nbsp;码:<input type="password" id="password" name="password" required><br>
        确认密码:<input type="password" id="confirmPassword" name="confirmPassword" onblur="checkPasswords()" required><br>
        <input type="submit" name="submitType" value="登录">
    </form>
</body>
</html>
