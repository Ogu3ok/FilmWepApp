<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <form action="${pageContext.request.contextPath}/films" method="post">
        <div class="form-field">
            <label for="title">Title:</label>
            <input type="text" id="title" name="title" required>
        </div>
        <div class="form-field">
            <label for="scenarist">Scenarist:</label>
            <input type="text" id="scenarist" name="scenarist" required>
        </div>
        <div class="slider-container">
            <label for="duration">Duration (min):</label>
            <input type="range" id="duration" name="duration" min="30" max="240" value="120" oninput="document.getElementById('duration-value').textContent = this.value">
            <span id="duration-value">120</span>
        </div>
        <div class="form-field">
            <label for="rating">Rating:</label>
            <select id="rating" name="rating" required>
                <option value="G">G</option>
                <option value="PG">PG</option>
                <option value="PG-13">PG-13</option>
                <option value="R">R</option>
                <option value="NC-17">NC-17</option>
            </select>
        </div>
        <button type="submit" class="submit-button">Submit</button>
    </form>
</div>
</body>
</html>
