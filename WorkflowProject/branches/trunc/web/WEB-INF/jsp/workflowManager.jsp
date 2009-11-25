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
                <spring:bind path="workflowManager.task.internalNumber">
                    <td><input type="text" name="${status.expression}" value="${status.value}" disabled="true"></td>
                    <td><font color="red">${status.errorMessage}</font></td>
                </spring:bind>
            </tr>
            <tr>
                <td>Состояние задачи:</td>
                <spring:bind path="workflowManager.state">
                    <td>
                        <select name="${status.expression}" style="width : 200">
                            <c:choose>
                                <c:when test="${status.value eq 'Не начата'}">
                                    <option value="0" selected>Не начата</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="0">Не начата</option>
                                </c:otherwise>
                            </c:choose>
                            <!--
                            <c:choose>
                                <c:when test="${status.value eq 'Передана'}">
                                    <option value="1" selected>Передана</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="1">Передана</option>
                                </c:otherwise>
                            </c:choose>
                            -->
                            <c:choose>
                                <c:when test="${status.value eq 'В работе'}">
                                    <option value="2" selected>В работе</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="2" >В работе</option>
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${status.value eq 'Завершена'}">
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
        </table>
        <p><input type="submit" align="right" value="Сохранить"></p>
    </form>
</body>
</html>
