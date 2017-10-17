<%--
 - author Loan Lassalle (loan.lassalle@heig-vd.ch)
 - author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 - since 13.09.2017
 --%>
<%@include file="includes/header.jsp" %>

<!-- Page Header -->
<header class="masthead text-center" style="background-image: url('static/img/list-bg.jpg')">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
                <div class="page-heading">
                    <h1>${pageTitle}</h1>
                </div>
            </div>
        </div>
    </div>
</header>

<!-- List Content -->
<article class="text-center">

    <!-- Links for all things -->
    <div class="container">
        <div class="row">
            <div class="mx-auto">
                <table class="table">
                    <thead class="tab-header-area">
                    <tr>
                        <th><a class="nav-link" href="<c:url value="/list"/>">All</a></th>
                        <th><a class="nav-link" href="<c:url value="/list?product=pc"/>">PC</a></th>
                        <th><a class="nav-link" href="<c:url value="/list?product=cpu"/>">Processor</a></th>
                        <th><a class="nav-link" href="<c:url value="/list?product=ram"/>">Memory</a></th>
                        <th><a class="nav-link" href="<c:url value="/list?product=gpu"/>">Graphic</a></th>
                    </tr>
                    </thead>
                </table>
                <br>
            </div>
        </div>
    </div>

    <!-- Information message during an action -->
    <c:if test="${!allList && informationMessage != null}">
        <div class="container">
            <div class="row">
                <div class="mx-auto">
                    <p class="p-3" style="color:red">${informationMessage}</p><br>
                </div>
            </div>
        </div>
    </c:if>

    <!-- List of pc -->
    <c:if test="${allList || pcList != null}">
        <div class="container">
            <div class="row">
                <div class="mx-auto">
                    <a class="nav-link" href="<c:url value="/list?product=pc"/>"><h3>PC</h3></a>
                    <br>
                    <table class="table">
                        <thead class="tab-header-area">
                        <tr>
                            <th><a class="nav-link"
                                   href="/pclist/list?product=pc&col=brand&order=${order == "DESC" ? "ASC" : "DESC"}">Brand</a>
                            </th>
                            <th><a class="nav-link"
                                   href="/pclist/list?product=pc&col=cpuBrand&order=${order == "DESC" ? "ASC" : "DESC"}">Processor</a>
                            </th>
                            <th><a class="nav-link"
                                   href="/pclist/list?product=pc&col=cpuCores&order=${order == "DESC" ? "ASC" : "DESC"}">Number
                                of Cores</a></th>
                            <th><a class="nav-link"
                                   href="/pclist/list?product=pc&col=cpuFrequency&order=${order == "DESC" ? "ASC" : "DESC"}">Frequency</a>
                            </th>
                            <th><a class="nav-link"
                                   href="/pclist/list?product=pc&col=ramBrand&order=${order == "DESC" ? "ASC" : "DESC"}">Memory</a>
                            </th>
                            <th><a class="nav-link"
                                   href="/pclist/list?product=pc&col=ramSize&order=${order == "DESC" ? "ASC" : "DESC"}">Memory
                                Size</a></th>
                            <th><a class="nav-link"
                                   href="/pclist/list?product=pc&col=gpuBrand&order=${order == "DESC" ? "ASC" : "DESC"}">Graphic</a>
                            </th>
                            <th><a class="nav-link"
                                   href="/pclist/list?product=pc&col=price&order=${order == "DESC" ? "ASC" : "DESC"}">Price</a>
                            </th>
                            <c:if test="${!allList}">
                                <th colspan="2"><a class="nav-link" href="<c:url value="/create?product=pc"/>">Add</a>
                                </th>
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
                                    <td><a class="nav-link"
                                           href="<c:url value="/edit?product=pc&id=${pc.idPc}"/>">Edit</a></td>
                                    <td><a class="nav-link" href="<c:url value="/delete?product=pc&id=${pc.idPc}"/>">Delete</a>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                        <c:if test="${allList}">
                            <tr>
                                <td colspan="8"><a class="nav-link" href="<c:url value="/list?product=pc"/>">...</a>
                                </td>
                            </tr>
                        </c:if>
                    </table>
                    <br>
                </div>
            </div>
        </div>
    </c:if>

    <!-- List of cpu -->
    <c:if test="${allList || cpuList != null}">
        <div class="container">
            <div class="row">
                <div class="mx-auto">
                    <a class="nav-link" href="<c:url value="/list?product=cpu"/>"><h3>Processor</h3></a>
                    <br>
                    <table class="table">
                        <thead class="tab-header-area">
                        <tr>
                            <th><a class="nav-link"
                                   href="/pclist/list?product=cpu&col=brand&order=${order == "DESC" ? "ASC" : "DESC"}">Brand</a>
                            </th>
                            <th><a class="nav-link"
                                   href="/pclist/list?product=cpu&col=cores&order=${order == "DESC" ? "ASC" : "DESC"}">Number
                                of Cores</a></th>
                            <th><a class="nav-link"
                                   href="/pclist/list?product=cpu&col=frequency&order=${order == "DESC" ? "ASC" : "DESC"}">Frequency</a>
                            </th>
                            <c:if test="${!allList}">
                                <th colspan="2"><a class="nav-link" href="<c:url value="/create?product=cpu"/>">Add</a>
                                </th>
                            </c:if>
                        </tr>
                        </thead>
                        <c:forEach items="${cpuList}" var="cpu">
                            <tr>
                                <td>${cpu.brand}</td>
                                <td>${cpu.cores}</td>
                                <td>${cpu.frequency}GHz</td>
                                <c:if test="${!allList}">
                                    <td><a class="nav-link" href="<c:url value="/edit?product=cpu&id=${cpu.idCpu}"/>">Edit</a>
                                    </td>
                                    <td><a class="nav-link"
                                           href="<c:url value="/delete?product=cpu&id=${cpu.idCpu}"/>">Delete</a>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                        <c:if test="${allList}">
                            <tr>
                                <td colspan="3"><a class="nav-link" href="<c:url value="/list?product=cpu"/>">...</a>
                                </td>
                            </tr>
                        </c:if>
                    </table>
                    <br>
                </div>
            </div>
        </div>
    </c:if>

    <!-- List of ram -->
    <c:if test="${allList || ramList != null}">
        <div class="container">
            <div class="row">
                <div class="mx-auto">
                    <a class="nav-link" href="<c:url value="/list?product=ram"/>"><h3>Memory</h3></a>
                    <br>
                    <table class="table">
                        <thead class="tab-header-area">
                        <tr>
                            <th><a class="nav-link"
                                   href="/pclist/list?product=ram&col=brand&order=${order == "DESC" ? "ASC" : "DESC"}">Brand</a>
                            </th>
                            <th><a class="nav-link"
                                   href="/pclist/list?product=ram&col=size&order=${order == "DESC" ? "ASC" : "DESC"}">Size</a>
                            </th>
                            <c:if test="${!allList}">
                                <th colspan="2"><a class="nav-link" href="<c:url value="/create?product=ram"/>">Add</a>
                                </th>
                            </c:if>
                        </tr>
                        </thead>
                        <c:forEach items="${ramList}" var="ram">
                            <tr>
                                <td>${ram.brand}</td>
                                <td>${ram.size}GB</td>
                                <c:if test="${!allList}">
                                    <td><a class="nav-link" href="<c:url value="/edit?product=ram&id=${ram.idRam}"/>">Edit</a>
                                    </td>
                                    <td><a class="nav-link" href="<c:url value="/delete?product=ram&id=${ram.idRam}"/>">Delete</a>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                        <c:if test="${allList}">
                            <tr>
                                <td colspan="2"><a class="nav-link" href="<c:url value="/list?product=ram"/>">...</a>
                                </td>
                            </tr>
                        </c:if>
                    </table>
                    <br>
                </div>
            </div>
        </div>
    </c:if>

    <!-- List of gpu -->
    <c:if test="${allList || gpuList != null}">
        <div class="container">
            <div class="row">
                <div class="mx-auto">
                    <a class="nav-link" href="<c:url value="/list?product=gpu"/>"><h3>Graphic</h3></a>
                    <br>
                    <table class="table">
                        <thead class="tab-header-area">
                        <tr>
                            <th><a class="nav-link"
                                   href="/pclist/list?product=gpu&col=brand&order=${order == "DESC" ? "ASC" : "DESC"}">Brand</a>
                            </th>
                            <c:if test="${!allList}">
                                <th colspan="2"><a class="nav-link" href="<c:url value="/create?product=gpu"/>">Add</a>
                                </th>
                            </c:if>
                        </tr>
                        </thead>
                        <c:forEach items="${gpuList}" var="gpu">
                            <tr>
                                <td>${gpu.brand}</td>
                                <c:if test="${!allList}">
                                    <td><a class="nav-link" href="<c:url value="/edit?product=gpu&id=${gpu.idGpu}"/>">Edit</a>
                                    </td>
                                    <td><a class="nav-link" href="<c:url value="/delete?product=gpu&id=${gpu.idGpu}"/>">Delete</a>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                        <c:if test="${allList}">
                            <tr>
                                <td><a class="nav-link" href="<c:url value="/list?product=gpu"/>">...</a></td>
                            </tr>
                        </c:if>
                    </table>
                    <br>
                </div>
            </div>
        </div>
    </c:if>

    <!-- Links for others pages -->
    <c:if test="${!allList}">
        <div class="container">
            <div class="row">
                <div class="mx-auto">
                    <br>
                    <table>
                        <tr>
                            <td><a class="nav-link" href="<c:url value="${firstPageLink}"/>">Page 1</a></td>
                            <td><a class="nav-link" href="<c:url value="${previousPageLink}"/>">Previous page</a></td>
                            <td><a class="nav-link" href="<c:url value="${nextPageLink}"/>">Next page</a></td>
                            <td><a class="nav-link" href="<c:url value="${lastPageLink}"/>">Page ${pageCount}</a></td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </c:if>

</article>

<hr>

<%@include file="includes/footer.jsp" %>
