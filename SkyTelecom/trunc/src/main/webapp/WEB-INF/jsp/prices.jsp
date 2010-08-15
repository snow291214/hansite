<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/taglib/pagerlib.tld" prefix="pg" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <%@ include file="/WEB-INF/jsp/includes/includeHead.jsp" %>
        <title>Prices View Page</title>
    </head>
    <body>
        <div class="navigation">
            <%@ include file="/WEB-INF/jsp/includes/navigation.jsp" %>
        </div>
        <div class="empty">
            <pg:paging url="prices.htm?customersPricesUid=${requestScope.model[\"customersPricesUid\"]}" pageSize="1000">
                <table class="justTable">
                    <tr class="odd">
                        <td width="15%">Destination</td>
                        <td width="10%">Area Code</td>
                        <td width="10%">Rate/Min Peak</td>
                        <td width="10%">Rate/Min Off-Peak</td>
                        <td width="10%">Currency</td>
                        <td width="10%">QoS</td>
                        <td width="10%">Indicator</td>
                        <td width="10%">Valid From</td>
                        <td width="15%">Routing</td>
                    </tr>
                    <c:forEach var="price" items="${requestScope.model[\"prices\"]}" varStatus="status">
                        <pg:item>
                            <c:choose>
                                <c:when test='${(status.index)%2 eq 0}'>
                                    <c:set var="rowColor" value="even" scope="page"/>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="rowColor" value="odd" scope="page"/>
                                </c:otherwise>
                            </c:choose>
                            <tr class="${rowColor}">
                                <td>${price.destination}</td>
                                <td>${price.phoneCode}</td>
                                <td>${price.ratePeak}</td>
                                <td>${price.rateOffpeak}</td>
                                <td>${price.currency}</td>
                                <td>${price.qos}</td>
                                <td>${price.priceIndicator}</td>
                                <td><fmt:formatDate pattern="dd.MM.yyyy" value="${price.activationDate}"/></td>
                                <td>${price.routing}</td>
                            </tr>
                        </pg:item>
                    </c:forEach>
                </table>
                <p>
                <pg:index title="Pages: ">
                    <pg:page><%=thisPage%></pg:page>
                </pg:index>
                </p>
            </pg:paging>
        </div>
    </body>
</html>
