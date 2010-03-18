<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
﻿<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link type="text/css" rel="stylesheet" href="css/dhtmlgoodies_calendar.css?random=20051112" media="screen"/>
<script type="text/javascript" src="scripts/dhtmlgoodies_calendar.js?random=20090118"></script>
<title>Регистрация распорядительных документов</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/ajax.js" ></script>
<script language="javascript" type="text/javascript">
    function requestdata()
    {
        var tb = document.getElementById("incoming");
        var incN = document.getElementById("incomingNumber");
        if (tb.checked){
            loadXMLDoc("<%=request.getContextPath()%>/ajax.htm");
        }else{
            incN.setAttribute("value", "")
        }
    }
</script>
</head>
<body>
    <form action="" method="POST">
        <table bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
            <tr>
                <td>Тип документа</td>
                <td>
                    
                </td>
            </tr>
            <tr>
                <td>Номер документа:</td>
                <spring:bind path="documentDto.documentNumber">
                    <td><input type="text" name="${status.expression}" value="${status.value}" disabled /></td>
                    <td><font color="red">${status.errorMessage}</font></td>
                </spring:bind>
            </tr>
            <tr>
                <td>Дата принятия:</td>
                <spring:bind path="documentDto.documentDate">
                    <td>
                        <input type="text" name="${status.expression}" value="${status.value}" readonly>
                        <input type="button" value="Дата" onclick="displayCalendar(document.forms[0].documentDate,'dd.mm.yyyy',this)">
                    </td>
                    <td><font color="red">${status.errorMessage}</font></td>
                </spring:bind>
            </tr>
            <tr>
                <td>Готовил:</td>
                <td>
                    <select name="combobox">
                        <c:forEach items="${requestScope.users}" var="user" varStatus="status">
                            <option value="${user.uid}">${user.lastName} ${user.firstName} ${user.middleName}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
        </table>
        <input type="submit" value="Cancel" name="_cancel">
        <input type="submit" value="Далее >>" name="_target2"/>
    </form>
</body>
</html>
