(function () {
    'use strict';
    window.addEventListener('load', function () {
        const alert = '.alert';
        const error = '${error}';
        if (error) {
            $(alert).removeClass('d-none');
        } else {
            $(alert).addClass('d-none');
        }
    }, false);
})();