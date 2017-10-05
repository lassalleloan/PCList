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
                <h3>Pc</h3>
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
                    <c:forEach items="${pcList}" var="pc">
                        <tr align="center">
                            <td>
                                    ${pc.brand}
                            </td>
                            <td>
                                    ${pc.cpu.brand}
                            </td>
                            <td>
                                    ${pc.cpu.cores}
                            </td>
                            <td>
                                    ${pc.cpu.frequency}GHz
                            </td>
                            <td>
                                    ${pc.ram.brand}
                            </td>
                            <td>
                                    ${pc.ram.size}GB
                            </td>
                            <td>
                                    ${pc.gpu.brand}
                            </td>
                            <td>
                                    ${pc.price}.-
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
                <br>
                <br>
                <br>
                <h3>Cpu</h3>
                <table>
                    <tr>
                        <th>
                            Brand
                        </th>
                        <th>
                            Number of Cores
                        </th>
                        <th>
                            Frequency
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
                                    ${cpu.cores}
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
                <br>
                <br>
                <br>
                <h3>Ram</h3>
                <table>
                    <tr>
                        <th>
                            Brand
                        </th>
                        <th>
                            Size
                        </th>
                        <th colspan="2">
                            <a class="nav-link" href="/pclist/create">Add</a>
                        </th>
                    </tr>
                    <c:forEach items="${ramList}" var="ram">
                        <tr align="center">
                            <td>
                                    ${ram.brand}
                            </td>
                            <td>
                                    ${ram.size}GB
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
                <br>
                <br>
                <br>
                <h3>Gpu</h3>
                <table>
                    <tr>
                        <th>
                            Brand
                        </th>
                        <th colspan="2">
                            <a class="nav-link" href="/pclist/create">Add</a>
                        </th>
                    </tr>
                    <c:forEach items="${gpuList}" var="gpu">
                        <tr align="center">
                            <td>
                                    ${gpu.brand}
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
