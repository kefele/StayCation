
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Form</title>
</head>
<body>
<form action="/checkAdminlogin" method="post">
    ID: <input name="userId" type="text" /><br />
    Password: <input name="password" type="password" /><br />
    <input type="submit" value="Login as Admin" />
</form>
</body>
</html>