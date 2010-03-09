<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<%@ page import="org.springframework.security.ui.webapp.AuthenticationProcessingFilter" %>
<%@ page import="org.springframework.security.ui.AbstractProcessingFilter" %>
<%@ page import="org.springframework.security.AuthenticationException" %>
<title>Вход в систему электронного документооборота</title>
</head>
<body>
    <form action="j_spring_security_check">

        <table bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5"> 
            <tr> 
                <td><label for="j_username">Сетевое имя входа в Windows:</label></td> 

                <td><input type="text" name="j_username" id="j_username" <c:if test="${not empty param.login_error}">value='<%= session.getAttribute(AuthenticationProcessingFilter.SPRING_SECURITY_LAST_USERNAME_KEY)%>'</c:if>/></td>
                <td><font color="red"></font></td>

            </tr> 
            <tr> 
                <td><label for="j_password">Пароль:</label></td>

                <td><input type="password" name="j_password" id="j_password"/></td>
                <td><font color="red"></font></td>

            </tr> 
            <tr> 
                <td colspan="3">
                    <!--<input type='checkbox' name='_spring_security_remember_me'/> Remember me on this computer.-->
                    <input type="submit" align="right" value="Вход в систему"> 
                </td> 
            </tr> 
            <tr> 
                <td colspan="3"> 

                </td> 
            </tr> 
        </table>
    </form>
</body>
</html>
