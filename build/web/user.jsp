<%-- 
    Document   : user
    Created on : Sep 22, 2023, 9:57:58 AM
    Author     : TINH
--%>

<%@page import="tinhdx.user.UserDTO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
        <link rel="stylesheet" href="./css/base.css">
        <link rel="stylesheet" href="./css/grid.css">
        <link rel="stylesheet" href="./css/searchStyle.css">        
    </head>
    <body>
    <c:set var="user" value="${sessionScope.LOGIN_USER}" />
    <c:choose>
        <c:when test="${user == null || 'US' ne user.roleID}">
            <c:redirect url="MainController?action=LOGIN" />
        </c:when>
    </c:choose>

    <div class="container grid">
        <div class="header row">
            <div class="welcome">
                Welcome, ${user.name}
            </div>
            <form action="MainController">
                <input type="submit" name="action" value="Logout" />
            </form>
        </div>

        <div class="header row">
            <h1>Your Profile</h1>
        </div>
        <div class="profile row">
            <div class="info">
                <div>
                    Username: ${user.userID}
                </div>
                <div>
                    Password: **
                </div>
                <div>
                    Full Name: ${user.name}
                </div>
                <div>
                    Role: ${user.roleID}
                </div>
            </div>
        </div>
    </div>

</body>
</html>
