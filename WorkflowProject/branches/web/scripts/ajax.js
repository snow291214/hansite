var req;

function loadXMLDoc(url) {
    if (window.XMLHttpRequest) {
        req = new XMLHttpRequest();
        req.onreadystatechange = processReqChange;
        req.open("GET", url, true);
        req.send(null);
    } else if (window.ActiveXObject) {
        req = new ActiveXObject("Microsoft.XMLHTTP");
        if (req) {
            req.onreadystatechange = processReqChange;
            req.open("GET", url, true);
            req.send();
        }
    }
}

function getElemText(node){
    return node.text || node.textContent || (function(node){
        var _result = "";
        if (node == null) {
            return _result;
        }
        var childrens = node.childNodes;
        var i = 0;
        while (i < childrens.length) {
            var child = childrens.item(i);
            switch (child.nodeType) {
                case 1: // ELEMENT_NODE
                case 5: // ENTITY_REFERENCE_NODE
                    _result += arguments.callee(child);
                    break;
                case 3: // TEXT_NODE
                case 2: // ATTRIBUTE_NODE
                case 4: // CDATA_SECTION_NODE
                    _result += child.nodeValue;
                    break;
                case 6: // ENTITY_NODE
                case 7: // PROCESSING_INSTRUCTION_NODE
                case 8: // COMMENT_NODE
                case 9: // DOCUMENT_NODE
                case 10: // DOCUMENT_TYPE_NODE
                case 11: // DOCUMENT_FRAGMENT_NODE
                case 12: // NOTATION_NODE
                    // skip
                    break;
            }
            i++;
        }
        return _result;
    }(node));
}

function processReqChange() {
    ab = window.setTimeout("req.abort();", 5000);
    if (req.readyState == 4) {
        clearTimeout(ab);
        //Если все пучком, то заполняем списки.
        if (req.status == 200) {
            var message = req.responseXML.getElementsByTagName("value");
            var tb = document.getElementById("incomingNumber");
            
            tb.setAttribute("value", message[0].firstChild.nodeValue);
            //alert(message[0].firstChild.nodeValue);
            //tb.value = message[0].textContent;
        } else {
            alert("Не удалось получить данные:\n" + req.statusText);
        }
    }
}

function stat(n)
{
    switch (n) {
        case 0:
            return "Hе инициализирован";
            break;
        case 1:
            return "Загрузка...";
            break;
        case 2:
            return "Загружено";
            break;
        case 3:
            return "В процессе...";
            break;
        case 4:
            return "Готово";
            break;
        default:
            return "Неизвестное состояние";
    }
}