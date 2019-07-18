<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<div class="container mb-4">
    <h2>Bootstrap Form</h2>
    <form class="needs-validation" novalidate>
        <input type="hidden" name="action" value="readJson">
        <div class="form-group">
            <label for="first_name">First name:</label>
            <input type="text" class="form-control" id="first_name" placeholder="Enter first name" name="first_name"
                   required>
            <div class="valid-feedback">
                Looks good!
            </div>
            <div class="invalid-feedback">
                First name is required.
            </div>
        </div>
        <div class="form-group">
            <label for="last_name">Last name:</label>
            <input type="text" class="form-control" id="last_name" placeholder="Enter last name" name="last_name"
                   required>
            <div class="valid-feedback">
                Looks good!
            </div>
            <div class="invalid-feedback">
                Last name is required.
            </div>
        </div>
        <div class="form-group">
            <label for="gender">Gender:</label>
            <select class="form-control" id="gender" name="gender" required>
                <option value>Choose...</option>
                <option value="Male">Male</option>
                <option value="Female">Female</option>
            </select>
            <div class="valid-feedback">
                Looks good!
            </div>
            <div class="invalid-feedback">
                Please select gender.
            </div>
        </div>
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
    <h2>Bootstrap Table</h2>
    <table class="table">
        <thead>
        <tr>
            <th>First name</th>
            <th>Last name</th>
            <th>Gender</th>
            <th>Description</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>John</td>
            <td>Doe</td>
            <td>Male</td>
            <td>Good</td>
        </tr>
        <tr>
            <td>Mary</td>
            <td>Moe</td>
            <td>Female</td>
            <td>Better</td>
        </tr>
        <tr>
            <td>July</td>
            <td>Dooley</td>
            <td>Female</td>
            <td>Best</td>
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

        function updateTable() {
            $.ajax({
                url: 'writeJson',
                type: "POST",
                data: {action: "writeJson"},
                success: function (result) {
                    // you can see the result from the console
                    // tab of the developer tools
                    console.log(result);
                    const people = JSON.parse(result);
                    let replacement = "<tbody>";
                    people.map(person => {
                        replacement += `<tr><td>` + person.firstName + `</td><td>` + person.lastName + `</td><td>` + person.gender + `</td><td>` + person.description + `</td></tr>`;
                    });
                    replacement += "</tbody>";
                    $('tbody').replaceWith(replacement);
                },
                error: function (xhr, resp, text) {
                    console.log(xhr, resp, text);
                }
            });
        }

        $(document).ready(function () {
            // click on button submit
            $(".needs-validation").on('submit', function () {

                if (areAllControlsValid()) {
                    const firstName = document.querySelector('#first_name').value;
                    const lastName = document.querySelector('#last_name').value;
                    const gender = document.querySelector('#gender').value;
                    const description = document.querySelector('#description').value;
                    // send ajax
                    $.ajax({
                        url: 'readJson', // url where to submit the request
                        type: "POST", // type of action POST || GET
                        data: {
                            action: "readJson",
                            json: JSON.stringify({
                                firstName,
                                lastName,
                                gender,
                                description
                            })
                        }, // post data || get data
                        success: function (result) {
                            // you can see the result from the console
                            // tab of the developer tools
                            console.log(result);
                            updateTable();
                        },
                        error: function (xhr, resp, text) {
                            console.log(xhr, resp, text);
                            updateTable();
                        }
                    });
                }
            });
        });
    })();
</script>
</body>
</html>

