<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:setBundle basename="messages.app"/>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="container">
    <div class="jumbotron">
        <div class="container">
            <p/>
        <form method="post" action="users">
            <fmt:message key="app.login"/>: <select name="userId">
            <option value="100000" selected>User</option>
            <option value="100001">Admin</option>
        </select>
            <button type="submit"><fmt:message key="common.select"/></button>
        </form>
        <ul>
            <li><strong><a href="users"><fmt:message key="users.title"/></a></strong></li>
            <li><strong><a href="restaurants"><fmt:message key="restaurants.title"/></a></strong></li>
        </ul>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
