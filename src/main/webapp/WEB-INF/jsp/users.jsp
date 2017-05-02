<%--shows users--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:setBundle basename="messages.app"/>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<script type="text/javascript" src="resources/js/datatableUtil.js" defer></script>
<script type="text/javascript" src="resources/js/usersUtil.js" defer></script>

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="container">
    <div class="jumbotron">
        <div class="shadow">
            <h3><fmt:message key="users.title"/></h3>
            <div class="view-box">
                <a class="btn btn-primary" type="button" onclick="add()">
                    <span class="glyphicon glyphicon-plus-sign"></span>
                    <fmt:message key="users.add"/>
                </a>
                <table class="table table-hover table-bordered " id="datatable">
                    <thead>
                    <tr>
                        <th><fmt:message key="users.name"/></th>
                        <th><fmt:message key="users.email"/></th>
                        <th><fmt:message key="users.roles"/></th>
                        <th><fmt:message key="orders.title"/></th>
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
                        <label for="name" class="control-label col-xs-3"><fmt:message key="users.name"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="name" name="name" placeholder="<fmt:message key="users.name"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="email" class="control-label col-xs-3"><fmt:message key="users.email"/></label>

                        <div class="col-xs-9">
                            <input type="email" class="form-control" id="email" name="email" placeholder="<fmt:message key="users.email"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="password" class="control-label col-xs-3"><fmt:message key="users.password"/></label>

                        <div class="col-xs-9">
                            <input type="password" class="form-control" id="password" name="password" placeholder="<fmt:message key="users.password"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label  class="control-label col-xs-3"><fmt:message key="users.roles"/></label>

                        <div class="col-xs-9">
                            <p>
                                <label  for="ADMIN"><fmt:message key="users.admin"/></label>
                                <input type="checkbox" id="ADMIN" name="roles" value="ADMIN">
                            </p>

                            <p>
                                <label  for="USER"><fmt:message key="users.user"/></label>
                                <input type="checkbox" id="USER" name="roles" value="USER">
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
