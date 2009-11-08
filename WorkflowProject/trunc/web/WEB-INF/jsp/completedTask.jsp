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
                <a href="<c:url value="selectUsers.htm"/>">Создать задачу</a> || <a href="<c:url value="logout.htm"/>">Завершить работу</a>
            </td>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
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
                            <a class="box" href="<c:url value="assignedTask.htm" />">Задачи, назначенные Вами</a>
                        </div>
                        <em class="ctl"><b>•</b></em><em class="ctr"><b>•</b></em><em class="cbl"><b>•</b></em><em class="cbr"><b>•</b></em>
                    </div>

                    <div class="box w300 green">
                        <div class="box-inner">
                            <a class="box" href="<c:url value="completedTask.htm" />">Завершенные Вами задачи (${requestScope.count})</a>
                        </div>
                        <em class="ctl"><b>•</b></em><em class="ctr"><b>•</b></em><em class="cbl"><b>•</b></em><em class="cbr"><b>•</b></em>
                    </div>
                </div>
            </td>
        </tr>
    </table>
    <div class="empty">
        <c:forEach var="wf" items="${requestScope.completed}">
            <div>
                <div class="header green">
                    Задача № <c:out value="${wf.task.internalNumber}"/>. <c:out value="${wf.task.description}"/>
                </div>
                <div>
                    Компания: ${wf.task.externalCompany}. Исходящий номер: ${wf.task.externalNumber}.
                    Отправитель: ${wf.task.externalAssignee}<br />
                    Задачу назначил: <c:out value="${wf.assignee.lastName} ${wf.assignee.firstName} ${wf.assignee.middleName}"/>
                    Дата начала задачи: <c:out value="${wf.task.startDate}"/> Срок до: <c:out value="${wf.task.startDate}"/><br />
                    <div class="content">
                        Резолюция к задаче: <c:out value="${wf.description}"/>. Дата назначения задачи: <c:out value="${wf.assignDate}"/>. Состояние задачи: <font color="red"><b><c:out value="${wf.state}"/></b>.</font><br />
                        Файлы, прикрепленные к задаче:
                        <c:forEach var = "taskFile" items="${wf.task.taskFiles}">
                            <a href="<c:url value="download.htm?fileID=${taskFile.uid}" />">${taskFile.fileName}</a>
                        </c:forEach>
                        <br />
                        <!--
                        <a href="<c:url value="selectUsers.htm?workflowID=${wf.uid}" />">Передать задачу</a>
                        <a href="<c:url value="workflowManager.htm?workflowID=${wf.uid}" />">Управление задачей</a>
                        -->
                    </div>
                </div>
            </div>
            <br />
        </c:forEach>
    </div>
</body>
</html>