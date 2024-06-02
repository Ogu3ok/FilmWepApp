<%@ page import="java.util.List" %>
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
        <div class="film-info container-field">
            <div class="form-container">
                <div class="form-field">
                    <label for="title" class="label-field">Title:</label>
                    <input type="text" id="title" name="title">
                </div>
                <div class="form-field">
                    <label for="director" class="label-field">Director:</label>
                    <input type="text" id="director" name="director">
                </div>
                <div class="slider-container">
                    <label class="label-field">Duration:</label>
                    <div class="duration-container">
                        <label for="minDuration" class="label-field">Min:</label>
                        <input type="number" id="minDuration" name="minDuration" min="30" max="330" value="30">
                        <label for="maxDuration" class="label-field">Max:</label>
                        <input type="number" id="maxDuration" name="maxDuration" min="30" max="330" value="330">
                    </div>
                </div>
                <div class="number-field">
                    <label for="minRating" class="label-field">Min Rating (0-10):</label>
                    <input type="number" id="minRating" name="minRating" min="0" max="10" value="0">
                </div>
            </div>
            <div class="genre-container">
                <% List<String> genres = (List<String>) request.getAttribute("genres"); %>
                <% for(String genre : genres) { %>
                <div class="checkbox-field">
                    <input type="checkbox" id="<%= genre %>" name="genres" value="<%= genre %>">
                    <label for="<%= genre %>"><%= genre %></label>
                </div>
                <% } %>
                <!-- Add more genres as needed -->
            </div>
        </div>
        <div>
            <button type="submit" class="submit-button button-field">Submit</button>
        </div>
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

    span.onclick = function () {
        modal.style.display = "none";
        setTimeout(function () {
            window.location.href = "${pageContext.request.contextPath}/chooser";
        }, 5000); // Redirect after 3 seconds
    }

    window.onclick = function(event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    }
</script>
<% } %>
</body>
</html>
