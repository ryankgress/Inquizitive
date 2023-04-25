<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
        <jsp:include page="include/header.jsp" />

        <!-- User Info -->
        <section>
            <div class="profile-main container mt-3 mb-5 d-flex flex-column border border-danger border-2 rounded-3">
                <h1>Profile</h1>
                <div class="row mx-5 align-items-center">
                    <div class="col">
                        <p>
                            Welcome to <b>${user.name}</b>'s profile.
                        </p>

                    </div>
                    <div class="col text-center">
                        <p>
                            <img src="${user.profilePic}" alt="pfp" width="200px">
                        </p>
                    </div>
                </div>
                <div class="row d-flex mx-5">
                    <div class="col profile-userinfo">
                        <h2>User Information</h2>

                        <p>
                        <table>
                            <tr>
                                <td><b>Username:</b></td>
                                <td>${user.username}</td>
                            </tr>
                            <tr>
                                <td><b>Email:</b></td>
                                <td>${user.email}</td>
                            </tr>
                            <tr>
                                <td><b>Home Field:</b></td>
                                <td>Yellow Bridges, New Ken</td>
                            </tr>
                            <tr>
                                <td><b>Total Points:</b></td>
                                <td>${totScore}</td>
                            </tr>
                        </table>
                        </p>
                        <br>
                        <p style="font-size: 12pt;">
                            See something wrong? Click <a href="/editProfile">here</a> to edit your profile
                        </p>

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
                            <tbody>
                                <c:forEach items="${recentStandings}" var="result">
                                    <tr>
                                        <td><a href="/trivia#heading${result.id}">${result.trivia_name}</a></td>
                                        <td class="placement">${result.placement}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </section>

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

        <!-- Trivia Info -->
        <sec:authorize access="hasAnyAuthority('HOST')">
            <section>
                <div class="pt-3">
                    <h1>Your Trivias</h1>
                    <div class="container d-flex flex-wrap justify-content-evenly mt-4">
                        <c:forEach items="${myTrivias}" var="trivia">
                            <div class="card text-center mb-3" style="width: 18rem;">
                                <div class="card-body profile-trivia">
                                    <h5 class="card-title"><a href="/trivia#heading${trivia.id}">${trivia.triviaName}</a> </h5>
                                    <p class="card-text">${trivia.locationName}</p>
                                </div>
                                <div class="card-footer d-flex justify-content-evenly">
                                    <a href="/results/${trivia.id}" class="btn btn-success col-6">Report Results</a>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>


            </section>
        </sec:authorize>



        <jsp:include page="include/footer.jsp" />