<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<title>Маршрут</title>
</head>
<body>
    <c:set var="counter" value="1" scope="page" />
    <table>
        <tr>
            <td class="all">№</td>
            <td class="all">WorkflowID</td>
            <td class="all">От кого</td>
            <td class="all">К кому</td>
            <td class="all">Передано с резолюцией</td>
            <td class="all">Статус</td>
            <td class="all">Записка к смене статуса</td>
        </tr>
        <c:forEach var="workflow" items="${requestScope.roadmap}">
            <tr>
                <td class="all">${counter}</td>
                <td class="all">${workflow.uid}</td>
                <td class="all">${workflow.assignee.lastName} ${workflow.assignee.firstName} ${workflow.assignee.middleName}</td>
                <td class="all">${workflow.receiver.lastName} ${workflow.receiver.firstName} ${workflow.receiver.middleName}</td>
                <td class="all">&nbsp;${workflow.description}</td>
                <td class="all">&nbsp;${workflow.state.stateDescription}</td>
                <td class="all">&nbsp;${workflow.workflowNote}</td>
            </tr>
            <c:set var="counter" value="${counter+1}" scope="page" />
        </c:forEach>
    </table>
    <form>
        <button type="button" onclick="javascript:history.back();">Назад</button>
    </form>
</body>
</html>
