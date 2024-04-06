<%-- 
    Document   : login
    Created on : Sep 22, 2023, 9:57:19 AM
    Author     : TINH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Login Page</title>
    </head>
    <body>
        <h1>Login Page!</h1>
        <form action="MainController" method="POST" id="form">
            User<input type="text" name="userID" required=""/> <br>
            Password<input type="password" name="password" required=""/><br>
            <input type="submit" name="action" value="Login"/>
            <input type="reset" value="Reset"/>
            <div class="g-recaptcha" data-sitekey="6LcXAO4oAAAAAMpuVqecgSkHvn3OWGPZqwLH2Pib"></div>
            <div id = "error"></div>
        </form>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
        ${requestScope.ERROR}
        <a href="MainController?action=Create_Page">Don't have account? Sign Up!</a> 
        <a href="MainController?action=Shopping_Page">Click here to buy books</a>
                 <a href="MainController?action=Total">Total</a>

    </body>
</html>