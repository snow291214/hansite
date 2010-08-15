function clear(){
    $('#id_destinations option').remove();
    $('#id_oldRate').val("");
    $('#id_newRate').val("");
    $('#id_activationDate').val("");
    $('#id_currency').val("");
    $('#id_areacodes').children('tbody').remove();
    $('#id_indicator').val("");
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
        $.post('ajax.htm',{
            'customerUid':$(this).val(),
            'requestType':0
        }, function(xml){
            $('#id_destinations').append('<option value="-">Choose...</option>');
            $(xml).find('Destination').each(function(){
                $('#id_destinations').append('<option value="' + $(this).text() + '">' + $(this).text() + '</option>');
            });
        });
        hideIndicator();
    });
});

$(function(){
    $('#id_destinations').change(function() {
        showIndicator();
        $.post('ajax.htm',{
            'destinationName':$(this).val(),
            'customerUid':$('#id_customers').val(),
            'requestType':1
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
        });
        hideIndicator();
    });
});

$(function(){
    $('#id_check1').change(function () {
        if ($(this).attr("checked")) {
            $.post('ajax.htm',{
                'requestType':2
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
    $('#id_newRate').keyup(function () {
        if($(this).val() > $('#id_oldRate').val()){
            $("#id_indicator").val("increase");
        }else{
            $("#id_indicator").val("decrease");
        }
        if($(this).val() == $('#id_oldRate').val()){
            $("#id_indicator").val("current");
        }
    });
});

$(function(){
    $("#id_newRate").numeric();;
});