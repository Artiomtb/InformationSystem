function makeEditable() {
    $(".stored").hide();
    $(".changed").show();
}

function cancel() {
    $(".stored").show();
    $(".changed").hide();
}

function submit(id) {
    var firstName = $("#fn").val();
    var lastName = $("#ln").val();
    var description = $("#dc").val();
    var url = "item?action=update&id=" + id + "&fn=" + firstName + "&ln=" + lastName + "&dc=" + description;
    console.log(url);
    $.ajax({
        url: url,
        method: "POST",
        datatype: "xml",
        success: function(xml) {
            if("ok" === $(xml).find("message result").text()) {
                updateValues(firstName, lastName, description);
                cancel();
                showSuccessAlert();
            } else {
                console.log("Incor responce");
                showUnsuccessAlert();
            }
        },
        error: function() {
            console.log("Error");
            showUnsuccessAlert();
        }
    })
}

function updateValues(firstName, lastName, description) {
    $("#fn-stored").html(firstName);
    $("#ln-stored").html(lastName);
    $("#dc-stored").html(description);
}

function showSuccessAlert() {
    $("#success-alert").show();
}

function showUnsuccessAlert() {
    $("#unsuccess-alert").show();
}