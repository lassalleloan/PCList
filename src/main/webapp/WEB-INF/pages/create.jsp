<%@include file="includes/header.jsp" %>

<!-- Page Header -->
<header class="masthead text-center" style="background-image: url('static/img/create-bg.jpg')">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
                <div class="page-heading text-md-center">
                    <h1>Create a ${pageTitle}</h1>
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
                        <th><a class="nav-link" style="display: inline" href="/pclist/create?product=pc">Pc</a></th>
                        <th><a class="nav-link" style="display: inline" href="/pclist/create?product=cpu">Processor</a>
                        </th>
                        <th><a class="nav-link" style="display: inline" href="/pclist/create?product=ram">Memory</a>
                        </th>
                        <th><a class="nav-link" style="display: inline" href="/pclist/create?product=gpu">Graphic</a>
                        </th>
                    </tr>
                    </thead>
                </table>
                <br>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <div class="mx-auto">
                <a class="nav-link" href="/pclist/list?product=${product}"><h3>${pageTitle}</h3></a>
                <form method="post" action="/pclist/create?product=${product}">
                    <table class="table">
                        <c:if test="${pcBrandList != null && cpuList != null && ramList != null && gpuList != null}">
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
                            </tr>
                            </thead>
                            <tr>
                                <td>
                                    <label><input type="text" list="pcBrandList" name="pcBrand" minlength="1"
                                                  maxlength="45"></label>
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
                                                <option value="${cpu.idCpu}">${cpu.brand} ${cpu.cores} ${cpu.frequency}GHz</option>
                                            </c:forEach>
                                        </select>
                                    </label>
                                </td>
                                <td>
                                    <label>
                                        <select name="idRam">
                                            <c:forEach items="${ramList}" var="ram">
                                                <option value="${ram.idRam}">${ram.brand} ${ram.size}GB</option>
                                            </c:forEach>
                                        </select>
                                    </label>
                                </td>
                                <td>
                                    <label>
                                        <select name="idGpu">
                                            <c:forEach items="${gpuList}" var="gpu">
                                                <option value="${gpu.idGpu}">${gpu.brand}</option>
                                            </c:forEach>
                                        </select>
                                    </label>
                                </td>
                            </tr>
                        </c:if>

                        <c:if test="${cpuBrandList != null}">
                            <thead class="tab-header-area">
                            <tr>
                                <th>Brand</th>
                                <th>Number of Cores</th>
                                <th>Frequency</th>
                            </tr>
                            </thead>
                            <tr>
                                <td>
                                    <label><input type="text" list="cpuBrandList" name="cpuBrand" minlength="1"
                                                  maxlength="45"></label>
                                    <datalist id="cpuBrandList">
                                        <c:forEach items="${cpuBrandList}" var="cpuBrand">
                                            <option value="${cpuBrand}">${cpuBrand}</option>
                                        </c:forEach>
                                    </datalist>
                                </td>
                                <td><label><input type="number" step="1" min="1" value="1" name="cpuCores"
                                                  required></label></td>
                                <td><label><input type="number" step="0.01" min="0.01" value="1.00" name="cpuFrequency"
                                                  required>GHz</label></td>
                            </tr>
                        </c:if>

                        <c:if test="${ramBrandList != null}">
                            <thead class="tab-header-area">
                            <tr>
                                <th>Brand</th>
                                <th>Size</th>
                            </tr>
                            </thead>
                            <tr>
                                <td>
                                    <label><input type="text" list="ramBrandList" name="ramBrand" minlength="1"
                                                  maxlength="45"></label>
                                    <datalist id="ramBrandList">
                                        <c:forEach items="${ramBrandList}" var="ramBrand">
                                            <option value="${ramBrand}">${ramBrand}</option>
                                        </c:forEach>
                                    </datalist>
                                </td>
                                <td><label><input type="number" step="1" min="1" value="1" name="ramSize"
                                                  required>GB</label></td>
                            </tr>
                        </c:if>

                        <c:if test="${gpuBrandList != null}">
                            <thead class="tab-header-area">
                            <tr>
                                <th>Brand</th>
                            </tr>
                            </thead>
                            <tr>
                                <td>
                                    <label><input type="text" list="gpuBrandList" name="gpuBrand" minlength="1"
                                                  maxlength="45"></label>
                                    <datalist id="gpuBrandList">
                                        <c:forEach items="${gpuBrandList}" var="gpuBrand">
                                            <option value="${gpuBrand}">${gpuBrand}</option>
                                        </c:forEach>
                                    </datalist>
                                </td>
                            </tr>
                        </c:if>

                        <tr>
                            <td colspan="8"><input type="submit" value="Create"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>

</article>

<hr>

<%@include file="includes/footer.jsp" %>