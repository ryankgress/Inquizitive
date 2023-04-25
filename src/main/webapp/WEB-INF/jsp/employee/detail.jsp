<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <jsp:include page="../include/header.jsp" />

    <section class="pt-3 pb-3 bg-light-grey">
        <div class="container text-center">
            <h1>Employee Detail</h1>
        </div>
    </section>

    <section>
        <div class="container mt-3 text-center">

            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>Attribute</th>
                        <th>Value</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Edit Employee</td>
                        <td><a href="/employee/edit/${employee.id}">Edit</a></td>
                    </tr>
                    <tr>
                        <td>id</td>
                        <td>${employee.id}</td>
                    </tr>
                    <tr>
                        <td>Office id</td>
                        <td>${employee.officeId}</td>
                    </tr>
                    <tr>
                        <td>First Name</td>
                        <td><a href="/employee/search?firstName=${employee.firstName}"> ${employee.firstName}</a></td>
                    </tr>
                    <tr>
                        <td>Last Name</td>
                        <td><a href="/employee/search?lastName=${employee.lastName}"> ${employee.lastName}</a></td>
                    </tr>
                    <tr>
                        <td>Extension</td>
                        <td>${employee.extension}</td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td>${employee.email}</td>
                    </tr>
                    <tr>
                        <td>Reports To</td>
                        <td>${employee.reportsTo}</td>
                    </tr>
                    <tr>
                        <td>Job Title</td>
                        <td>${employee.jobTitle}</td>
                    </tr>
                    <tr>
                        <td>Vacation Hours</td>
                        <td>${employee.vacationHours}</td>
                    </tr>
                    <tr>
                        <td>Profile Image</td>
                        <td><img src="${employee.profileImageUrl}" width="50px" alt="pfp"></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </section>

    <jsp:include page="../include/footer.jsp" />