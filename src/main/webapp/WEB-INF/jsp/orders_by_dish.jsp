<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages.app"/>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<script type="text/javascript" src="resources/js/datatableUtil.js" defer></script>
<script type="text/javascript" src="resources/js/ordersByDishUtil.js" defer></script>


<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<%--orders datatable--%>
<div class="container">
    <div class="jumbotron">
        <div class="shadow">
            <h3><fmt:message key="orders.title"/></h3>
            <p>${restaurant.name}, ${restaurant.address}</p>
            <p>${menuList.description}</p>
            <p>${localDate}</p>
            <p>${dish.description}</p>
            <div class="view-box">
                <a class="btn btn-primary" type="button" onclick="addOrder()">
                    <span class="glyphicon glyphicon-plus-sign"></span>
                    <fmt:message key="orders.add"/>
                </a>
                <table class="table table-hover table-bordered " id="ordersDT">
                    <thead>
                    <tr>
                        <th><fmt:message key="common.dateTime"/></th>
                        <th><fmt:message key="users.name"/></th>
                        <th><fmt:message key="users.email"/></th>
                        <th><fmt:message key="orders.content"/></th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>