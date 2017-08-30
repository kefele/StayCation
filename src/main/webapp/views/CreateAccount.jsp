
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Account</title>

</head>
<body>

<%--this form takes user input, to be sent to the addUser mapping in the HomeController, then Dbase--%>
<form action="addUser" method="post">

    Enter User ID. (User ID must be at least 3 to 12 characters long)
    <br>
    ID: <input name="userId" type="text" />
    <br /><br />

    First Name: <input name="fName" type="text" />

    <br />
    Last Name: <input name="lName" type="text" />

    <br />
    Email: <input name="email" type="text" />

    <br />
    Cell Phone: <input name="Cphone" type="text" />

    <br /><br>
    Password must be at least 8, but less than 60 characters long. Password must include one of each,<br>a capital letter, a lower case letter, a number, and one of these symbols (! @ # $ ?)<br>
    Password: <input name="password" type="password" />

    <br />
    <br />
    <input type="submit" value="Create Account" />
</form>

<a href="/"><button>Go Back To Home Page</button></a>

</body>
</html>
