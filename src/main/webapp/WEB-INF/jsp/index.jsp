<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="include/header.jsp" />

<section class="mainpage my-3">
  <div class="container intro d-flex flex-column align-items-center justify-content-center">
    <div>
      <h1 class="display-1" style="font-weight:bold">Welcome to <span style="color:#263B80">In<span
            id="quiz">quiz</span>itive</span>!</h1>
    </div>
  </div>
</section>

<section>
  <div class="container w-75 mt-5 home-carousel">
    <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
      <div class="carousel-indicators">
        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active"
          aria-current="true" aria-label="Slide 1"></button>
        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1"
          aria-label="Slide 2"></button>
        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2"
          aria-label="Slide 3"></button>
      </div>
      <div class="carousel-inner">
        <div class="carousel-item active">
          <img src="/pub/images/option-1.jpg" class="d-block w-100" alt="carousel options">
          <div class="carousel-caption d-none d-md-block">
            <h5>New Locations Everyday</h5>
            <p>Check out our 'Submit a Location' page to show us where you're quizzing!</p>
          </div>
        </div>
        <div class="carousel-item">
          <img src="/pub/images/option-2.jpg" class="d-block w-100" alt="carousel options">
          <div class="carousel-caption d-none d-md-block">
            <sec:authorize access="isAuthenticated()">
              <h5><a href="/teams/score" style="text-decoration: none; color:white">Check out the Top Teams</a></h5>
            </sec:authorize>
            <sec:authorize access="!isAuthenticated()">
              <h5>Check out the Top Teams</h5>
            </sec:authorize>
            <p>See how you stand up against the top teams in the country!</p>
          </div>
        </div>
        <div class="carousel-item">
          <img src="/pub/images/option-3.jpg" class="d-block w-100" alt="carousel options">
          <div class="carousel-caption d-none d-md-block">
            <h5>Find a new quizzing home</h5>
            <p>Track down a new trivia night near you.</p>
          </div>
        </div>
      </div>
      <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions"
        data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
      </button>
      <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions"
        data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
      </button>
    </div>
  </div>
</section>


<jsp:include page="include/footer.jsp" />