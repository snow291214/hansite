<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<title>Результаты поиска</title>
</head>
</head>
<body>
    <div>
        <a href="<c:url value="outgoingMailSearch.htm" />">Новый поиск</a> ||
        <a href="<c:url value="index.htm" />">На главную</a>
    </div>
    <br>
    <c:forEach var="omb" items="${requestScope.outgoingMailBeans}">
        <div>
            <div class="header green">
                Исходящий номер: ${omb.outgoingNumber}. ${omb.description}
            </div>
            <div class="content">
                Компания-получатель: ${omb.receiverCompany}.
                Имя получателя: ${omb.receiverName}. Ответственный от СГНХП:
                ${omb.workflowUserBean.lastName} ${omb.workflowUserBean.firstName} ${omb.workflowUserBean.middleName}<br />
                Зарегистрировано: <fmt:formatDate pattern="dd.MM.yyyy" value="${omb.outgoingDate}" />.
                Ожидаемая дата ответа: <fmt:formatDate pattern="dd.MM.yyyy" value="${omb.dueDate}" />
                Номер в "Documentum": ${omb.documentumNumber}<br />
                Файлы, прикрепленные к письму:
                <c:forEach var = "taskFile" items="${omb.outgoingFileBeanSet}">
                    <a href="<c:url value="getOutgoingFiles.htm?fileID=${taskFile.uid}" />">${taskFile.fileName}</a>
                </c:forEach>
            </div>
        </div>
        <br />
    </c:forEach>


</body>
</html>
