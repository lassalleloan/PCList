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
                    <c:if test="${cpu != null}">
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
                        <tr>
                            <td colspan="2" align="right">
                                <input type="submit" value="Edit">
                            </td>
                        </tr>
                    </c:if>
                </table>
            </form>
        </div>
    </div>
</div>

<hr>

<%@include file="includes/footer.jsp" %>