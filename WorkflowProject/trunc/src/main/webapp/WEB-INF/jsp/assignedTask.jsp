<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<title>Система электронного документооборота ООО "Медсервис"</title>
<%@ taglib uri="/WEB-INF/taglib/pagerlib.tld" prefix="pg" %>
</head>
<body>
    <table>
        <tr>
            <td rowspan="2"><img src="images/logo.png" alt="Медсервис" align="left" vspace="5" hspace="5"/></td>
            <td height="30px"><c:out value="Здравствуйте,
                   ${sessionScope.initiator.lastName}
                   ${sessionScope.initiator.firstName}
                   ${sessionScope.initiator.middleName}
                   (${sessionScope.initiator.login})" />
            </td>
        </tr>
        <tr>
            <td colspan="3">
                <div>
                    <div class="box w300 red">
                        <div class="box-inner">
                            <a class="box" href="<c:url value="index.htm" />">Задачи, назначенные Вам</a>
                        </div>
                        <em class="ctl"><b>•</b></em><em class="ctr"><b>•</b></em><em class="cbl"><b>•</b></em><em class="cbr"><b>•</b></em>
                    </div>
                    <div class="box w300 blue">
                        <div class="box-inner">
                            <a class="box" href="<c:url value="assignedTask.htm" />">Задачи, назначенные Вами (${requestScope.count})</a>
                            <a class="box" href="<c:url value="assignedTask.htm?completed=true" />">Показать завершенные </a>
                        </div>
                        <em class="ctl"><b>•</b></em><em class="ctr"><b>•</b></em><em class="cbl"><b>•</b></em><em class="cbr"><b>•</b></em>
                    </div>
                    <div class="box w300 green">
                        <div class="box-inner">
                            <a class="box" href="<c:url value="completedTask.htm" />">Завершенные Вами задачи</a>
                        </div>
                        <em class="ctl"><b>•</b></em><em class="ctr"><b>•</b></em><em class="cbl"><b>•</b></em><em class="cbr"><b>•</b></em>
                    </div>
                </div>
            </td>
        </tr>
    </table>
    <div class="navigation">
        <%@ include file="/WEB-INF/jsp/includes/navigation.jsp" %>
    </div>
    <div class="empty">
        <pg:paging url="assignedTask.htm">
            <c:forEach var="wf" items="${requestScope.assigned}">
                <pg:item>
                    <div style="margin-bottom: 10px;">
                        <div class="header blue">
                            Задача № <c:out value="${wf.taskBean.internalNumber}"/>. <c:out value="${wf.taskBean.description}" escapeXml="false"/>
                        </div>
                        <div>
                            <!--Описание задачи: <br />-->
                            Компания: <b>${wf.taskBean.externalCompany}</b>.
                            Отправитель: <b>${wf.taskBean.externalAssignee}</b>
                            Входящий номер письма: <b>${wf.taskBean.incomingNumber}</b>.
                            Исходящий номер: <b>${wf.taskBean.externalNumber}</b>.<br />
                            Задача создана для: <b><c:out value="${wf.receiver.lastName} ${wf.receiver.firstName} ${wf.receiver.middleName}"/></b>
                            Резолюция к задаче: <b><u><c:out value="${wf.description}" escapeXml="false"/>.</u></b>
                            <br/><br/>
                            <div class="content">
                                <%@ include file="/WEB-INF/jsp/includes/contentInclude.jsp" %>
                                <a href="<c:url value="executionRequest.htm?workflowID=${wf.uid}" />">Запросить отчет</a>
                                <a href="<c:url value="roadmap.htm?workflowID=${wf.uid}" />">Просмотреть маршрут задачи</a>
                                <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_REG,ROLE_TOP">
                                    <a href="<c:url value="selectUsers.htm?workflowID=${wf.uid}" />">Передать задачу</a>
                                </sec:authorize>
                                <sec:authorize ifAnyGranted="ROLE_ADMIN">
                                    <a href="<c:url value="deleteTask.htm?workflowID=${wf.uid}" />">Удалить задачу и ее маршруты</a>
                                </sec:authorize>
                            </div>
                        </div>
                    </div>
                </pg:item>
            </c:forEach>
            <pg:index title="Страницы: ">
                <pg:page><%=thisPage%></pg:page>
            </pg:index>
        </pg:paging>
    </div>
</body>
</html>