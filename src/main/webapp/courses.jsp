<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Courses</title>
</head>
<body>

<h2>Available Courses</h2>

<p>Welcome, ${sessionScope.studentName}</p>

<table border="1" cellpadding="10">
    <tr>
        <th>Course ID</th>
        <th>Name</th>
        <th>Instructor</th>
        <th>Credits</th>
        <th>Action</th>
    </tr>

    <c:forEach var="course" items="${courses}">
        <tr>
            <td>${course.courseId}</td>
            <td>${course.name}</td>
            <td>${course.instructor}</td>
            <td>${course.credits}</td>
            <td>
                <form action="${pageContext.request.contextPath}/register/${course.courseId}" method="post">
                    <button type="submit">Register</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<br>

<a href="${pageContext.request.contextPath}/logout">Logout</a>

</body>
</html>
