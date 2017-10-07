<%@include file="includes/header.jsp" %>

<!-- Page Header -->
<header class="masthead" style="background-image: url('static/img/list-bg.jpg')">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
                <div class="page-heading" style="text-align: center">
                    <h1>List of ${titlePage}</h1>
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
                        <td colspan="10">
                            <a class="nav-link" style="display: inline" href="/pclist/list">All</a>
                            <a class="nav-link" style="display: inline" href="/pclist/list?what=pc">Pc</a>
                            <a class="nav-link" style="display: inline" href="/pclist/list?what=cpu">Processor</a>
                            <a class="nav-link" style="display: inline" href="/pclist/list?what=ram">Memory</a>
                            <a class="nav-link" style="display: inline" href="/pclist/list?what=gpu">Graphic</a>
                        </td>
                    </tr>
                    <c:if test="${rowsAffected != null && what != null}">
                        <tr>
                            <td colspan="10">
                                    ${rowsAffected} ${what} was deleted
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${allList || pcList != null}">
                        <tr>
                            <td colspan="10">
                                <br>
                                <br>
                                <br>
                            </td>
                        </tr>
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
                                Graphic
                            </th>
                            <th>
                                Price
                            </th>
                            <th colspan="2">
                                <a class="nav-link" href="/pclist/create?what=pc">Add</a>
                            </th>
                        </tr>
                        <c:forEach items="${pcList}" var="pc">
                            <tr>
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
                                    <a class="nav-link" href="/pclist/edit?what=pc&id=${pc.idPc}">Edit</a>
                                </td>
                                <td>
                                    <a class="nav-link" href="/pclist/delete?what=pc&id=${pc.idPc}">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${allList || cpuList != null}">
                        <tr>
                            <td colspan="10">
                                <br>
                                <br>
                                <br>
                            </td>
                        </tr>
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
                            <th>
                                Number of Cores
                            </th>
                            <th colspan="6">
                                Frequency
                            </th>
                            <th colspan="2">
                                <a class="nav-link" href="/pclist/create?what=cpu">Add</a>
                            </th>
                        </tr>
                        <c:forEach items="${cpuList}" var="cpu">
                            <tr>
                                <td>
                                        ${cpu.brand}
                                </td>
                                <td>
                                        ${cpu.cores}
                                </td>
                                <td colspan="6">
                                        ${cpu.frequency}GHz
                                </td>
                                <td>
                                    <a class="nav-link" href="/pclist/edit?what=cpu&id=${cpu.idCpu}">Edit</a>
                                </td>
                                <td>
                                    <a class="nav-link" href="/pclist/delete?what=cpu&id=${cpu.idCpu}">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${allList || ramList != null}">
                        <tr>
                            <td colspan="10">
                                <br>
                                <br>
                                <br>
                            </td>
                        </tr>
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
                            <th colspan="7">
                                Size
                            </th>
                            <th colspan="2">
                                <a class="nav-link" href="/pclist/create?what=ram">Add</a>
                            </th>
                        </tr>
                        <c:forEach items="${ramList}" var="ram">
                            <tr>
                                <td>
                                        ${ram.brand}
                                </td>
                                <td colspan="7">
                                        ${ram.size}GB
                                </td>
                                <td>
                                    <a class="nav-link" href="/pclist/edit?what=ram&id=${ram.idRam}">Edit</a>
                                </td>
                                <td>
                                    <a class="nav-link" href="/pclist/delete?what=ram&id=${ram.idRam}">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${allList || gpuList != null}">
                        <tr>
                            <td colspan="10">
                                <br>
                                <br>
                                <br>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="10">
                                <a class="nav-link" href="/pclist/list?what=gpu">
                                    <h3>Graphic</h3>
                                </a>
                            </td>
                        </tr>
                        <tr>
                            <th colspan="8">
                                Brand
                            </th>
                            <th colspan="2">
                                <a class="nav-link" href="/pclist/create?what=gpu">Add</a>
                            </th>
                        </tr>
                        <c:forEach items="${gpuList}" var="gpu">
                            <tr>
                                <td colspan="8">
                                        ${gpu.brand}
                                </td>
                                <td>
                                    <a class="nav-link" href="/pclist/edit?what=gpu&id=${gpu.idGpu}">Edit</a>
                                </td>
                                <td>
                                    <a class="nav-link" href="/pclist/delete?what=gpu&id=${gpu.idGpu}">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </table>
            </div>
        </div>
    </div>
</article>

<hr>

<%@include file="includes/footer.jsp" %>
