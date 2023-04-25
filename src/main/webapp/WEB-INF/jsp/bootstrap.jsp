<jsp:include page="include/header.jsp" />

<h1 class = "page-header">Title of Page</h1>
    <p>
        <a class = "hoverTest" href="http://google.com">This is a link</a>
    </p>
    <div class="container" style="margin-top: 30px;">
        <table class="table table-striped table-hover">
            <thead class="table-dark">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">First</th>
                    <th scope="col">Last</th>
                    <th scope="col">Handle</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th scope="row">1</th>
                    <td>Mark</td>
                    <td>Otto</td>
                    <td>@mdo</td>
                </tr>
                <tr>
                    <th scope="row">2</th>
                    <td>Jacob</td>
                    <td>Thornton</td>
                    <td>@fat</td>
                </tr>
                <tr>
                    <th scope="row">3</th>
                    <td colspan="2">Larry the Bird</td>
                    <td>@twitter</td>
                </tr>
            </tbody>
        </table>

        <div style="text-align: center;">
            <button type="button" class="btn btn-secondary">Next</button>
            <button type="button" class="btn btn-primary">Save</button>
        </div>
    </div>

<jsp:include page="include/footer.jsp" />