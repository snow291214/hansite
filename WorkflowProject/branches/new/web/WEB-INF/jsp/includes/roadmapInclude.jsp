<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<c:set var="counter" value="1" scope="page" />
<c:set var="show" value="false" scope="page" />
<table class="workflowManager">
    <tr>
        <td>№</td>
        <td>WorkflowID</td>
        <td>От кого</td>
        <td>К кому</td>
        <td>Прикрепленные файлы задачи</td>
        <td>Передано с резолюцией</td>
        <td>Состояние</td>
        <td>Дата получения</td>
        <td>Дата смены состояния</td>
        <td>Записка к смене состояния</td>
        <td>Прикрепленные файлы пользователя</td>
    </tr>
    <c:forEach var="workflow" items="${requestScope.roadmap}">
        <tr>
            <td>${counter}</td>
            <td>${workflow.uid}</td>
            <td>${workflow.assignee.lastName} ${workflow.assignee.firstName} ${workflow.assignee.middleName}</td>
            <td>${workflow.receiver.lastName} ${workflow.receiver.firstName} ${workflow.receiver.middleName}</td>
            <td>
        <c:if test="${show == 'false'}">
            <c:forEach var = "taskFile" items="${workflow.taskBean.filesSet}">
                <a href="download.htm?fileID=${taskFile.uid}">${taskFile.fileName}</a>
            </c:forEach>
            <c:set var="show" value="true" scope="page" />
        </c:if>
        </td>
        <td>${workflow.description}</td>
        <td>${workflow.state.stateDescription}</td>
        <td><fmt:formatDate pattern="dd.MM.yyyy" value="${workflow.assignDate}"/></td>
        <td><fmt:formatDate pattern="dd.MM.yyyy" value="${workflow.finishDate}"/></td>
        <td>&nbsp;${workflow.workflowNote}</td>
        <td>
        <c:forEach var="workflowFile" items="${workflow.workflowFileBeanSet}">
            <a href="getWorkflowFiles.htm?fileID=${workflowFile.uid}">${workflowFile.fileName}</a>
        </c:forEach>
        </td>
        </tr>
        <c:set var="counter" value="${counter+1}" scope="page" />
    </c:forEach>
</table>
