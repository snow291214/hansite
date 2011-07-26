<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="/WEB-INF/taglib/pagerlib.tld" prefix="pg" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<link type="text/css" rel="stylesheet" href="css/dhtmlgoodies_calendar.css?random=20051112" media="screen"/>
<script type="text/javascript" src="scripts/dhtmlgoodies_calendar.js?random=20090118"></script>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<title>Мои согласования</title>
</head>
<body>
    <div class="navigation">
        <%@ include file="/WEB-INF/jsp/includes/navigation.jsp" %>
    </div>
    <div class="empty">
        <pg:paging url="negotiations.htm">
            <table width="100%" class="workflowManager">
                <tr>
                    <td width="10%">Дата начала</td>
                    <td width="20%">Краткое описание</td>
                    <td width="10%">Сканы документов</td>
                    <td width="60%">Участники согласования</td>
                </tr>
                <c:forEach var="negotiation" items="${requestScope.negotiations}">
                    <pg:item>
                        <tr>
                            <td colspan="4">
                                <div class="header blue">
                                    Идентификатор согласования: ${negotiation.uid}
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td><fmt:formatDate pattern="dd.MM.yyyy HH:MM" value="${negotiation.startDate}"/></td>
                            <td>${negotiation.description}</td>
                            <td>
                                <c:forEach var="file" items="${negotiation.negotiationFileBeanCollection}">
                                    <a href="getNegotiationFile.htm?fileID=${file.uid}">${file.fileName}</a><br />
                                </c:forEach>
                            </td>
                            <td>
                                <table width="100%" class="workflowManager">
                                    <tr>
                                        <td width="30%">Согласующий</td>
                                        <td width="10%">Состояние</td>
                                        <td width="15%">Дата получения</td>
                                        <td width="15%">Дата завершения</td>
                                        <td width="30%">Замечания</td>
                                    </tr>
                                    <c:forEach var="conclusion" items="${negotiation.conclusionBeanCollection}">
                                        <tr>
                                            <td>
                                                ${conclusion.workflowUserBean.lastName}
                                                ${conclusion.workflowUserBean.firstName} 
                                                ${conclusion.workflowUserBean.middleName} 
                                            </td>
                                            <td>${conclusion.conclusionTypeBean.name}</td>
                                            <td><fmt:formatDate pattern="dd.MM.yyyy HH:MM" value="${conclusion.startDate}"/></td>
                                            <td><fmt:formatDate pattern="dd.MM.yyyy HH:MM" value="${conclusion.dateOfConclusion}"/></td>
                                            <td>${conclusion.description}</td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                    </pg:item>
                </c:forEach>
            </table>
            <pg:index title="Страницы: ">
                <pg:page><%=thisPage%></pg:page>
            </pg:index>
        </pg:paging>
    </div>
</body>
</html>
