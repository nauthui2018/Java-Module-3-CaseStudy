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
                            <img src="/images/icon/logo.png" alt="Register">
                        </a>
                    </div>
                    <div class="login-form">
                        <form action="users?action=add" method="post">
                            <input class="au-input au-input--full" type="hidden" name="totalOrders" value="0">
                            <input class="au-input au-input--full" type="hidden" name="totalAmounts" value="0">
                            <input class="au-input au-input--full" type="hidden" name="rankID" value="4">
                            <input class="au-input au-input--full" type="hidden" name="userUsername" value="${requestScope["userUsername"]}">
                            <input class="au-input au-input--full" type="hidden" name="userPassword" value="${requestScope["userPassword"]}">
                            <div class="row form-group">
                                <div class="col col-md-3">
                                    <label for="text-input" class=" form-control-label">First Name</label>
                                </div>
                                <div class="col-12 col-md-9">
                                    <input type="text" name="firstName" id="text-input" value="${customer.firstName}" placeholder="First Name" class="form-control" required>
                                    <label class="ml-2 mt-2">
                                        <c:forEach items="${validationResult}" var="item">
                                            <c:if test="${(item.key).equals('firstName')}">
                                                <span class="message" style="color: red; font-size: smaller">${item.value}</span>
                                            </c:if>
                                        </c:forEach>
                                    </label>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col col-md-3">
                                    <label for="text-input" class=" form-control-label">Last Name</label>
                                </div>
                                <div class="col-12 col-md-9">
                                    <input type="text" name="lastName" value="${customer.lastName}" placeholder="Last Name" class="form-control" required>
                                    <label class="ml-2 mt-2">
                                        <c:forEach items="${validationResult}" var="item">
                                            <c:if test="${(item.key).equals('lastName')}">
                                                <span class="message" style="color: red; font-size: smaller">${item.value}</span>
                                            </c:if>
                                        </c:forEach>
                                    </label>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col col-md-3">
                                    <label for="select" class="form-control-label">Gender</label>
                                </div>
                                <div class="col-12 col-md-9">
                                    <select name="gender" id="select" class="form-control" required>
                                        <c:if test="${customer.gender == null}">
                                            <option value="-1" selected>-- Please select --</option>
                                            <option value="0">Female</option>
                                            <option value="1">Male</option>
                                            <option value="2">LGBT</option>
                                        </c:if>
                                        <c:if test="${customer.gender != null}">
                                            <option value="${customer.gender}" selected>${customer.viewGender(customer.gender)}</option>
                                            <c:forEach var = "i" begin = "0" end = "2">
                                                <c:if test="${customer.gender != i}">
                                                    <option value="${i}">${customer.viewGender(i)}</option>
                                                </c:if>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                    <label class="ml-2 mt-2">
                                        <c:forEach items="${validationResult}" var="item">
                                            <c:if test="${(item.key).equals('gender')}">
                                                <span class="message" style="color: red; font-size: smaller">${item.value}</span>
                                            </c:if>
                                        </c:forEach>
                                    </label>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col col-md-3">
                                    <label for="date-input" class="form-control-label">DOB</label>
                                </div>
                                <div class="col-12 col-md-9">
                                    <input type="date" id="date-input" name="dob" value="${customer.dob}" placeholder="DOB" class="form-control" required>
                                    <label class="ml-2 mt-2">
                                        <c:forEach items="${validationResult}" var="item">
                                            <c:if test="${(item.key).equals('dob')}">
                                                <span class="message" style="color: red; font-size: smaller">${item.value}</span>
                                            </c:if>
                                        </c:forEach>
                                    </label>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col col-md-3">
                                    <label for="text-input" class="form-control-label">Mobile</label>
                                </div>
                                <div class="col-12 col-md-9">
                                    <input type="text" name="mobile" value="${customer.mobile}" placeholder="Mobile" class="form-control" required>
                                    <label class="ml-2 mt-2">
                                        <c:forEach items="${validationResult}" var="item">
                                            <c:if test="${(item.key).equals('mobile')}">
                                                <span class="message" style="color: red; font-size: smaller">${item.value}</span>
                                            </c:if>
                                        </c:forEach>
                                    </label>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col col-md-3">
                                    <label for="text-input" class=" form-control-label">Address</label>
                                </div>
                                <div class="col-12 col-md-9">
                                    <input type="text" name="address" value="${customer.address}" placeholder="Address" class="form-control" required>
                                    <label class="ml-2 mt-2">
                                        <c:forEach items="${validationResult}" var="item">
                                            <c:if test="${(item.key).equals('address')}">
                                                <span class="message" style="color: red; font-size: smaller">${item.value}</span>
                                            </c:if>
                                        </c:forEach>
                                    </label>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col col-md-3">
                                    <label for="email-input" class=" form-control-label">Email</label>
                                </div>
                                <div class="col-12 col-md-9">
                                    <input type="email" id="email-input" name="email" value="${customer.email}" placeholder="Email" class="form-control" required>
                                    <label class="ml-2 mt-2">
                                        <c:forEach items="${validationResult}" var="item">
                                            <c:if test="${(item.key).equals('email')}">
                                                <span class="message" style="color: red; font-size: smaller">${item.value}</span>
                                            </c:if>
                                        </c:forEach>
                                    </label>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col col-md-3">
                                    <label for="select" class="form-control-label">Province</label>
                                </div>
                                <div class="col-12 col-md-9">
                                    <select name="provinceID" id="provinceID" class="form-control" required>
                                        <c:if test="${customer.provinceID == null}">
                                            <option value="-1" selected>-- Please select --</option>
                                        </c:if>
                                        <c:if test="${customer.provinceID != null}">
                                            <c:forEach items="${listProvince}" var="item">
                                                <c:if test="${item.provinceID == customer.provinceID}">
                                                    <option value="${item.provinceID}" selected>${item.provinceName}</option>
                                                </c:if>
                                            </c:forEach>
                                        </c:if>
                                        <c:forEach items="${listProvince}" var="item">
                                            <option value="${item.provinceID}">${item.provinceName}</option>
                                        </c:forEach>
                                    </select>
                                    <label class="ml-2 mt-2">
                                        <c:forEach items="${validationResult}" var="item">
                                            <c:if test="${(item.key).equals('provinceID')}">
                                                <span class="message" style="color: red; font-size: smaller">${item.value}</span>
                                            </c:if>
                                        </c:forEach>
                                    </label>
                                </div>
                            </div>
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
