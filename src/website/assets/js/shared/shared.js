import "./_bootstrap";

import "./_header";
import "./_footer";

// toggle search box via command/ctrl + k
$(window).keydown(function (event) {
    if ((event.metaKey || event.ctrlKey) && event.keyCode === 75) {
        event.preventDefault()
        $("#search").focus();
    }
});

