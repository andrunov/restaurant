<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages.app"/>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<script type="text/javascript" src="resources/js/datatableUtil.js" defer></script>
<script type="text/javascript" src="resources/js/orders_dishesUtil.js" defer></script>

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="container">
    <div class="jumbotron">
        <div class="shadow">
            <h3><fmt:message key="dishes.title"/></h3>
            <p>${user.name}, ${user.email}</p>
            <p>${restaurant.name}, ${restaurant.address}</p>
            <p>${localDate}</p>
            <div class="view-box">
                <%--<a class="btn btn-primary" type="button" onclick="add()">--%>
                    <%--<span class="glyphicon glyphicon-plus-sign"></span>--%>
                    <%--<fmt:message key="dishes.add"/>--%>
                <%--</a>--%>
                <table class="table table-hover table-bordered " id="datatable">
                    <thead>
                    <tr>
                        <th><fmt:message key="dishes.description"/></th>
                        <th><fmt:message key="dishes.price"/></th>
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


