<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <jsp:include page="include/header.jsp" />

    <script src="../pub/js/register.js"></script>
    <!-- Need to handle validation still. Submits no matter what now -->
    <section>
        <div class="register-main container mt-3 mb-5 d-flex flex-column border border-danger border-2 rounded-3 w-50">
            <h1 class="mt-3">Create New Account</h1>
            <form class="m-3 d-flex flex-column justify-content-center" action="/registerSubmit" method="post"
                enctype="multipart/form-data">
                <div class="mb-3 col-sm-9 mx-auto">
                    <label for="nameInput" class="form-label">Name</label>
                    <input type="text" class="form-control" placeholder="i.e., Ken Jennings" id="nameInput" name="name"
                        value="${form.name}" required>
                    <c:if test="${bindingResult.hasFieldErrors('name')}">
                        <c:forEach items="${bindingResult.getFieldErrors('name')}" var="error">
                            <div style="color:red">${error.getDefaultMessage()}</div>
                        </c:forEach>
                    </c:if>
                </div>
                <div class="mb-3 col-sm-9 mx-auto">
                    <label for="usernameInput" class="form-label">Username</label>
                    <input type="text" class="form-control" id="usernameInput" name="username" value="${form.username}"
                        required>
                    <c:if test="${bindingResult.hasFieldErrors('username')}">
                        <c:forEach items="${bindingResult.getFieldErrors('username')}" var="error">
                            <div style="color:red">${error.getDefaultMessage()}</div>
                        </c:forEach>
                    </c:if>
                </div>
                <div class="mb-3 col-sm-9 mx-auto">
                    <label for="emailInput" class="form-label">Email</label>
                    <input type="email" class="form-control" id="emailInput" name="email" value="${form.email}" required>
                    <c:if test="${bindingResult.hasFieldErrors('email')}">
                        <c:forEach items="${bindingResult.getFieldErrors('email')}" var="error">
                            <div style="color:red">${error.getDefaultMessage()}</div>
                        </c:forEach>
                    </c:if>
                </div>
                <div class="mb-3 col-sm-9 mx-auto" id="passField">
                    <label for="passwordInput" class="form-label">Password</label>
                    <input type="password" class="form-control" id="passwordInput" name="password" required>
                    <c:if test="${bindingResult.hasFieldErrors('password')}">
                        <c:forEach items="${bindingResult.getFieldErrors('password')}" var="error">
                            <div style="color:red">${error.getDefaultMessage()}</div>
                        </c:forEach>
                    </c:if>
                </div>
                <div class="mb-3 col-sm-9 mx-auto" id="confirmField">
                    <label for="confirmPasswordInput" class="form-label">Confirm Password</label>
                    <input type="password" class="form-control" id="confirmPasswordInput" name="confirmPassword" required>
                    <c:if test="${bindingResult.hasFieldErrors('confirmPassword')}">
                        <c:forEach items="${bindingResult.getFieldErrors('confirmPassword')}" var="error">
                            <div style="color:red">${error.getDefaultMessage()}</div>
                        </c:forEach>
                    </c:if>
                </div>

                <div class="mb-3 col-sm-9 mx-auto">
                    <label for="profilePic" class="form-label">Profile Picture <span
                            id="lightText">(optional)</span></label>
                    <input class="form-control" type="file" id="profilePic" name="profilePicture">
                </div>

                <div class="form-check mb-4 col-sm-9 mx-auto">
                    <input class="form-check-input" type="checkbox" value="" id="host" name="host">
                    <label class="form-check-label" for="host">
                        I will be hosting quizzes.
                    </label>
                </div>

                <button class="btn btn-dark mx-5">Register</button>
            </form>
        </div>
    </section>

    <jsp:include page="include/footer.jsp" />