<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <%@ include file="/WEB-INF/jsp/includes/includeHead.jsp" %>
        <title>Update current price-list</title>
    </head>
    <body>
        <input type="hidden" name="customersPricesUid" value="${customersPricesUid}" />
        <form:form commandName="fileUpload" action="" enctype="multipart/form-data">
            <div id="container" >
                <p>Choose a csv-file: <input type="file" name="files" id="f1"/></p>
            </div>
            <input type="submit"  value="Update"/>
        </form:form>
    </body>
</html>
