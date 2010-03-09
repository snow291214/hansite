<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<a href="<c:url value="selectUsers.htm" />">Создать задачу</a><br />
<a href="tasksForReview.htm">Задачи, ожидающие проверки</a><br />
<sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_REG,ROLE_BOSS">
    <a href="searchTask.htm">Поиск задачи</a><br />
</sec:authorize>
<sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_REG">
    <p>
        <a href="<c:url value="outgoingLetterWizard.htm"/>">Зарегистрировать исходящее письмо</a><br />
    </p>
</sec:authorize>
<sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_REG,ROLE_BOSS">
    <a href="outgoingMailSearch.htm">Поиск в исходящих</a><br />
</sec:authorize>
<p><a href="<c:url value="logout.htm"/>">Завершить работу</a></p>