<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<title>Поиск документации</title>
</head>
<body>
    <form name="formSearch" method="post">
        <table>
            <tr>
                <td><label for="incomingMail">Сформировать журнал документов за год:</label></td>
                <td>
                    <spring:bind path="documentReportDto.reportYear">
                        <input type="text" name="${status.expression}" value="${status.value}"/>
                    </spring:bind>
                </td>
            </tr>
            <tr>
                <td><label for="incomingMail">Журнал входящих писем</label></td>
                <td>
                    <input type="radio" name="reportType" id="incomingMail" value="0" checked/>
                </td>
            </tr>
            <tr>
                <td><label for="outgoingMail">Журнал исходящих писем</label></td>
                <td>
                    <input type="radio" name="reportType" id="outgoingMail" value="1"/>
                </td>
            </tr>
        </table>
        <input type="submit" value="Сформировать" />
    </form>
</body>
</html>
