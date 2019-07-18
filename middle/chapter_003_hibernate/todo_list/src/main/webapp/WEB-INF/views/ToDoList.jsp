<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <div class="form-check">
        <input class="form-check-input" type="checkbox" value="" id="showDoneCheck">
        <label class="form-check-label"
               for="showDoneCheck">Show done</label>
    </div>
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
<script type="text/javascript">
    <%@include file="/WEB-INF/js/form-validation.js" %>
</script>
<script type="text/javascript">
    <%@include file="/WEB-INF/js/update.js" %>
</script>
</body>
</html>