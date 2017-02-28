<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<fmt:setBundle basename="messages.app"/>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="app.title"/></title>
    <base href="${pageContext.request.contextPath}/"/>

    <%--<link rel="stylesheet" href="resources/css/style.css">--%>
    <link rel="stylesheet" href="webjars/bootstrap/3.3.7-1/css/bootstrap.min.css">
    <link rel="stylesheet" href="webjars/datatables/1.10.13/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="webjars/datatables/1.10.13/css/dataTables.bootstrap.min.css">

    <!--http://stackoverflow.com/a/24070373/548473-->
    <%--<script type="text/javascript" src="webjars/jquery/3.1.1-1/jquery.min.js" defer></script>--%>
    <%--<script type="text/javascript" src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js" defer></script>--%>
    <%--<script type="text/javascript" src="webjars/datatables/1.10.13/js/jquery.dataTables.min.js" defer></script>--%>
    <%--<script type="text/javascript" src="webjars/datatables/1.10.13/js/dataTables.bootstrap.min.js" defer></script>--%>
</head>

