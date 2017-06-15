<%--home page for users without ROLE_ADMIN--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:setBundle basename="messages.app"/>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="container">
    <div class="jumbotron">
        <p><strong><a href="#">Профиль</a></strong></p>
        <div class="shadow">
            <p><fmt:message key="common.welcome"/>, ${user.name}!</p>
            <p><fmt:message key="orders.title"/></p>
            <div class="view-box">
                <a class="btn btn-primary" type="button" onclick="addOrder()">
                    <span class="glyphicon glyphicon-plus-sign"></span>
                    <fmt:message key="orders.add"/>
                </a>
                <table class="table table-hover table-bordered " id="ordersDT">
                    <thead>
                    <tr>
                        <th></th>
                        <th><fmt:message key="common.dateTime"/></th>
                        <th><fmt:message key="restaurants.nameAndAddress"/></th>
                        <th><fmt:message key="orders.content"/></th>
                        <th><fmt:message key="common.update"/></th>
                        <th><fmt:message key="common.delete"/></th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
