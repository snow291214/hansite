<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="/WEB-INF/taglib/pagerlib.tld" prefix="pg" %>
<title>Система электронного документооборота ООО "Салаватгазонефтехимпроект"</title>
</head>
<body>
    <table>
        <tr>
            <td rowspan="2"><img src="images/logo.jpg" alt="СГНХП" align="left" vspace="5" hspace="5"/></td>
            <td height="30px"><c:out value="Здравствуйте,
                   ${sessionScope.initiator.lastName}
                   ${sessionScope.initiator.firstName}
                   ${sessionScope.initiator.middleName}
                   (${sessionScope.initiator.login}) " />
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <div>
                    <div class="box w300 red">
                        <div class="box-inner">
                            <a class="box" href="<c:url value="index.htm" />">Задачи, назначенные Вам (${requestScope.count})</a>
                        </div>
                        <em class="ctl"><b>•</b></em><em class="ctr"><b>•</b></em><em class="cbl"><b>•</b></em><em class="cbr"><b>•</b></em>
                    </div>

                    <div class="box w300 blue">
                        <div class="box-inner">
                            <a class="box" href="<c:url value="assignedTask.htm" />">Задачи, назначенные Вами</a>
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
        <pg:paging url="index.htm">
            <c:forEach var="wf" items="${requestScope.workflows}">
                <pg:item>
                    <div style="margin-bottom: 10px;">
                        <div class="header red">
                            Задача № <c:out value="${wf.taskBean.internalNumber}"/>. ${wf.taskBean.description}
                        </div>
                        <div>
                            Компания: <b>${wf.taskBean.externalCompany}</b>.
                            Отправитель: <b>${wf.taskBean.externalAssignee}</b>
                            Входящий номер письма: <b>${wf.taskBean.incomingNumber}</b>.
                            Исходящий номер: <b>${wf.taskBean.externalNumber}</b>.<br />
                            Задачу назначил: <b><c:out value="${wf.assignee.lastName} ${wf.assignee.firstName} ${wf.assignee.middleName}"/></b>.
                            Резолюция к задаче: <b><u><c:out value="${wf.description}" escapeXml="false"/></u></b>.
                            <br/><br/>
                            <div class="content">
                                <%@ include file="/WEB-INF/jsp/includes/contentInclude.jsp" %>
                                <a href="<c:url value="roadmap.htm?workflowID=${wf.uid}" />">Просмотреть маршрут задачи</a>
                                <a href="<c:url value="selectUsers.htm?workflowID=${wf.uid}" />">Передать задачу</a>
                                <a href="<c:url value="workflowManager.htm?workflowID=${wf.uid}" />">Управление задачей</a>
                                <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_REG,ROLE_TOP">
                                    <a href="<c:url value="extraDescription.htm?workflowID=${wf.uid}" />">Добавить резолюцию</a>
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