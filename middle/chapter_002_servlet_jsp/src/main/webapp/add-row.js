(function () {
    const form = document.querySelector('.needs-validation');
    const formControls = document.querySelectorAll('.form-control');
    const firstName = document.querySelector('#first_name');
    const lastName = document.querySelector('#last_name');
    const gender = document.querySelector('#gender');
    const description = document.querySelector('#description');

    function addRow() {
        if (areAllControlsValid()) {
            $('.table tr:last').after(
                `<tr><td>${firstName.value}</td><td>${lastName.value}</td><td>${gender.value}</td><td>${description.value}</td></tr>`
            );
        }
    }

    function areAllControlsValid() {
        return formControls.length === getValidControls().length;
    }

    function getValidControls() {
        return [...formControls].filter(control => control.validity.valid);
    }

    form.addEventListener('submit', addRow);
})();