<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<title>Журнал входящей корреспонденции</title>
</head>
<body>
    <p>
        <a href="index.htm">На главную</a> ||
        <a href="documentReports.htm">Журналы документов</a>
    </p>
    <H1>Журнал входящей корреспонденции</H1>
    <TABLE WIDTH=100% BORDER=1 CELLSPACING=0>
        <TR>
            <TD WIDTH="10%" ROWSPAN=3>Входящий номер</TD>
            <TD WIDTH="20%" COLSPAN=3>Информация о входящем документе</TD>
            <TD WIDTH="10%" ROWSPAN=3>Краткое содержание</TD>
            <TD WIDTH="15%" ROWSPAN=3>Электронная копия</TD>
            <TD WIDTH="15%" ROWSPAN=3>Кому направлен</TD>
            <TD WIDTH="30%" ROWSPAN=3>Подпись получателя</TD>
        </TR>
        <TR>
            <TD COLSPAN=2>Исходящий</TD>
            <TD ROWSPAN=2>Откуда и от кого получен</TD>
        </TR>
        <TR>
            <TD>номер</TD>
            <TD>дата</TD>
        </TR>
        <TR>
            <TD>1</TD>
            <TD>2</TD>
            <TD>3</TD>
            <TD>4</TD>
            <TD>5</TD>
            <TD>6</TD>
            <TD>7</TD>
            <TD>8</TD>
        </TR>
        <c:forEach var="m" items="${requestScope.model}">
            <TR>
                <TD>${m.incomingNumber}</TD>
                <TD>${m.externalNumber}</TD>
                <TD><fmt:formatDate pattern="dd.MM.yyyy" value="${m.startDate}" /></TD>
                <TD>
                    ${m.externalCompany} <br />
                    ${m.externalAssignee}
                </TD>
                <TD>${m.description}</TD>
                <TD>
                    <c:forEach var="taskFile" items="${m.filesSet}">
                        <a href="<c:url value="download.htm?fileID=${taskFile.uid}" />">${taskFile.fileName}</a><br/>
                    </c:forEach>
                </TD>
                <TD>
                    <c:forEach var="wf" items="${m.workflowsSet}">
                        ${wf.receiver.lastName}<BR />
                    </c:forEach>
                </TD>
                <TD>
                    <c:forEach var="wf" items="${m.workflowsSet}">
                        ${wf.receiver.lastName}.<fmt:formatDate pattern="dd.MM.yyyy" value="${wf.assignDate}" /><BR />
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
