<%--
  Created by IntelliJ IDEA.
  User: NguyenVanHuong
  Date: 9/30/20
  Time: 11:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">

<head>
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="au theme template">
    <meta name="author" content="Hau Nguyen">
    <meta name="keywords" content="au theme template">

    <!-- Title Page-->
    <title>Dashboard</title>

    <!-- Fontfaces CSS-->
    <link href="<c:url value="/css/font-face.css"/>" rel="stylesheet" media="all">
    <link href="<c:url value="/vendor/font-awesome-4.7/css/font-awesome.min.css"/>" rel="stylesheet" media="all">
    <link href="<c:url value="/vendor/font-awesome-5/css/fontawesome-all.min.css"/>" rel="stylesheet" media="all">
    <link href="<c:url value="/vendor/mdi-font/css/material-design-iconic-font.min.css"/>" rel="stylesheet" media="all">

    <!-- Bootstrap CSS-->
    <link href="<c:url value="/vendor/bootstrap-4.1/bootstrap.min.css"/>" rel="stylesheet" media="all">

    <!-- Vendor CSS-->
    <link href="<c:url value="/vendor/animsition/animsition.min.css"/>" rel="stylesheet" media="all">
    <link href="<c:url value="/vendor/bootstrap-progressbar/bootstrap-progressbar-3.3.4.min.css"/>" rel="stylesheet" media="all">
    <link href="<c:url value="/vendor/wow/animate.css"/>" rel="stylesheet" media="all">
    <link href="<c:url value="/vendor/css-hamburgers/hamburgers.min.css"/>" rel="stylesheet" media="all">
    <link href="<c:url value="/vendor/slick/slick.css"/>" rel="stylesheet" media="all">
    <link href="<c:url value="/vendor/select2/select2.min.css"/>" rel="stylesheet" media="all">
    <link href="<c:url value="/vendor/perfect-scrollbar/perfect-scrollbar.css"/>" rel="stylesheet" media="all">
    <link href="<c:url value="/vendor/vector-map/jqvmap.min.css"/>" rel="stylesheet" media="all">

    <!-- Main CSS-->
    <link href="<c:url value="/css/theme.css"/>" rel="stylesheet" media="all">
    <style>
        ::placeholder {
            color: white;
        }
    </style>

</head>

<body class="animsition">
<div class="page-wrapper">
    <!-- MENU SIDEBAR-->
    <aside class="menu-sidebar2">
        <div class="logo">
            <a href="#">
                <img src="/images/icon/logo-white.png" alt="Cool Admin" />
            </a>
        </div>
        <div class="menu-sidebar2__content js-scrollbar1">
            <nav class="navbar-sidebar2">
                <ul class="list-unstyled navbar__list">
                    <li class="active has-sub">
                        <a class="js-arrow" href="${pageContext.request.contextPath}/customers"/>
                        <i class="fas fa-table"></i>Customers
                        </a>
                    </li>
                    <li class="has-sub">
                        <a class="js-arrow" href="${pageContext.request.contextPath}/provinces">
                            <i class="fas fa-table"></i>Provinces
                        </a>
                    </li>
                    <li class="has-sub">
                        <a class="js-arrow" href="${pageContext.request.contextPath}/ranks">
                            <i class="fas fa-table"></i>Ranks
                        </a>
                    </li>
                    <li class="has-sub">
                        <a class="js-arrow" href="${pageContext.request.contextPath}/users">
                            <i class="fas fa-table"></i>Users
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
    </aside>
    <!-- END MENU SIDEBAR-->

    <!-- PAGE CONTAINER-->
    <div class="page-container2">
        <!-- HEADER DESKTOP-->
        <header class="header-desktop2">
            <div class="section__content section__content--p30">
                <div class="container-fluid">
                    <div class="header-wrap2">
                        <div class="logo d-block d-lg-none">
                            <a href="#">
                                <img src="/images/icon/logo-white.png" alt="CoolAdmin" />
                            </a>
                        </div>
                        <div class="header-button2">
                            <div class="header-button-item js-item-menu">
                                <form action="" method="post">
                                    <input style="width: 20vw; border-bottom: white solid 1px; background: none; font-size: medium; " type="text" name="search" placeholder="Search here...">
                                    <i class="button" style="font-size: medium"></i><i class="zmdi zmdi-search ml-2"></i>
                                </form>
                            </div>
                            <div class="header-button-item has-noti js-item-menu">
                                <i class="zmdi zmdi-notifications"></i>
                                <div class="notifi-dropdown js-dropdown">
                                    <div class="notifi__title">
                                        <p>You have 3 Notifications</p>
                                    </div>
                                    <div class="notifi__item">
                                        <div class="bg-c1 img-cir img-40">
                                            <i class="zmdi zmdi-email-open"></i>
                                        </div>
                                        <div class="content">
                                            <p>You got a email notification</p>
                                            <span class="date">April 12, 2018 06:50</span>
                                        </div>
                                    </div>
                                    <div class="notifi__item">
                                        <div class="bg-c2 img-cir img-40">
                                            <i class="zmdi zmdi-account-box"></i>
                                        </div>
                                        <div class="content">
                                            <p>Your account has been blocked</p>
                                            <span class="date">April 12, 2018 06:50</span>
                                        </div>
                                    </div>
                                    <div class="notifi__item">
                                        <div class="bg-c3 img-cir img-40">
                                            <i class="zmdi zmdi-file-text"></i>
                                        </div>
                                        <div class="content">
                                            <p>You got a new file</p>
                                            <span class="date">April 12, 2018 06:50</span>
                                        </div>
                                    </div>
                                    <div class="notifi__footer">
                                        <a href="#">All notifications</a>
                                    </div>
                                </div>
                            </div>
                            <div class="header-button-item mr-0 js-sidebar-btn">
                                <i class="zmdi zmdi-menu"></i>
                            </div>
                            <div class="setting-menu js-right-sidebar d-none d-lg-block">
                                <div class="account-dropdown__body">
                                    <div class="account-dropdown__item">
                                        <a href="#">
                                            <i class="zmdi zmdi-account"></i>Account</a>
                                    </div>
                                    <div class="account-dropdown__item">
                                        <a href="#">
                                            <i class="zmdi zmdi-settings"></i>Logout</a>
                                    </div>
                                    <div class="account-dropdown__item">
                                        <a href="#">
                                            <i class="zmdi zmdi-settings"></i>Setting</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <aside class="menu-sidebar2 js-right-sidebar d-block d-lg-none">
            <div class="logo">
                <a href="#">
                    <img src="images/icon/logo-white.png" alt="Cool Admin" />
                </a>
            </div>
            <div class="menu-sidebar2__content js-scrollbar2">
                <nav class="navbar-sidebar2">
                    <ul class="list-unstyled navbar__list">
                        <li class="active has-sub">
                            <a class="js-arrow" href="${pageContext.request.contextPath}/customers">
                                <i class="fas fa-table"></i>Customers
                            </a>
                        </li>
                        <li class="has-sub">
                            <a class="js-arrow" href="${pageContext.request.contextPath}/provinces">
                                <i class="fas fa-table"></i>Users
                            </a>
                        </li>
                        <li class="has-sub">
                            <a class="js-arrow" href="${pageContext.request.contextPath}/ranks">
                                <i class="fas fa-table"></i>Ranks
                            </a>
                        </li>
                        <li class="has-sub">
                            <a class="js-arrow" href="${pageContext.request.contextPath}/users">
                                <i class="fas fa-table"></i>Users
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
        </aside>
        <!-- END HEADER DESKTOP-->

        <!-- BREADCRUMB-->
        <section class="au-breadcrumb m-t-75">
            <div class="section__content section__content--p30">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="au-breadcrumb-content">
                                <div class="au-breadcrumb-left">
                                    <a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath}/customers">
                                        <i class="fas fa-undo mr-1"></i>Back to Dashboard</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- END BREADCRUMB-->

        <section>
            <div class="section__content section__content--p30">
                <div class="container-fluid">
                    <div class="row m-t-30">
                        <div class="col-md-12">
                            <!-- DATA TABLE-->
                            <div class="card">
                                <div class="card-body card-block">
                                    <form action="" method="post" class="form-horizontal">
                                        <div class="row form-group">
                                            <input value="${customer.getCustomerID()}" type="hidden" name="customerID" class="form-control">
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label class="form-control-label">First Name</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <input value="${customer.getFirstName()}" disabled type="text" name="firstName" class="form-control">
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label class="form-control-label">Last Name</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <input value="${customer.getLastName()}" disabled type="text" name="lastName" class="form-control">
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label class="form-control-label">Gender</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <input value="${customer.viewGender()}" disabled type="text" name="gender" class="form-control">
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label class="form-control-label">DOB</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <input value="${customer.getDob()}" disabled type="date" name="dob" class="form-control">
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label class="form-control-label">Mobile</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <input value="${customer.getMobile()}" disabled type="text" name="mobile" class="form-control">
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label class="form-control-label">Address</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <input value="${customer.getAddress()}" disabled type="text" name="address" class="form-control">
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label class="form-control-label">Email</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <input value="${customer.getEmail()}" disabled type="email" name="address" class="form-control">
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label class="form-control-label">Province</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <c:forEach items="${listProvince}" var="item">
                                                    <c:if test="${item.provinceID==customer.getProvinceID()}">
                                                        <input value="${item.provinceName}" disabled type="text" name="rankID" class="form-control">
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label class=" form-control-label">Total Orders</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <input value="${customer.getTotalOrders()}" disabled type="number" name="totalOrders" class="form-control">
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label class=" form-control-label">Total Amounts</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <input value="${customer.getTotalAmounts()}" disabled type="number" name="totalAmounts" class="form-control">
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label class="form-control-label">Rank</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <c:forEach items="${listRank}" var="item">
                                                    <c:if test="${item.rankID==customer.getRankID()}">
                                                        <input value="${item.rankName}" disabled type="text" name="rankID" class="form-control">
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-12"  align="right">
                                                <a type="reset" class="btn btn-warning btn-sm" href="/customers" style="width: 90px">
                                                    <i class="fa fa-ban mr-1"></i>Reset</a>
                                                <button type="submit" class="btn btn-primary btn-sm" style="width: 90px; color: black">
                                                    <i class="fas fa-save mr-1"></i>Save</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <!-- END DATA TABLE-->
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="copyright">
                            <p>Copyright © 2018 Colorlib. All rights reserved. Template by <a href="https://colorlib.com">Colorlib</a>.</p>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- END PAGE CONTAINER-->
    </div>

</div>

<!-- Jquery JS-->
<script src="${pageContext.request.contextPath}/vendor/jquery-3.2.1.min.js"></script>
<!-- Bootstrap JS-->
<script src="${pageContext.request.contextPath}/vendor/bootstrap-4.1/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrap-4.1/bootstrap.min.js"></script>
<!-- Vendor JS       -->
<script src="${pageContext.request.contextPath}/vendor/slick/slick.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/wow/wow.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/animsition/animsition.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/counter-up/jquery.waypoints.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/counter-up/jquery.counterup.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/circle-progress/circle-progress.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/perfect-scrollbar/perfect-scrollbar.js"></script>
<script src="${pageContext.request.contextPath}/vendor/chartjs/Chart.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/select2/select2.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/vector-map/jquery.vmap.js"></script>
<script src="${pageContext.request.contextPath}/vendor/vector-map/jquery.vmap.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/vector-map/jquery.vmap.sampledata.js"></script>
<script src="${pageContext.request.contextPath}/vendor/vector-map/jquery.vmap.world.js"></script>

<!-- Main JS-->
<script src="${pageContext.request.contextPath}/js/main.js"></script>

</body>

</html>
<!-- end document-->
