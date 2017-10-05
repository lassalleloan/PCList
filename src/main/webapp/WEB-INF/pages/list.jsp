<%@include file="includes/header.jsp" %>

<!-- Page Header -->
<header class="masthead" style="background-image: url('static/img/list-bg.jpg')">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
                <div class="page-heading">
                    <h1>List of PC</h1>
                </div>
            </div>
        </div>
    </div>
</header>

<!-- List Content -->
<article>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
                <table>
                    <tr>
                        <th>
                            Brand
                        </th>
                        <th>
                            Processor
                        </th>
                        <th>
                            Number of Cores
                        </th>
                        <th>
                            Frequency
                        </th>
                        <th>
                            Memory
                        </th>
                        <th>
                            Memory Size
                        </th>
                        <th>
                            Graphics
                        </th>
                        <th>
                            Price
                        </th>
                        <th colspan="2">
                            <a class="nav-link" href="/pclist/create">Add</a>
                        </th>
                    </tr>
                    <c:forEach items="${cpuList}" var="cpu">
                        <tr align="center">
                            <td>
                                    ${cpu.brand}
                            </td>
                            <td>
                                    ${cpu.nbCores}
                            </td>
                            <td>
                                    ${cpu.frequency}GHz
                            </td>
                            <td>
                                <a class="nav-link" href="/pclist/edit">Edit</a>
                            </td>
                            <td>
                                <a class="nav-link" href="/pclist/edit">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</article>

<hr>

<%@include file="includes/footer.jsp" %>
