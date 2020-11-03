<%--
  Created by IntelliJ IDEA.
  User: NguyenVanHuong
  Date: 10/7/20
  Time: 8:03 AM
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
    <title>Detail</title>

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

    <!-- Main CSS-->
    <link href="<c:url value="/css/theme.css"/>" rel="stylesheet" media="all">

</head>

<body class="animsition">
<div class="page-wrapper">
    <div class="page-content--bge5">
        <div class="container">
            <div class="login-wrap">
                <div class="login-content">
                    <div class="login-logo m-b-20">
                        <a href="#">
                            <img src="/images/icon/logo.png" alt="CoolAdmin">
                        </a>
                    </div>
                    <div class="col-md-12">
                        <!-- DATA TABLE-->
                                <div class="table-responsive table--no-card m-b-20">
                                    <table class="table table-borderless table-striped table-earning">
                                        <tbody>
                                        <tr>
                                            <td class="text-right" style="padding-right: 0; width: 30%">Username:</td>
                                            <td class="text-left">${user.userUsername}</td>
                                        </tr>
                                        <tr>
                                            <td class="text-right" style="padding-right: 0">Full Name:</td>
                                            <td class="text-left">${customer.firstName} ${customer.lastName}</td>
                                        </tr>
                                        <tr>
                                            <td class="text-right" style="padding-right: 0">Gender:</td>
                                            <td class="text-left">${customer.viewGender(customer.gender)}</td>
                                        </tr>
                                        <tr>
                                            <td class="text-right" style="padding-right: 0">DOB:</td>
                                            <td class="text-left">${customer.dob}</td>
                                        </tr>
                                        <tr>
                                            <td class="text-right" style="padding-right: 0">Mobile:</td>
                                            <td class="text-left">${customer.mobile}</td>
                                        </tr>
                                        <tr>
                                            <td class="text-right" style="padding-right: 0">Address:</td>
                                            <td class="text-left">${customer.address}</td>
                                        </tr>
                                        <tr>
                                            <td class="text-right" style="padding-right: 0">Email:</td>
                                            <td class="text-left">${customer.email}</td>
                                        </tr>
                                        <tr>
                                            <td class="text-right" style="padding-right: 0">Province:</td>
                                            <td class="text-left">${province.provinceName}</td>
                                        </tr>
                                        <tr>
                                            <td class="text-right" style="padding-right: 0">Rank:</td>
                                            <td class="text-left">${rank.rankName}</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                        <!-- END DATA TABLE-->
                    </div>
                    <div class="col-md-12" style="text-align: center">
                        <a href="#" onclick="window.history.back()" style="align-items: center">
                            <button class="au-btn au-btn--block au-btn--green" style="width: 300px">DONE</button>
                        </a>
                    </div>
                    <div class="col-md-12 mt-2" style="text-align: center">
                        <a href="${pageContext.request.contextPath}/users?action=updateInformation">Update Information</a>
                    </div>
                    <div class="col-md-12 mt-1" style="text-align: center">
                        <a href="${pageContext.request.contextPath}/users?action=updatePassword">Change Password</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<!-- Jquery JS-->
<script src="${pageContext.request.contextPath}/vendor/jquery-3.2.1.min.js"></script>
<!-- Bootstrap JS-->
<script src="${pageContext.request.contextPath}/vendor/bootstrap-4.1/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrap-4.1/bootstrap.min.js"></script>
<!-- Vendor JS       -->
<script src="${pageContext.request.contextPath}/vendor/slick/slick.min.js">
</script>
<script src="${pageContext.request.contextPath}/vendor/wow/wow.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/animsition/animsition.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrap-progressbar/bootstrap-progressbar.min.js">
</script>
<script src="${pageContext.request.contextPath}/vendor/counter-up/jquery.waypoints.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/counter-up/jquery.counterup.min.js">
</script>
<script src="${pageContext.request.contextPath}/vendor/circle-progress/circle-progress.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/perfect-scrollbar/perfect-scrollbar.js"></script>
<script src="${pageContext.request.contextPath}/vendor/chartjs/Chart.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/select2/select2.min.js">
</script>

<!-- Main JS-->
<script src="${pageContext.request.contextPath}/js/main.js"></script>

</body>

</html>
<!-- end document-->
