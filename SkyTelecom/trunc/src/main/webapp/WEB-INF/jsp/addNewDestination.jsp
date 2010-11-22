<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <%@ include file="/WEB-INF/jsp/includes/includeHead.jsp" %>
        <link type="text/css" rel="stylesheet" href="css/dhtmlgoodies_calendar.css?random=20100719" media="screen"/>
        <script type="text/javascript" src="scripts/dhtmlgoodies_calendar.js?random=20100719"></script>
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
                        <td colspan="2">Customer Name: ${priceDto.customer.customerName}. Price: ${priceDto.currency}</td>
                    </tr>
                    <tr>
                        <td>Destination Name</td>
                        <spring:bind path="priceDto.destination">
                            <td><input type="text" name="${status.expression}" value="${status.value}"  style="width : 250px"/></td>
                            <td><font color="red">${status.errorMessage}</font></td>
                        </spring:bind>
                    </tr>
                    <tr>
                        <td>Currency</td>
                        <spring:bind path="priceDto.currency">
                            <td><input type="text" name="${status.expression}" value="${status.value}"  style="width : 250px" readonly/></td>
                            <td><font color="red">${status.errorMessage}</font></td>
                        </spring:bind>
                    </tr>
                    <tr>
                        <td>Rate</td>
                        <spring:bind path="priceDto.ratePeak">
                            <td><input type="text" name="${status.expression}" value="${status.value}"  style="width : 250px"/></td>
                            <td><font color="red">${status.errorMessage}</font></td>
                        </spring:bind>
                    </tr>
                    <tr>
                        <td>Activation Date</td>
                        <spring:bind path="priceDto.activationDate">
                            <td>
                                <input type="text" name="${status.expression}" value="${status.value}" readonly>
                                <input type="button" value="Date" onclick="displayCalendar(document.forms[0].activationDate,'dd.mm.yyyy',this)">
                            </td>
                            <td><font color="red">${status.errorMessage}</font></td>
                        </spring:bind>
                    </tr>
                    <tr>
                        <td>Phone Codes (comma-separated, e.g. 57,5710,570)</td>
                        <spring:bind path="priceDto.phoneCode">
                            <td><input type="text" name="${status.expression}" value="${status.value}"  style="width : 250px" /></td>
                            <td><font color="red">${status.errorMessage}</font></td>
                        </spring:bind>
                    </tr>
                    <tr>
                        <td>QoS</td>
                        <spring:bind path="priceDto.qos">
                            <td>
                                <select name="${status.expression}" style="width : 250px">
                                    <c:choose>
                                        <c:when test="${status.value eq ''}">
                                            <option value="" selected>-</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="">-</option>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${status.value eq 'Premium'}">
                                            <option value="Premium" selected>Premium</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="Premium" >Premium</option>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${status.value eq 'Premium, CLI'}">
                                            <option value="Premium, CLI" selected>Premium, CLI</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="Premium, CLI">Premium, CLI</option>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${status.value eq 'Direct'}">
                                            <option value="Direct" selected>Direct</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="Direct">Direct</option>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${status.value eq ' 0\\\60\\\60'}">
                                            <option value=" 0\60\60" selected> 0\60\60</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value=" 0\60\60"> 0\60\60</option>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                            </td>
                            <td><font color="red">${status.errorMessage}</font></td>
                        </spring:bind>
                    </tr>
                    <tr>
                        <td colspan="2" align="right">
                            <input type="submit" value="Add Destination" id="buttonAddDestination"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
