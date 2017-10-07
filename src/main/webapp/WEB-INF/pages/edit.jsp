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
                <table align="center" style="text-align: center">
                    <tr>
                        <td colspan="2">
                            <a class="nav-link" href="/pclist/list?product=cpu">
                                <h3>${titlePage}</h3>
                            </a>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            Brand
                        </th>
                        <td>
                            <label>
                                <input type="text" list="cpuBrandList" name="cpuBrand" minlength="1" maxlength="45"
                                       value="${cpu.brand}">
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
                                <input type="number" step="1" min="1" value="${cpu.cores}" name="cpuCores" required>
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            Frequency
                        </th>
                        <td>
                            <label>
                                <input type="number" step="0.01" min="0" value="${cpu.frequency}" name="cpuFrequency"
                                       required>GHz
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <br>
                        </td>
                    </tr>
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