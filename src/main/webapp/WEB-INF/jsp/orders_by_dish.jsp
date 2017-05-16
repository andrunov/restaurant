<%--shows orders of specify dish--%>
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
                <table class="table table-hover table-bordered " id="ordersDT">
                    <thead>
                    <tr>
                        <th></th>
                        <th><fmt:message key="common.dateTime"/></th>
                        <th><fmt:message key="users.name"/></th>
                        <th><fmt:message key="users.email"/></th>
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
                        <label for="dateTime" class="control-label col-xs-3"><fmt:message  key="common.dateTime"/></label>

                        <div class="col-xs-9">
                            <input class="form-control" id="dateTime" name="dateTime"
                                   placeholder="<fmt:message key="common.dateTime"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <p>
                            <label  class="control-label col-xs-3"><fmt:message key="order.status"/></label>
                        </p>

                        <div class="col-xs-9">
                            <p>
                                <input name="status" type="radio" id="ACCEPTED" value="ACCEPTED">
                                <label  for="ACCEPTED"><fmt:message key="status.ACCEPTED"/></label>
                            </p>
                            <p>
                                <input name="status" type="radio" id="PREPARING" value="PREPARING">
                                <label  for="PREPARING"><fmt:message key="status.PREPARING"/></label>
                            </p>
                            <p>
                                <input name="status" type="radio" id="READY" value="READY">
                                <label  for="READY"><fmt:message key="status.READY"/></label>
                            </p>
                            <p>
                                <input name="status" type="radio" id="FINISHED" value="FINISHED">
                                <label  for="FINISHED"><fmt:message key="status.FINISHED"/></label>
                            </p>
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
</body>
</html>