<jsp:include page="../include/header.jsp" />

<section class="pt-5 bg-bisque">
    <div class="container text-center">
        <h1 class="m-0">500 Server Error</h1>
    </div>
</section>

<section class="pt-5 pb-5 bg-bisque">
    <div class="container">
        <c:if test="${not empty requestUrl}">
            <p>${requestUrl}</p>
        </c:if>
        <h3>Stack Trace</h3>
        <c:if test="${not empty message}">
            <p>${message}</p>
        </c:if>
        <c:if test="${not empty stackTrace}">
            <p>${stackTrace}</p>
        </c:if>
        <h3>Root Cause</h3>
        <c:if test="${not empty rootcause}">
            <p>${rootcause}</p>
        </c:if>
        <c:if test="${not empty rootTrace}">
            <p>${roottrace}</p>
        </c:if>
    </div>
</section>

<jsp:include page="../include/footer.jsp" />