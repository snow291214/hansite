<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <%@ include file="/WEB-INF/jsp/includes/includeHead.jsp" %>
        <title>Create a new price list</title>
    </head>
    <body>
        <form:form commandName="fileUpload" action="" enctype="multipart/form-data">
            <table>
                <tr>
                    <td width="200px">Choose a type of price list</td>
                    <td>
                        <select name="priceTypeUid" style="width: 265px;">
                            <option value="-1">Choose a type of price</option>
                            <c:forEach var="priceType" items="${priceTypes}" varStatus="status">
                                <option value="${priceType.uid}">${priceType.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Choose a *.csv file for uploading</td>
                    <td>
                        <p><input type="file" name="files" id="f1"/></p>
                        <input type="submit"  value="Save"/>
                        <input type="button"  onclick="javascript:history.back();" value="Go back"/>
                    </td>
                </tr>
            </table>
        </form:form>
    </body>
</html>
