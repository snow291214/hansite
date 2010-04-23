<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<link type="text/css" rel="stylesheet" href="css/dhtmlgoodies_calendar.css?random=20051112" media="screen"/>
<script type="text/javascript" src="scripts/dhtmlgoodies_calendar.js?random=20090118"></script>
<title>Поиск исходящего письма</title>
</head>
<body>
    <div class="navigation">
        <%@ include file="/WEB-INF/jsp/includes/navigation.jsp" %>
    </div>
    <div class="empty">
        <form name="formSearch" method="post" action="">
            <table border="0" cellspacing="0" cellpadding="5">
                <tr>
                    <td><label for="byDescription">Поиск по теме письма</label></td>
                    <td>
                        <input type="radio" name="searchType" id="byDescription" value="0" checked/>
                    </td>
                    <td><label for="byDescription">
                            <spring:bind path="outgoingMailSearch.description">
                                <input type="text" name="${status.expression}" value="${status.value}"/>
                            </spring:bind>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td><label for="byOutgoingNumber">Поиск по исходящему номеру</label></td>
                    <td>
                        <input type="radio" name="searchType" id="byOutgoingNumber" value="1" />
                    </td>
                    <td><label for="byOutgoingNumber">
                            <spring:bind path="outgoingMailSearch.outgoingNumber">
                                <input type="text" name="${status.expression}" value="${status.value}"/>
                            </spring:bind>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td><label for="byDocumentumNumber">Поиск по номеру в "Documentum"</label></td>
                    <td>
                        <input type="radio" name="searchType" id="byDocumentumNumber" value="2" />
                    </td>
                    <td><label for="byDocumentumNumber">
                            <spring:bind path="outgoingMailSearch.documentumNumber">
                                <input type="text" name="${status.expression}" value="${status.value}"/>
                            </spring:bind>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td><label for="byPrimaveraId">Поиск по идентификатору Primavera ID</label></td>
                    <td>
                        <input type="radio" name="searchType" id="byPrimaveraId" value="7" />
                    </td>
                    <td><label for="byPrimaveraId">
                            <spring:bind path="outgoingMailSearch.primaveraUid">
                                <input type="text" name="${status.expression}" value="${status.value}"/>
                            </spring:bind>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td><label for="byReceiverName">Поиск по фамилии получателя письма</label></td>
                    <td>
                        <input type="radio" name="searchType" id="byReceiverName" value="3" />
                    </td>
                    <td><label for="byReceiverName">
                            <spring:bind path="outgoingMailSearch.receiverName">
                                <input type="text" name="${status.expression}" value="${status.value}"/>
                            </spring:bind>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td><label for="byReceiverCompany">Поиск по компании-получателю</label></td>
                    <td>
                        <input type="radio" name="searchType" id="byReceiverCompany" value="4" />
                    </td>
                    <td><label for="byReceiverCompany">
                            <spring:bind path="outgoingMailSearch.receiverCompany">
                                <input type="text" name="${status.expression}" value="${status.value}"/>
                            </spring:bind>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td><label for="byResponsible">Поиск по ответственному лицу</label></td>
                    <td>
                        <input type="radio" name="searchType" id="byResponsible" value="5" />
                    </td>
                    <td>
                        <label for="byResponsible">
                            <select name="combobox">
                                <c:forEach items="${requestScope.users}" var="user" varStatus="status">
                                    <option value="${user.uid}">${user.lastName} ${user.firstName} ${user.middleName}</option>
                                </c:forEach>
                            </select>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td><label for="byPeriodOfDate">По временному интервалу</label></td>
                    <td>
                        <input type="radio" name="searchType" id="byPeriodOfDate" value="6" />
                    </td>
                    <td>
                        Период с:
                        <label for="byPeriodOfDate">
                            <spring:bind path="outgoingMailSearch.outgoingDate">
                                <input style="width:80px" type="text" name="${status.expression}" value="${status.value}" readonly>
                                <input type="button" value="Дата" onclick="displayCalendar(document.forms[0].outgoingDate,'dd.mm.yyyy',this)">
                                <font color="red">${status.errorMessage}</font>
                            </spring:bind>
                        </label>
                        по:
                        <label for="byPeriodOfDate">
                            <spring:bind path="outgoingMailSearch.dueDate">
                                <input style="width:80px" type="text" name="${status.expression}" value="${status.value}" readonly>
                                <input type="button" value="Дата" onclick="displayCalendar(document.forms[0].dueDate,'dd.mm.yyyy',this)">
                                <font color="red">${status.errorMessage}</font>
                            </spring:bind>
                        </label>
                    </td>
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
    </div>
</body>
</html>
