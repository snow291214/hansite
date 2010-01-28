<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<c:set var="counter" value="1" scope="page" />
<c:set var="show" value="false" scope="page" />
<table>
    <tr>
        <td class="all">№</td>
        <td class="all">WorkflowID</td>
        <td class="all">От кого</td>
        <td class="all">К кому</td>
        <td class="all">Прикрепленные файлы задачи</td>
        <td class="all">Передано с резолюцией</td>
        <td class="all">Состояние</td>
        <td class="all">Записка к смене состояния</td>
        <td class="all">Прикрепленные файлы пользователя</td>
    </tr>
    <c:forEach var="workflow" items="${requestScope.roadmap}">
        <tr>
            <td class="all">${counter}</td>
            <td class="all">${workflow.uid}</td>
            <td class="all">${workflow.assignee.lastName} ${workflow.assignee.firstName} ${workflow.assignee.middleName}</td>
            <td class="all">${workflow.receiver.lastName} ${workflow.receiver.firstName} ${workflow.receiver.middleName}</td>
            <td class="all">
                <c:if test="${show == 'false'}">
                    <c:forEach var = "taskFile" items="${workflow.taskBean.filesSet}">
                        <a href="download.htm?fileID=${taskFile.uid}">${taskFile.fileName}</a><br />
                     </c:forEach>
                     <c:set var="show" value="true" scope="page" />
                </c:if>
            </td>
            <td class="all">${workflow.description}</td>
            <td class="all">${workflow.state.stateDescription}</td>
            <td class="all">&nbsp;${workflow.workflowNote}</td>
            <td class="all">
                <c:forEach var="workflowFile" items="${workflow.workflowFileBeanSet}">
                    <a href="getWorkflowFiles.htm?fileID=${workflowFile.uid}">${workflowFile.fileName}</a><br />
                </c:forEach>
            </td>
        </tr>
        <c:set var="counter" value="${counter+1}" scope="page" />
    </c:forEach>
</table>
