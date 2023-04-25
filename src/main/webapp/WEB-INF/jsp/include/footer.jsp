<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
  <script>
    function toRegister() {
      location.href = "/register";
    }
  </script>

  <section class="Footer mt-5">
    <footer class="text-center text-white bg-dark navbar-fixed-bottom">
      <div class="container p-4 pb-0">
        <section class="">
          <sec:authorize access="isAuthenticated()">
            <div>
              <p class="d-flex justify-content-center align-items-center">
                <span class="me-3">Like Inquizitive? Leave us a review!</span>
                <button type="button" class="btn btn-outline-light btn-rounded" onclick="toRegister()">
                  Take Me There
                </button>
              </p>
            </div>
          </sec:authorize>
          <sec:authorize access="!isAuthenticated()">
            <div>
              <p class="d-flex justify-content-center align-items-center">
                <span class="me-3">Create an account now</span>
                <button type="button" class="btn btn-outline-light btn-rounded" onclick="toRegister()">
                  Sign up!
                </button>
              </p>
            </div>
          </sec:authorize>

        </section>
      </div>
      <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2);">© 2023 Copyright:
        <a class="text-white" href="https://perscholas.instructure.com/courses/1312/pages/java-home">Inquizitive.com</a>
      </div>
    </footer>
  </section>

  </body>

  </html>