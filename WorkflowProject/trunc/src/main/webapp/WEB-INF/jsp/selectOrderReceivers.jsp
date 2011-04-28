<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<title>Выбор пользователей</title>
<SCRIPT LANGUAGE="JavaScript" type="text/javascript" >
    function Enable() {
        frm=document.forms[0]
        frm._target2.disabled=true;
        for(var i=0; i < frm.checks.length; i++){
            if(frm.checks[i].checked){
                frm._target2.disabled=false;
                break;
            }
        }
    }

    function goToUrl(){
        window.location = "index.htm";
    }
</SCRIPT>
</head>
<body>
    <h2>Ознакомить с документом следующих пользователей:</h2>
    <form name="Form1" method="post" action="">
        <div class="checklistbox" style="background:#f8f8ff">
            <table>
                <c:forEach items="${requestScope.users}" var="user" varStatus="status">
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
            </table>
        </div>
        <p>
            <input type="submit" value="Отмена" name="_cancel">
            <input type="submit" value="<< Назад" name="_target0"/>
            <input type="submit" value="Далее >>" name="_target2" disabled/>
        </p>
    </form>
</body>
</html>
