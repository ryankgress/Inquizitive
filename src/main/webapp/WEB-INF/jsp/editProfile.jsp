<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                            <input type="file" name="profilePicture" id="profilePicture" class="text-center col-6 mx-auto">
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
                                <td><input type="text" id="usernameInput" name="username" value="${form.username}"></td>
                            </tr>
                            <tr>
                                <td><b>Email:</b></td>
                                <td><input type="email" id="emailInput" name="email" value="${form.email}"></td>
                            </tr>
                            <tr>
                                <td><b>Home Field:</b></td>
                                <td>Yellow Bridges, New Ken</td>
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
                <tbody>
                    <tr>
                        <td><a href="index">Buzzworthy Thursday 3/9</a></td>
                        <td class="placement">6th</td>
                    </tr>
                    <tr>
                        <td><a href="index">Buzzworthy Thursday 3/16</a></td>
                        <td class="placement">2nd</td>
                    </tr>
                    <tr>
                        <td><a href="index">HO1KB #63</a></td>
                        <td class="placement">5th</td>
                    </tr>
                    <tr>
                        <td><a href="index">Trivia Time Virtual 132</a></td>
                        <td class="placement">2nd</td>
                    </tr>
                </tbody>
            </table>
        </div>
        </div>
        </div>

        <!-- Team Info -->
        <section>
            <h1>Your Teams</h1>
            <div class="profile-teams container d-flex mt-2 justify-content-evenly flex-wrap">
                <div class="card text-center mb-3" style="width: 18rem;">
                    <img src="/pub/images/team-logo.jpg" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">Quiz Tigers</h5>
                        <p class="card-text">Ferocious in the jungle and the quiz field. They're grrrreat!</p>
                        <a href="#" class="btn btn-dark">Team Page</a>
                    </div>
                </div>
                <div class="card text-center mb-3" style="width: 18rem;">
                    <img src="/pub/images/team-logo2.jpg" class="card-img-top" alt="...">
                    <div class="card-body">
                        <h5 class="card-title">Outside Spotlights.</h5>
                        <p class="card-text">A disconnected family that unites solely to take the quizzing crown! ...
                            Just
                            leave motown out of it.</p>
                        <a href="#" class="btn btn-dark">Team Page</a>
                    </div>
                </div>

                <!-- Able to add more teams easily below -->
                <!-- <div class="card text-center mb-3" style="width: 18rem;">
                <img src="/pub/images/team-logo2.jpg" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">Outside Spotlights.</h5>
                    <p class="card-text">A disconnected family that unites solely to take the quizzing crown! ... Just leave motown out of it.</p>
                    <a href="#" class="btn btn-dark">Team Page</a>
                </div>
            </div>
            <div class="card text-center mb-3" style="width: 18rem;">
                <img src="/pub/images/team-logo2.jpg" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">Outside Spotlights.</h5>
                    <p class="card-text">A disconnected family that unites solely to take the quizzing crown! ... Just leave motown out of it.</p>
                    <a href="#" class="btn btn-dark">Team Page</a>
                </div>
            </div>
            <div class="card text-center mb-3" style="width: 18rem;">
                <img src="/pub/images/team-logo2.jpg" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">Outside Spotlights.</h5>
                    <p class="card-text">A disconnected family that unites solely to take the quizzing crown! ... Just leave motown out of it.</p>
                    <a href="#" class="btn btn-dark">Team Page</a>
                </div>
            </div> -->
            </div>
        </section>

        <jsp:include page="include/footer.jsp" />