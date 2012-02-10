<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<title>Выбор пользователей</title>
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
        window.location = "index.htm";
    }
</SCRIPT>
</head>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Выбор пользователя, для которого создается задача.</h2>
        ${requestScope.departmets}
        <form name="Form1" method="post" action="${actionUrl}">
            <div class="checklistbox" style="background:#f8f8ff">
                <table>
                    <c:forEach items="${requestScope.departments}" var="department" varStatus="status">
                        <tr>
                            <td colspan="4">
                                <b><c:out value="${department.departmentName}" /></b>
                            </td>
                        </tr>
                        <c:forEach items="${department.workflowUserBeanCollection}" var="user" varStatus="status">
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
                                <td><input TYPE="checkbox" name="checks" VALUE="${user.uid}" onClick="Enable();"></td>
                                <td><c:out value="${user.lastName}" /></td>
                                <td><c:out value="${user.firstName}" /></td>
                                <td><c:out value="${user.middleName}" /></td>
                            </tr>
                        </c:forEach>
                    </c:forEach>
                </table>
            </div>
            <p><button type="button" onclick="goToUrl()">Назад</button> || <input type="submit" disabled align="right" name="Button1" value="Продолжить >>"></p>
        </form>
    </body>
</html>
