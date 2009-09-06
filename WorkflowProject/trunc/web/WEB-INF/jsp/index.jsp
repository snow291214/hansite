<%@ include file="/WEB-INF/jsp/include.jsp" %>
<title>Система электронного документооборота ООО "Салаватгазонефтехмипроект"</title>
</head>

<body>
    <c:out value="Здравствуйте,
           ${sessionScope.initiator.lastName}
           ${sessionScope.initiator.firstName}
           ${sessionScope.initiator.middleName}
           (${sessionScope.initiator.login})" />

    <p><a href="<c:url value="selectUsers.htm"/>">Назначить задачу</a></p>

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
                Завершенные задачи
            </div>
            <em class="ctl"><b>•</b></em><em class="ctr"><b>•</b></em><em class="cbl"><b>•</b></em><em class="cbr"><b>•</b></em>
        </div>
    </div>
    <div class="empty">
        <c:forEach var="wf" items="${sessionScope.initiator.workflows}">
            <div>
                <div class="header red">
                    Задача № <c:out value="${wf.task.internalNumber}"/>. <c:out value="${wf.task.description}"/>
                </div>
                <div>
                    <!--Описание задачи: <c:out value="${wf.task.description}"/><br />-->
                    Задачу назначил: <c:out value="${wf.assignee.lastName} ${wf.assignee.firstName} ${wf.assignee.middleName}"/>
                    Дата начала задачи: <c:out value="${wf.task.startDate}"/> Срок до: <c:out value="${wf.task.startDate}"/><br />
                    <div class="content">
                        Резолюция к задаче: <c:out value="${wf.description}"/>. Дата назначения задачи: <c:out value="${wf.assignDate}"/>. Состояние задачи: <font color="red"><b><c:out value="${wf.state}"/></b>.</font><br />
                        Файлы, прикрепленные к задаче:
                        <c:forEach var = "taskFile" items="${wf.task.taskFiles}">
                            <a href="<c:url value="download.htm?fileID=${taskFile.uid}" />">${taskFile.fileName}</a>
                        </c:forEach>
                        <br />
                        <a href="<c:url value="selectUsers.htm?workflowID=${wf.uid}" />">Передать задачу</a>
                        <a href="<c:url value="taskdetails.htm?taskID=${wf.task.uid}" />">Управление задачей</a>
                    </div>
                </div>
            </div>
            <br />
        </c:forEach>
    </div>
</body>
</html>