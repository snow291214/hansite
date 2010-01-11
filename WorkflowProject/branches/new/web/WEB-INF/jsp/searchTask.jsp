<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
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
                    <spring:bind path="searchTask.taskInternalNumber">
                        <input type="text" name="${status.expression}" value="${status.value}"/>
                    </spring:bind>
                </td>
            </tr>
            <tr>
                <td><label for="byIncomingNumber">Поиск по входящему номеру</label></td>
                <td>
                    <input type="radio" name="searchType" id="byIncomingNumber" value="1" />
                </td>
                <td>
                    <spring:bind path="searchTask.taskIncomingNumber">
                        <input type="text" name="${status.expression}" value="${status.value}"/>
                    </spring:bind>
                </td>
            </tr>
            <tr>
                <td><label for="byAssigneeName">Отправитель (кто подписал письмо)</label></td>
                <td>
                    <input type="radio" name="searchType" id="byAssigneeName" value="2" />
                </td>
                <td>
                    <spring:bind path="searchTask.assigneeName">
                        <input type="text" name="${status.expression}" value="${status.value}"/>
                    </spring:bind>
                </td>
            </tr>
            <tr>
                <td><label for="byDescription">Поиск по резолюции к задаче</label></td>
                <td>
                    <input type="radio" name="searchType" id="byDescription" value="3" checked/>
                </td>
                <td>
                    <spring:bind path="searchTask.taskDescription">
                        <input type="text" name="${status.expression}" value="${status.value}"/>
                    </spring:bind>
                <td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>
                    <input type="button" onclick="javascript:history.back();" value="Назад"/>
                    <input type="submit" value="Поиск" />
                <td>
            </tr>
        </table>
    </form>
</body>
</html>
