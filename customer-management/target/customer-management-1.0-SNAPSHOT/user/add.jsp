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
    <title>Register</title>

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
                    <div class="login-logo">
                        <a href="#">
                            <img src="/images/icon/logo.png" alt="CoolAdmin">
                        </a>
                    </div>
                    <div class="login-form">
                        <form action="users?action=add" method="post">
                            <div class="row form-group">
                                <div class="col col-sm-3">
                                    <label class="form-control-label">First Name</label>
                                </div>
                                <div class="col-12 col-sm-9">
                                    <input type="text" name="firstName" placeholder="First Name" class="form-control" required>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col col-sm-3">
                                    <label class="form-control-label">Lats Name</label>
                                </div>
                                <div class="col-12 col-sm-9">
                                    <input type="text" name="lastName" placeholder="Last Name" class="form-control" required>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col col-sm-3">
                                    <label class="form-control-label">Gender</label>
                                </div>
                                <div class="col-12 col-sm-9">
                                    <select name="gender" required>
                                        <option value="-1">Please select</option>
                                        <option value="true" name="Male">Male</option>
                                        <option value="false" name="Female">Female</option>
                                    </select>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col col-sm-3">
                                    <label class="form-control-label">DOB</label>
                                </div>
                                <div class="col-12 col-sm-9">
                                    <input type="date" name="dob" placeholder="DOB" class="form-control" required>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col col-sm-3">
                                    <label class="form-control-label">Mobile</label>
                                </div>
                                <div class="col-12 col-sm-9">
                                    <input type="text" name="mobile" placeholder="Mobile" class="form-control" required>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col col-sm-3">
                                    <label class="form-control-label">Address</label>
                                </div>
                                <div class="col-12 col-sm-9">
                                    <input type="text" name="address" placeholder="Address" class="form-control" required>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col col-sm-3">
                                    <label class="form-control-label">Email</label>
                                </div>
                                <div class="col-12 col-sm-9">
                                    <input type="email" name="email" placeholder="Email address" class="form-control" required>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col col-sm-3">
                                    <label class="form-control-label">Province</label>
                                </div>
                                <div class="col-12 col-sm-9">
                                    <select name="provinceID" class="form-control" required>
                                        <c:forEach items="${listProvince}" var="item">
                                            <option value="${item.provinceID}" selected>${item.provinceName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <input class="au-input au-input--full" type="hidden" name="totalOrders" value="0">
                            <input class="au-input au-input--full" type="hidden" name="totalAmounts" value="0">
                            <input class="au-input au-input--full" type="hidden" name="rankID" value="4">
                            <input class="au-input au-input--full" type="hidden" name="userUsername" value="${requestScope["userUsername"]}">
                            <input class="au-input au-input--full" type="hidden" name="userPassword" value="${requestScope["userPassword"]}">
                            <button class="au-btn au-btn--block au-btn--green m-b-20" type="submit">Register</button>
                        </form>
                        <div class="login-link">
                            <p>
                                Already have account?
                                <a href="${pageContext.request.contextPath}/users?action=login" class="ml-3">Sign In</a>
                            </p>
                        </div>
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
