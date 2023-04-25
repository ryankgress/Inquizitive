<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
      <meta charset="UTF-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>Inquizitive</title>
      <link rel="stylesheet" href="/pub/css/styles.css">
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
      <link rel="preconnect" href="https://fonts.googleapis.com">
      <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
      <link href="https://fonts.googleapis.com/css2?family=Sedgwick+Ave+Display&display=swap" rel="stylesheet">
    </head>

    <body class="min-vh-100 d-flex flex-column">
      <!-- Navbar -->
      <nav class="navbar navbar-expand-lg navbar-light px-3" style="background-color: #cc9cbe;">
        <a class="navbar-brand d-lg-none mx-3" href="/index"><img src="/pub/images/logo.png" width="200px"></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
          aria-controls="nav#navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
          <ul class="navbar-nav mx-auto">

            <sec:authorize access="isAuthenticated()">
              <!-- Assign username to a variable from auth with jstl, then query for the id somehow-->
              <!-- Need to go from this username to its ID from the DB. How?-->
              <c:set var="usernameAuth" value="<sec:authentication property='principal.username' />" scope="page" />
              <li class="nav-item">
                <a class="nav-link" href='/profile'>Profile</a>
              </li>
            </sec:authorize>
            <sec:authorize access="!isAuthenticated()">
              <li class="nav-item">
                <a class="nav-link" href="/register">Register</a>
              </li>
            </sec:authorize>

            <sec:authorize access="isAuthenticated()">
              <li class="nav-item">
                <a class="nav-link" href="/trivia">Trivia</a>
              </li>
            </sec:authorize>
            <sec:authorize access="!isAuthenticated()">
              <li class="nav-item">
                <a class="nav-link" href="/signin/redirect">Trivia</a>
              </li>
            </sec:authorize>
            <a class="d-none d-lg-block mx-3" href="/index"><img src="/pub/images/logo.png" width="200px"></a>

            <sec:authorize access="isAuthenticated()">
              <li class="nav-item">
                <a class="nav-link" href="/teams">Teams</a>
              </li>
            </sec:authorize>
            <sec:authorize access="!isAuthenticated()">
              <li class="nav-item">
                <a class="nav-link" href="/signin/redirect">Teams</a>
              </li>
            </sec:authorize>

            <sec:authorize access="isAuthenticated()">
              <li class="nav-item">
                <a class="nav-link" href="/signin/signout">Sign Out</a>
              </li>
            </sec:authorize>
            <sec:authorize access="!isAuthenticated()">
              <li class="nav-item">
                <a class="nav-link" href="/signin">Sign in</a>
              </li>
            </sec:authorize>
            <li class="nav-item">
              <br><br>
            </li>
          </ul>
        </div>
      </nav>