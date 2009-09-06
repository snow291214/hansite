<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<title>Работа с заданиями</title>
<link type="text/css" rel="stylesheet" href="css/dhtmlgoodies_calendar.css?random=20051112" media="screen"></link>
<script type="text/javascript" src="scripts/dhtmlgoodies_calendar.js?random=20090118"></script>
</head>
<body>
    <form name="Form1" method="post" action="${actionUrl}">
        <table bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
            <tr>
                <td>Внутренний номер:</td>
                <spring:bind path="registerTask.internalNumber">
                    <td><input type="text" name="${status.expression}" value="${status.value}" disabled="true"></td>
                    <td><font color="red">${status.errorMessage}</font></td>
                </spring:bind>
            </tr>
            <tr>
                <td>Входящий номер документа:</td>
                <spring:bind path="registerTask.externalNumber">
                    <td><input type="text" name="${status.expression}" value="${status.value}"></td>
                    <td><font color="red">${status.errorMessage}</font></td>
                </spring:bind>
            </tr>
            <tr>
                <td>Резолюция:</td>
                <spring:bind path="registerTask.description">
                    <td><input type="text" name="${status.expression}" value="${status.value}" /></td>
                    <td><font color="red">${status.errorMessage}</font></td>
                </spring:bind>
            </tr>
            <tr>
                <td>Дата регистрации задачи:</td>
                <spring:bind path="registerTask.startDate">
                    <td>
                        <input type="text" name="${status.expression}" value="${status.value}" readonly>
                        <input type="button" value="Дата" onclick="displayCalendar(document.forms[0].startDate,'dd.mm.yyyy',this)">
                    </td>
                    <td><font color="red">${status.errorMessage}</font></td>
                </spring:bind>
            </tr>
            <tr>
                <td>Задача имеет срок до:</td>
                <spring:bind path="registerTask.dueDate">
                    <td>
                        <input type="text" readonly name="${status.expression}" value="${status.value}">
                        <input type="button" value="Дата" onclick="displayCalendar(document.forms[0].dueDate,'dd.mm.yyyy',this)">
                    </td>
                    <td><font color="red">${status.errorMessage}</font></td>
                </spring:bind>
            </tr>
            <tr>
                <td colspan="2">
                    <p><input TYPE="checkbox" name="hasFiles" VALUE="true" checked />Добавить файлы к заданию?</p>
                </td>
                <td>
                    <input type="submit" align="right" value="Продолжить >>">
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    <spring:hasBindErrors name="registerTask">
                        <b>Возникли ошибки при регистрации</b>
                    </spring:hasBindErrors>
                </td>
            </tr>
        </table>
        <br/>
        <a href="index.htm">Назад</a>
    </form>
</body>
</html>
