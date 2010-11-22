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
        <c:if test="${param.result != null}"><script language="javascript">window.alert('Done!');</script></c:if>
        <div class="navigation">
            <%@ include file="/WEB-INF/jsp/includes/navigation.jsp" %>
        </div>
        <div class="empty">
            <table class="justTable">
                <tr class="odd">
                    <td width="20%">Customer's name</td>
                    <td width="10%">Main currency</td>
                    <td width="55%">Customer's prices</td>
                    <td width="15%">Actions</td>
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
                            ${customer.currency}
                        </td>
                        <td>
                            <table class="justTableSmall">
                                <c:forEach var="customersPrices" items="${customer.customersPrices}" varStatus="status">
                                    <tr>
                                        <td>
                                            <a href="prices.htm?customersPricesUid=${customersPrices.uid}">
                                                ${customersPrices.priceType.name} (View)
                                            </a>
                                        </td>
                                        <td>
                                            <a href="refreshPriceList.htm?customersPricesUid=${customersPrices.uid}">
                                                Update the price list
                                            </a>
                                        </td>
                                        <td>
                                            <a href="priceEditor.htm?direct=true&customersPricesUid=${customersPrices.uid}">
                                                Change rates wizard
                                            </a><br/>
                                            <a href="addNewDestination.htm?customersPricesUid=${customersPrices.uid}">
                                                Add New Destinations
                                            </a><br/>
                                            <a href="deleteDestinations.htm?customersPricesUid=${customersPrices.uid}">
                                                Delete Destinations
                                            </a>
                                        </td>
                                        <td>
                                            <a href="downloadChangedPriceList.htm?customersPricesUid=${customersPrices.uid}">
                                                Download CSV price list
                                            </a>
                                        </td>
                                        <td>
                                            <a href="downloadExcelPriceList.htm?customersPricesUid=${customersPrices.uid}">
                                                Download Excel price list
                                            </a><br/>
                                            <a href="downloadExcelPriceList.htm?customersPricesUid=${customersPrices.uid}&changesOnly=yes">
                                                Download changes of the price list
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </td>
                        <td>
                            <a href="customerEditor.htm?customerUid=${customer.uid}">Edit Customer</a> <br />
                            <a href="deleteCustomer.htm?customerUid=${customer.uid}" onclick="return confirm('Do you really want to delete the customer?')">Delete Customer</a><br />
                            <a href="priceListWizard.htm?customerUid=${customer.uid}">Create A Price List</a><br />
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <br/>
            <a href="customerEditor.htm">Add Customer</a>
        </div>
    </body>
</html>
