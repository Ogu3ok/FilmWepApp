<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Film Selection</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="container">
    <h1>Welcome to Film Selection</h1>
    <div class="floating-box">
        <h2>Enter Film Details</h2>
        <label for="title">Title:</label>
        <input type="text" id="title" name="title" required><br><br>
        <label for="scenarist">Scenarist:</label>
        <input type="text" id="scenarist" name="scenarist" required><br><br>
        <label for="duration">Film Duration:</label>
        <input type="range" id="duration" name="duration" min="30" max="180" step="15"><br><br>
        <label for="rating">Film Rating:</label>
        <select id="rating" name="rating">
            <option value="G">G</option>
            <option value="PG">PG</option>
            <option value="PG-13">PG-13</option>
            <option value="R">R</option>
            <option value="NC-17">NC-17</option>
        </select>
        <button type="submit">Submit</button>
    </div>
</div>
</body>
</html>