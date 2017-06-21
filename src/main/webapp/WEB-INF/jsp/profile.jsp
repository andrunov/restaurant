<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<fmt:setBundle basename="messages.app"/>


<%--<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>--%>
<%--<%@ taglib prefix="topjava" tagdir="/WEB-INF/tags" %>--%>

<html>
<jsp:include page="fragments/headTag.jsp"/>

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="container">
    <div class="jumbotron">
        <div class="shadow">

            <h3 class="page-header">${user.name}:  <fmt:message key="app.profile"/></h3>

            <div class="view-box">

                <form class="form-horizontal" method="post" action="profile">

                    <div class="form-group">
                       <label for="name" class="control-label col-xs-3"><fmt:message key="users.name"/></label>
                         <div class="col-xs-9">
                             <input type="text" class="form-control" id="name" name="name" value="${user.name}" placeholder="<fmt:message key="users.name"/>">
                         </div>
                    </div>

                    <div class="form-group">
                       <label for="email" class="control-label col-xs-3"><fmt:message key="users.email"/></label>
                           <div class="col-xs-9">
                               <input type="email" class="form-control" id="email" name="email" value="${user.email}" placeholder="<fmt:message key="users.email"/>">
                           </div>
                    </div>

                    <div class="form-group">
                       <label for="password" class="control-label col-xs-3"><fmt:message key="users.password"/></label>
                            <div class="col-xs-9">
                               <input type="password" class="form-control" id="password" name="password" value="${user.password}" placeholder="<fmt:message key="users.password"/>">
                            </div>
                    </div>

                    <div class="form-group">
                            <div class="col-xs-offset-2 col-xs-10">
                                <div class="pull-right">
                                    <button type="submit" class="btn btn-primary">
                                        <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                                    </button>
                                </div>
                            </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>