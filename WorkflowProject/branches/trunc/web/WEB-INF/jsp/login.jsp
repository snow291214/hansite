<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<title>Вход в систему электронного документооборота</title>
</head>
<body>
    <form name="Form1" method="post" action="${actionUrl}">
        <table bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
            <tr>
                <td>Сетевое имя входа в Windows</td>
                <spring:bind path="userLogin.login">
                    <td><input type="text" name="${status.expression}" value="${status.value}"></td>
                    <td><font color="red">${status.errorMessage}</font></td>
                </spring:bind>
            </tr>
            <tr>
                <td>Пароль:</td>
                <spring:bind path="userLogin.password">
                    <td><input type="password" name="${status.expression}" value="${status.value}"></td>
                    <td><font color="red">${status.errorMessage}</font></td>
                </spring:bind>
            </tr>
            <tr>
                <td colspan="3">
                    <input type="submit" align="right" value="Вход в систему">
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    <spring:hasBindErrors name="userLogin">
                        <b>Возникли ошибки при регистрации</b>
                    </spring:hasBindErrors>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
