<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="include/header.jsp" />

<script src="../pub/js/signup.js"></script>

<section>
        <div class="container mt-3" style="margin-bottom: 20%;">
            <h1 class="text-center">New User Form</h1>
            <form action="/signup" method="post">
                <div class="mb-3 col-7">
                    <label for="email" class="form-label">Email address</label>
                    <input type="email" class="form-control" id="email" aria-describedby="emailHelp" name="email" value="${form.email}">
                    <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
                </div>
                <div class="mb-3 col-7">
                    <label for="fullName" class="form-label">Full Name</label>
                    <input type="text" class="form-control" id="fullName" name="fullName" value="${form.fullName}">
                </div>
                <div class="mb-3 col-7">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" name="password" value="${form.password}">
                </div>
                <div class="mb-3 col-7">
                    <label for="confirmPassword" class="form-label">Confirm Password</label>
                    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" value="${form.confirmPassword}">
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </section>
    <jsp:include page="include/footer.jsp" />