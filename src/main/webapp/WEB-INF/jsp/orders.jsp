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
                        <th><fmt:message key="restaurants.name"/></th>
                        <th><fmt:message key="orders.content"/></th>
                        <th><fmt:message key="app.changeTateTime"/></th>
                        <th><fmt:message key="common.delete"/></th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>

<%--edit dateTime modal window--%>
<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="modalTitle"></h2>

            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" id="detailsForm">
                    <input type="text" hidden="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="dateTime" class="control-label col-xs-3"><fmt:message
                                key="common.dateTime"/></label>

                        <div class="col-xs-9">
                            <input class="form-control" id="dateTime" name="dateTime"
                                   placeholder="<fmt:message key="common.dateTime"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="button" onclick="save()" class="btn btn-primary">
                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<%-- restaurant modal window--%>
<div class="modal fade" id="selectRestaurant">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="modalTitle2"></h2>
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
                <h2 class="modal-title" id="modalTitle3"></h2>
                <h3 class="modal-title" id="modalTitle35"></h3>

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
                <h2 class="modal-title" id="modalTitle4"></h2>
            </div>

            <div class="modal-body">

                <table class="table table-hover table-bordered " id="dishDT">
                    <thead>
                    <tr>
                        <th><fmt:message key="dishes.description"/></th>
                        <th><fmt:message key="dishes.price"/></th>
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



