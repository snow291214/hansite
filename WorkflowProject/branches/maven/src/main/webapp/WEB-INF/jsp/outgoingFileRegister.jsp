<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/jsp/includes/include.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Добавление файлов к письму</title>
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

<a href="#" onclick="createNewInput()">Добавить файл</a>
<form action="outgoingLetterWizard.htm" method="POST" enctype="multipart/form-data">
    <div id="container" >
        <p>Имя файла: <input type="file" name="files" id="f1"/></p>
    </div>
    <input type="submit" value="Cancel" name="_cancel">
    <input type="submit" value="<< Back" name="_target0">
    <input type="submit" value="Finish" name="_finish" />
</form>
</body>
</html>
