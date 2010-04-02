<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<a href="index.htm">Главная страница</a><br />
<a href="<c:url value="selectUsers.htm" />">Создать задачу</a><br />
<a href="tasksForReview.htm">Задачи, ожидающие проверки</a><br />
<a href="primaveraReportWizard.htm">Текущие работы в Primavera</a><br />
<sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_REG,ROLE_BOSS,ROLE_TOP">
    <a href="searchTask.htm">Поиск задачи</a><br />
</sec:authorize>
<sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_REG">
    <p>
        <a href="outgoingLetterWizard.htm">Зарегистрировать исходящее письмо</a><br />
        <a href="documentRegisterWizard.htm">Зарегистрировать распорядительный документ</a>
    </p>
</sec:authorize>
<sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_REG,ROLE_TOP">
    <a href="outgoingMailSearch.htm">Поиск в исходящих</a><br />
    <p>
        <a href="documentReports.htm">Журналы документов</a><br />
    </p>
</sec:authorize>
<p><a href="logout.htm">Завершить работу</a></p>