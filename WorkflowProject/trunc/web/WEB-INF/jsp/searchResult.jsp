<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<title>Результаты поиска</title>
</head>
<body>
    <c:forEach var="wf" items="${requestScope.workflowBeans}">
            <div>
                <div class="header green">
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
                        <!--
                        <a href="<c:url value="selectUsers.htm?workflowID=${wf.uid}" />">Передать задачу</a>
                        <a href="<c:url value="workflowManager.htm?workflowID=${wf.uid}" />">Управление задачей</a>
                        -->
                    </div>
                </div>
            </div>
    </c:forEach>
</body>
</html>