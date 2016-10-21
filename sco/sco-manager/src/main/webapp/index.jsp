<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html class="no-js" lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
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
      <div id="circlein" class="text-center">&nbsp;</div>
    </div>
    <!-- End PreLoader -->
    
    <div class="page">
      
      <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      
        <a type="button" class="leftmenu-trigger"></a>
        <a type="button" class="logo pull-left">IFEN</a>
        
        <div class="container-fluid">
          
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse">
              <i class="fa fa-bars fa-2x"></i>
            </button>
          </div>
          
          <div class="collapse navbar-collapse navbar-right" id="navbar-collapse">
            
            <ul class="nav navbar-nav toolbar">
            
              <li><a href="#"><i class="fa fa-envira"></i> test</a></li>
              
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle='dropdown'>
                  <i class="fa fa-bell"></i>
                  <span class="badge">3</span>
                </a>
                <ul class="dropdown-menu arrow notifications">
                  <li class="dd-header">
                    <span>3条新通知</span>
                    <span><a href="#">全部标为已读</a></span>
                  </li>
                  <li class="scrollthis">
                    <a href="#" class="notification-danger">
                      <span class="time">20 mins</span>
                      <i class="fa fa-bolt"></i>
                      <span class="msg">CPU at 92% on server#3! </span>
    				</a>
                  </li>
                  <li class="dd-footer"><a href="#">View All Messages</a></li>
                </ul>
              </li>
              
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle='dropdown'>
                  <i class="fa fa-envelope"></i>
                  <span class="badge">1</span>
                </a>
                <ul class="dropdown-menu arrow messages">
                  <li class="dd-header">
                    <span>1条新消息</span>
                    <span><a href="#">全部标为已读</a></span>
                  </li>
                  <li class="scrollthis">
                    <a href="#">
                      <span class="time">6 mins</span>
    			      <img src="${ctx }/static/images/default_user1.png" alt="avatar" />
    			      <span>
    			        <small>Alan Doyle</small><br>
    			        <small>This line of text is meant to be treated as fine print. test the long text, show the css .</small>
    			      </span>
    			    </a>
    			    <a href="#">
                      <span class="time">6 mins</span>
    			      <img src="${ctx }/static/images/default_user1.png" alt="avatar" />
    			      <span>
    			        <small>Alan Doyle</small><br>
    			        <small>This line of text is meant to be treated as fine print.</small>
    			      </span>
    			    </a>
                  </li>
                  <li class="dd-footer"><a href="#">View All Messages</a></li>
                </ul>
              </li>
              
              <li class="dropdown">
                <a href="#" class="dropdown-toggle user" data-toggle="dropdown">
                  <span class="hidden-xs">scorpio wang <i class="fa fa-caret-down"></i></span>
                  <img src="static/images/default_user.png" alt="head">
                </a>
                <ul class="dropdown-menu arrow userinfo" role="menu">
                  <li><a href="#">Edit Profile <i class="pull-right fa fa-pencil"></i></a></li>
                  <li><a href="#">Account <i class="pull-right fa fa-cog"></i></a></li>
                  <li><a href="#">Help <i class="pull-right fa fa-question-circle"></i></a></li>
                  <li class="divider"></li>
                  <li><a href="#" class="text-right">Sign Out</a></li>
                </ul>
              </li>
              
            </ul>
            
          </div>
          
        </div>
      </nav>
      
    </div>

    <script src="${ctx}/static/js/jquery.min.js"></script> 
    <script src="${ctx}/static/js/bootstrap.min.js"></script>
    <script src="${ctx}/static/js/portal.js"></script>
    
  </body>
</html>