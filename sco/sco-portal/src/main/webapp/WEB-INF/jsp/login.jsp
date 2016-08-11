<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>

<!DOCTYPE html>
<html class="no-js" lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <title></title>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="author" content="">
    <meta name="keywords" content="">
    <meta name="description" content="" />
    <link rel="stylesheet" href="${ctx}/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/static/css/base.css">
    <link rel="stylesheet" href="${ctx}/static/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/static/css/animate.min.css">
  </head>
  
  <body class="login-body">
  	<div id="submitPreloader">
  	  <div id="circleout"></div>
	  <div id="circlein" class="text-center">旺</div>
  	</div>
    <div class="container">
      <form class="form-signin" id="loginForm" name="loginForm" method="POST" action="${ctx}/signPC.php">
        <div class="form-signin-heading text-center">
          <h1 class="sign-title">Sign In</h1> 
          <%-- <img src="${pageContext.request.contextPath}/static/images/login-logo.png"> --%>
        </div>
        <div class="login-wrap">
          <div class="form-group">
            <div class="input-group">
              <div class="input-group-addon">
                <i class="fa fa-user"></i>
              </div>
              <input class="form-control" id="userName" name="userName" type="text" placeholder="用户名" required="required" maxlength="20">
            </div>
          </div>
          <div class="form-group">
            <div class="input-group">
              <div class="input-group-addon">
                <i class="fa fa-lock"></i>
              </div>
              <input class="form-control" id="password" name="password" type="password" placeholder="密码" required="required" maxlength="16">
            </div>
          </div>
          <button class="btn btn-block btn-success" type="submit">
            <i class="fa fa-check"></i>
          </button>
          <div class="other-login">
          	其他登录方式：<a class="pull-right" href="${ctx}/signQQ.php"><img src="${ctx}/static/images/qq_logo_1.png"></a>
          </div>
        </div>
        <c:if test="${emsg != null}">
          <p class="bg-danger text-center emsg">${emsg }</p>
        </c:if>
      </form>
    </div>
    
    <script src="${ctx}/static/js/jquery-1.12.4.min.js"></script>
    <script src="${ctx}/static/js/bootstrap.min.js"></script>
    <script src="${ctx}/static/js/jquery.validate.min.js"></script>
    <script src="${ctx}/static/js/messages_zh.min.js"></script>
    <script src="${ctx}/static/js/z.v.js"></script>
    <script src="${ctx}/static/js/z.c.js"></script>
    
  </body>
</html>