<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<fmt:setBundle basename="messages.app"/>


<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<nav class="container">

<nav class="navbar navbar-default">
        <div class="container-fluid">
        <div class="navbar-header navbar-brand"><fmt:message key="app.title"/></div>
        <div class="navbar-collapse collapse">

            <ul class="nav navbar-nav navbar-right">
                <li>
                    <form class="navbar-form" role="form" action="spring_security_check" method="post">
                        <div class="form-group">
                            <input type="text" placeholder="Email" class="form-control" name="username">
                        </div>
                        <div class="form-group">
                            <input type="password" placeholder="Password" class="form-control" name="password">
                        </div>
                        <button type="submit" class="btn btn-success">
                            <span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>
                        </button>
                    </form>
                </li>
                <jsp:include page="fragments/lang.jsp"/>
            </ul>
        </div>
    </div>
</nav>

<div class="jumbotron">
    <div class="container">
        <c:if test="${error}">
            <div class="error">
                    ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
            </div>
        </c:if>
        <c:if test="${not empty message}">
            <div class="message">
                ${message}"
            </div>
        </c:if>
        <br/>
        <p>
            <a class="btn btn-lg btn-success" href="register"><fmt:message key="app.register"/> &raquo;</a>
            <button type="submit" class="btn btn-lg btn-primary" onclick="setCredentials('rzanetti@gmail.com', '444')">
                <fmt:message key="app.login"/> User
            </button>
            <button type="submit" class="btn btn-lg btn-success" onclick="setCredentials('andrunov@gmail.com', '222')">
                <fmt:message key="app.login"/> Admin
            </button>
        </p>
        <p>Стек технологий: <a href="http://projects.spring.io/spring-security/">Spring Security</a>,
            <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html">Spring MVC</a>,
            <a href="http://spring.io/blog/2014/05/07/preview-spring-security-test-method-security">Spring Security Test</a>,
            <a href="http://hibernate.org/orm/">Hibernate ORM</a>,
            <a href="http://hibernate.org/validator/">Hibernate Validator</a>,
            <a href="http://www.slf4j.org/">SLF4J</a>,
            <a href="https://github.com/FasterXML/jackson">Json Jackson</a>,
            <a href="http://ru.wikipedia.org/wiki/JSP">JSP</a>,
            <a href="http://en.wikipedia.org/wiki/JavaServer_Pages_Standard_Tag_Library">JSTL</a>,
            <a href="http://tomcat.apache.org/">Apache Tomcat</a>,
            <a href="http://www.webjars.org/">WebJars</a>,
            <a href="http://datatables.net/">DataTables plugin</a>,
            <a href="http://ehcache.org">Ehcache</a>,
            <a href="http://www.postgresql.org/">PostgreSQL</a>,
            <a href="http://junit.org/">JUnit</a>,
            <a href="http://hamcrest.org/JavaHamcrest/">Hamcrest</a>,
            <a href="http://jquery.com/">jQuery</a>,
            <a href="http://ned.im/noty/">jQuery notification</a>,
            <a href="http://getbootstrap.com/">Bootstrap</a>.</p>
    </div>
</div>
<div class="container">
    <div class="lead">
        <%--&lt;%&ndash;todo-change web address-%>--%>
        &nbsp;&nbsp;&nbsp;<a href="https://github.com/JavaOPs/topjava">Java Enterprise проект</a>
        Web-приложение для осуществления online-заказов в рестараны. По аналогии с электронным табло заказов
        в McDonald's, только все упрощено и ресторанов много.
        Два типа пользователей на основе ролей (USER, ADMIN).
        Администратор может создавать/редактировать/удалять/пользователей, рестораны, меню, блюда и заказы,
        а пользователь - управлять своим профилем, создавать и редактировать свои заказы.
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
<script type="text/javascript">
    <c:if test="${not empty param.username}">
    setCredentials("${param.username}", "");
    </c:if>
    function setCredentials(username, password) {
        $('input[name="username"]').val(username)
        $('input[name="password"]').val(password)
    }
</script>
</nav>
</body>
</html>