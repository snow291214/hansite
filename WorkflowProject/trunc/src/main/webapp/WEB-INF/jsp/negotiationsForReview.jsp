<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="/WEB-INF/taglib/pagerlib.tld" prefix="pg" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<link type="text/css" rel="stylesheet" href="css/dhtmlgoodies_calendar.css?random=20051112" media="screen"/>
<script type="text/javascript" src="scripts/dhtmlgoodies_calendar.js?random=20090118"></script>
<script type="text/javascript" src="scripts/jquery.min.js"></script>
<script type="text/javascript" src="scripts/popupWindow.js"></script>
<title>Согласования, ожидающие Вашего решения</title>
</head>
<body>
    <div id="popup_name" class="popup_block">
        <h2>Turtle Power</h2>
        <p>I <3 Turtles.</p>
    </div>
    <div class="navigation">
        <%@ include file="/WEB-INF/jsp/includes/navigation.jsp" %>
    </div>
    <div class="empty">
        <table width="100%" class="justTable">
            <tr class="odd">
                <td width="10%">Тип документа</td>
                <td width="10%">Согласовать до</td>
                <td width="40%">Описание</td>
                <td width="15%">Электронная копия документа</td>
                <td width="25%">Решение по документу</td>
            </tr>
            <c:forEach var="conclusion" items="${requestScope.conclusions}" varStatus="status">
                <c:choose>
                    <c:when test='${(status.index)%2 eq 0}'>
                        <c:set var="rowColor" value="even" scope="page"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="rowColor" value="odd" scope="page"/>
                    </c:otherwise>
                </c:choose>
                <tr class="${rowColor}">
                    <td width="5%">${conclusion.negotiationBean.negotiationTypeBean.name}</td>
                    <td width="5%"><fmt:formatDate pattern="dd.MM.yyyy" value="${conclusion.negotiationBean.dueDate}" /></td>
                    <td width="20%">${conclusion.negotiationBean.description}</td>
                    <td width="5%">
                        <c:forEach var="file" items="${conclusion.negotiationBean.negotiationFileBeanCollection}">
                            <a href="getNegotiationFile.htm?fileID=${file.uid}">${file.fileName}</a><br />
                        </c:forEach>
                    </td>
                    <td width="5%" class="${rowColor}">
                        <a href="conclusionApprove.htm?conclusionUid=${conclusion.uid}">Документ согласован</a> 
                        || 
                        <a href="conclusionReject.htm?conclusionUid=${conclusion.uid}">Документ не согласован</a>
                        <!--||
                        <a href="#?w=500" rel="popup_name" class="poplight">Тест</a>
                        -->
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
