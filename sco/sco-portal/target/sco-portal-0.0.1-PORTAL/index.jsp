<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html class="no-js" lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
	<meta name="renderer" content="webkit">
	<meta name="author" content="">
	<meta name="keywords" content="">
	<meta name="description" content="" />
	<title>SCO</title>
	<link rel="stylesheet" href="${ctx}/static/css/bootstrap.min.css">
	<link rel="stylesheet" href="${ctx}/static/css/font-awesome.min.css">
	<link rel="stylesheet" href="${ctx}/static/css/animate.min.css"/>
	<link rel="stylesheet" href="${ctx}/static/css/base.css">
	<link rel="stylesheet" href="${ctx}/static/css/style.css">
    <script src="${ctx}/static/js/modernizr-custom.js"></script>
  </head>
  <body>
  
    <!-- Start PreLoader -->
	<div id="preloader">
  	  <div id="circleout"></div>
	  <div id="circlein" class="text-center">旺</div>
  	</div><!-- End PreLoader -->
  	
  	<div class="page">
  	
      <!-- Start nav -->
  	  <nav class="navbar" role="navigation">
  	    <div class="container">
  	      
  	      <div class="navbar-header">
  	        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse">
  	          <i class="fa fa-bars fa-2x"></i>
  	        </button>
  	        <a class="navbar-brand" href="#"><img alt="" src="${ctx }/static/images/logo.png" class="logo-img" /></a>
  	      </div>
  	      
  	      <div class="collapse navbar-collapse" id="navbar-collapse">
  	        <ul class="nav navbar-nav navbar-right">
  	          <li><a href="#">HOME</a></li>
  	          <li><a href="#">BLOG</a></li>
  	          <li><a href="#">SHARE</a></li>
  	          <li class="dropdown">
  	            <a href="#" data-toggle="dropdown" data-hover="dropdown" id="" data-ref="#" class="dropdown-toggle">TOOLS <span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                  <li><a href="#">TEST2</a></li>
                  <li><a href="#">TEST3</a></li>
                  <li><a href="#">TEST4</a></li>
                </ul>
              </li>
  	        </ul>
  	      </div>
  	      
  	    </div>
  	  </nav><!-- End nav -->
  	  
  	  asdf
  	</div>
    
    <script src="${ctx}/static/js/jquery.min.js"></script>
    <script src="${ctx}/static/js/bootstrap.min.js"></script>
    <script src="${ctx}/static/js/portal.js"></script>
    
  </body>
</html>