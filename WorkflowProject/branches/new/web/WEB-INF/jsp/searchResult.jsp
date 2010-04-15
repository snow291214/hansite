<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<%@ taglib uri="/WEB-INF/taglib/pagerlib.tld" prefix="pg" %>
<title>Результаты поиска</title>
</head>
<body>
    <div>
        <a href="<c:url value="searchTask.htm" />">Новый поиск</a> ||
        <a href="<c:url value="index.htm" />">На главную</a>
    </div>
    <br>
    <pg:paging url="searchResult.htm">
        <c:forEach var="wf" items="${requestScope.workflowBeans}">
            <pg:item>
                <div style="margin-bottom: 10px;">
                    <div class="header green">
                        Задача № <c:out value="${wf.taskBean.internalNumber}"/>. ${wf.taskBean.description}"
                    </div>
                    <div>
                        Компания: <b>${wf.taskBean.externalCompany}</b>.
                        Отправитель: <b>${wf.taskBean.externalAssignee}</b>
                        Входящий номер письма: <b>${wf.taskBean.incomingNumber}</b>.
                        Исходящий номер: <b>${wf.taskBean.externalNumber}</b>.<br />
                        Задача создана для: <b><c:out value="${wf.receiver.lastName} ${wf.receiver.firstName} ${wf.receiver.middleName}"/></b>
                        Резолюция к задаче: <b><u><c:out value="${wf.description}"/>.</u></b>
                        <br/><br/>
                        <div class="content">
                            <%@ include file="/WEB-INF/jsp/includes/contentInclude.jsp" %>
                            <a href="<c:url value="roadmap.htm?workflowID=${wf.uid}" />">Просмотреть маршрут задачи</a>
                        </div>
                    </div>
                </div>
            </pg:item>
        </c:forEach>
        <pg:index title="Страницы: ">
            <pg:page><%=thisPage%></pg:page>
        </pg:index>
    </pg:paging>
</body>
</html>
