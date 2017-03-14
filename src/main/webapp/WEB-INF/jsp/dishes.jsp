<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages.app"/>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<script type="text/javascript" src="resources/js/datatableUtil.js" defer></script>
<script type="text/javascript" src="resources/js/dishesUtil.js" defer></script>

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="container">
    <div class="jumbotron">
        <div class="shadow">
            <h3><fmt:message key="dishes.title"/></h3>
            <p>${restaurant.name}, ${restaurant.address}</p>
            <p>${localDate}</p>
            <div class="view-box">
                <a class="btn btn-primary" type="button" onclick="add()">
                    <span class="glyphicon glyphicon-plus-sign"></span>
                    <fmt:message key="dishes.add"/>
                </a>
                <table class="table table-hover table-bordered " id="datatable">
                    <thead>
                    <tr>
                        <th><fmt:message key="dishes.description"/></th>
                        <th><fmt:message key="dishes.price"/></th>
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
                        <label for="description" class="control-label col-xs-3"><fmt:message
                                key="dishes.description"/></label>

                        <div class="col-xs-9">
                            <input class="form-control" id="description" name="description"
                                   placeholder="<fmt:message key="dishes.description"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="price" class="control-label col-xs-3"><fmt:message
                                key="dishes.price"/></label>

                        <div class="col-xs-9">
                            <input class="form-control" id="price" name="price"
                                   placeholder="<fmt:message key="dishes.price"/>">
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



