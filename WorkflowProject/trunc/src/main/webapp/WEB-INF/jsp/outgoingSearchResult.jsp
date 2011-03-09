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
                Компания-получатель: <b>${omb.receiverCompany}.</b>
                Имя получателя: <b>${omb.receiverName}.</b>
                <table class="workflowManager">
                    <tr>
                        <td width="20%">Ответственный от Медсервис</td>
                        <td width="10%">Зарегистрировано</td>
                        <td width="15%">Ожидаемая дата ответа</td>
                        <td width="15%">Номер в "Documentum"</td>
                        <td width="20%">Идентификатор Primavera ID</td>
                        <td width="20%">Файлы, прикрепленные к письму:</td>
                    </tr>
                    <tr>
                        <td>${omb.workflowUserBean.lastName} ${omb.workflowUserBean.firstName} ${omb.workflowUserBean.middleName}</td>
                        <td><fmt:formatDate pattern="dd.MM.yyyy" value="${omb.outgoingDate}" /></td>
                        <td><fmt:formatDate pattern="dd.MM.yyyy" value="${omb.dueDate}" /></td>
                        <td>${omb.documentumNumber}</td>
                        <td><a href="primaveraReport.htm?primaveraUid=${omb.primaveraUid}">${omb.primaveraUid}</a></td>
                        <td>
                            <c:forEach var = "taskFile" items="${omb.outgoingFileBeanSet}">
                                <a href="<c:url value="getOutgoingFiles.htm?fileID=${taskFile.uid}" />">${taskFile.fileName}</a>
                            </c:forEach>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <br />
    </c:forEach>


</body>
</html>
