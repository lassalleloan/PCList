<%@include file="includes/header.jsp" %>

<!-- Page Header -->
<header class="masthead" style="background-image: url('static/img/configuration-bg.jpg')">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
                <div class="page-heading">
                    <h1>Configuration</h1>
                </div>
            </div>
        </div>
    </div>
</header>

<!-- Configuration Content -->
<article>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
                <form method="post">
                    <table>
                        <tr>
                            <th>
                                Number of PC
                            </th>
                            <td>
                                <input type="number" step="1" value="1000000" min="0" required>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" align="right">
                                <input type="submit" value="Generate random">
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>
</article>

<hr>

<%@include file="includes/footer.jsp" %>