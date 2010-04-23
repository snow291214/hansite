<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<title>Управление задачей</title>
<script language="javascript" type="text/javascript">
    uid = 1
    function createNewInput(){
        uid ++;
        var input = document.createElement("input");
        input.type = "file";
        input.name = "files" + uid.toString();
        input.id = "f"+uid.toString()
        var elem = document.getElementById("container");
        var p = document.createElement("p");
        elem = elem.appendChild(p);
        var newText = document.createTextNode("Имя файла: ");
        elem.appendChild(newText);
        elem.appendChild(input);
    }
</script>
</head>

<body>
    <%@ include file="/WEB-INF/jsp/includes/roadmapInclude.jsp" %>
    <form name="Form1" method="post" action="${actionUrl}" enctype="multipart/form-data">
        <input type="hidden" name="workflowID" value="${workflowID}">
        <table>
            <tr>
                <td>Внутренний номер задачи:</td>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td>Состояние задачи:</td>
                <spring:bind path="workflowManager.stateUid">
                    <td>
                        <select name="${status.expression}" style="width : 200">
                            <c:choose>
                                <c:when test="${status.value eq 0}">
                                    <option value="0" selected>Не начата</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="0">Не начата</option>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${status.value eq 2}">
                                    <option value="2" selected>В работе</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="2" >В работе</option>
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${status.value eq 5}">
                                    <option value="5" selected>Сохранить для проверки</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="5">Сохранить для проверки</option>
                                </c:otherwise>
                            </c:choose>
                            <sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_TOP">
                                <c:choose>
                                    <c:when test="${status.value eq 3}">
                                        <option value="3" selected>Принято к сведению</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="3">Принято к сведению</option>
                                    </c:otherwise>
                                </c:choose>
                            </sec:authorize>
                        </select>
                    </td>
                    <td><font color="red">${status.errorMessage}</font></td>
                </spring:bind>
            </tr>
            <tr>
                <td>Записка к смене состояния задачи:</td>
                <spring:bind path="workflowManager.workflowNote">
                    <td>
                        <textarea rows="4" cols="22" name="<c:out value="${status.expression}"/>">${status.value}</textarea>
                    </td>
                    <td><font color="red">${status.errorMessage}</font></td>
                </spring:bind>
            </tr>
        </table>
        Вы можете прикрепить файл к смене состояния задачи.
        <div id="container" >
            <p>Имя файла: <input type="file" name="files" id="f1"/></p>
        </div>
        <a href="#" onclick="createNewInput()">Прикрепить еще один файл...</a>
        <p>
            <button type="button" onclick="javascript:document.location.href='index.htm'">На главную</button>
            <button type="button" onclick="javascript:history.back();"><< Назад</button><input type="submit" align="right" value="Сохранить">
        </p>
    </form>
</body>
</html>
