<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<title>Маршрут</title>
</head>
<body>
    <%@ include file="/WEB-INF/jsp/includes/roadmapInclude.jsp" %>
    <br />
    <form>
        <button type="button" onclick="javascript:document.location.href='index.htm'">На главную</button>
        <button type="button" onclick="javascript:history.back();">Назад</button>
    </form>
</body>
</html>
