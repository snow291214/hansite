<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<link type="text/css" rel="stylesheet" href="css/dhtmlgoodies_calendar.css?random=20051112" media="screen"/>
<script type="text/javascript" src="scripts/dhtmlgoodies_calendar.js?random=20090118"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Регистрация исходящего письма</title>
</head>
<body>
    <form action="outgoingLetterWizard.htm" method="POST">
        <table bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
            <tr>
                <td>Исходящий номер:</td>
                <spring:bind path="outgoingMailDto.outgoingNumber">
                    <td><input type="text" name="${status.expression}" value="${status.value}" disabled /></td>
                    <td><font color="red">${status.errorMessage}</font></td>
                </spring:bind>
            </tr>
            <tr>
                <td>Номер в "Documentum":</td>
                <spring:bind path="outgoingMailDto.documentumNumber">
                    <td><input type="text" name="${status.expression}" value="${status.value}" /></td>
                    <td><font color="red">${status.errorMessage}</font></td>
                </spring:bind>
            </tr>
            <tr>
                <td>Идентификатор в СКП "Primavera"</td>
                <spring:bind path="outgoingMailDto.primaveraUid">
                    <td><input type="text" name="${status.expression}" value="${status.value}" /></td>
                    <td><font color="red">${status.errorMessage}</font></td>
                </spring:bind>
            </tr>
            <tr>
                <td>Краткое описание:</td>
                <spring:bind path="outgoingMailDto.description">
                    <td>
                        <textarea rows="8" cols="23" name="<c:out value="${status.expression}"/>">${status.value}</textarea>
                    </td>
                    <td><font color="red">${status.errorMessage}</font></td>
                </spring:bind>
            </tr>
            <tr>
                <td>Дата регистрации письма:</td>
                <spring:bind path="outgoingMailDto.outgoingDate">
                    <td>
                        <input type="text" name="${status.expression}" value="${status.value}" readonly>
                        <input type="button" value="Дата" onclick="displayCalendar(document.forms[0].outgoingDate,'dd.mm.yyyy',this)">
                    </td>
                    <td><font color="red">${status.errorMessage}</font></td>
                </spring:bind>
            </tr>
            <tr>
                <td>Ответ в срок до:</td>
                <spring:bind path="outgoingMailDto.dueDate">
                    <td>
                        <input type="text" name="${status.expression}" value="${status.value}" readonly>
                        <input type="button" value="Дата" onclick="displayCalendar(document.forms[0].dueDate,'dd.mm.yyyy',this)">
                    </td>
                    <td><font color="red">${status.errorMessage}</font></td>
                </spring:bind>
            </tr>
            <tr>
                <td>Получатель (компания):</td>
                <spring:bind path="outgoingMailDto.receiverCompany">
                    <td><input type="text" name="${status.expression}" value="${status.value}" /></td>
                    <td><font color="red">${status.errorMessage}</font></td>
                </spring:bind>
            </tr>
            <tr>
                <td>Получатель (Ф.И.О.):</td>
                <spring:bind path="outgoingMailDto.receiverName">
                    <td><input type="text" name="${status.expression}" value="${status.value}" /></td>
                    <td><font color="red">${status.errorMessage}</font></td>
                </spring:bind>
            </tr>
            <tr>
                <td>Ответственный:</td>
                <td>
                    <select name="combobox">
                        <c:forEach items="${requestScope.users}" var="user" varStatus="status">
                            <option value="${user.uid}">${user.lastName} ${user.firstName} ${user.middleName}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
        </table>
        <input type="submit" value="Отмена" name="_cancel">
        <input type="submit" value="Далее >>" name="_target1"/>
    </form>
</body>
</html>
