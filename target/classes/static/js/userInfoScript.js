$(document).ready(
    function () {
        $("*[id*='img0']").css("display", "block");
    }
);


function showInfoFunc()
{
    $("*[id*='showInfo']").css("display", "block");
    $("*[id*='showHomes']").css("display", "none");
    $("*[id*='showChangePass']").css("display", "none");
    $("*[id*='editHomes']").css("display", "none");
}


function showChangePassFunc()
{
    $("*[id*='showHomes']").css("display", "none");
    $("*[id*='showChangePass']").css("display", "block");
    $("*[id*='editHomes']").css("display", "none");
    $("*[id*='showInfo']").css("display", "none");
}
