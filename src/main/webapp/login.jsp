<html>
<head>
    <title>Login</title>
</head>
<body>

<h2>Student Login</h2>

<form action="${pageContext.request.contextPath}/login" method="post">
    <label>Email:</label>
    <input type="text" name="email" required>
    <br><br>

    <label>Password:</label>
    <input type="password" name="password" required>
    <br><br>

    <button type="submit">Login</button>
</form>

<p style="color:red;">${error}</p>

</body>
</html>