<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Справка по работе ${requestScope.arrayList[2]}</title>
</head>
<body>
    <h1>Справка по работе ${requestScope.arrayList[2]}</h1>
    <h3>Задачи</h3>
    <table class="workflowManager">
        <tr>
            <td width="5%">Компания</td>
            <td width="5%">Отправитель</td>
            <td width="15%">Входящий номер письма</td>
            <td width="10%">Исходящий номер</td>
            <td width="25%">Описание задачи</td>
            <td width="10%">Дата начала задачи</td>
            <td width="10%">Срок до</td>
            <td width="10%">Файлы</td>
            <td width="10%">Задача назначена</td>
        </tr>
        <c:forEach var="task" items="${requestScope.arrayList[0]}">
            <tr>
                <td>${task.externalCompany}</td>
                <td>${task.externalAssignee}</td>
                <td>${task.incomingNumber}</td>
                <td>${task.externalNumber}</td>
                <td>${task.description}</td>
                <td><fmt:formatDate pattern="dd.MM.yyyy" value="${task.startDate}"/></td>
                <td><fmt:formatDate pattern="dd.MM.yyyy" value="${task.dueDate}"/></td>
                <td>
                    <c:forEach var = "taskFile" items="${task.filesSet}">
                        <a href="<c:url value="download.htm?fileID=${taskFile.uid}" />">${taskFile.fileName}</a><br/>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var = "wf" items="${task.workflowsSet}">
                        <a href="<c:url value="roadmap.htm?workflowID=${wf.uid}" />">${wf.receiver.lastName}</a><br/>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </table>
    <h3>Исходящая почта</h3>
    <table class="workflowManager">
        <tr>
            <td width="10%">Компания</td>
            <td width="10%">Получатель</td>
            <td width="10%">Исходящий номер</td>
            <td width="10%">Ответственный</td>
            <td width="17%">Тема письма</td>
            <td width="10%">Дата отправки</td>
            <td width="13%">Ожидаемая дата ответа</td>
            <td width="20%">Файлы</td>
        </tr>
        <c:forEach var="omb" items="${requestScope.arrayList[1]}">
            <tr>
                <td>${omb.receiverCompany}</td>
                <td>${omb.receiverName}</td>
                <td>${omb.outgoingNumber}</td>
                <td>${omb.workflowUserBean.lastName}</td>
                <td>${omb.description}</td>
                <td><fmt:formatDate pattern="dd.MM.yyyy" value="${omb.outgoingDate}"/></td>
                <td><fmt:formatDate pattern="dd.MM.yyyy" value="${omb.dueDate}"/></td>
                <td>
                    <c:forEach var = "taskFile" items="${omb.outgoingFileBeanSet}">
                        <a href="<c:url value="getOutgoingFiles.htm?fileID=${taskFile.uid}" />">${taskFile.fileName}</a>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </table>
    <p><a href="<c:url value="javascript:history.back();" />">Назад</a> || <a href="<c:url value="index.htm" />">На главную</a></p>
</body>
</html>
