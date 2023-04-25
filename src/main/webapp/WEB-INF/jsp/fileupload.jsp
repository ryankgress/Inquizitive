<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <jsp:include page="include/header.jsp" />

    <section class="pt-3 pb-3 bg-light-grey">
        <div class="container text-center">
            <h1>File Upload</h1>
        </div>
    </section>

    <section>
        <div class="container mt-3 col-5" style="margin-bottom: 20%;">
            <form action="/fileupload" method="post" enctype="multipart/form-data">
                <div class="mb-3">
                    <label for="pic" class="form-label">File Upload</label>
                    <input type="file" class="form-control" id="pic" name="pic">
                </div>



                <div class="d-flex flexcolumn justify-content-center">
                    <button class="btn btn-primary">Upload</button>
                </div>
            </form>
            <c:if test="${not empty fileUrl}">
                <div class="container text-center pt-4">
                    <img src="${fileUrl}" alt="pic">
                </div>
            </c:if>

        </div>

    </section>




    <jsp:include page="include/footer.jsp" />