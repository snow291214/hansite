<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <%@ include file="/WEB-INF/jsp/includes/includeHead.jsp" %>
        <title>Customers View Page</title>
    </head>
    <body>
        <div class="navigation">
            <%@ include file="/WEB-INF/jsp/includes/navigation.jsp" %>
        </div>
        <div class="empty">
            <table class="justTable">
                <tr class="odd">
                    <td width="20%">Customer's name</td>
                    <td width="50%">Customer's prices</td>
                    <td width="30%">Actions</td>
                </tr>
                <c:forEach var="customer" items="${customers}" varStatus="status">
                    <c:choose>
                        <c:when test='${(status.index)%2 eq 0}'>
                            <c:set var="rowColor" value="even" scope="page"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="rowColor" value="odd" scope="page"/>
                        </c:otherwise>
                    </c:choose>
                    <tr class="${rowColor}">
                        <td>
                            ${customer.customerName}
                        </td>
                        <td>
                            <c:forEach var="customersPrices" items="${customer.customersPrices}" varStatus="status">
                                <a href="prices.htm?customersPricesUid=${customersPrices.uid}">${customersPrices.priceType.name}</a> ||
                                <a href="refreshPriceList.htm?customersPricesUid=${customersPrices.uid}">
                                    Refresh the price list
                                </a>
                                ||
                                <a href="priceEditor.htm?direct=true&customersPricesUid=${customersPrices.uid}">
                                    Change rates wizard
                                </a>
                                ||
                                <a href="downloadChangedPriceList.htm?customersPricesUid=${customersPrices.uid}">
                                    Download CSV price list
                                </a>
                                ||
                                <a href="downloadExcelPriceList.htm?customersPricesUid=${customersPrices.uid}">
                                    Download Excel price list
                                </a>
                                <br />
                            </c:forEach>
                        </td>
                        <td>
                            <a href="customerEditor.htm?customerUid=${customer.uid}">Edit</a> ||
                            <a href="priceListWizard.htm?customerUid=${customer.uid}">Create A Price List</a> ||
                            <sec:authorize ifAnyGranted="ROLE_ADMIN">
                                <a href="customerEditor.htm?action=delete&customerUid=${customer.uid}">Delete</a>
                            </sec:authorize>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <br/>
            <a href="customerEditor.htm">Add Customer</a>
        </div>
    </body>
</html>