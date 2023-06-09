<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
        <jsp:include page="include/header.jsp" />

        <!-- User Info -->
        <section>
            <div class="profile-main container mt-3 mb-5 d-flex flex-column border border-danger border-2 rounded-3">
                <form action="/editProfileSubmit" method="post" enctype="multipart/form-data">
                    <h1>Edit Profile</h1>
                    <div class="row mx-5 align-items-center">
                        <div class="col">
                            <p>
                                Welcome to <input type="text" name="name" id="name" value="${form.name}">'s profile.
                            </p>

                        </div>
                        <div class="col text-center d-flex flex-column justify-content-center">
                            <div>
                                <img src="${form.profilePic}" alt="pfp" width="200px">
                            </div>

                            <div class="d-flex justify-content-center flex-column text-center">
                                <label for="profilePicture">Change your Profile Picture?</label><br>
                                <input type="file" name="profilePicture" id="profilePicture"
                                    class="text-center col-6 mx-auto">
                            </div>

                        </div>
                    </div>
                    <div class="row d-flex mx-5">
                        <div class="col profile-userinfo">
                            <h2>User Information</h2>

                            <input type="hidden" name="id" value="${form.id}">
                            <input type="hidden" name="profilePic" value="${form.profilePic}">
                            <table>
                                <tr>
                                    <td><b>Username:</b></td>
                                    <td><input type="text" id="usernameInput" name="username" value="${form.username}">
                                    </td>
                                </tr>
                                <tr>
                                    <td><b>Email:</b></td>
                                    <td><input type="email" id="emailInput" name="email" value="${form.email}"></td>
                                </tr>
                                <tr>
                                    <td><b>Host Account:</b></td>
                                    <td>
                                        <sec:authorize access="hasAnyAuthority('HOST')">
                                            Yes
                                        </sec:authorize>
                                        <sec:authorize access="!hasAnyAuthority('HOST')">
                                            No
                                        </sec:authorize>
                                    </td>
                                </tr>
                                <tr>
                                    <td><b>Total Points:</b></td>
                                    <td>${userScore}</td>
                                </tr>
                            </table>

                            <br>
                            <div class="text-center me-5">
                                <!-- <button type="submit" class="btn btn-primary">Submit Changes</button> -->
                                <c:if test="${bindingResult.hasFieldErrors('password')}">
                                    <c:forEach items="${bindingResult.getFieldErrors('password')}" var="error">
                                        <div style="color:red" class="mb-2">${error.getDefaultMessage()}</div>
                                    </c:forEach>
                                </c:if>
                                <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                                    data-bs-target="#enterPass">Submit Changes</button>
                                <button type="button" class="btn btn-dark" onclick="toProfile()">Undo Changes</button>
                            </div>

                            <div class="modal fade" id="enterPass" tabindex="-1" aria-labelledby="enterPass"
                                aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h4 class="modal-title" id="enterPass">Enter Password</h4>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <label for="password" class="form-label">Password</label>
                                            <input type="password" class="form-control" id="password" name="password">

                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary"
                                                data-bs-dismiss="modal">Close</button>
                                            <button type="submit" class="btn btn-primary">Submit Changes</button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                </form>

                <script>
                    function toProfile() {
                        location.href = "/profile";
                    }
                </script>

            </div>

            <div class="col profile-recent">
                <h2 class="text-center">Recent Quizzes</h2>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Name</th>
                            <th scope="col" style="text-align: center;">Placement</th>
                        </tr>
                    </thead>
                    <tbody id="recentTable">
                        <c:forEach items="${recentStandings}" var="result">
                            <tr>
                                <td><a href="/trivia#heading${result.id}">${result.trivia_name}</a></td>
                                <td class="placement">${result.placement}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </section>

        <script>
            let table = document.getElementById("recentTable");
            if (table.innerText == "") {
                let insertStr = "<tr><td colspan='2'>No Games Reported.</td></tr>";
                table.innerHTML += insertStr;
                table.classList.add("text-center");
                table.style = "font-size:14pt;";
                console.log("Empty table");
            } else
                console.log("Not empty");
        </script>

        <!-- Team Info -->
        <section>
            <h1>Your Teams</h1>
            <div class="profile-teams container d-flex mt-4 justify-content-evenly flex-wrap">
                <c:forEach items="${teams}" var="team">
                    <div class="card text-center mb-3" style="width: 18rem;">
                        <img src="${team.team_pic}" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">${team.team_name}</h5>
                            <p class="card-text">${team.team_desc}</p>

                            <!-- <a href="/teams#${team.team_name}" class="btn btn-dark">Team Page</a> -->
                        </div>
                        <div class="card-footer d-flex justify-content-evenly">
                            <a href="/teams#${team.team_name}" class="btn btn-dark">Team Page</a>
                            <a href="/profile/${team.id}" class="btn btn-danger">Leave Team</a>
                        </div>
                    </div>
                </c:forEach>

            </div>
        </section>


        <jsp:include page="include/footer.jsp" />