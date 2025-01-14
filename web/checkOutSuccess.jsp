    <%-- 
    Document   : checkOutSuccess
    Created on : Oct 30, 2023, 5:47:52 PM
    Author     : TINH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Success</title>
        <link rel="stylesheet" href="./css/base.css">
        <link rel="stylesheet" href="./css/grid.css">
        <link rel="stylesheet" href="./css/checkOutSuccessStyle.css">
    </head>
    <body>
        <c:set var="order" value="${sessionScope.ORDER}" />
        <div class="container grid">
            <c:if test="${not empty order}">
                <div class="row">
                    <h1>Check Out Success</h1>
                </div>

                <div class="row">
                    <h2>Your Receipt</h2>
                </div>

                <div class="receipt row">
                    <c:set var="orderDetails" value="${sessionScope.LIST_ORDER_DETAILS}" />
                    <c:if test="${not empty orderDetails}">
                        <div class="receipt-info">
                            <div class="store-name">
                                PRJ301 Book Store
                            </div>

                            <div class="info">
                                Order ID: ${order.orderID} <br/>
                                Date: ${order.date} <br/>
                                Customer Name: ${order.name} <br/>
                                Customer Address: ${order.address} <br/>
                            </div>
                        </div>

                        <div class="result">
                            <table border="1">
                                <thead>
                                    <tr>
                                        <th>ProductID</th>
                                        <th>Name</th>
                                        <th>Quantity</th>
                                        <th>Price</th>
                                        <th>Money</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="dto" items="${orderDetails}">
                                        <tr>
                                            <td>
                                                ${dto.productID}
                                            </td>
                                            <td>
                                                ${dto.name}
                                            </td>
                                            <td style="text-align: center">
                                                ${dto.quantity}
                                            </td>
                                            <td>
                                                <fmt:formatNumber value="${dto.price}" 
                                                                  maxFractionDigits="0" />đ
                                            </td>
                                            <td>
                                                <fmt:formatNumber value="${dto.total}"
                                                                  maxFractionDigits="0"/>đ
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <tr>
                                        <td colspan="4" style="text-align: right">
                                            Total
                                        </td>
                                        <td>
                                            <fmt:formatNumber value="${order.total}"
                                                              maxFractionDigits="0"/>đ
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </c:if>
                </div>


                <div class="button row">
                    <a href="MainController?action=Shopping_Page">
                        <input type="submit" value="Go Shopping" class="btn" />
                    </a>
                    <a href="MainController?action=View Your Cart">
                        <input type="submit" value="View Cart" class="btn" />
                    </a>
                </div>
            </c:if>
            
            <c:if test="${empty order}">
                <div class="row">
                    <h1>No invoice created!</h1>
                </div>

                <div class="button row">
                    <a href="MainController?action=Shopping_Page">
                        <input type="submit" value="Go Shopping" class="btn" />
                    </a>
                    <a href="MainController?action=View Your Cart">
                        <input type="submit" value="View Cart" class="btn" />
                    </a>
                </div>
            </c:if>
        </div>
    </body>
</html>