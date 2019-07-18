(function () {
    const showDoneCheck = document.querySelector('#showDoneCheck');

    function areAllControlsValid() {
        const formControls = document.querySelectorAll('.form-control');
        return formControls.length === [...formControls].filter(control => control.validity.valid).length;
    }

    function updateTable(result) {
        const tasks = JSON.parse(result);
        let replacement = "<tbody>";
        tasks.map(task => {
            replacement += `<tr><td>` + task.description + `</td><td>month:` + task.created.month + ` day:` + task.created.day + `</td><td>`
                + `<div class="form-check"><input class="form-check-input" type="checkbox" value="" id="defaultCheck1" data-task-id="`
                + task.id +
                `" data-task-done="`
                + task.done
                + `"><label class="form-check-label" for="defaultCheck1">`
                + task.done
                + `</label></div></td></tr>`;
        });
        replacement += "</tbody>";
        $('tbody').replaceWith(replacement);

        const checkboxes = document.querySelectorAll('tbody .form-check-input');
        checkboxes.forEach(c => c.checked = JSON.parse(c.dataset.taskDone));

        checkboxes.forEach(checkbox => checkbox.addEventListener('click', handleCheck));
        handleShowDone();
    }

    function handleCheck(e) {
        $.ajax({
            url: '',
            type: "POST",
            data: {
                action: "done",
                taskId: e.target.dataset.taskId,
                done: e.target.checked,
            },
            success: function (result) {
                updateTable(result);
            },
            error: function (xhr, resp, text) {
                console.log(xhr, resp, text);
            }
        });
    }

    function handleShowDone() {
        const checkboxes = document.querySelectorAll('tbody .form-check-input');
        if (showDoneCheck.checked) {
            [...checkboxes]
                .filter(c => c.checked)
                .forEach(c => $(c).parents("tr").show());
        } else {
            [...checkboxes]
                .filter(c => c.checked)
                .forEach(c => $(c).parents("tr").hide());
        }
    }

    $(document).ready(function () {
        // load tasks into table on document ready
        $.ajax({
            url: '',
            type: "POST",
            data: {
                action: "getAll"
            },
            success: function (result) {
                updateTable(result);
            },
            error: function (xhr, resp, text) {
                console.log(xhr, resp, text);
            }
        });
        // click on button submit
        $(".needs-validation").on('submit', function () {
            if (areAllControlsValid()) {
                const description = document.querySelector('#description').value;
                // send ajax
                $.ajax({
                    url: '', // url where to submit the request
                    type: "POST", // type of action POST || GET
                    data: {
                        action: "add",
                        json: JSON.stringify({description}), // post data || get data
                    },
                    success: function (result) {
                        updateTable(result);
                    },
                    error: function (xhr, resp, text) {
                        console.log(xhr, resp, text);
                    }
                });
            }
        });


        showDoneCheck.addEventListener('click', handleShowDone);
        showDoneCheck.checked = true;
    });
})();