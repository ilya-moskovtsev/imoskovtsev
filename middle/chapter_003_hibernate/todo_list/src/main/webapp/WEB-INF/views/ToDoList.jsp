<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Todo list</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div class="container mb-4">
    <h2>Add Task</h2>
    <form class="needs-validation" novalidate>
        <div class="form-group">
            <label for="description">Description:</label>
            <textarea class="form-control z-depth-1" id="description" rows="3"
                      placeholder="Write something here..." name="description" required></textarea>
            <div class="valid-feedback">
                Looks good!
            </div>
            <div class="invalid-feedback">
                Description is required.
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
<div class="container">
    <h2>TODO List</h2>
    <table class="table">
        <thead>
        <tr>
            <th>Description</th>
            <th>Created</th>
            <th>Done</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>John</td>
            <td>Doe</td>
            <td>Male</td>
        </tr>
        <tr>
            <td>Mary</td>
            <td>Moe</td>
            <td>Female</td>
        </tr>
        <tr>
            <td>July</td>
            <td>Dooley</td>
            <td>Female</td>
        </tr>
        </tbody>
    </table>
</div>
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<script>
    // Example starter JavaScript for disabling form submissions if there are invalid fields
    (function () {
        'use strict';
        window.addEventListener('load', function () {
            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            const forms = document.getElementsByClassName('needs-validation');
            // Loop over them and prevent submission
            const validation = Array.prototype.filter.call(forms, function (form) {
                form.addEventListener('submit', function (event) {
                    event.preventDefault();
                    event.stopPropagation();
                    if (form.checkValidity()) {
                        form.classList.remove('was-validated');
                    } else {
                        form.classList.add('was-validated');
                    }
                }, false);
            });
        }, false);
    })();
</script>
<script>
    (function () {
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
                    `"><label class="form-check-label" for="defaultCheck1">`
                    + task.done
                    + `</label></div></td></tr>`;
            });
            replacement += "</tbody>";
            $('tbody').replaceWith(replacement);
        }

        $(document).ready(function () {
            // click on button submit
            $(".needs-validation").on('submit', function () {
                if (areAllControlsValid()) {
                    const description = document.querySelector('#description').value;
                    // send ajax
                    $.ajax({
                        url: '', // url where to submit the request
                        type: "POST", // type of action POST || GET
                        contentType: "application/json",
                        data: JSON.stringify({description}), // post data || get data
                        success: function (result) {
                            // you can see the result from the console
                            // tab of the developer tools
                            console.log(result);
                            updateTable(result);
                        },
                        error: function (xhr, resp, text) {
                            console.log(xhr, resp, text);
                        }
                    });
                }
            });
        });
    })();
</script>
</body>
</html>