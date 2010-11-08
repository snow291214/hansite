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
        <title>Delete Destinations Page</title>
        <SCRIPT LANGUAGE="JavaScript" type="text/javascript" >
            function Enable() {
                frm=document.forms[0]
                frm.Button1.disabled=true;
                for(var i=0; i < frm.checks.length; i++){
                    if(frm.checks[i].checked){
                        frm.Button1.disabled=false;
                        break;
                    }
                }
            }

            function goToUrl(){
                window.location = "customers.htm";
            }
        </SCRIPT>
    </head>
    <body>
        <div class="navigation">
            <%@ include file="/WEB-INF/jsp/includes/navigation.jsp" %>
        </div>
        <div class="empty">
            <c:set var="counter" value="1" scope="page" />
            <h3>Select destinations which you want to delete</h3>
            <form name="Form1" method="post" action="${actionUrl}">
                <div class="destinations" style="background:#f8f8ff">
                    <table>
                        <c:forEach items="${requestScope.destinations}" var="destination" varStatus="status">
                            <c:choose>
                                <c:when test="${b == true}">
                                    <tr bgcolor="#e2f1f9">
                                        <c:set var="b" scope="page" value="false" />
                                    </c:when>
                                    <c:otherwise>
                                    <tr>
                                        <c:set var="b" scope="page" value="true" />
                                    </c:otherwise>
                                </c:choose>
                                <td><input TYPE="checkbox" name="checks" VALUE="${destination}" onClick="Enable();" ID="${counter}"></td>
                                <td><label for="${counter}"><c:out value="${destination}" /></label></td>
                            
                            </tr>
                            <c:set var="counter" value="${counter+1}" scope="page" />
                        </c:forEach>
                    </table>
                </div>
                <p>
                    <button type="button" onclick="goToUrl()">Go back</button> ||
                    <input type="submit" disabled align="right" name="Button1" value="Delete" onclick="return confirm('Do you really want to delete the destination?')">
                </p>
            </form>

        </div>
    </body>
</html>
