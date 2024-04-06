<%-- 
    Document   : admin
    Created on : Sep 22, 2023, 9:56:57 AM
    Author     : TINH
--%>

<%@page import="java.util.List"%>
<%@page import="tinhdx.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <link rel="stylesheet" href="./css/base.css">
        <link rel="stylesheet" href="./css/grid.css">
        <link rel="stylesheet" href="./css/searchStyle.css">
    </head>
    <body>
        <c:if test ="${sessionScope.LOGIN_USER == null || sessionScope.LOGIN_USER.roleID ne 'AD'}">
            <c:redirect url="MainController?action=LOGIN"></c:redirect>
        </c:if>

        <div class="container grid">
            <div class="header row">
                <div class="welcome">
                    Welcome, ${sessionScope.LOGIN_USER.name}
                </div>
                <c:url var="Logout" value="MainController">
                    <c:param name="action" value="Logout"></c:param>
                </c:url></br>
                <a href="${Logout}" class="logout">Log out</a>
            </div>

            <div class="search row">
                <h1>Search Account</h1>
                <form action="MainController" >
                    <div class="search-content">
                        Search 
                    </div>
                    <input type="text" name="search" value="${param.search}" class="text-box search-box" />
                    <input type="submit" name="action" value="Search" class="btn"/>
                </form>
            </div>

            <c:if test="${requestScope.LIST_USER != null}">
                <c:if test="${not empty requestScope.LIST_USER}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>User ID</th>
                                <th>Name</th>
                                <th>Phone</th>
                                <th>Address</th>
                                <th>Role ID</th>
                                <th>Password</th>
                                <th>Update</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="user" varStatus="counter" items="${requestScope.LIST_USER}">
                            <form action="MainController" >
                                <tr>
                                    <td style="text-align: center" >${counter.count}</td>
                                    <td>${user.userID}</td>

                                    <td>
                                        <input type="text" name="name" value="${user.name}" required=""/>
                                    </td>
                                    <td>
                                        <input type="text" name="phone" value="${user.phone}" required=""/>
                                    </td>
                                    <td>
                                        <input type="text" name="address" value="${user.address}" required=""/>
                                    </td>
                                    <td>
                                        <input type="text" name="roleID" value="${user.roleID}" required=""/>
                                    </td>
                                    <td>${user.password}</td>
                                    <!--Update-->
                                    <td>
                                        <input type="submit" name="action" value="Update" class="btn"/>
                                        <input type="hidden" name="userID" value="${user.userID}" />
                                        <input type="hidden" name="search" value="${param.search}" />
                                    </td>

                                    <!--Delete-->
                                    <td>
                                        <a href="MainController?userID=${user.userID}&action=Delete&search=${param.search}" class="btn">Delete</a>
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                        </tbody>
                    </table>

                </div>

                <c:set var="error" value="${requestScope.ERROR}" />
                <c:if test="${empty error}">
                    <c:set var="error" value="" />
                </c:if>
                ${error}
                
            </c:if>
        </c:if>    
    </body>
</html>
