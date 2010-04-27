<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<table class="workflowManager">
    <tr>
        <td width="10%">Дата начала задачи</td>
        <td width="5%">Срок до</td>
        <td width="10%">Дата назначения исполнителю</td>
        <td width="10%">Состояние задачи</td>
        <td width="10%">Идентификатор СКП "Primavera"</td>
        <td width="15%">Файлы, прикрепленные к задаче</td>
        <td width="10%">Записка к смене состояния</td>
        <td width="15%">Прикрепленные файлы пользователя</td>
        <td width="15%">Эту задачу получили:</td>
    </tr>
    <tr>
        <td>
    <fmt:formatDate pattern="dd.MM.yyyy" value="${wf.taskBean.startDate}"/>
</td>
<td>
<fmt:formatDate pattern="dd.MM.yyyy" value="${wf.taskBean.dueDate}"/>
</td>
<td>
<fmt:formatDate pattern="dd.MM.yyyy" value="${wf.assignDate}"/>
</td>
<td>
    <font color="red"><b><c:out value="${wf.state.stateDescription}" escapeXml="false"/></b>.</font>
</td>
<td>
    <a href="primaveraReport.htm?primaveraUid=${wf.taskBean.primaveraUid}">${wf.taskBean.primaveraUid}</a>
</td>
<td>
<c:forEach var = "taskFile" items="${wf.taskBean.filesSet}">
    <a href="<c:url value="download.htm?fileID=${taskFile.uid}" />">${taskFile.fileName}</a>
</c:forEach>
</td>
<td>&nbsp;${wf.workflowNote}</td>
<td>
<c:forEach var="workflowFile" items="${wf.workflowFileBeanSet}">
    <a href="getWorkflowFiles.htm?fileID=${workflowFile.uid}">${workflowFile.fileName}</a><br />
</c:forEach>
</td>
<td>
<c:forEach var="wfset" items="${wf.taskBean.workflowsSet}">
    <a href="roadmap.htm?workflowID=${wfset.uid}">${wfset.receiver.lastName}</a>;
</c:forEach>
</td>
</tr>
</table>