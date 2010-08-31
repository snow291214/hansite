<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <%@ include file="/WEB-INF/jsp/includes/includeHead.jsp" %>
        <title>Customer Editor</title>
    </head>
    <body>
        <div class="navigation">
            <%@ include file="/WEB-INF/jsp/includes/navigation.jsp" %>
        </div>
        <div class="empty">
            <form name="Form1" method="post" action="">
                <table bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
                    <tr>
                        <td>Customer Name</td>
                        <spring:bind path="customerDto.customerName">
                            <td><input type="text" name="${status.expression}" value="${status.value}" /></td>
                            <td><font color="red">${status.errorMessage}</font></td>
                        </spring:bind>
                    </tr>
                    <tr>
                        <td>Customer ID</td>
                        <spring:bind path="customerDto.customerId">
                            <td><input type="text" name="${status.expression}" value="${status.value}" /></td>
                            <td><font color="red">${status.errorMessage}</font></td>
                        </spring:bind>
                    </tr>
                    <tr>
                        <td>Currency</td>
                        <spring:bind path="customerDto.currency">
                            <td><input type="text" name="${status.expression}" value="${status.value}" /></td>
                            <td><font color="red">${status.errorMessage}</font></td>
                        </spring:bind>
                    </tr>
                    <tr>
                        <td>Contact Person</td>
                        <spring:bind path="customerDto.contactPerson">
                            <td><input type="text" name="${status.expression}" value="${status.value}" /></td>
                            <td><font color="red">${status.errorMessage}</font></td>
                        </spring:bind>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <spring:bind path="customerDto.email">
                            <td><input type="text" name="${status.expression}" value="${status.value}" /></td>
                            <td><font color="red">${status.errorMessage}</font></td>
                        </spring:bind>
                    </tr>
                    <tr>
                        <td>Hidden Email</td>
                        <spring:bind path="customerDto.hiddenEmail">
                            <td><input type="text" name="${status.expression}" value="${status.value}" /></td>
                            <td><font color="red">${status.errorMessage}</font></td>
                        </spring:bind>
                    </tr>
                    <tr>
                        <td>Email Subject</td>
                        <spring:bind path="customerDto.emailSubject">
                            <td><input type="text" name="${status.expression}" value="${status.value}" /></td>
                            <td><font color="red">${status.errorMessage}</font></td>
                        </spring:bind>
                    </tr>
                    <tr>
                        <td>Sender</td>
                        <spring:bind path="customerDto.sender">
                            <td><input type="text" name="${status.expression}" value="${status.value}" /></td>
                            <td><font color="red">${status.errorMessage}</font></td>
                        </spring:bind>
                    </tr>
                    <tr>
                        <td>
                            <input type="submit" align="right" value="Save">
                            <input type="button"  onclick="javascript:history.back();" value="Go back"/>
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
