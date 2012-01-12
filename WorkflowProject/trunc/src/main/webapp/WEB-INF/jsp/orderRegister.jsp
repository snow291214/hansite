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
        var dropdownIndex = document.getElementById('documentTypeUid').selectedIndex;
        var dropdownValue = document.getElementById('documentTypeUid')[dropdownIndex].value;
        loadXMLDoc("<%=request.getContextPath()%>/ajax.htm?documentTypeUid="+dropdownValue);
    }
</script>
</head>
<body>
    <div class="navigation">
        <%@ include file="/WEB-INF/jsp/includes/navigation.jsp" %>
    </div>
    <div class="empty">
        <form action="" method="POST">
            <table bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
                <tr>
                    <td>Тип документа</td>
                    <td>
                        <spring:bind path="documentDto.documentTypeUid">
                            <select name="${status.expression}" id="${status.expression}" style="width : 200px" onchange="requestdata();">
                                <c:choose>
                                    <c:when test="${status.value eq 0}">
                                        <option value="0" selected>Выберите тип документа</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="0">Выберите тип документа</option>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${status.value eq 1}">
                                        <option value="1" selected>Приказ</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="1" >Приказ</option>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${status.value eq 2}">
                                        <option value="2" selected>Распоряжение</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="2" >Распоряжение</option>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${status.value eq 3}">
                                        <option value="3" selected>Служебная записка</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="3" >Служебная записка</option>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${status.value eq 4}">
                                        <option value="4" selected>Приказы сторонних организаций</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="4" >Приказы сторонних организаций</option>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                        </spring:bind>
                    </td>
                </tr>
                <tr>
                    <td>Номер документа:</td>
                    <spring:bind path="documentDto.incomingNumber">
                        <td><input type="text" name="${status.expression}" id="incomingNumber" value="${status.value}" /></td>
                        <td><font color="red">${status.errorMessage}</font></td>
                    </spring:bind>
                    <td>Префикс документа:</td>
                    <spring:bind path="documentDto.documentPrefix">
                        <td><input type="text" name="${status.expression}" id="documentPrefix" value="${status.value}" /></td>
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
                        <select name="contactPerson">
                            <c:forEach items="${requestScope.users}" var="user" varStatus="status">
                                <option value="${user.uid}">${user.lastName} ${user.firstName} ${user.middleName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Контроль над исполнением возложить на:</td>
                    <td>
                        <select name="controlPerson">
                            <c:forEach items="${requestScope.users}" var="user" varStatus="status">
                                <option value="${user.uid}">${user.lastName} ${user.firstName} ${user.middleName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Тема распорядительного документа</td>
                    <spring:bind path="documentDto.description">
                        <td>
                            <textarea rows="8" cols="28" name="<c:out value="${status.expression}"/>">${status.value}</textarea>
                        </td>
                        <td><font color="red">${status.errorMessage}</font></td>
                    </spring:bind>
                </tr>
            </table>
            <input type="submit" value="Отмена" name="_cancel">
            <input type="submit" value="Далее >>" name="_target1"/>
        </form>
    </div>
</body>
</html>
