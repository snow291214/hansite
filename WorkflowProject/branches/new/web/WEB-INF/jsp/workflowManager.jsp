<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
﻿<%@ include file="/WEB-INF/jsp/include.jsp" %>
<title>Управление задачей</title>
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
        </tr>
        <c:forEach var="workflow" items="${requestScope.roadmap}">
            <tr>
                <td class="all">${counter}</td>
                <td class="all">${workflow.uid}</td>
                <td class="all">${workflow.assignee.lastName} ${workflow.assignee.firstName} ${workflow.assignee.middleName}</td>
                <td class="all">${workflow.receiver.lastName} ${workflow.receiver.firstName} ${workflow.receiver.middleName}</td>
                <td class="all">${workflow.description}</td>
            </tr>
            <c:set var="counter" value="${counter+1}" scope="page" />
        </c:forEach>
    </table>
    <form name="Form1" method="post" action="${actionUrl}">
        <input type="hidden" name="workflowID" value="${workflowID}">
        <table>
            <tr>
                <td>Внутренний номер задачи:</td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td>Состояние задачи:</td>
                <spring:bind path="workflowManager.stateUid">
                    <td>
                        <select name="${status.expression}" style="width : 200">
                            <c:choose>
                                <c:when test="${status.value eq 0}">
                                    <option value="0" selected>Не начата</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="0">Не начата</option>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${status.value eq 2}">
                                    <option value="2" selected>В работе</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="2" >В работе</option>
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${status.value eq 3}">
                                    <option value="3" selected>Завершена</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="3">Завершена</option>
                                </c:otherwise>
                            </c:choose>
                        </select>
                    </td>
                    <td><font color="red">${status.errorMessage}</font></td>
                </spring:bind>
            </tr>
            <tr>
                <td>Записка к смене состояния задачи:</td>
                <spring:bind path="workflowManager.workflowNote">
                    <td>
                        <textarea rows="4" cols="22" name="<c:out value="${status.expression}"/>">${status.value}</textarea>
                    </td>
                    <td><font color="red">${status.errorMessage}</font></td>
                </spring:bind>
            </tr>
        </table>
        <p><button type="button" onclick="javascript:history.back();"><< Назад</button><input type="submit" align="right" value="Сохранить"></p>
    </form>
</body>
</html>
