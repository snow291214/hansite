<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<title>Получение отчета о переписке по работе</title>

</head>
<body>
    <div class="navigation">
        <%@ include file="/WEB-INF/jsp/includes/navigation.jsp" %>
    </div>
    <div class="empty">
        <form action="" method="POST">
        <table>
            <tr>
                <td>Выберите идентификатор работы:</td>
                <td>
                    <select name="combobox">
                        <c:forEach items="${requestScope.primaveraUids}" var="primaveraUid" varStatus="status">
                            <option value="${primaveraUid}">${primaveraUid}</option>
                        </c:forEach>
                    </select>
                </td>
                <td><input type="submit" value="Создать отчет >>" /></td>
            <tr>
        </table>
            </form>
    </div>
</body>
</html>
