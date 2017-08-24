<%--header of any page, includs navigation bar--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages.app"/>
<!-- Static navbar -->
<div class="container">

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="javascript:javascript:history.go(-1)">&nbsp;&nbsp;<span class="glyphicon glyphicon-arrow-left"></span></a></li>
                <li><a href="javascript:javascript:history.go(+1)">&nbsp;&nbsp;<span class="glyphicon glyphicon-arrow-right"></span></a></li>
            </ul>
            <ul class="nav navbar-nav">
                <li class="active"><a href="/"><fmt:message key="app.home"/></a></li>
                <li><a href="#">About</a></li>
                <li><a href="#">Contact</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right" vertical-align: center>
                <ul class="nav navbar-nav">
                    <li>
                    <a class="btn btn-info" role="button" href="profile">${user.name} <fmt:message key="app.profile"/></a>
                    <li><a href="logout"><fmt:message key="app.logout"/>&nbsp;&nbsp;<span class="glyphicon glyphicon-log-out"></span></a></li>
                    </li>
                    <jsp:include page="lang.jsp"/>
                </ul>
            </ul>
        </div><!--/.nav-collapse -->
    </div><!--/.container-fluid -->
</nav>
</div>
