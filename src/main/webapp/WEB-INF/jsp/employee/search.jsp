<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <jsp:include page="../include/header.jsp" />

    <section class="pt-3 pb-3 bg-light-grey">
        <div class="container text-center">
            <h1>Employee Search</h1>
        </div>
    </section>

    <section class="bg-bisque">
        <div class="container d-flex flex-column col-6">
            <form class="m-3 d-flex flex-column justify-content-center">
                <div class="input-group mb-3">
                    <input type="text" class="form-control" name="firstName" placeholder="First Name"
                        aria-label="First Name" aria-describedby="searchBtn1" value="${firstName}">
                    <!-- <button class="btn btn-outline-secondary" id="searchBtn1">Search</button> -->
                </div>
                <div class="input-group mb-3">
                    <input type="text" class="form-control" name="lastName" placeholder="Last Name"
                        aria-label="Last Name" aria-describedby="searchBtn2" value="${lastName}">
                </div>
                <div class="input-group mb-3 d-flex flexcolumn justify-content-center">
                    <button class="btn btn-outline-secondary" id="searchBtn2">Search</button>
                </div>
            </form>
        </div>
    </section>

    <section>
        <div class="container mt-3 text-center">

            <h4 class="pb-2">${employeesList.size()} Search Results</h4>

            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>id</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>Job Title</th>
                        <th>Office City</th>
                        <th>Edit</th>
                        
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${employeesList}" var="emp">
                        <tr>
                            <td><a href="/employee/detail/${emp.id}">${emp.id}</a></td>
                            <td>${emp.firstName}</td>
                            <td>${emp.lastName}</td>
                            <td>${emp.email}</td>
                            <td>${emp.jobTitle}</td>
                            <td>${emp.officeId}</td>
                            <td><a href="/employee/edit/${emp.id}">Edit</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </section>


    <jsp:include page="../include/footer.jsp" />