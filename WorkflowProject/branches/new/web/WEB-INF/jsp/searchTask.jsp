<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<link type="text/css" rel="stylesheet" href="css/dhtmlgoodies_calendar.css?random=20051112" media="screen" />
<script type="text/javascript" src="scripts/dhtmlgoodies_calendar.js?random=20090118"></script>
<title>Поиск задачи</title>
</head>
<body>
    <form name="formSearch" method="post" action="searchTask.htm">
        <table border="0" cellspacing="0" cellpadding="5">
            <tr>
                <td rowspan="5"><img src="images/logo.jpg" alt="СГНХП" align="left" vspace="5" hspace="5"/></td>
                <td><label for="byInternalNumber">Поиск по внутреннему номеру задачи</label></td>
                <td>
                    <input type="radio" name="searchType" id="byInternalNumber" value="0" />
                </td>
                <td>
                    <label for="byInternalNumber">
                        <spring:bind path="searchTask.taskInternalNumber">
                            <input type="text" name="${status.expression}" value="${status.value}"/>
                        </spring:bind>
                    </label>
                </td>
            </tr>
            <tr>
                <td><label for="byIncomingNumber">Поиск по входящему номеру</label></td>
                <td>
                    <input type="radio" name="searchType" id="byIncomingNumber" value="1" />
                </td>
                <td>
                    <label for="byIncomingNumber">
                        <spring:bind path="searchTask.taskIncomingNumber">
                            <input type="text" name="${status.expression}" value="${status.value}"/>
                        </spring:bind>
                    </label>
                </td>
            </tr>
            <tr>
                <td><label for="byAssigneeName">Отправитель (кто подписал письмо)</label></td>
                <td>
                    <input type="radio" name="searchType" id="byAssigneeName" value="2" />
                </td>
                <td>
                    <label for="byAssigneeName">
                        <spring:bind path="searchTask.assigneeName">
                            <input type="text" name="${status.expression}" value="${status.value}"/>
                        </spring:bind>
                    </label>
                </td>
            </tr>
            <tr>
                <td><label for="byDescription">Поиск по резолюции к задаче</label></td>
                <td>
                    <input type="radio" name="searchType" id="byDescription" value="3" checked/>
                </td>
                <td>
                    <label for="byDescription">
                        <spring:bind path="searchTask.taskDescription">
                            <input type="text" name="${status.expression}" value="${status.value}"/>
                        </spring:bind>
                    </label>
                <td>
            </tr>
            <tr>
                <td><label for="byDate">Задачи поставленные вами за период</label></td>
                <td>
                    <input type="radio" name="searchType" id="byDate" value="4"/>
                </td>
                <td>
                    Период с:
                    <label for="byDate">
                        <spring:bind path="searchTask.startDate">
                            <input type="text" name="${status.expression}" value="${status.value}" readonly>
                            <input type="button" value="Дата" onclick="displayCalendar(document.forms[0].startDate,'dd.mm.yyyy',this)">
                            <font color="red">${status.errorMessage}</font>
                        </spring:bind>
                    </label>
                    по:
                    <label for="byDate">
                        <spring:bind path="searchTask.finishDate">
                            <input type="text" name="${status.expression}" value="${status.value}" readonly>
                            <input type="button" value="Дата" onclick="displayCalendar(document.forms[0].finishDate,'dd.mm.yyyy',this)">
                            <font color="red">${status.errorMessage}</font>
                        </spring:bind>
                    </label>
                <td>
            </tr>
            <tr>
                <td>
                    <input type="button" onclick="javascript:history.back();" value="Назад"/>
                    <input type="submit" value="Поиск" />
                <td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
        </table>
    </form>
</body>
</html>
