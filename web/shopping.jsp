<%-- 
    Document   : shopping
    Created on : Oct 6, 2023, 10:22:19 AM
    Author     : TINH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
        <link rel="stylesheet" href="./css/base.css">
        <link rel="stylesheet" href="./css/grid.css"> 
        <link rel="stylesheet" href="./css/shoppingStyle">
        <link rel="stylesheet" href="./css/searchStyle.css">
    </head>
    <body>
        <div class="container grid">
            <div class="row">
                <h1>Welcome to PRJ301 Book Store</h1>
            </div>

            <div class="result row">

                <div class="search row">
                    <h4>Search Book</h4>
                    <form action="MainController" >
                        <div class="search-content">
                            Search 
                        </div>
                        <input type="text" name="search" value="${param.search}" class="text-box search-box" />
                        <input type="submit" name="action" value="SearchBook" class="btn"/>
                    </form>
                </div>
                <c:set var="result" value="${requestScope.BOOK_LIST}"/>
                <c:if test="${not empty result}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>ProductID</th>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Add to Cart</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dto" items="${result}" varStatus="counter">
                                <tr>
                                    <td style="text-align: center">
                                        ${counter.count}
                                    </td>
                                    <td>
                                        ${dto.productID}
                                    </td>
                                    <td>
                                        ${dto.name}
                                    </td>
                                    <td>
                                        <fmt:formatNumber value="${dto.price}" maxFractionDigits="0"/> đ
                                    </td>
                                    <td>

                                        <c:if test="${quantity ne 0}">
                                            <form action="MainController">
                                                <input type="submit" name="action" value="Add" class="btn"
                                                       <c:if test="${remainQuantity eq 0}">
                                                           style="visibility: hidden"
                                                       </c:if>
                                                       />

                                                <input type="hidden" name="cmbBook" value="${dto.productID}-${dto.name}-${dto.price}"/>
                                            </form>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
                <a href="MainController?action=View Your Cart" class="btn">View Your Cart</a>
            </div>
            <c:set var="message" value="${requestScope.MESSAGE}"/>
            <c:if test="${empty message}">
                <c:set var="message" value=""/>
            </c:if>
            <div class="row" style="font-weight: bolder; padding: 10px; font-size: 1.8rem;">
                ${message} 
            </div>

            <div class="row">
                <c:if test="${empty result}">
                    <h2>No book is in store!</h2>
                </c:if>
            </div>
        </div>
    </body>
</html>
