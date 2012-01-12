<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<title>Поиск документации</title>
<SCRIPT TYPE="text/javascript">
    <!--
    // copyright 1999 Idocs, Inc. http://www.idocs.com
    // Distribute this script freely but keep this notice in place
    function numbersonly(myfield, e, dec)
    {
        var key;
        var keychar;

        if (window.event)
            key = window.event.keyCode;
        else if (e)
            key = e.which;
        else
            return true;
        keychar = String.fromCharCode(key);

        // control keys
        if ((key==null) || (key==0) || (key==8) ||
            (key==9) || (key==13) || (key==27) )
            return true;

        // numbers
        else if ((("0123456789").indexOf(keychar) > -1))
            return true;

        // decimal point jump
        else if (dec && (keychar == "."))
        {
            myfield.form.elements[dec].focus();
            return false;
        }
        else
            return false;
    }

    //-->
</SCRIPT>
</head>
<body>
    <div class="navigation">
        <%@ include file="/WEB-INF/jsp/includes/navigation.jsp" %>
    </div>
    <div class="empty">
        <form name="formSearch" method="post" action="">
            <table>
                <tr>
                    <td><label for="incomingMail">Сформировать журнал документов за год:</label></td>
                    <td>
                        <spring:bind path="documentReportDto.reportYear">
                            <input type="text" name="${status.expression}" value="${status.value}"
                                   SIZE=5 MAXLENGTH=5
                                   onKeyPress="return numbersonly(this, event)"/>
                        </spring:bind>
                    </td>
                </tr>
                <tr>
                    <td><label for="incomingMail">Журнал входящих писем</label></td>
                    <td>
                        <input type="radio" name="reportType" id="incomingMail" value="0" checked/>
                    </td>
                </tr>
                <tr>
                    <td><label for="outgoingMail">Журнал исходящих писем</label></td>
                    <td>
                        <input type="radio" name="reportType" id="outgoingMail" value="1"/>
                    </td>
                </tr>
                <tr>
                    <td><label for="order">Журнал приказов</label></td>
                    <td>
                        <input type="radio" name="reportType" id="order" value="2"/>
                    </td>
                </tr>
                <tr>
                    <td><label for="order2">Журнал распоряжений</label></td>
                    <td>
                        <input type="radio" name="reportType" id="order2" value="3"/>
                    </td>
                </tr>
                <tr>
                    <td><label for="order3">Журнал служебных записок</label></td>
                    <td>
                        <input type="radio" name="reportType" id="order3" value="4"/>
                    </td>
                </tr>
                <tr>
                    <td><label for="order4">Журнал приказов сторонних организаций</label></td>
                    <td>
                        <input type="radio" name="reportType" id="order4" value="5"/>
                    </td>
                </tr>
            </table>
            <input type="button" onclick="javascript:history.back();" value="Назад"/>
            <input type="submit" value="Сформировать" />
        </form>
    </div>
</body>
</html>
