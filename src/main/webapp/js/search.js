$(document).ready(function () {
    console.log("ready!");


    $('#searchinput').keyup(function (e) {
        switch (e.which) {
            case 13: //enter
                if (!$(".list-group-item.active").length) {
                    performSearch($('#searchinput').val())
                } else {
                    location.href = $(".list-group-item.active").attr("href");
                }
                break;

            case 27: //esc
                clearResults();
                break;

            case 38: // up
                if (!$(".list-group-item").length) {
                    performAutocomplete($('#searchinput').val());
                }

                if (!$(".list-group-item.active").length) {
                    if (!$(".list-group-item").first().hasClass("list-group-item-danger"))
                        makeActive($(".list-group-item").last());
                } else {
                    var active = $(".list-group-item.active");
                    makeInactive(active);
                    if ($(active).prev().length)
                        makeActive($(active).prev());
                    else
                        makeActive($(".list-group-item").last());
                }
                break;

            case 40: // down
                if (!$(".list-group-item").length) {
                    performAutocomplete($('#searchinput').val());
                }

                if (!$(".list-group-item.active").length) {
                    if (!$(".list-group-item").first().hasClass("list-group-item-danger"))
                        makeActive($(".list-group-item").first());
                } else {
                    var active = $(".list-group-item.active");
                    makeInactive(active);
                    if ($(active).next().length)
                        makeActive($(active).next());
                    else
                        makeActive($(".list-group-item").first());
                }
                break;

            default:
                performAutocomplete($('#searchinput').val());
                break;
        }
        e.preventDefault();
    });
});

function performAutocomplete(text) {

    if (text.length > 0) {
        var url = "search?action=autocomplete&text=" + text;

        $.ajax({
            url: url,
            type: "GET",
            datatype: "xml",
            success: function (xml) {
                clearResults();
                $(xml).find("item").each(function () {
                    var id = $(this).find("id").text();
                    var firstName = $(this).find("firstName").text();
                    var lastName = $(this).find("lastName").text();
                    var desription = $(this).find("description").text();
                    var type = $(this).find("type").text();
                    addItemToList(id, firstName, lastName, desription, type);
                    mListener();
                })
            },
            error: function () {
                clearResults();
                addErrorMessage();
            }
        });
    }
}

function clearResults() {
    $("#results").empty();
}

function mListener() {
    $(".list-group-item").hover(function () {
        $(".list-group-item").each(function () {
            makeInactive($(this));
        })
        makeActive($(this));
    }, function () {
        //makeInactive($(this));
    });
    $(document).click(function (event) {
        if ($(event.target).closest("#searchblock").length) return;
        clearResults();
    })
}

function makeActive(element) {
    $(element).addClass("active");
    $(element).find(".primary").hide();
    $(element).find(".additional").show();
}

function makeInactive(element) {
    $(element).removeClass("active");
    $(element).find(".primary").show();
    $(element).find(".additional").hide();
}

function addItemToList(id, firstName, lastName, description, type) {
    $("#results").append("<a href=\"item?id=" + id + "\" class=\"list-group-item\">"
    + "<span class=\"badge\">" + type + "</span>"
    + "<div class=\"primary\">"
    + firstName + " " + lastName
    + "</div>"
    + "<div class=\"additional\" hidden='hidden'>"
    + "<h4 class=\"list-group-item-heading\">" + firstName + " " + lastName + "</h4>"
    + "<p class=\"list-group-item-text\">" + description + "</p>"
    + "</div>"
    + "</a>");
}

function addErrorMessage() {
    $("#results").append("<li class=\"list-group-item list-group-item-danger\">"
    + "<h4 class=\"list-group-item-heading\">Connection error</h4>"
    + "<p class=\"list-group-item-text\">Unable to get server connection</p>"
    + "</li>");
}

function performSearch(text) {
    console.log("Search " + text);
}
