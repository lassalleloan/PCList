<%--
 - author Loan Lassalle (loan.lassalle@heig-vd.ch)
 - author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 - since 13.09.2017
 --%>
<%@include file="includes/header.jsp" %>

<!-- Page Header -->
<header class="masthead text-center" style="background-image: url('static/img/home-bg.jpg')">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
                <div class="site-heading">
                    <h1>AMT - PCList</h1>
                    <span class="subheading">A AMT project</span>
                </div>
            </div>
        </div>
    </div>
</header>

<!-- Home Content -->
<article class="text-center">

    <div class="container">
        <div class="row">
            <div class="mx-auto">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item"><a class="nav-link" href="<c:url value="/list"/>">Manage things</a></li>
                    <li class="nav-item"><a class="nav-link" href="<c:url value="/configuration?product=pc"/>">Configuration</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>

</article>

<hr>

<%@include file="includes/footer.jsp" %>