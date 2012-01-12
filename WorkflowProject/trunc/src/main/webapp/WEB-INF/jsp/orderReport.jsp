<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<title>Журнал регистрации ${requestScope.model["documentType"]}</title>
</head>
<body>
    <p>
        <a href="index.htm">На главную</a> ||
        <a href="documentReports.htm">Журналы документов</a>
    </p>
    <h2 align="center">Журнал регистрации ${requestScope.model["documentType"]} за ${requestScope.model["year"]} год</h2>
    <table  WIDTH="100%" BORDER="1" CELLSPACING="0" cellpadding="5" style="font-family: arial; font-size: small;">
        <tr>
            <td align="center" width="10%">Номер ${requestScope.model["documentTypeHeader"]}</td>
            <td align="center" width="10%">Дата регистрации</td>
            <td align="center" width="35%">Заголовок к тексту</td>
            <td align="center" width="20%">Исполнитель</td>
            <td align="center" width="20">Контроль исполнения</td>
            <td align="center" width="5">Электронная копия</td>
        </tr>
        <!--
        <tr>
            <td align="center">1</td>
            <td align="center">2</td>
            <td align="center">3</td>
            <td align="center">4</td>
            <td align="center">5</td>
            <td align="center">6</td>
        </tr>
        -->
        <c:forEach var="m" items="${requestScope.model['orders']}">
            <tr>
                <td>
                    ${m.documentNumber}
                    <c:if test="${m.documentPrefix != ''}">
                        /${m.documentPrefix}
                    </c:if>
                </td>
                <td><fmt:formatDate pattern="dd.MM.yyyy" value="${m.documentDate}" /></td>
                <td>${m.description}</td>
                <td>
                    ${m.contactPerson.lastName}
                    ${m.contactPerson.firstName}
                    ${m.contactPerson.middleName}
                </td>
                <td>
                    ${m.controlPerson.lastName}
                    ${m.controlPerson.firstName}
                    ${m.controlPerson.middleName}
                </td>
                <td>
                    <c:forEach var = "orderFile" items="${m.documentFileBeanSet}">
                        <a href="<c:url value="getDocumentFiles.htm?fileID=${orderFile.uid}" />">${orderFile.fileName}</a>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
