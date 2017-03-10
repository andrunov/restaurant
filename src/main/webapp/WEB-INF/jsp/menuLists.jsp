<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages.app"/>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<script type="text/javascript" src="resources/js/datatableUtil.js" defer></script>
<script type="text/javascript" src="resources/js/menuListsUtil.js" defer></script>

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="container">
    <div class="jumbotron">
        <div class="shadow">
            <h3><fmt:message key="menuLists.title"/></h3>
            <div class="view-box">
                <a class="btn btn-primary" type="button" onclick="add()">
                    <span class="glyphicon glyphicon-plus-sign"></span>
                    <fmt:message key="menuLists.add"/>
                </a>
                <table class="table table-hover table-bordered " id="datatable">
                    <thead>
                    <tr>
                        <th><fmt:message key="common.dateTime"/></th>
                        <th/>
                        <th/>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>

<%--<div class="modal fade" id="editRow">--%>
    <%--<div class="modal-dialog">--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>--%>
                <%--<h2 class="modal-title" id="modalTitle"></h2>--%>

            <%--</div>--%>
            <%--<div class="modal-body">--%>
                <%--<form class="form-horizontal" method="post" id="detailsForm">--%>
                    <%--<input type="text" hidden="hidden" id="id" name="id">--%>

                    <%--&lt;%&ndash;<div class="form-group">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<label for="name" class="control-label col-xs-3"><fmt:message key="restaurants.name"/></label>&ndash;%&gt;--%>

                        <%--&lt;%&ndash;<div class="col-xs-9">&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<input type="text" class="form-control" id="name" name="name" placeholder="<fmt:message key="restaurants.name"/>">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>

                    <%--<div class="form-group">--%>
                        <%--<label for="d" class="control-label col-xs-3"><fmt:message key="restaurants.address"/></label>--%>

                        <%--<div class="col-xs-9">--%>
                            <%--<input type="text" class="form-control" id="address" name="address" placeholder="<fmt:message key="restaurants.address"/>">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<div class="col-xs-offset-3 col-xs-9">--%>
                            <%--<button type="button" onclick="save()" class="btn btn-primary">--%>
                                <%--<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>--%>
                            <%--</button>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</form>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>
</body>
</html>



