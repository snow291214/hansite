<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<a href="<c:url value="selectUsers.htm" />">Создать задачу</a><br />
<a href="searchTask.htm">Поиск задачи</a><br />
<sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_REG">
    <a href="<c:url value="outgoingLetterWizard.htm"/>">Исходящее письмо</a><br />
</sec:authorize>
<sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_REG,ROLE_BOSS">
    <a href="outgoingLetterSearch.htm">Поиск в исходящих</a><br />
</sec:authorize>
<p><a href="<c:url value="logout.htm"/>">Завершить работу</a></p>