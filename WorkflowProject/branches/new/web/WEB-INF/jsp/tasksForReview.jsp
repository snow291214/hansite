<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<title>Задачи, ожидающие проверки и завершения</title>
</head>
<body>
    <div>
        <a href="<c:url value="index.htm" />">На главную</a>
    </div>
    <br>
    <c:if test="${fn:length(requestScope.workflowBeans) == 0}">
        <p>
            Нет задач, ожидающих завершения. <a href="index.htm">Перейти на главную страницу.</a>
        </p>
    </c:if>
    <c:forEach var="wf" items="${requestScope.workflowBeans}">
        <div style="margin-bottom: 10px;">
            <div class="header green">
                Задача № <c:out value="${wf.taskBean.internalNumber}"/>. <c:out value="${wf.taskBean.description}"/>
            </div>
            <div>
                <u>
                    ${wf.assignee.lastName}
                    ${wf.assignee.firstName}
                    ${wf.assignee.middleName}
                    ==>
                    ${wf.receiver.lastName}
                    ${wf.receiver.firstName}
                    ${wf.receiver.middleName}
                </u>
                <br/>
                Компания-отправитель: ${wf.taskBean.externalCompany}.
                Отправитель: ${wf.taskBean.externalAssignee}.
                Резолюция к задаче: <c:out value="${wf.description}"/>.
                <br /><br />
                <div class="content">
                    <%@ include file="/WEB-INF/jsp/includes/contentInclude.jsp" %>
                    <p>
                        <a href="<c:url value="index.htm" />">На главную</a>
                        <a href="<c:url value="roadmap.htm?workflowID=${wf.uid}" />">Просмотреть маршрут задачи</a> ||
                        <a href="<c:url value="rejectWorkflow.htm?workflowID=${wf.uid}" />">Вернуть в работу</a> ||
                        <a href="<c:url value="approveWorkflow.htm?workflowID=${wf.uid}" />">Завершить задачу</a> || 
                        <a href="<c:url value="approveAndAssignWorkflow.htm?workflowID=${wf.uid}" />">Подтвердить выполнение и передать другому пользователю</a>
                    </p>
                </div>
            </div>
        </div>
    </c:forEach>
</body>
</html>
