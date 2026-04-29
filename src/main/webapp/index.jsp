<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>HR Directory</title>
</head>
<body>
<h1>HR Directory</h1>

<ul>
    <li>
        <a href="${pageContext.request.contextPath}/departments.xhtml">Departments</a>
        (view all departments)
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/employees.xhtml">Employees</a>
        (view/filter/search employees)
    </li>
</ul>

</body>
</html>