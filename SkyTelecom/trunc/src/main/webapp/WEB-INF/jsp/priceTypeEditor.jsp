<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <%@ include file="/WEB-INF/jsp/includes/includeHead.jsp" %>
        <title>Type Of Price List Editor</title>
    </head>
    <body>
        <div class="navigation">
            <%@ include file="/WEB-INF/jsp/includes/navigation.jsp" %>
        </div>
        <div class="empty">
            <table class="justTable">
                <tr class="odd">
                    <td width="80%">Types of price-list</td>
                    <td width="20%">Action</td>
                </tr>
                <c:forEach var="priceType" items="${priceTypes}" varStatus="status">
                    <c:choose>
                        <c:when test='${(status.index)%2 eq 0}'>
                            <c:set var="rowColor" value="even" scope="page"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="rowColor" value="odd" scope="page"/>
                        </c:otherwise>
                    </c:choose>
                    <tr class="${rowColor}">
                        <td>${priceType.name}</td>
                        <td><a href="priceTypeEditor.htm?priceTypeUid=${priceType.uid}">Edit</a></td>
                    </tr>
                </c:forEach>
            </table>
            <br>
            <form name="Form1" method="post" action="">
                <table bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
                    <tr>
                        <td colspan="3">Add new type</td>
                    </tr>
                    <tr>
                        <td>Enter the name of a price-list type</td>
                        <spring:bind path="priceTypeDto.name">
                            <td><input type="text" name="${status.expression}" value="${status.value}" /></td>
                            <td><font color="red">${status.errorMessage}</font></td>
                        </spring:bind>
                    </tr>
                    <tr>
                        <td>
                            <input type="submit" align="right" value="Save">
                        </td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                </table>
                <p><a href="customers.htm">Go back</a></p>
            </form>
        </div>
    </body>
</html>
