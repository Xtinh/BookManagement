<%-- 
    Document   : create
    Created on : Sep 29, 2023, 11:30:44 AM
    Author     : TINH
--%>

<%@page import="tinhdx.user.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
        <link rel="stylesheet" href="./css/base.css">
        <link rel="stylesheet" href="./css/grid.css">
        <link rel="stylesheet" href="./css/signUpStyle.css">
    </head>
    <body>
        <div class="container">
            <div class="grid">
                <div class="row">
                    <h1>Create New Account</h1>
                </div>
                
                <c:set var="userError" value="${requestScope.USER_ERROR}" />
                <c:if test="${userError eq null}">
                    <c:set var="userError" value="<%= new UserError()%>" />
                </c:if>

                <div class="main row">
                    <form action="MainController" method="POST">
                        <div class="item">
                            <div class="title">
                                User ID *
                                <span class="guide">
                                    {e.g. 2 - 10 chars}
                                </span>
                            </div>
                            <input type="text" name="userID" required="" class="text-box"/>
                            <c:out value="${userError.userIDError}" />
                        </div>

                        <div class="item">
                            <div class="title">
                                Full Name *
                                <span class="guide">
                                    {e.g. 5 - 20 chars}
                                </span> 
                            </div>
                            <input type="text" name="name" required="" class="text-box"/>
                            <c:out value="${userError.fullnameError}" />
                        </div>
                        <div class="item">
                            <div class="title">
                                Phone *
                                <span class="guide">
                                    {e.g. 10 numbers}
                                </span> 
                            </div>
                            <input type="text" name="phone" required="" class="text-box"/>
                            <c:out value="${userError.phoneError}" />
                        </div>

                        <div class="item">
                            <div class="title">
                                Address *
                                <span class="guide">
                                    {e.g. 10 - 50 chars}
                                </span> 
                            </div>
                            <input type="text" name="address" required="" class="text-box"/>
                            <c:out value="${userError.addressError}" />
                        </div>

                        <div class="item">
                            <div class="title">
                                Role ID
                            </div>
                            <input type="text" name="roleID" value="US" readonly="" class="text-box"/> </br>
                        </div>

                        <div class="item">
                            <div class="title">
                                Password *
                                <span class="guide">
                                    {e.g. 1 - 20 chars}
                                </span>
                            </div>
                            <input type="password" name="password" required="" class="text-box"/> </br>
                        </div>

                        <div class="item">
                            <div class="title">
                                Confirm *
                            </div>
                            <input type="password" name="confirm" required="" class="text-box"/>
                            <c:out value="${userError.confirmError}" />
                        </div>
                        <c:out value="${userError.error}" />

                        <div class="footer">
                            <div class="button">
                                <input type="submit" name="action" value="Create" class="btn" />
                                <input type="reset" value="Reset" class="btn" />
                            </div>
                            <div class="login">
                                <span class="text">Have already an account?</span>
                                <a href="login.html">Login Here!</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>

</html>
