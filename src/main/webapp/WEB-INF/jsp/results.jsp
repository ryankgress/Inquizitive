<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <jsp:include page="include/header.jsp" />

    <section>
        <div class="register-main container mt-3 mb-5 d-flex flex-column border border-danger border-2 rounded-3 w-50">
            <h1 class="mt-3">Report Results</h1>
            <form class="m-3 d-flex flex-column justify-content-center" action="/results/submit" method="post">
                <input type="hidden" name="triviaId" value="${trivia.id}">
                <div class="mb-3 col-sm-9 mx-auto">
                    <label for="triviaName" class="form-label">Trivia Name</label>
                    <input type="text" class="form-control" id="triviaName" name="name"
                        value="${trivia.triviaName}" readonly>
                </div>

                <!-- Iterate over teams.length for outer loop. Set map's value to teamID and key to standing-->
                <c:set var="counter" scope="page" value="${0}" />
                
                <c:forEach items="${teams}" var="x">
                    <div class="mb-3 col-sm-9 mx-auto">
                        <label for="${standingArr[counter]}pos" class="form-label">${standingArr[counter]}</label>
                        <select class="form-select" id="${standingArr[counter]}pos" name="${standingArr[counter]}">
                            <c:forEach items="${teams}" var="team">
                                <option value="${team.id}">${team.team_name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <c:set var="counter" value="${counter + 1}" scope="page" />
                </c:forEach>
                

                <button class="btn btn-dark mx-5">Submit Results</button>
            </form>
        </div>
    </section>

    <jsp:include page="include/footer.jsp" />