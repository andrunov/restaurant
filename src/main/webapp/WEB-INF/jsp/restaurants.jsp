<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<fmt:setBundle basename="messages.app"/>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <h3><fmt:message key="restaurants.title"/></h3>

    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th><fmt:message key="restaurants.name"/></th>
            <th><fmt:message key="restaurants.address"/></th>
            <th/>
            <th/>
        </tr>
        </thead>
        <c:forEach items="${restaurants}" var="restaurant">
            <jsp:useBean id="restaurant" scope="page" type="ru.agorbunov.restaurant.model.Restaurant"/>
            <tr>
                <td><c:out value="${restaurant.name}"/></td>
                <td><c:out value="${restaurant.address}"/></td>
                <td><a href="restaurants/update?id=${restaurant.id}"><fmt:message key="common.update"/></a></td>
                <td><a href="restaurants/delete?id=${restaurant.id}"><fmt:message key="common.delete"/></a></td>
            </tr>
        </c:forEach>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
