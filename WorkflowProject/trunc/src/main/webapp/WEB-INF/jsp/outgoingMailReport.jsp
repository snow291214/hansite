<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<title>Журнал исходящей корреспонденции</title>
</head>
<body>
    <p>
        <a href="index.htm">На главную</a> ||
        <a href="documentReports.htm">Журналы документов</a>
    </p>
    <H1>Журнал исходящей корреспонденции</H1>
    <TABLE WIDTH="100%" BORDER=1 CELLSPACING=0>
        <TR>
            <TD WIDTH="10%">Исходящий номер</TD>
            <TD WIDTH="10%">Дата</TD>
            <TD WIDTH="20%">Куда и кому адресован документ</TD>
            <TD WIDTH="40%">Краткое содержание</TD>
            <TD WIDTH="10%">Ответственный от Медсервис</TD>
            <TD WIDTH="10%">Электронная копия</TD>
        </TR>
        <TR>
            <TD align="center">1</TD>
            <TD align="center">2</TD>
            <TD align="center">3</TD>
            <TD align="center">4</TD>
            <TD align="center">5</TD>
            <TD align="center">6</TD>
        </TR>
        <c:forEach var="m" items="${requestScope.model}">
            <TR>
                <TD>${m.outgoingNumber}</TD>
                <TD>
                    <fmt:formatDate pattern="dd.MM.yyyy" value="${m.outgoingDate}"/>
                </TD>
                <TD>
                    ${m.receiverCompany}<BR />
                    ${m.receiverName}
                </TD>
                <TD>${m.description}</TD>
                <TD>
                    ${m.workflowUserBean.lastName}
                    ${m.workflowUserBean.firstName}
                    ${m.workflowUserBean.middleName}
                </TD>
                <TD>
                    <c:forEach var = "taskFile" items="${m.outgoingFileBeanSet}">
                        <a href="<c:url value="getOutgoingFiles.htm?fileID=${taskFile.uid}" />">${taskFile.fileName}</a>
                    </c:forEach>
                </TD>
            </TR>
        </c:forEach>
    </TABLE>
    <p>
        <a href="index.htm">На главную</a> ||
        <a href="documentReports.htm">Журналы документов</a>
    </p>
</body>
</html>
