<%--footer of any page, includes link to application repository in WEB--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="messages.app"/>

<hr>
    <div class="navbar-fixed-bottom">
        <div class="container">

        <nav class="navbar navbar-link">
            <fmt:message key="app.footer"/>
        </nav>
    </div>
</div>

<%--function creates an array of bundle resourses specify of users lical--%>
<script type="text/javascript">
    var i18n = [];
    <c:forEach var='key' items='<%=new String[]{"common.select","common.update","common.delete",
                                                    "users.add","users.edit",
                                                    "restaurants.add","restaurants.edit","restaurants.select",
                                                    "menuLists.add","menuLists.edit", "menuLists.select",
                                                    "orders.add","orders.edit",
                                                    "dishes.add","dishes.edit","dishes.select"}%>'>
    i18n['${key}'] = '<fmt:message key="${key}"/>';
    </c:forEach>
</script>