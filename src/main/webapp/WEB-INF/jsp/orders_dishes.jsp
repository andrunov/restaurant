<%--shows content of order. Uses for update exist order
or as finish 4-th step of creation new order--%>
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
            <h3><fmt:message key="order.from"/>  ${localDate}</h3>
            <p>${currentUser.name}, ${currentUser.email}</p>
            <p>${restaurant.name}, ${restaurant.address}</p>
            <div class="view-box">
                <table class="table table-hover table-bordered " id="datatable">
                    <thead>
                    <tr>
                        <th><fmt:message key="dishes.description"/></th>
                        <th><fmt:message key="dishes.price"/></th>
                        <th class="col-xs-1"><span class="glyphicon glyphicon-plus"></span></th>
                        <th class="col-xs-1"><fmt:message key="common.quantity"/></th>
                        <th class="col-xs-1"><span class="glyphicon glyphicon-minus"></span></th>
                        <th><fmt:message key="common.delete"/></th>
                    </tr>
                    </thead>
                </table>
                <div class="span7 text-center">
                    <a class="btn btn-primary" type="button" onclick="complete()">
                        <span class="glyphicon glyphicon-ok"></span>
                        <fmt:message key="common.complete"/>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>



