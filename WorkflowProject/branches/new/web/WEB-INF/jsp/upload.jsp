<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавление файлов к задаче</title>
        <script language="javascript">
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
        <form:form commandName="fileUpload" action="upload.htm" enctype="multipart/form-data">
            <div id="container" >
                <p>Имя файла: <input type="file" name="files" id="f1"/></p>
            </div>
            <input type="submit"  value="Сохранить"/>
        </form:form>
    </body>
</html>
