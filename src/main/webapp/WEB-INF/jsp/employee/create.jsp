<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <jsp:include page="../include/header.jsp" />
    <style>
        body {
            background-color: #d3d3d3;
        }
    </style>

    <script>
        function toDetails() {
            location.href = "/employee/detail/${form.id}";
        }
    </script>

    <div class="container mt-3 col-5" style="margin-bottom: 20%;">
        <c:if test="${empty form.id}">
            <h1 class="text-center">Create Employee</h1>
        </c:if>
        <c:if test="${not empty form.id}">
            <h1 class="text-center">Edit Employee</h1>
        </c:if>

        <form action="/employee/createSubmit" method="post">
            <c:if test="${success}">
                <div class="alert alert-success" role="alert">
                    Employee Created!
                </div>
            </c:if>
            <input type="hidden" name="id" value="${form.id}">

            <c:if test="${not empty form.id}">
                <div class="d-flex flexcolumn justify-content-center">
                    <button type="button" class="btn btn-light" onclick="toDetails()">Employee Details</button>
                </div>
            </c:if>

            <div class="mb-3">
                <label for="firstName" class="form-label">First Name</label>
                <input type="text" class="form-control" id="firstName" name="firstName" value="${form.firstName}">
                <C:if test="${bindingResult.hasFieldErrors('firstName')}">
                    <c:forEach items="${bindingResult.getFieldErrors('firstName')}" var="error">
                        <div style="color:red">${error.getDefaultMessage()}</div>
                    </c:forEach>
                </C:if>
            </div>
            <div class="mb-3">
                <label for="lastName" class="form-label">Last Name</label>
                <input type="text" class="form-control" id="lastName" name="lastName" value="${form.lastName}">
                <C:if test="${bindingResult.hasFieldErrors('lastName')}">
                    <c:forEach items="${bindingResult.getFieldErrors('lastName')}" var="error">
                        <div style="color:red">${error.getDefaultMessage()}</div>
                    </c:forEach>
                </C:if>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email address</label>
                <input type="email" class="form-control" id="email" aria-describedby="emailHelp" name="email"
                    value="${form.email}">
                <div id="emailHelp" class="form-text">We'll never share your email with anyone.</div>
                <C:if test="${bindingResult.hasFieldErrors('email')}">
                    <c:forEach items="${bindingResult.getFieldErrors('email')}" var="error">
                        <div style="color:red">${error.getDefaultMessage()}</div>
                    </c:forEach>
                </C:if>
            </div>
            <div class="mb-3">
                <label for="extension" class="form-label">Extension</label>
                <input type="text" class="form-control" id="extension" name="extension" value="${form.extension}">
                <C:if test="${bindingResult.hasFieldErrors('extension')}">
                    <c:forEach items="${bindingResult.getFieldErrors('extension')}" var="error">
                        <div style="color:red">${error.getDefaultMessage()}</div>
                    </c:forEach>
                </C:if>
            </div>
            <div class="mb-3">
                <label for="jobTitle" class="form-label">Job Title</label>
                <input type="text" class="form-control" id="jobTitle" name="jobTitle" value="${form.jobTitle}">
                <C:if test="${bindingResult.hasFieldErrors('jobTitle')}">
                    <c:forEach items="${bindingResult.getFieldErrors('jobTitle')}" var="error">
                        <div style="color:red">${error.getDefaultMessage()}</div>
                    </c:forEach>
                </C:if>
            </div>
            <div class="mb-3">
                <label for="profileImage" class="form-label">Profile Image</label>
                <input type="text" class="form-control" id="profileImage" name="profileImage"
                    value="${form.profileImage}">
                <C:if test="${bindingResult.hasFieldErrors('profileImage')}">
                    <c:forEach items="${bindingResult.getFieldErrors('profileImage')}" var="error">
                        <div style="color:red">${error.getDefaultMessage()}</div>
                    </c:forEach>
                </C:if>
            </div>
            <div class="mb-3">
                <label for="vacationHours" class="form-label">Vacation Hours</label>
                <input type="number" class="form-control" id="vacationHours" name="vacationHours"
                    value="${form.vacationHours}">
                <C:if test="${bindingResult.hasFieldErrors('vacationHours')}">
                    <c:forEach items="${bindingResult.getFieldErrors('vacationHours')}" var="error">
                        <div style="color:red">${error.getDefaultMessage()}</div>
                    </c:forEach>
                </C:if>
            </div>
            <!-- <h6>Office</h6> -->
            <div class="mb-3">
                <label for="officeId" class="form-label">Office</label>
                <select name="officeId" id="officeId" class="form-select">
                    <c:forEach items="${offices}" var="off">
                        <option value="${off.id}" <c:if test="${off.id eq form.officeId}">
                            selected
                            </c:if>
                            >${off.city}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="d-flex flexcolumn justify-content-center">
                <button class="btn btn-primary">
                    <c:if test="${empty form.id}">
                        Create Employee
                    </c:if>
                    <c:if test="${not empty form.id}">
                        Edit Employee
                    </c:if>
                </button>
            </div>
        </form>
    </div>
    </section>

    <jsp:include page="../include/footer.jsp" />