<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<title>Регистрация распорядительного документа</title>
</head>
<body>
    <div class="navigation">
        <%@ include file="/WEB-INF/jsp/includes/navigation.jsp" %>
    </div>
    <div class="empty">
        <form name="formSearch" method="post" action="documentRegisterWizard.htm">
            <table>
                <tr>
                    <td>Тип документа</td>
                    <td></td>
                </tr>
                <tr>
                    <td><label for="first">Приказ</label></td>
                    <td>
                        <input type="radio" name="documentType" id="first" value="1" checked/>
                    </td>
                </tr>
                <tr>
                    <td><label for="second">Распоряжение</label></td>
                    <td>
                        <input type="radio" name="documentType" id="second" value="2"/>
                    </td>
                </tr>
            </table>
            <input type="submit" value="Cancel" name="_cancel">
            <input type="submit" value="Далее >>" name="_target1"/>
        </form>
    </div>
</body>
</html>
