﻿<%@ include file="/WEB-INF/jsp/include.jsp" %>
<title>Система электронного документооборота ООО "Салаватгазонефтехимпроект"</title>
</head>
<body>
    <table>
        <tr>
            <td rowspan="2"><img src="images/logo.jpg" alt="СГНХП" align="left" vspace="5" hspace="5"/></td>
            <td height="30px"><c:out value="Здравствуйте,
                   ${sessionScope.initiator.lastName}
                   ${sessionScope.initiator.firstName}
                   ${sessionScope.initiator.middleName}
                   (${sessionScope.initiator.login}) || " />
                <a href="<c:url value="selectUsers.htm"/>">Создать задачу</a> ||
                <a href="<c:url value="logout.htm"/>">Завершить работу</a> ||
                <a href="searchTask.htm">Поиск</a>
            </td>
        </tr>
        <tr>
            <td colspan="3">
                <div>
                    <div class="box w300 red">
                        <div class="box-inner">
                            <a class="box" href="<c:url value="index.htm" />">Задачи, назначенные Вам</a>
                        </div>
                        <em class="ctl"><b>•</b></em><em class="ctr"><b>•</b></em><em class="cbl"><b>•</b></em><em class="cbr"><b>•</b></em>
                    </div>
                    <div class="box w300 blue">
                        <div class="box-inner">
                            <a class="box" href="<c:url value="assignedTask.htm" />">Задачи, назначенные Вами (${requestScope.count})</a>
                            <a class="box" href="<c:url value="assignedTask.htm?completed=true" />">Показать завершенные</a>
                        </div>
                        <em class="ctl"><b>•</b></em><em class="ctr"><b>•</b></em><em class="cbl"><b>•</b></em><em class="cbr"><b>•</b></em>
                    </div>
                    <div class="box w300 green">
                        <div class="box-inner">
                            <a class="box" href="<c:url value="completedTask.htm" />">Завершенные Вами задачи</a>
                        </div>
                        <em class="ctl"><b>•</b></em><em class="ctr"><b>•</b></em><em class="cbl"><b>•</b></em><em class="cbr"><b>•</b></em>
                    </div>
                </div>
            </td>
        </tr>
    </table>
    <div class="empty">
        <c:forEach var="wf" items="${requestScope.assigned}">
            <div>
                <div class="header blue">
                    Задача № <c:out value="${wf.taskBean.internalNumber}"/>. <c:out value="${wf.taskBean.description}"/>
                </div>
                <div>
                    <!--Описание задачи: <br />-->
                    Компания: ${wf.taskBean.externalCompany}. Входящий номер письма: ${wf.taskBean.incomingNumber}. Исходящий номер: ${wf.taskBean.externalNumber}.
                    Отправитель: ${wf.taskBean.externalAssignee}<br />
                    <b>Задача создана для: <c:out value="${wf.receiver.lastName} ${wf.receiver.firstName} ${wf.receiver.middleName}"/></b>
                    Дата начала задачи: <fmt:formatDate pattern="dd.MM.yyyy" value="${wf.taskBean.startDate}" />
                    Срок до: <fmt:formatDate pattern="dd.MM.yyyy" value="${wf.taskBean.dueDate}" /><br />
                    <div class="content">
                        <b><u>Резолюция к задаче: <c:out value="${wf.description}"/>.</u></b><br/>
                        Дата назначения задачи: <fmt:formatDate pattern="dd.MM.yyyy" value="${wf.assignDate}" />.
                        Состояние задачи:
                        <font color="red"><b><c:out value="${wf.state.stateDescription}"/></b>.
                            <c:if test="${wf.workflowNote != ''}">
                                ${wf.workflowNote}
                            </c:if>
                        </font><br />
                        Файлы, прикрепленные к задаче:
                        <c:forEach var = "taskFile" items="${wf.taskBean.filesSet}">
                            <a href="<c:url value="download.htm?fileID=${taskFile.uid}" />">${taskFile.fileName}</a>
                        </c:forEach>
                        <br />
                        <a href="<c:url value="executionRequest.htm?workflowID=${wf.uid}" />">Запросить отчет</a>
                        <a href="<c:url value="roadmap.htm?workflowID=${wf.uid}" />">Просмотреть маршрут задачи</a>
                    </div>
                </div>
            </div>
            <br />
        </c:forEach>
    </div>
</body>
</html>