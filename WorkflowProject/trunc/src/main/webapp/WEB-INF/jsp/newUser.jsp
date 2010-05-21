<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Новый пользователь</title>
    </head>
    <body>
        <div class="navigation">
            <%@ include file="/WEB-INF/jsp/includes/navigation.jsp" %>
        </div>
        <div class="empty">
            <form name="Form1" method="post" action="${actionUrl}">
                <table bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
                    <tr>
                        <td>Сетевое имя</td>
                        <spring:bind path="userLogin.username">
                            <td><input type="text" name="${status.expression}" value="${status.value}" /></td>
                            <td><font color="red">${status.errorMessage}</font></td>
                        </spring:bind>
                    </tr>
                    <tr>
                        <td>Фамилия</td>
                        <spring:bind path="userLogin.lastName">
                            <td><input type="text" name="${status.expression}" value="${status.value}" /></td>
                            <td><font color="red">${status.errorMessage}</font></td>
                        </spring:bind>
                    </tr>
                    <tr>
                        <td>Имя</td>
                        <spring:bind path="userLogin.firstName">
                            <td><input type="text" name="${status.expression}" value="${status.value}" /></td>
                            <td><font color="red">${status.errorMessage}</font></td>
                        </spring:bind>
                    </tr>
                    <tr>
                        <td>Отчество</td>
                        <spring:bind path="userLogin.middleName">
                            <td><input type="text" name="${status.expression}" value="${status.value}" /></td>
                            <td><font color="red">${status.errorMessage}</font></td>
                        </spring:bind>
                    </tr>
                    <tr>
                        <td>Электронная почта</td>
                        <spring:bind path="userLogin.email">
                            <td><input type="text" name="${status.expression}" value="${status.value}" /></td>
                            <td><font color="red">${status.errorMessage}</font></td>
                        </spring:bind>
                    </tr>
                    <tr>
                        <td>
                            <input type="submit" align="right" value="Сохранить">
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                </table>
                <a href="index.htm">Назад</a>
            </form>
        </div>
    </body>
</html>
