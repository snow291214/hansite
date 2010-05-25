<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="/WEB-INF/taglib/pagerlib.tld" prefix="pg" %>
<title>Список пользователей системы</title>
</head>
<body>
    <div class="navigation">
        <%@ include file="/WEB-INF/jsp/includes/navigation.jsp" %>
    </div>
    <div class="empty">
        <c:set var="counter" value="1" scope="page" />
        <table  class="workflowManager">
            <tr>
                <td><b>№</b></td>
                <td><b>UserUID</b></td>
                <td><b>Login</b></td>
                <td><b>Фамилия</b></td>
                <td><b>Имя</b></td>
                <td><b>Отчество</b></td>
                <td><b>E-MAIL</b></td>
                <td><b>Группа</b></td>
            </tr>
            <c:forEach var="user" items="${requestScope.workflowUserBeans}">
                <tr>
                    <td>${counter}</td>
                    <td>${user.uid}</td>
                    <td>${user.login}</td>
                    <td>${user.lastName}</td>
                    <td>${user.firstName}</td>
                    <td>${user.middleName}</td>
                    <td>${user.email}</td>
                    <td>${user.userGroupBean.description}</td>
                </tr>
                <c:set var="counter" value="${counter+1}" scope="page" />
            </c:forEach>
        </table>
    </div>
</body>
</html>
