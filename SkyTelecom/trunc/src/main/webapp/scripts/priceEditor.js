function clear(){
    $('#id_destinations option').remove();
    //$('#id_customersPricesUid option').remove();
    $('#id_oldRate').val("");
    $('#id_newRate').val("");
    $('#id_activationDate').val("");
    $('#id_currency').val("");
    $('#id_areacodes').children('tbody').remove();
    $('#id_indicator').val("");
}

function clear1(){
    $('#id_destination_remove_link').remove();
    $('#id_oldRate').val("");
    $('#id_newRate').val("");
    $('#id_activationDate').val("");
    $('#id_currency').val("");
    $('#id_areacodes').children('tbody').remove();
    $('#id_indicator').val("");
}

function enable() {
    $('#buttonChangeRate').attr('disabled', false);
}

function addComment(comment){
    if((comment!=null)&&(comment!=""))
        $("#id_SelectRight").attr('selectedIndex', '-1');

    $('#id_SelectRight').append('<option value="' + comment + '">' + comment + '</option>');
}


function showIndicator(){
    $('#placeholder').html('<img alt="Ajax Loader"  src="/images/ajax_loader.gif"/>');
}

function hideIndicator(){
    $('#placeholder').html('');
}

$(function(){
    $('#id_customers').change(function() {
        clear();
        showIndicator();
        $('#id_customersPricesUid option').remove();
        $.post('ajax.htm',{
            'customerUid':$(this).val(),
            'requestType':0
        }, function(xml){
            $('#id_customersPricesUid').append('<option value="-">Choose...</option>');
            $(xml).find('PriceType').each(function(){
                $('#id_customersPricesUid').append('<option value="' + $(this).find("Uid").text() + '">' + $(this).find("Name").text() + '</option>');
            });
        });
        hideIndicator();
    });
});

$(function(){
    $('#id_customersPricesUid').change(function() {
        clear();
        showIndicator();
        //alert ($(this).val());
        $.post('ajax.htm',{
            'customersPricesUid':$(this).val(),
            'requestType':1
        }, function(xml){
            $('#id_addnew_href').attr('href', 'addNewDestination.htm?customersPricesUid='+
                $('#id_customersPricesUid').val());
            $('#id_destinations').append('<option value="-">Choose...</option>');
            $(xml).find('Destination').each(function(){
                $('#id_destinations').append('<option value="' + $(this).text() + '">' + $(this).text() + '</option>');
            });
            $('#id_destination_header_td').append('(<a href="addNewDestination.htm?customersPricesUid='+
                $('#id_customersPricesUid').val()+'" id="id_addnew_href">Add new</a>)');
        });
        hideIndicator();
    });
});

$(function(){
    $('#id_destinations').change(function() {
        if($(this).val() == "-"){
            clear1();
            return;
        }
        showIndicator();
        $.post('ajax.htm',{
            'destinationName':$(this).val(),
            'customersPricesUid':$('#id_customersPricesUid').val(),
            'requestType':2
        }, function(xml){
            $(xml).find('Value').each(function(){
                $('#id_oldRate').val($(this).text());
            });
            $(xml).find('ActivationDate').each(function(){
                $('#id_activationDate').val($(this).text());
            });
            $(xml).find('Currency').each(function(){
                $('#id_currency').val($(this).text());
            });
            $('#id_areacodes').children('tbody').remove();
            $('#id_areacodes').append("<tr><td>Area Codes Count: "+$(xml).find('AreaCode').size()+"</td></tr>");
            counter = 1;
            $(xml).find('AreaCode').each(function(){
                $('#id_areacodes').append("<tr><td>"+counter+": "+ $(this).text()+"</td></tr>");
                counter++;
            });
            $('#id_destination_remove_link').remove();
            $('#id_destination_td').append('<a href="deleteCurrentDestination.htm?customersPricesUid='+
                $('#id_customersPricesUid').val()+ '&destinationName='+$('#id_destinations').val()
                +'" id="id_destination_remove_link" onclick="return confirm(\'Do you really want to delete the destination?\')">Delete current destination</a>');
        });
        hideIndicator();
    });
});

$(function(){
    $('#id_check1').change(function () {
        if ($(this).attr("checked")) {
            $.post('ajax.htm',{
                'requestType':3
            }, function(xml){
                $(xml).find('Provider').each(function(){
                    $('#SelectLeft').append('<option value="' + $(this).text() + '">' + $(this).text() + '</option>');
                });
            });
            return;
        }
        $("#SelectLeft").empty();
    });
});

$(function() {
    $("#MoveRight,#MoveLeft").click(function(event) {
        var id = $(event.target).attr("id");
        var selectFrom = id == "MoveRight" ? "#SelectLeft" : "#id_SelectRight";
        var moveTo = id == "MoveRight" ? "#id_SelectRight" : "#SelectLeft";

        var selectedItems = $(selectFrom + " :selected");
        var output = [];
        $.each(selectedItems, function(key, e) {
            output.push('<option value="' + e.value + '">' + e.text + '</option>');
        });

        $(moveTo).append(output.join(""));
        $(moveTo).each(function(){
            $("#id_SelectRight option").attr("selected","selected");
        });
        selectedItems.remove();
    });
});

$(function(){
    //alert(new Date ($('id_activationDate').value));
    $('#id_newRate').keyup(function () {
        /*
         * I HATE A FUCKING JAVA-SCRIPT!!!111
         */
        var tempString = '';
        var currentDate = new Date();
        var dateString = $('#id_activationDate').val();
        var s = dateString.split('.');
        dateString = s[1]+'/'+s[0]+'/'+s[2];
        var activationDate = new Date();
        activationDate.setTime(Date.parse(dateString));
        if(activationDate > currentDate){
            tempString = 'Pending ';
        }


        if($(this).val() > $('#id_oldRate').val()){
            $("#id_indicator").val(tempString +"increase");
            $("#id_indicator").css("color","white");
            $("#id_indicator").css("backgroundColor","red");
        }else{
            $("#id_indicator").val(tempString +"decrease");
            $("#id_indicator").css("color","white");
            $("#id_indicator").css("backgroundColor","green");
        }
        if($(this).val() == $('#id_oldRate').val()){
            $("#id_indicator").val("current");
            $("#id_indicator").css("color","black");
            $("#id_indicator").css("backgroundColor","white");
        }
    });
});

$(function(){
    $("#id_newRate").numeric();
});