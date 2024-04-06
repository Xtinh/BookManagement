<%-- 
    Document   : viewCart
    Created on : Oct 6, 2023, 11:27:43 AM
    Author     : TINH
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
        <link rel="stylesheet" href="./css/base.css">
        <link rel="stylesheet" href="./css/grid.css">
        <link rel="stylesheet" href="./css/viewCartStyle.css">
    </head>
    <body>
        <div class="container grid">
            <c:set var="cart" value="${sessionScope.CART}" />

            <!-- NOT EMPTY CART -->
            <c:if test="${not empty cart}">
                <div class="row">
                    <h1>Your Cart</h1>
                </div>


                <c:set var="cart" value="${cart.cart}" />
                <!-- NOT EMPTY ITEMS -->
                <c:if test="${not empty cart}">
                    <div class="result row">
                        <form action="MainController">
                            <table border="1">
                                <thead>
                                    <tr>
                                        <th>No.</th>
                                        <th>ProductID</th>
                                        <th>Name</th>
                                        <th>Quantity</th>
                                        <th>Price</th>
                                        <th>Remove</th>
                                        <th>Check Out</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="item" items="${cart}" varStatus="counter">
                                        <c:set var="dto" value="${item.value}" />
                                        <c:set var="quantity" value="${dto.quantity}" />
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
                                            <td style="text-align: center">
                                                ${quantity}
                                            </td>
                                            <td>
                                                <fmt:formatNumber value="${dto.price}" 
                                                                  maxFractionDigits="0" />đ
                                            </td>
                                            <td style="text-align: center">
                                                <input type="checkbox" name="chkItem" 
                                                       value="${dto.productID}" />
                                            </td>
                                            <td style="text-align: center">
                                                <input type="checkbox" name="chkCheckOut" 
                                                       value="${dto.productID}" />
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <tr>
                                        <td colspan="5">
                                            <a href="MainController?action=Shopping_Page">
                                                Add More Books to Your Cart
                                            </a>
                                        </td>
                                        <td>
                                            <input type="submit" class="btn"
                                                   name="action"
                                                   value="Remove Selected Books" 
                                                   />
                                        </td>
                                        <td>
                                            <input type="submit" class="btn"
                                                   name="action"
                                                   value="Check Out Selected Books"
                                                   />
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </form>
                    </div>
                </c:if>

                
            </c:if>

            <!-- EMPTY CART -->
            <c:if test="${empty cart}">
                <div class="no-cart row">
                    <h1>No cart is existed!</h1>
                    <a href="MainController?action=Shopping_Page">Click Here To Go Shopping</a>
                </div>
            </c:if>
        </div>
    </body>
</html>
