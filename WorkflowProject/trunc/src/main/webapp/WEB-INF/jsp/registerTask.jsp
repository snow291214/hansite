<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<title>Работа с заданиями</title>
<link type="text/css" rel="stylesheet" href="css/dhtmlgoodies_calendar.css?random=20051112" media="screen"/>
<script type="text/javascript" src="scripts/dhtmlgoodies_calendar.js?random=20090118"></script>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery.autocomplete.css" />
<!--
<script type="text/javascript"
src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
-->
<script language="javascript" type="text/javascript" src="scripts/jquery-1.3.2.js"></script>
<script language="javascript" type="text/javascript" src="scripts/jquery.autocomplete.js"></script>

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
    <div class="navigation">
        <%@ include file="/WEB-INF/jsp/includes/navigation.jsp" %>
    </div>
    <div class="empty">
        <form name="Form1" method="post" action="${actionUrl}">
            <table bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
                <tr>
                    <td>Внутренний номер:</td>
                    <spring:bind path="registerTask.internalNumber">
                        <td><input type="text" name="${status.expression}" value="${status.value}" disabled /></td>
                        <td><font color="red">${status.errorMessage}</font></td>
                    </spring:bind>
                </tr>
                <tr>
                    <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_REG">
                        <td><input type="checkbox" id="incoming" onclick="requestdata()" />Входящее письмо</td>
                            <spring:bind path="registerTask.incomingNumber">
                            <td><input type="text" id="incomingNumber" name="${status.expression}" value="${status.value}" readonly /></td>
                            <td><font color="red">${status.errorMessage}</font>
                            </spring:bind>
                        </td>
                    </sec:authorize>
                </tr>
                <tr>
                    <td>Исходящий номер:</td>
                    <spring:bind path="registerTask.externalNumber">
                        <td><input type="text" name="${status.expression}" value="${status.value}" /></td>
                        <td><font color="red">${status.errorMessage}</font></td>
                    </spring:bind>
                </tr>
                <tr>
                    <td>Идентификатор работы в СКП "Primavera":</td>
                    <spring:bind path="registerTask.primaveraUid">
                        <td><input type="text" name="${status.expression}" value="${status.value}" /></td>
                        <td><font color="red">${status.errorMessage}</font></td>
                    </spring:bind>
                </tr>
                <tr>
                    <td>Компания:</td>
                    <spring:bind path="registerTask.externalCompany">
                        <td>
                            <input type="text" id="${status.expression}" name="${status.expression}" value="${status.value}">
                            <script>
                                $("#externalCompany").autocomplete("autocompleteCompanies.htm");
                            </script>

                        </td>
                        <td><font color="red">${status.errorMessage}</font></td>
                    </spring:bind>
                </tr>
                <tr>
                    <td>Ф.И.О. отправителя:</td>
                    <spring:bind path="registerTask.externalAssignee">
                        <td>
                            <input type="text" name="${status.expression}" id="${status.expression}" value="${status.value}">
                            <script>
                                $("#externalAssignee").autocomplete("autocompleteAssignees.htm");
                            </script>
                        </td>
                        <td><font color="red">${status.errorMessage}</font></td>
                    </spring:bind>
                </tr>
                <tr>
                    <td>Резолюция:</td>
                    <spring:bind path="registerTask.description">
                        <!--<td><input type="text" name="${status.expression}" value="${status.value}" /></td>-->
                        <td>
                            <textarea rows="8" cols="23" name="<c:out value="${status.expression}"/>">${status.value}</textarea>
                        </td>
                        <td><font color="red">${status.errorMessage}</font></td>
                    </spring:bind>
                </tr>
                <tr>
                    <td>Дата регистрации задачи:</td>
                    <spring:bind path="registerTask.startDate">
                        <td>
                            <input type="text" name="${status.expression}" value="${status.value}" readonly>
                            <input type="button" value="Дата" onclick="displayCalendar(document.forms[0].startDate,'dd.mm.yyyy',this)">
                        </td>
                        <td><font color="red">${status.errorMessage}</font></td>
                    </spring:bind>
                </tr>
                <tr>
                    <td>Задача имеет срок до:</td>
                    <spring:bind path="registerTask.dueDate">
                        <td>
                            <input type="text" readonly name="${status.expression}" value="${status.value}">
                            <input type="button" value="Дата" onclick="displayCalendar(document.forms[0].dueDate,'dd.mm.yyyy',this)">
                        </td>
                        <td><font color="red">${status.errorMessage}</font></td>
                    </spring:bind>
                </tr>
                <tr>
                    <td colspan="2">
                        <p><input TYPE="checkbox" name="hasFiles" VALUE="true" checked />Добавить файлы к заданию?</p>
                    </td>
                    <td>
                        <input type="submit" align="right" value="Продолжить >>">
                    </td>
                </tr>
                <tr>
                    <td colspan="3">
                        <spring:hasBindErrors name="registerTask">
                            <b>Возникли ошибки при регистрации</b>
                        </spring:hasBindErrors>
                    </td>
                </tr>
            </table>
            <br/>
            <a href="index.htm">Назад</a>
        </form>
    </div>
</body>
</html>
