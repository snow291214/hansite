<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Возврат задания в работу</title>
    </head>
    <body>
        <p>Возврат задания в работу</p>
        <form name="Form1" method="post" action="">
            <table bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
                <tr>
                    <td>Объясните причину возврата в работу</td>
                    <spring:bind path="rejectWorkflow.description">
                        <td>
                            <textarea rows="8" cols="23" name="<c:out value="${status.expression}"/>">${status.value}</textarea>
                        </td>
                        <td><font color="red">${status.errorMessage}</font></td>
                    </spring:bind>
                </tr>
                <tr>
                    <td colspan="3">
                        <input type="submit" align="right" value="Сохранить">
                        <button type="button" onclick="javascript:history.back();">Назад</button>
                    </td>
                </tr>
                <tr>
                    <td colspan="3">
                        <spring:hasBindErrors name="rejectWorkflow">
                            <b>Возникли ошибки при регистрации</b>
                        </spring:hasBindErrors>
                    </td>
                </tr>

            </table>
        </form>
    </body>
</html>
