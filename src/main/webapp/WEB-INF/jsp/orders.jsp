<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages.app"/>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<script type="text/javascript" src="resources/js/datatableUtil.js" defer></script>
<script type="text/javascript" src="resources/js/ordersUtil.js" defer></script>


<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<%--orders datatable--%>
<div class="container">
    <div class="jumbotron">
        <div class="shadow">
            <h3><fmt:message key="orders.title"/></h3>
            <p>${user.name}, ${user.email}</p>
            <div class="view-box">
                <a class="btn btn-primary" type="button" onclick="addOrder()">
                    <span class="glyphicon glyphicon-plus-sign"></span>
                    <fmt:message key="orders.add"/>
                </a>
                <table class="table table-hover table-bordered " id="ordersDT">
                    <thead>
                    <tr>
                        <th><fmt:message key="common.dateTime"/></th>
                        <th><fmt:message key="restaurants.nameAndAddress"/></th>
                        <th><fmt:message key="orders.content"/></th>
                        <%--<th><fmt:message key="app.changeTateTime"/></th>--%>
                        <th><fmt:message key="common.delete"/></th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>

<%-- restaurant modal window--%>
<div class="modal fade" id="selectRestaurant">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title"> <fmt:message key="restaurants.select"/></h2>
            </div>

            <div class="modal-body">

                <table class="table table-hover table-bordered " id="restaurantDT">
                    <thead>
                    <tr>
                        <th><fmt:message key="restaurants.name"/></th>
                        <th><fmt:message key="restaurants.address"/></th>
                        <th><fmt:message key="common.select"/></th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>

<%--menuList modal window--%>
<div class="modal fade" id="selectMenuList">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title"><fmt:message key="menuLists.select"/></h2>
                <h3 class="modal-title" id="modalTitleRestaurant"></h3>

            </div>

            <div class="modal-body">

                <table class="table table-hover table-bordered " id="menuListDT">
                    <thead>
                    <tr>
                        <th><fmt:message key="menuLists.description"/></th>
                        <th><fmt:message key="common.dateTime"/></th>
                        <th><fmt:message key="common.select"/></th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>

<%--dishes modal window--%>
<div class="modal fade" id="selectDishes">
    <div class="container">

    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title"><fmt:message key="dishes.select"/></h2>
                <h3 class="modal-title" id="modalTitleRestaurant2"></h3>
                <h3 class="modal-title" id="modalTitleMenuList"></h3>

            </div>

            <div class="modal-body">

                <table class="table table-hover table-bordered " id="dishDT">
                    <thead>
                    <tr>
                        <th><fmt:message key="dishes.description"/></th>
                        <th><fmt:message key="dishes.price"/></th>
                        <th><fmt:message key="app.select"/></th>
                    </tr>
                    </thead>
                </table>
                <div class="span7 text-center">
                    <a class="btn btn-primary" type="button" onclick="complete()">
                        <span class="glyphicon glyphicon-saved"></span>
                        <fmt:message key="common.complete"/>
                    </a>
                </div>
            </div>
        </div>
    </div>
    </div>
</div>

</body>
</html>



