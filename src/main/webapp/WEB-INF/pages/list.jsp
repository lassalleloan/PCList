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
                <table align="center" style="text-align: center">
                    <tr>
                        <td colspan="10">
                            <a class="nav-link" style="display: inline" href="/pclist/list">All</a>
                            <a class="nav-link" style="display: inline" href="/pclist/list?product=pc">Pc</a>
                            <a class="nav-link" style="display: inline" href="/pclist/list?product=cpu">Processor</a>
                            <a class="nav-link" style="display: inline" href="/pclist/list?product=ram">Memory</a>
                            <a class="nav-link" style="display: inline" href="/pclist/list?product=gpu">Graphic</a>
                        </td>
                    </tr>
                    <c:if test="${rowsAffected > 0 && product != null && action != null}">
                        <tr>
                            <td colspan="10">
                                <br>
                                <br>
                                <br>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="10">
                                    ${rowsAffected}
                                    ${product}
                                    <c:choose>
                                    <c:when test="${rowsAffected == 1}"> was</c:when>
                                <c:otherwise>s were</c:otherwise>
                            </c:choose>
                                    ${action}
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
                                <a class="nav-link" href="/pclist/list?product=pc">
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
                            <c:if test="${!allList && pcList != null}">
                                <th colspan="2">
                                    <a class="nav-link" href="/pclist/create?product=pc">Add</a>
                                </th>
                            </c:if>
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
                                <c:if test="${!allList && pcList != null}">
                                    <td>
                                        <a class="nav-link" href="/pclist/edit?product=pc&id=${pc.idPc}">Edit</a>
                                    </td>
                                    <td>
                                        <a class="nav-link" href="/pclist/delete?product=pc&id=${pc.idPc}">Delete</a>
                                    </td>
                                </c:if>
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
                                <a class="nav-link" href="/pclist/list?product=cpu">
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
                            <c:if test="${!allList && cpuList != null}">
                                <th colspan="2">
                                    <a class="nav-link" href="/pclist/create?product=cpu">Add</a>
                                </th>
                            </c:if>
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
                                <c:if test="${!allList && cpuList != null}">
                                    <td>
                                        <a class="nav-link" href="/pclist/edit?product=cpu&id=${cpu.idCpu}">Edit</a>
                                    </td>
                                    <td>
                                        <a class="nav-link" href="/pclist/delete?product=cpu&id=${cpu.idCpu}">Delete</a>
                                    </td>
                                </c:if>
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
                                <a class="nav-link" href="/pclist/list?product=ram">
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
                            <c:if test="${!allList && ramList != null}">
                                <th colspan="2">
                                    <a class="nav-link" href="/pclist/create?product=ram">Add</a>
                                </th>
                            </c:if>
                        </tr>
                        <c:forEach items="${ramList}" var="ram">
                            <tr>
                                <td>
                                        ${ram.brand}
                                </td>
                                <td colspan="7">
                                        ${ram.size}GB
                                </td>
                                <c:if test="${!allList && ramList != null}">
                                    <td>
                                        <a class="nav-link" href="/pclist/edit?product=ram&id=${ram.idRam}">Edit</a>
                                    </td>
                                    <td>
                                        <a class="nav-link" href="/pclist/delete?product=ram&id=${ram.idRam}">Delete</a>
                                    </td>
                                </c:if>
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
                                <a class="nav-link" href="/pclist/list?product=gpu">
                                    <h3>Graphic</h3>
                                </a>
                            </td>
                        </tr>
                        <tr>
                            <th colspan="8">
                                Brand
                            </th>
                            <c:if test="${!allList && gpuList != null}">
                                <th colspan="2">
                                    <a class="nav-link" href="/pclist/create?product=gpu">Add</a>
                                </th>
                            </c:if>
                        </tr>
                        <c:forEach items="${gpuList}" var="gpu">
                            <tr>
                                <td colspan="8">
                                        ${gpu.brand}
                                </td>
                                <c:if test="${!allList && gpuList != null}">
                                    <td>
                                        <a class="nav-link" href="/pclist/edit?product=gpu&id=${gpu.idGpu}">Edit</a>
                                    </td>
                                    <td>
                                        <a class="nav-link" href="/pclist/delete?product=gpu&id=${gpu.idGpu}">Delete</a>
                                    </td>
                                </c:if>
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
