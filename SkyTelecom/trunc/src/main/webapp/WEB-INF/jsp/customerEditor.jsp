<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                            <td><input type="text" name="${status.expression}" value="${status.value}"  style="width : 250px"/></td>
                            <td><font color="red">${status.errorMessage}</font></td>
                        </spring:bind>
                    </tr>
                    <tr>
                        <td>Main Currency</td>
                        <spring:bind path="customerDto.currency">
                            <td>
                                <select name="${status.expression}" style="width : 250px">
                                    <c:choose>
                                        <c:when test="${status.value eq 'USD'}">
                                            <OPTION VALUE='USD' selected>USD</OPTION>
                                        </c:when>
                                        <c:otherwise>
                                            <OPTION VALUE='USD'>USD</OPTION>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${status.value eq 'EUR'}">
                                            <OPTION VALUE='EUR' selected>EUR</OPTION>
                                        </c:when>
                                        <c:otherwise>
                                            <OPTION VALUE='EUR'>EUR</OPTION>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                            </td>
                            <td><font color="red">${status.errorMessage}</font></td>
                        </spring:bind>
                    </tr>
                    <tr>
                        <td>Account Manager</td>
                        <spring:bind path="customerDto.contactPerson">
                            <!--
                            <td><input type="text" name="${status.expression}" value="${status.value}"  style="width : 250px"/></td>
                            <td><font color="red">${status.errorMessage}</font></td>
                            -->
                            <td>
                                <select name="${status.expression}" style="width : 250px">
                                    <c:choose>
                                        <c:when test="${status.value eq 'Elmira Tameeva'}">
                                            <OPTION VALUE='Elmira Tameeva' selected>Elmira Tameeva</OPTION>
                                        </c:when>
                                        <c:otherwise>
                                            <OPTION VALUE='Elmira Tameeva'>Elmira Tameeva</OPTION>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${status.value eq 'Irina Bondarchuk'}">
                                            <OPTION VALUE='Irina Bondarchuk' selected>Irina Bondarchuk</OPTION>
                                        </c:when>
                                        <c:otherwise>
                                            <OPTION VALUE='Irina Bondarchuk'>Irina Bondarchuk</OPTION>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${status.value eq 'Eugene Nisterenko'}">
                                            <OPTION VALUE='Eugene Nisterenko' selected>Eugene Nisterenko</OPTION>
                                        </c:when>
                                        <c:otherwise>
                                            <OPTION VALUE='Eugene Nisterenko'>Eugene Nisterenko</OPTION>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${status.value eq 'Alexander Sokotuhov'}">
                                            <OPTION VALUE='Alexander Sokotuhov' selected>Alexander Sokotuhov</OPTION>
                                        </c:when>
                                        <c:otherwise>
                                            <OPTION VALUE='Alexander Sokotuhov'>Alexander Sokotuhov</OPTION>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${status.value eq 'Viktor Mikheev'}">
                                            <OPTION VALUE='Viktor Mikheev' selected>Viktor Mikheev</OPTION>
                                        </c:when>
                                        <c:otherwise>
                                            <OPTION VALUE='Viktor Mikheev'>Viktor Mikheev</OPTION>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${status.value eq 'Gregory Veh'}">
                                            <OPTION VALUE='Gregory Veh' selected>Gregory Veh</OPTION>
                                        </c:when>
                                        <c:otherwise>
                                            <OPTION VALUE='Gregory Veh'>Gregory Veh</OPTION>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                            </td>
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
