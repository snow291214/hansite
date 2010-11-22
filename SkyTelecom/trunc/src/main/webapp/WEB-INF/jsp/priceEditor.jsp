<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" type="text/css" href="css/style.css" title="style" />
        <script language="javascript" type="text/javascript" src="scripts/date.js"></script>
        <script language="javascript" type="text/javascript" src="scripts/jquery-1.3.2.js"></script>
        <script language="javascript" type="text/javascript" src="scripts/jquery.numeric.js"></script>
        <link type="text/css" rel="stylesheet" href="css/dhtmlgoodies_calendar.css?random=20100719" media="screen"/>
        <script type="text/javascript" src="scripts/dhtmlgoodies_calendar.js?random=20100719"></script>
        <script type='text/javascript' src="scripts/priceEditor.js"></script>
        <title><fmt:message key="priceEditor.view.title"/></title>
    </head>
    <body>
        <div class="navigation">
            <%@ include file="/WEB-INF/jsp/includes/navigation.jsp" %>
        </div>
        <div class="empty">
            <c:set var="counter" value="0" scope="page" />
            <form name="Form1" method="post" action="">
                <input name="action" type="hidden" value="${action}" />
                <table bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
                    <tr>
                        <td>Choose a customer</td>
                        <td colspan="2">
                            <c:choose>
                                <c:when test="${requestScope.customerUid != null}">
                                    <select name="customerName" id="id_customers" style="width: 265px;">
                                        <option value="${requestScope.customerUid}">${requestScope.customerName}</option>
                                    </select>
                                    <a href="priceEditor.htm">
                                        <fmt:message key="priceEditor.view.chooseAnotherCustomer"/>
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <select name="customerName" id="id_customers" style="width: 265px;">
                                        <option value="-">Choose...</option>
                                        <c:forEach var="customer" items="${requestScope.customers}">
                                            <option value="${customer.uid}">${customer.customerName}</option>
                                        </c:forEach>
                                    </select>
                                    <div id="placeholder" style="position: absolute; z-index: 1;"></div>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td rowspan="12" style="vertical-align: top;">
                            <div class="checklistbox">
                                <table id="id_areacodes">
                                </table>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Choose a customer's price</td>
                        <td colspan="2" id="id_customersPrices_td">
                            <c:choose>
                                <c:when test="${requestScope.customerUid != null}">
                                    <select name="customersPricesUid" id="id_customersPricesUid" style="width: 265px;">
                                        <option value="${requestScope.customersPricesUid}">${requestScope.priceType}</option>
                                    </select>
                                </c:when>
                                <c:otherwise>
                                    <select name="customersPricesUid" id="id_customersPricesUid" style="width: 265px;">
                                        <option value="-">Choose customer's price...</option>
                                    </select>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <td id="id_destination_header_td">Choose a destination
                            <c:choose>
                                <c:when test="${requestScope.customersPricesUid != null}">
                                    (<a href="addNewDestination.htm?customersPricesUid=${requestScope.customersPricesUid}" id="id_addnew_href">Add new</a>)
                                </c:when>
                            </c:choose>
                        </td>
                        <td colspan="2" id="id_destination_td">
                            <c:choose>
                                <c:when test="${requestScope.customerUid != null}">
                                    <select name="destination" id="id_destinations" style="width: 265px;">
                                        <option value="-">Choose...</option>
                                        <c:forEach var="destination" items="${requestScope.destinations}">
                                            <option value="${destination}">${destination}</option>
                                        </c:forEach>
                                    </select>
                                </c:when>
                                <c:otherwise>
                                    <select name="destination" id="id_destinations" style="width: 265px;">
                                        <option value="-">Choose destination...</option>
                                    </select>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <td>Currency</td>
                        <td align="left">
                            <input type="text" name="currency" id="id_currency" readonly/>
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td>Actual date</td>
                        <td align="left">
                            <input type="text" name="activationDate" id="id_activationDate" value="${requestScope.pendingDate}" readonly/>
                            <input type="button" value="Date" onclick="displayCalendar(document.forms[0].activationDate,'dd.mm.yyyy',this)">
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td rowspan="2">Rate.</td>
                        <td>An old value</td>
                        <td align="left">
                            <input type="text" name="oldRate" id="id_oldRate" readonly />
                        </td>
                    </tr>
                    <tr>
                        <td align="left">Enter a new value</td>
                        <td>
                            <input type="text" name="newRate" id="id_newRate" onchange="enable();"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="left">Indicator</td>
                        <td colspan="2">
                            <input type="text" name="indicator" id="id_indicator" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td align="left">Remarks</td>
                        <td colspan="2">
                            <select name="qos" id="id_qos" style="width: 265px;">
                                <option value="">-</option>
                                <option value="Premium">Premium</option>
                                <option value="Premium, CLI">Premium,CLI</option>
                                <option value="Direct">Direct</option>
                                <option value=" 0\60\60"> 0\60\60</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <input type="checkbox" name="check1" id="id_check1">Routing<br>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <select id="SelectLeft" style="width: 240px;">
                            </select>
                        </td>
                        <td align="center">
                            <INPUT type="button" id="MoveRight" value="Add >>" name="button1" style="width: 120px"> <br />
                            <INPUT type="button" id="MoveLeft" value="<< Remove" name="button2" style="width: 120px"><br/>
                            <INPUT type="button" id="MoveLeft" value="Add New Route" name="button3" style="width: 120px" onclick='addComment(prompt ("Route?",""));'>
                        </td>
                        <td>
                            <select size="10" name="routes" id="id_SelectRight" style="width: 100%;" multiple="multiple">
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3" align="right">
                            <a href="customers.htm">Customers Page</a>
                            ||
                            <a href="downloadExcelPriceList.htm?customersPricesUid=${requestScope.customersPricesUid}">
                                Download Excel price list
                            </a>
                            ||
                            <a href="downloadExcelPriceList.htm?customersPricesUid=${requestScope.customersPricesUid}&changesOnly=yes">
                                Download changes of the price list
                            </a>
                            <input type="submit" class="btn" value="Change Rate" id="buttonChangeRate"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
