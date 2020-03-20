(function () {
    'use strict';
    window.addEventListener('load', function () {
        const forms = document.getElementsByClassName('delete');
        Array.prototype.filter.call(forms, function (form) {
            form.addEventListener('submit', function (event) {
                event.preventDefault();
                event.stopPropagation();
                $.ajax({
                    url: 'user',
                    type: "POST",
                    data: {
                        action: "delete",
                        id: event.target.dataset.userId
                    },
                    success: function (result) {
                        $(form).closest('tr').remove();
                    },
                    error: function (xhr, resp, text) {
                        console.log(xhr, resp, text);
                    }
                });
            }, false);
        });
    }, false);
})();