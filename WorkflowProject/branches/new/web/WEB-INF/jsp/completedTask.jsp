<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
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
                   (${sessionScope.initiator.login})" />
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
    <div class="navigation">
        <%@ include file="/WEB-INF/jsp/includes/navigation.jsp" %>
    </div>
    <div class="empty">
        <c:forEach var="wf" items="${requestScope.completed}">
            <div>
                <div class="header green">
                    Задача № <c:out value="${wf.taskBean.internalNumber}"/>. <c:out value="${wf.taskBean.description}"/>
                </div>
                <div>
                    Компания: ${wf.taskBean.externalCompany}. Входящий номер письма: ${wf.taskBean.incomingNumber}. Исходящий номер: ${wf.taskBean.externalNumber}.
                    Отправитель: ${wf.taskBean.externalAssignee}<br />
                    <b>Задачу назначил: <c:out value="${wf.assignee.lastName} ${wf.assignee.firstName} ${wf.assignee.middleName}"/></b>
                    Дата начала задачи: <fmt:formatDate pattern="dd.MM.yyyy" value="${wf.taskBean.startDate}" />
                    Срок до: <fmt:formatDate pattern="dd.MM.yyyy" value="${wf.taskBean.dueDate}" /><br />
                    <div class="content">
                        Резолюция к задаче: <c:out value="${wf.description}"/>.
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
                        <a href="<c:url value="roadmap.htm?workflowID=${wf.uid}" />">Просмотреть маршрут задачи</a>
                    </div>
                </div>
            </div>
            <br />
        </c:forEach>
    </div>
</body>
</html>