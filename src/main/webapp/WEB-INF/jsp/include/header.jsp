<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
        <link href="/pub/css/global.css" rel="stylesheet">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300&display=swap" rel="stylesheet">
        <title>Hello World</title>
    </head>

    <body>

        <nav class="navbar navbar-expand-lg navbar-light" style="background-color: rgb(198, 228, 151);">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Navbar</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false"
                    aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNavDropdown">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="/index">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/signup">Signup</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/bootstrap">Bootstrap Example</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/fileupload">File Upload</a>
                        </li>


                        <sec:authorize access="isAuthenticated()">
                            <li class="nav-item">
                                <a class="nav-link" href="/login/logout">Logout</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href=""><sec:authentication property="principal.username" /></a>
                            </li>
                        </sec:authorize>
                        <sec:authorize access="!isAuthenticated()">
                            <li class="nav-item">
                                <a class="nav-link" href="/login/loginPage">Login</a>
                            </li>
                        </sec:authorize>
                        <sec:authorize access="hasAnyAuthority('ADMIN')">
                            <li class="nav-item">
                                <a class="nav-link" href="/index">Admin Access</a>
                            </li>
                        </sec:authorize>


                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                                data-bs-toggle="dropdown" aria-expanded="false">
                                Employee
                            </a>
                            <ul class="dropdown-menu" aria-labeled="navbarDropdownMenuLink">
                                <li><a class="dropdown-item" href="/employee/search">Employee Search</a></li>
                                <li><a class="dropdown-item" href="/employee/create">Create Employee</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>