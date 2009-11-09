<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<title>Поиск задачи</title>
</head>
<body>
    <form name="formSearch" method="post" action="searchTask.htm">
        <table>
            <tr>
                <td>Поиск по внутреннему номеру задачи</td>
                <td>
                    <spring:bind path="searchTask.isTaskInternalNumber">
                        <input type="radio" name="find" <c:if test="${status.value}">checked</c:if>/>
                    </spring:bind>
                </td>
                <td>
                    <spring:bind path="searchTask.taskInternalNumber">
                        <input type="text" name="${status.expression}" value="${status.value}"/>
                    </spring:bind>
                </td>
            </tr>
            <tr>
                <td>Поиск по резолюции к задаче</td>
                <td>
                    <spring:bind path="searchTask.isTaskDescription">
                        <input type="radio" name="find" <c:if test="${status.value}">checked</c:if>/>
                    </spring:bind>
                </td>
                <td>
                    <spring:bind path="searchTask.taskDescription">
                        <input type="text" name="${status.expression}" value="${status.value}"/>
                    </spring:bind>
                <td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td><input type="submit"><td>
            </tr>
        </table>
    </form>
</body>
</html>
