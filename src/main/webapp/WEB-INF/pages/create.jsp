<%--
 - author Loan Lassalle (loan.lassalle@heig-vd.ch)
 - author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 --%>
<%@include file="includes/header.jsp" %>

<!-- Page Header -->
<header class="masthead text-center" style="background-image: url('static/img/create-bg.jpg')">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
                <div class="page-heading text-md-center">
                    <h1>${pageTitle}</h1>
                </div>
            </div>
        </div>
    </div>
</header>

<!-- List Content -->
<article class="text-center">

    <!-- Links for all things -->
    <c:if test="${isCreatePath}">
        <div class="container">
            <div class="row">
                <div class="mx-auto">
                    <table class="table">
                        <thead class="tab-header-area">
                        <tr>
                            <th><a class="nav-link" href="<c:url value="/create?product=pc"/>">PC</a></th>
                            <th><a class="nav-link" href="<c:url value="/create?product=cpu"/>">Processor</a></th>
                            <th><a class="nav-link" href="<c:url value="/create?product=ram"/>">Memory</a></th>
                            <th><a class="nav-link" href="<c:url value="/create?product=gpu"/>">Graphic</a></th>
                        </tr>
                        </thead>
                    </table>
                    <br>
                </div>
            </div>
        </div>
    </c:if>

    <!-- Information message during an action -->
    <c:if test="${informationMessage != null}">
        <div class="container">
            <div class="row">
                <div class="mx-auto">
                    <p class="p-3" style="color:red">${informationMessage}</p><br>
                </div>
            </div>
        </div>
    </c:if>

    <!-- Form for create pc -->
    <c:if test="${pcBrandList != null && cpuList != null && ramList != null && gpuList != null}">
        <div class="container">
            <div class="row">
                <div class="mx-auto">
                    <a class="nav-link" href="<c:url value="/list?product=pc"/>"><h3>PC</h3></a>
                    <form method="post">
                        <table class="table">
                            <thead class="tab-header-area">
                            <tr>
                                <th>Brand</th>
                                <th>Processor</th>
                                <th>Memory</th>
                                <th>Graphic</th>
                                <th>Price</th>
                            </tr>
                            </thead>
                            <tr>
                                <td>
                                    <label><input name="pcBrand" type="text" list="pcBrandList" minlength="1"
                                                  maxlength="45" value="${pc.brand}"></label>
                                    <datalist id="pcBrandList">
                                        <c:forEach items="${pcBrandList}" var="pcBrand">
                                            <option value="${pcBrand}">${pcBrand}</option>
                                        </c:forEach>
                                    </datalist>
                                </td>
                                <td>
                                    <label>
                                        <select name="idCpu">
                                            <c:forEach items="${cpuList}" var="cpu">
                                                <option value="${cpu.idCpu}" <c:if
                                                        test="${pc.idCpu == cpu.idCpu}"> selected </c:if>>${cpu.brand} ${cpu.cores} ${cpu.frequency}GHz
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </label>
                                </td>
                                <td>
                                    <label>
                                        <select name="idRam">
                                            <c:forEach items="${ramList}" var="ram">
                                                <option value="${ram.idRam}" <c:if
                                                        test="${pc.idRam == ram.idRam}"> selected </c:if>>${ram.brand} ${ram.size}GB
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </label>
                                </td>
                                <td>
                                    <label>
                                        <select name="idGpu">
                                            <c:forEach items="${gpuList}" var="gpu">
                                                <option value="${gpu.idGpu}" <c:if
                                                        test="${pc.idGpu == gpu.idGpu}"> selected </c:if>>${gpu.brand}</option>
                                            </c:forEach>
                                        </select>
                                    </label>
                                </td>
                                <td><label><input name="pcPrice" type="number" step="0.01" min="0.01"
                                                  value="${pc.price}"
                                                  required>.-</label></td>
                            </tr>
                            <tr>
                                <td colspan="5"><input type="submit" value="Create"></td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </c:if>

    <!-- Form for create cpu -->
    <c:if test="${cpuBrandList != null}">
        <div class="container">
            <div class="row">
                <div class="mx-auto">
                    <a class="nav-link" href="<c:url value="/list?product=cpu"/>"><h3>Processor</h3></a>
                    <form method="post">
                        <table class="table">
                            <thead class="tab-header-area">
                            <tr>
                                <th>Brand</th>
                                <th>Number of Cores</th>
                                <th>Frequency</th>
                            </tr>
                            </thead>
                            <tr>
                                <td>
                                    <label><input name="cpuBrand" type="text" list="cpuBrandList" minlength="1"
                                                  maxlength="45" value="${cpu.brand}"></label>
                                    <datalist id="cpuBrandList">
                                        <c:forEach items="${cpuBrandList}" var="cpuBrand">
                                            <option value="${cpuBrand}">${cpuBrand}</option>
                                        </c:forEach>
                                    </datalist>
                                </td>
                                <td><label><input name="cpuCores" type="number" step="1" min="1" value="${cpu.cores}"
                                                  required></label></td>
                                <td><label><input name="cpuFrequency" type="number" step="0.01" min="0.01"
                                                  value="${cpu.frequency}"
                                                  required>GHz</label></td>
                            </tr>
                            <tr>
                                <td colspan="3"><input type="submit" value="Create"></td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </c:if>

    <!-- Form for create ram -->
    <c:if test="${ramBrandList != null}">
        <div class="container">
            <div class="row">
                <div class="mx-auto">
                    <a class="nav-link" href="<c:url value="/list?product=ram"/>"><h3>Memory</h3></a>
                    <form method="post">
                        <table class="table">
                            <thead class="tab-header-area">
                            <tr>
                                <th>Brand</th>
                                <th>Size</th>
                            </tr>
                            </thead>
                            <tr>
                                <td>
                                    <label><input name="ramBrand" type="text" list="ramBrandList" minlength="1"
                                                  maxlength="45" value="${ram.brand}"></label>
                                    <datalist id="ramBrandList">
                                        <c:forEach items="${ramBrandList}" var="ramBrand">
                                            <option value="${ramBrand}">${ramBrand}</option>
                                        </c:forEach>
                                    </datalist>
                                </td>
                                <td><label><input name="ramSize" type="number" step="1" min="1" value="${ram.size}"
                                                  required>GB</label></td>
                            </tr>
                            <tr>
                                <td colspan="2"><input type="submit" value="Create"></td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </c:if>

    <!-- Form for create gpu -->
    <c:if test="${gpuBrandList != null}">
        <div class="container">
            <div class="row">
                <div class="mx-auto">
                    <a class="nav-link" href="<c:url value="/list?product=gpu"/>"><h3>Graphic</h3></a>
                    <form method="post">
                        <table class="table">
                            <thead class="tab-header-area">
                            <tr>
                                <th>Brand</th>
                            </tr>
                            </thead>
                            <tr>
                                <td>
                                    <label><input name="gpuBrand" type="text" list="gpuBrandList" minlength="1"
                                                  maxlength="45" value="${gpu.brand}"></label>
                                    <datalist id="gpuBrandList">
                                        <c:forEach items="${gpuBrandList}" var="gpuBrand">
                                            <option value="${gpuBrand}">${gpuBrand}</option>
                                        </c:forEach>
                                    </datalist>
                                </td>
                            </tr>
                            <tr>
                                <td><input type="submit" value="Create"></td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </c:if>

</article>

<hr>

<%@include file="includes/footer.jsp" %>