<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<div>
    <p>Пользователю</p>
    <a href="index.htm">Главная страница</a><br />
    <a href="<c:url value="selectUsers.htm" />">Создать задачу</a><br />
    <a href="tasksForReview.htm">Задачи, ожидающие проверки</a><br />
    <!--
    <a href="primaveraReportWizard.htm">Текущие работы в Primavera</a><br />
    -->
    <a href="logout.htm">Завершить работу</a>
</div>
<sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_REG">
    <div>
        <p>Секретарю</p>
        <a href="outgoingLetterWizard.htm">Зарегистрировать исходящее письмо</a><br />
        <a href="documentRegisterWizard.htm">Зарегистрировать распорядительный документ</a>
    </div>
</sec:authorize>
<sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_REG,ROLE_BOSS,ROLE_TOP">
    <div>
        <p>Поиск</p>
        <a href="searchTask.htm">Поиск задачи</a><br />
        <a href="outgoingMailSearch.htm">Поиск в исходящих</a><br />
        <a href="documentReports.htm">Журналы документов</a><br />
    </div>
</sec:authorize>
<sec:authorize ifAnyGranted="ROLE_ADMIN">
    <div>
        <p>Администратору</p>
        <a href="newUser.htm">Добавить пользователя</a><br />
        <a href="userReport.htm">Список пользователей</a><br />
    </div>
</sec:authorize>
<div>
    <p>Работа с согласованиями</p>
    <a href="negotiationStart.htm">Новое согласование</a><br />
    <a href="negotiations.htm">Мои согласования</a><br />
    <a href="review.htm">Ожидают согласования</a><br />
    <a href="completedNegotiations.htm">Завершенные согласования</a><br />
    <a href="rejectedNegotiations.htm">Несогласованные</a><br />
    <a href="negotiationsReport.htm">Реестр согласований</a>
</div>