<%@include file="includes/header.jsp" %>

<!-- Page Header -->
<header class="masthead" style="background-image: url('static/img/edit-bg.jpg')">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
                <div class="page-heading">
                    <h1>Edit a ${titlePage}</h1>
                </div>
            </div>
        </div>
    </div>
</header>

<!-- Main Content -->
<div class="container">
    <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
            <form method="post">
                <table>
                    <c:if test="${pc != null}">
                        <tr>
                            <td colspan="10">
                                <a class="nav-link" href="/pclist/list?what=pc">
                                    <h3>PC</h3>
                                </a>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Brand
                            </th>
                            <td>
                                    ${pc.brand}
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Processor
                            </th>
                            <td>
                                    ${pc.cpu.brand}
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Number of Cores
                            </th>
                            <td>
                                    ${pc.cpu.cores}
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Frequency
                            </th>
                            <td>
                                    ${pc.cpu.frequency}GHz
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Memory
                            </th>
                            <td>
                                    ${pc.ram.brand}
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Memory Size
                            </th>
                            <td>
                                    ${pc.ram.size}GB
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Graphic
                            </th>
                            <td>
                                    ${pc.gpu.brand}
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Price
                            </th>
                            <td>
                                    ${pc.price}.-
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${cpu != null}">
                        <tr>
                            <td colspan="10">
                                <a class="nav-link" href="/pclist/list?what=cpu">
                                    <h3>Processor</h3>
                                </a>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Brand
                            </th>
                            <td>
                                    ${cpu.brand}
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Number of Cores
                            </th>
                            <td>
                                    ${cpu.cores}
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Frequency
                            </th>
                            <td>
                                    ${cpu.frequency}
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${ram != null}">
                        <tr>
                            <td colspan="10">
                                <a class="nav-link" href="/pclist/list?what=ram">
                                    <h3>Memory</h3>
                                </a>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Brand
                            </th>
                            <td>
                                    ${ram.brand}
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Number of Cores
                            </th>
                            <td>
                                    ${ram.size}GB
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${gpu != null}">
                        <tr>
                            <td colspan="10">
                                <a class="nav-link" href="/pclist/list?what=gpu">
                                    <h3>Graphic</h3>
                                </a>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Brand
                            </th>
                            <td>
                                    ${gpu.brand}
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td colspan="2" align="right">
                            <input type="submit" value="Edit">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>

<hr>

<%@include file="includes/footer.jsp" %>