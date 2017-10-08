<%@include file="includes/header.jsp" %>

<!-- Page Header -->
<header class="masthead text-center" style="background-image: url('static/img/configuration-bg.jpg')">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
                <div class="page-heading">
                    <h1>Configuration of ${pageTitle}</h1>
                </div>
            </div>
        </div>
    </div>
</header>

<!-- Configuration Content -->
<article class="text-center">

    <div class="container">
        <div class="row">
            <div class="mx-auto">
                <table class="table">
                    <thead class="tab-header-area">
                    <tr>
                        <th><a class="nav-link" style="display: inline" href="/pclist/configuration?product=pc">Pc</a>
                        </th>
                        <th><a class="nav-link" style="display: inline" href="/pclist/configuration?product=cpu">Processor</a>
                        </th>
                        <th><a class="nav-link" style="display: inline"
                               href="/pclist/configuration?product=ram">Memory</a></th>
                        <th><a class="nav-link" style="display: inline"
                               href="/pclist/configuration?product=gpu">Graphic</a></th>
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
                <form method="post">
                    <table class="table">
                        <thead class="tab-header-area">
                        <tr>
                            <th>Number of ${pageTitle}</th>
                        </tr>
                        </thead>
                        <tr>
                            <td><input type="number" step="1" value="1000000" min="1" required></td>
                        </tr>
                        <tr>
                            <td><input type="submit" value="Generate random"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>

</article>

<hr>

<%@include file="includes/footer.jsp" %>