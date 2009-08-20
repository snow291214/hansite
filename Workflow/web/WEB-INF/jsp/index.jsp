<%@ include file="/WEB-INF/jsp/include.jsp" %>
<title>Система электронного документооборота ООО "Салаватгазонефтехмипроект"</title>
</head>

<body>
    <c:out value="Здравствуйте,${sessionScope.initiator.login}
           (${sessionScope.initiator.lastName}
           ${sessionScope.initiator.firstName}
           ${sessionScope.initiator.middleName})" />

    <p><a href="<c:url value="selectUsers.htm"/>">Создать задачу</a></p>

    <div class="box w300 red">

        <div class="box-inner">
            Задачи, назначенные Вам
        </div>
        <em class="ctl"><b>•</b></em><em class="ctr"><b>•</b></em><em class="cbl"><b>•</b></em><em class="cbr"><b>•</b></em>
    </div>

    <div class="box w300 blue">
        <div class="box-inner">
            <b>Задачи, назначенные Вами</b>
        </div>
        <em class="ctl"><b>•</b></em><em class="ctr"><b>•</b></em><em class="cbl"><b>•</b></em><em class="cbr"><b>•</b></em>
    </div>

</body>
</html>
