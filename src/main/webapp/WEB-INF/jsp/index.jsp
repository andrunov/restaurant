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
        <%--<p class="container">--%>
            <%--<p/>--%>
        </form>
            <p><strong><a href="users"><fmt:message key="users.title"/></a></strong></p>
            <p><strong><a href="restaurants"><fmt:message key="restaurants.title"/></a></strong></p>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
