<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Film Information Form</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<div class="floating-box">
    <h2>Film Information</h2>
    <form action="${pageContext.request.contextPath}/chooser" method="post">
        <div class="form-field">
            <label for="title">Title:</label>
            <input type="text" id="title" name="title">
        </div>
        <div class="form-field">
            <label for="scenarist">Scenarist:</label>
            <input type="text" id="scenarist" name="scenarist">
        </div>
        <div class="slider-container">
            <p>Duration:</p>
            <div>
                <label for="minDuration">Min:</label>
                <input type="number" id="minDuration" name="minDuration" min="30" max="240" value="30">
                <label for="maxDuration">Max:</label>
                <input type="number" id="maxDuration" name="maxDuration" min="30" max="240" value="240">
            </div>
        </div>
        <div class="number-field">
            <label for="minRating">Min Rating (0-10):</label>
            <input type="number" id="minRating" name="minRating" min="0" max="10" value="0">
        </div>
        <br>
        <button type="submit" class="submit-button">Submit</button>
    </form>
</div>
<!-- The Modal -->
<div id="myModal" class="modal">
    <!-- Modal content -->
    <div class="modal-content">
        <span class="close">&times;</span>
        <h2 id="modalTitle">Modal Title</h2>
        <p id="modalMessage">Modal Message</p>
    </div>
</div>

<% if (request.getAttribute("errorMessage") != null) { %>
<script>
    var modal = document.getElementById("myModal");
    var span = document.getElementsByClassName("close")[0];

    document.getElementById("modalTitle").innerText = "Error";
    document.getElementById("modalMessage").innerText = '<%= request.getAttribute("errorMessage") %>';

    modal.style.display = "block";

    span.onclick = function() {
        modal.style.display = "none";
        setTimeout(function () {
            window.location.href = "${pageContext.request.contextPath}/chooser";
        }, 3000); // Redirect after 3 seconds
    }
</script>
<% } %>
</body>
</html>
