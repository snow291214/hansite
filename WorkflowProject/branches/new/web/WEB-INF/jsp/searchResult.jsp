<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<title>Результаты поиска</title>
</head>
<body>
    <div>
        <a href="<c:url value="searchTask.htm" />">Новый поиск</a> ||
        <a href="<c:url value="index.htm" />">На главную</a>
    </div>
    <br>
    <c:forEach var="wf" items="${requestScope.workflowBeans}">
        <div style="margin-bottom: 10px;">
            <div class="header green">
                Задача № <c:out value="${wf.taskBean.internalNumber}"/>. <c:out value="${wf.taskBean.description}"/>
            </div>
            <div>
                Дата начала задачи: <fmt:formatDate pattern="dd.MM.yyyy" value="${wf.taskBean.startDate}"/> Срок до: <fmt:formatDate pattern="dd.MM.yyyy" value="${wf.taskBean.startDate}"/><br />
                <div class="content">
                    <u>
                        ${wf.assignee.lastName}
                        ${wf.assignee.firstName}
                        ${wf.assignee.middleName}
                        ==>
                        ${wf.receiver.lastName}
                        ${wf.receiver.firstName}
                        ${wf.receiver.middleName}
                    </u>
                    Состояние задачи: <font color="red"><b><c:out value="${wf.state.stateDescription}"/></b>.</font>
                    <br/>
                    Компания-отправитель: ${wf.taskBean.externalCompany}.
                    Отправитель: ${wf.taskBean.externalAssignee}.
                    Резолюция к задаче: <c:out value="${wf.description}"/>.
                    <br />
                    Дата назначения задачи: <fmt:formatDate pattern="dd.MM.yyyy" value="${wf.assignDate}"/>.
                    <br />
                    Файлы, прикрепленные к задаче:
                    <c:forEach var = "taskFile" items="${wf.taskBean.filesSet}">
                        <a href="<c:url value="download.htm?fileID=${taskFile.uid}" />">${taskFile.fileName}</a>
                    </c:forEach>
                    <br />
                    <a href="<c:url value="roadmap.htm?workflowID=${wf.uid}" />">Просмотреть маршрут задачи</a>
                </div>
            </div>
        </div>
    </c:forEach>
</body>
</html>
