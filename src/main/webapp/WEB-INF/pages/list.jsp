<%@include file="includes/header.jsp" %>

<!-- Page Header -->
<header class="masthead" style="background-image: url('static/img/list-bg.jpg')">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
                <div class="page-heading text-md-center">
                    <h1>List of ${pageTitle}</h1>
                </div>
            </div>
        </div>
    </div>
</header>

<!-- List Content -->
<article class="text-center">

    <div class="container">
        <div class="row">
            <div class="mx-auto">
                <table class="table">
                    <thead class="tab-header-area">
                    <tr>
                        <th><a class="nav-link" style="display: inline" href="/pclist/list">All</a></th>
                        <th><a class="nav-link" style="display: inline" href="/pclist/list?product=pc">Pc</a></th>
                        <th><a class="nav-link" style="display: inline" href="/pclist/list?product=cpu">Processor</a>
                        </th>
                        <th><a class="nav-link" style="display: inline" href="/pclist/list?product=ram">Memory</a></th>
                        <th><a class="nav-link" style="display: inline" href="/pclist/list?product=gpu">Graphic</a></th>
                    </tr>
                    </thead>
                </table>
                <br>
            </div>
        </div>
    </div>

    <c:if test="${rowsAffected > 0 && product != null && action != null}">
        <div class="container">
            <div class="row">
                <div class="mx-auto">
                    <p class="p-3">${rowsAffected} ${product} was ${action}</p><br>
                </div>
            </div>
        </div>
    </c:if>

    <c:if test="${allList || pcList != null}">
        <div class="container">
            <div class="row">
                <div class="mx-auto">
                    <a class="nav-link" href="/pclist/list?product=pc"><h3>Pc</h3></a>
                    <br>
                    <table class="table">
                        <thead class="tab-header-area">
                        <tr>
                            <th>Brand</th>
                            <th>Processor</th>
                            <th>Number of Cores</th>
                            <th>Frequency</th>
                            <th>Memory</th>
                            <th>Memory Size</th>
                            <th>Graphic</th>
                            <th>Price</th>
                            <c:if test="${!allList}">
                                <th colspan="2"><a class="nav-link" href="/pclist/create?product=pc">Add</a></th>
                            </c:if>
                        </tr>
                        </thead>
                        <c:forEach items="${pcList}" var="pc">
                            <tr>
                                <td>${pc.brand}</td>
                                <td>${pc.cpu.brand}</td>
                                <td>${pc.cpu.cores}</td>
                                <td>${pc.cpu.frequency}GHz</td>
                                <td>${pc.ram.brand}</td>
                                <td>${pc.ram.size}GB</td>
                                <td>${pc.gpu.brand}</td>
                                <td>${pc.price}.-</td>
                                <c:if test="${!allList}">
                                    <td><a class="nav-link" href="/pclist/edit?product=pc&id=${pc.idPc}">Edit</a></td>
                                    <td><a class="nav-link" href="/pclist/delete?product=pc&id=${pc.idPc}">Delete</a>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </table>
                    <br>
                </div>
            </div>
        </div>
    </c:if>

    <c:if test="${allList || cpuList != null}">
        <div class="container">
            <div class="row">
                <div class="mx-auto">
                    <a class="nav-link" href="/pclist/list?product=cpu"><h3>Processor</h3></a>
                    <br>
                    <table class="table">
                        <thead class="tab-header-area">
                        <tr>
                            <th>Brand</th>
                            <th>Number of Cores</th>
                            <th>Frequency</th>
                            <c:if test="${!allList}">
                                <th colspan="2"><a class="nav-link" href="/pclist/create?product=cpu">Add</a></th>
                            </c:if>
                        </tr>
                        </thead>
                        <c:forEach items="${cpuList}" var="cpu">
                            <tr>
                                <td>${cpu.brand}</td>
                                <td>${cpu.cores}</td>
                                <td>${cpu.frequency}GHz</td>
                                <c:if test="${!allList}">
                                    <td><a class="nav-link" href="/pclist/edit?product=cpu&id=${cpu.idCpu}">Edit</a>
                                    </td>
                                    <td><a class="nav-link" href="/pclist/delete?product=cpu&id=${cpu.idCpu}">Delete</a>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </table>
                    <br>
                </div>
            </div>
        </div>
    </c:if>

    <c:if test="${allList || ramList != null}">
        <div class="container">
            <div class="row">
                <div class="mx-auto">
                    <a class="nav-link" href="/pclist/list?product=ram"><h3>Memory</h3></a>
                    <br>
                    <table class="table">
                        <thead class="tab-header-area">
                        <tr>
                            <th>Brand</th>
                            <th>Size</th>
                            <c:if test="${!allList}">
                                <th colspan="2"><a class="nav-link" href="/pclist/create?product=ram">Add</a></th>
                            </c:if>
                        </tr>
                        </thead>
                        <c:forEach items="${ramList}" var="ram">
                            <tr>
                                <td>${ram.brand}</td>
                                <td>${ram.size}GB</td>
                                <c:if test="${!allList}">
                                    <td><a class="nav-link" href="/pclist/edit?product=ram&id=${ram.idRam}">Edit</a>
                                    </td>
                                    <td><a class="nav-link" href="/pclist/delete?product=ram&id=${ram.idRam}">Delete</a>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </table>
                    <br>
                </div>
            </div>
        </div>
    </c:if>

    <c:if test="${allList || gpuList != null}">
        <div class="container">
            <div class="row">
                <div class="mx-auto">
                    <a class="nav-link" href="/pclist/list?product=gpu"><h3>Graphic</h3></a>
                    <br>
                    <table class="table">
                        <thead class="tab-header-area">
                        <tr>
                            <th>Brand</th>
                            <c:if test="${!allList}">
                                <th colspan="2"><a class="nav-link" href="/pclist/create?product=gpu">Add</a></th>
                            </c:if>
                        </tr>
                        </thead>
                        <c:forEach items="${gpuList}" var="gpu">
                            <tr>
                                <td>${gpu.brand}</td>
                                <c:if test="${!allList}">
                                    <td><a class="nav-link" href="/pclist/edit?product=gpu&id=${gpu.idGpu}">Edit</a>
                                    </td>
                                    <td><a class="nav-link" href="/pclist/delete?product=gpu&id=${gpu.idGpu}">Delete</a>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </table>
                    <br>
                </div>
            </div>
        </div>
    </c:if>

</article>

<hr>

<%@include file="includes/footer.jsp" %>
