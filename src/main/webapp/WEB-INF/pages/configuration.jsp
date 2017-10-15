<%--
 - author Loan Lassalle (loan.lassalle@heig-vd.ch)
 - author Jérémie Zanone (jeremie.zanone@heig-vd.ch)
 - since 13.09.2017
 --%>
<%@include file="includes/header.jsp" %>

<!-- Page Header -->
<header class="masthead text-center" style="background-image: url('static/img/configuration-bg.jpg')">
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

<!-- Configuration Content -->
<article class="text-center">

    <!-- Links for all things -->
    <div class="container">
        <div class="row">
            <div class="mx-auto">
                <table class="table">
                    <thead class="tab-header-area">
                    <tr>
                        <th><a class="nav-link" href="<c:url value="/configuration?product=pc"/>">PC</a></th>
                        <th><a class="nav-link" href="<c:url value="/configuration?product=cpu"/>">Processor</a></th>
                        <th><a class="nav-link" href="<c:url value="/configuration?product=ram"/>">Memory</a></th>
                        <th><a class="nav-link" href="<c:url value="/configuration?product=gpu"/>">Graphic</a></th>
                    </tr>
                    </thead>
                </table>
                <br>
            </div>
        </div>
    </div>

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

    <!-- Form for generate things -->
    <div class="container">
        <div class="row">
            <div class="mx-auto">
                <form method="post">
                    <table class="table">
                        <thead class="tab-header-area">
                        <tr>
                            <th>${headerTitle}</th>
                        </tr>
                        </thead>
                        <tr>
                            <td><label><input name="productGenerated" type="number" step="1" value="1000000" min="1"
                                              required></label></td>
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