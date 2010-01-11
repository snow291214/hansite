<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

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
                    <a class="box" href="<c:url value="assignedTask.htm" />">Задачи, назначенные Вами (${requestScope.count})</a>
                    <a class="box" href="<c:url value="assignedTask.htm?completed=true" />">Показать завершенные</a>
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
