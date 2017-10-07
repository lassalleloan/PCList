<%@include file="includes/header.jsp" %>

<!-- Page Header -->
<header class="masthead" style="background-image: url('static/img/create-bg.jpg')">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
                <div class="page-heading">
                    <h1>Create a ${titlePage}</h1>
                </div>
            </div>
        </div>
    </div>
</header>

<!-- Main Content -->
<div class="container">
    <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
            <form method="post" action="/pclist/create?product=${product}">
                <table align="center" style="text-align: center">
                    <tr>
                        <td colspan="2">
                            <a class="nav-link" href="/pclist/list?product=${product}">
                                <h3>${titlePage}</h3>
                            </a>
                        </td>
                    </tr>
                    <c:if test="${pcBrandList != null && cpuList != null && ramList != null && gpuList != null}">
                        <tr>
                            <th>
                                Brand
                            </th>
                            <td>
                                <label>
                                    <input type="text" list="pcBrandList" name="pcBrand" minlength="1" maxlength="45">
                                </label>
                                <datalist id="pcBrandList">
                                    <c:forEach items="${pcBrandList}" var="pcBrand">
                                        <option value="${pcBrand}">
                                                ${pcBrand}
                                        </option>
                                    </c:forEach>
                                </datalist>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Processor
                            </th>
                            <td>
                                <label>
                                    <select name="idCpu">
                                        <c:forEach items="${cpuList}" var="cpu">
                                            <option value="${cpu.idCpu}">
                                                    ${cpu.brand} ${cpu.cores} ${cpu.frequency}GHz
                                            </option>
                                        </c:forEach>
                                    </select>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Memory
                            </th>
                            <td>
                                <label>
                                    <select name="idRam">
                                        <c:forEach items="${ramList}" var="ram">
                                            <option value="${ram.idRam}">
                                                    ${ram.brand} ${ram.size}GB
                                            </option>
                                        </c:forEach>
                                    </select>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Graphic
                            </th>
                            <td>
                                <label>
                                    <select name="idGpu">
                                        <c:forEach items="${gpuList}" var="gpu">
                                            <option value="${gpu.idGpu}">
                                                    ${gpu.brand}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </label>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${cpuBrandList != null}">
                        <tr>
                            <th>
                                Brand
                            </th>
                            <td>
                                <label>
                                    <input type="text" list="cpuBrandList" name="cpuBrand" minlength="1" maxlength="45">
                                </label>
                                <datalist id="cpuBrandList">
                                    <c:forEach items="${cpuBrandList}" var="cpuBrand">
                                        <option value="${cpuBrand}">
                                                ${cpuBrand}
                                        </option>
                                    </c:forEach>
                                </datalist>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Number of Cores
                            </th>
                            <td>
                                <label>
                                    <input type="number" step="1" min="1" value="1" name="cpuCores" required>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Frequency
                            </th>
                            <td>
                                <label>
                                    <input type="number" step="0.01" min="0.01" value="1.00" name="cpuFrequency"
                                           required>GHz
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <br>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${ramBrandList != null}">
                        <tr>
                            <th>
                                Brand
                            </th>
                            <td>
                                <label>
                                    <input type="text" list="ramBrandList" name="ramBrand" minlength="1" maxlength="45">
                                </label>
                                <datalist id="ramBrandList">
                                    <c:forEach items="${ramBrandList}" var="ramBrand">
                                        <option value="${ramBrand}">
                                                ${ramBrand}
                                        </option>
                                    </c:forEach>
                                </datalist>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                Size
                            </th>
                            <td>
                                <label>
                                    <input type="number" step="1" min="1" value="1" name="ramSize" required>GB
                                </label>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${gpuBrandList != null}">
                        <tr>
                            <th>
                                Brand
                            </th>
                            <td>
                                <label>
                                    <input type="text" list="gpuBrandList" name="gpuBrand" minlength="1" maxlength="45">
                                </label>
                                <datalist id="gpuBrandList">
                                    <c:forEach items="${gpuBrandList}" var="gpuBrand">
                                        <option value="${gpuBrand}">
                                                ${gpuBrand}
                                        </option>
                                    </c:forEach>
                                </datalist>
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td colspan="2">
                            <br>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="right">
                            <input type="submit" value="Create">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>

<hr>

<%@include file="includes/footer.jsp" %>