<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<link type="text/css" rel="stylesheet" href="css/dhtmlgoodies_calendar.css?random=20051112" media="screen"/>
<script type="text/javascript" src="scripts/dhtmlgoodies_calendar.js?random=20090118"></script>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<title>Новое согласование</title>
</head>
<body>
    <div class="navigation">
        <%@ include file="/WEB-INF/jsp/includes/navigation.jsp" %>
    </div>
    <div class="empty">
        <form:form method="POST" commandName="negotiationDto">
            <p>Выберите тип согласования</p>
            <c:forEach items="${requestScope.negotiationTypes}" var="negotiationType" varStatus="status">
                <p>
                    <form:radiobutton path="negotiationTypeUid" value="${negotiationType.uid}" id="${negotiationType.uid}"/>
                    <label for="${negotiationType.uid}">
                        ${negotiationType.name}
                    </label>
                </p>
            </c:forEach>
            <p>
                Согласовать до
                <spring:bind path="negotiationDto.dueDate">
                    <input type="text" name="${status.expression}" value="${status.value}" readonly>
                    <input type="button" value="Дата" onclick="displayCalendar(document.forms[0].dueDate,'dd.mm.yyyy',this)">
                    <font color="red">${status.errorMessage}</font> 
                </spring:bind>
            </p>
            <p>
                Краткое описание согласования<br>
                <spring:bind path="negotiationDto.description">
                    <textarea rows="8" cols="36" name="<c:out value="${status.expression}"/>">${status.value}</textarea>
                    <font color="red">${status.errorMessage}</font>
                </spring:bind>
            </p>
            <p>
                <input type="submit" value="Отмена" name="_cancel">
                <input type="submit" value="Далее >>" name="_target1"/>
            </p>
        </form:form>
    </div>
</body>
</html>
