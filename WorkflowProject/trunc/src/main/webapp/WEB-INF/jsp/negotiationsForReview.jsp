<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="/WEB-INF/taglib/pagerlib.tld" prefix="pg" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<link type="text/css" rel="stylesheet" href="css/dhtmlgoodies_calendar.css?random=20051112" media="screen"/>
<script type="text/javascript" src="scripts/dhtmlgoodies_calendar.js?random=20090118"></script>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<title>Согласования, ожидающие Вашего решения</title>
</head>
<body>   
    <div class="navigation">
        <%@ include file="/WEB-INF/jsp/includes/navigation.jsp" %>
    </div>
    <div class="empty">
        <table width="100%" class="workflowManager">
            <tr>
                <td width="10%">Тип документа</td>
                <td width="10%">Согласовать до</td>
                <td width="40%">Описание</td>
                <td width="10%">Электронная копия документа</td>
                <td width="30%">Решение по документу</td>
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
                    <td width="5%">Документ согласован || Документ не согласован</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
