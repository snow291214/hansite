<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<title>Назначить задание коллегам</title>
</head>
<body>
    <form name="Form1" method="post" action="${actionUrl}">
        <table bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
            <tr>
                <td>Резолюция:</td>
                <spring:bind path="workflowManager.description">
                    <td>
                        <textarea rows="8" cols="23" name="<c:out value="${status.expression}"/>">${status.value}</textarea>
                    </td>
                    <td><font color="red">${status.errorMessage}</font></td>
                </spring:bind>
            </tr>
            <tr>
                <td colspan="3">
                    <input type="submit" value="<< Назад" name="_target0"/>
                        <input type="submit" value="Сохранить" name="_finish" />
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    <spring:hasBindErrors name="assignWorkflow">
                        <b>Возникли ошибки при регистрации</b>
                    </spring:hasBindErrors>
                </td>
            </tr>

        </table>
    </form>
</body>
</html>
