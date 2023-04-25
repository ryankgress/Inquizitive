    <jsp:include page="include/header.jsp" />
    <section>
        <div class="container mt-5" style="background-color: blanchedalmond;">
            <h1 style="color:rgb(35, 87, 94)">First Title</h1>

            <p>
                Here's a paragraph with some <strong>bold text</strong> in it
                And now, here's a <a href="http://www.dndbeyond.com" target="_blank">link</a>

            <table border="1">
                <tr>
                    <td>Column 1</td>
                    <td>Column 2</td>
                    <td>Column 3</td>
                    <td>Column 4</td>
                </tr>
                <tr>
                    <td>data 1</td>
                    <td>data 2</td>
                    <td>data 3</td>
                </tr>
                <tr>
                    <td>data 4</td>
                </tr>
            </table>
            </p>

            <ul>
                <li>Item 1</li>
                <li>Item 2</li>
                <li>Item 3</li>
            </ul>

            <script>
                console.log("Hello World")
                console.log();

                var person = { name: "John", age: 30, city: "New York", eyeColor: "blue" };
                var myArray = Object.values(person);
                console.log(myArray)

            </script>

            <img width=400px src="https://www.dndbeyond.com/attachments/10/329/druid-featured.jpg">
        </div>
    </section>

    <section>
        <div class="pt-5 pb-5 text-center" style="background-color: cadetblue; color: white;">
            <h2>Second Section</h2>

        </div>
    </section>

    <jsp:include page="include/footer.jsp" />

