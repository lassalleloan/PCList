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
            <form method="post">
                <table>
                    <tr>
                        <th>
                            Brand
                        </th>
                        <td>
                            <select>
                                <c:forEach items="${pcBrandList}" var="pcBrand">
                                    <option value="${pcBrand}">${pcBrand}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            Processor
                        </th>
                        <td>
                            <select>
                                <c:forEach items="${cpuBrandList}" var="cpuBrand">
                                    <option value="${cpuBrand}">${cpuBrand}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            Number of Cores
                        </th>
                        <td>
                            <select>
                                <c:forEach items="${cpuNbCoreList}" var="cpuNbCore">
                                    <option value="${cpuNbCore}">${cpuNbCore}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            Frequency
                        </th>
                        <td>
                            <input type="number" step="0.01" min="0" required>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            Memory
                        </th>
                        <td>
                            <select>
                                <c:forEach items="${ramBrandList}" var="ramBrand">
                                    <option value="${ramBrand}">${ramBrand}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            Memory Size
                        </th>
                        <td>
                            <select>
                                <c:forEach items="${ramSizeList}" var="ramSize">
                                    <option value="${ramSize}">${ramSize}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            Graphics
                        </th>
                        <td>
                            <select>
                                <c:forEach items="${gpuBrandList}" var="gpuBrand">
                                    <option value="${gpuBrand}">${gpuBrand}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            Price
                        </th>
                        <td>
                            <input type="number" step="0.01" min="0" required>.-
                        </td>
                    </tr>
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