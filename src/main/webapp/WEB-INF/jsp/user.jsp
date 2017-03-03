<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages.app"/>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <jsp:useBean id="user" type="ru.agorbunov.restaurant.model.User" scope="request"/>
    <h3><fmt:message key="${user.isNew() ? 'users.add' : 'users.edit'}"/></h3>
    <hr>
    <form method="post" action="save">
        <input type="hidden" name="id" value="${user.id}">
        <dl>
            <dt><fmt:message key="users.name"/>:</dt>
            <dd><input type="text" value="${user.name}" size=50 name="name"></dd>
        </dl>
        <dl>
            <dt><fmt:message key="users.email"/>:</dt>
            <dd><input type="text" value="${user.email}" size=40 name="email"></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()">Cancel</button>
    </form>
</section>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>