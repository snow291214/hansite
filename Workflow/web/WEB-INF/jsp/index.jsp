<%@ include file="/WEB-INF/jsp/include.jsp" %>
<title>Система электронного документооборота ООО "Салаватгазонефтехмипроект"</title>
</head>

<body>
    <c:out value="Здравствуйте,${sessionScope.initiator.login}
           (${sessionScope.initiator.lastName}
           ${sessionScope.initiator.firstName}
           ${sessionScope.initiator.middleName})" />
           
    <p><a href="<c:url value="selectUsers.htm"/>">Создать задачу</a></p>

</body>
</html>
