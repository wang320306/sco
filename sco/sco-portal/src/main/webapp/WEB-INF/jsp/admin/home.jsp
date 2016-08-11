<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="zh-cn" class="no-js">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="author" content="">
    <meta name="keywords" content="">
    <meta name="description" content="" />
    <title>后台</title>
    <link rel="stylesheet" href="${ctx}/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/static/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx}/static/css/animate.min.css">
    <link rel="stylesheet" href="${ctx}/static/css/style.css">
  </head>
  <body class="sticky-header">
	<section>

      <!-- left side -->
	  <div class="left-side sticky-left-side">

	    <!-- logo -->
		<div class="logo text-center">
		  <a href="javascript:;">IFEN</a>
		</div>
		<div class="logo-icon text-center">
		  <a href="javascript:;">IF</a>
		</div>
		<!-- logo end -->

		<!-- left menu -->
		<div class="left-side-inner">

		  <!-- nav -->
		  <ul class="nav nav-pills nav-stacked custom-nav">
		    <li class="menu-list nav-active">
		      <a href="index.html"><i class="fa fa-home"></i><span>Dashboard</span></a>
			  <ul class="sub-menu-list">
			    <li><a href="index_alt.html"> Dashboard 1</a></li>
				<li><a href="index.html"> Dashboard 2</a></li>
			  </ul>
			</li>
			<li class="menu-list">
			  <a href=""><i class="fa fa-laptop"></i><span>Layouts</span></a>
			  <ul class="sub-menu-list">
			    <li><a href="blank_page.html"> Blank Page</a></li>
				<li><a href="boxed_view.html"> Boxed Page</a></li>
			    <li><a href="leftmenu_collapsed_view.html"> Sidebar Collapsed</a></li>
				<li><a href="horizontal_menu.html"> Horizontal Menu</a></li>
			  </ul>
			</li>
		  </ul><!-- nav end-->
			
		</div><!-- left menu end -->
	  </div><!-- left side end -->

	  <!-- right main content -->
	  <div class="main-content">

	    <!-- header section start-->
		<div class="header-section">

		  <!--toggle button start-->
		  <a class="toggle-btn"><i class="fa fa-bars"></i></a>
		  <!--toggle button end-->

		  <!--notification menu start -->
		  <div class="menu-right">
		    <ul class="notification-menu">
			  <li>
			    <a href="#" class="btn btn-default dropdown-toggle info-number" data-toggle="dropdown"> 
			      <i class="fa fa-envelope-o"></i> <span class="badge">5</span>
			    </a>
				<div class="dropdown-menu dropdown-menu-right dropdown-menu-head">
				  <h5 class="title">You have 5 Mails</h5>
				  <ul class="dropdown-list normal-list">
				    <li class="new">
					  <a href=""> 
					    <span class="thumb"> <img src="images/photos/user1.png" alt="" /> </span> 
					    <span class="desc">
						  <span class="name">John Doe <span class="badge badge-success">new</span></span> 
						  <span class="msg">Lorem ipsum dolor sit amet...</span>
						</span>
					  </a>
					</li>
					<li>
					  <a href=""> 
					    <span class="thumb"><img src="images/photos/user2.png" alt="" /></span> 
					    <span class="desc">
						  <span class="name">Jonathan Smith</span> 
						  <span class="msg">Lorem ipsum dolor sit amet...</span>
						</span>
					  </a>
					</li>
				    <li class="new"><a href="">Read All Mails</a></li>
				  </ul>
				</div>
			  </li>
			  <li>
			    <a href="#" class="btn btn-default dropdown-toggle info-number" data-toggle="dropdown"> 
			      <i class="fa fa-bell-o"></i> <span class="badge">4</span>
			    </a>
				<div class="dropdown-menu dropdown-menu-right dropdown-menu-head">
				  <h5 class="title">Notifications</h5>
				  <ul class="dropdown-list normal-list">
				    <li class="new">
				      <a href=""> 
				        <span class="label label-danger"><i class="fa fa-bolt"></i></span>
						<span class="name">Server #1 overloaded. </span> <em class="small">34 mins</em>
					  </a>
					</li>
					<li class="new">
					  <a href=""> 
					    <span class="label label-danger"><i class="fa fa-bolt"></i></span>
						  <span class="name">Server #3 overloaded. </span> <em class="small">1 hrs</em>
					  </a>
					</li>
					<li class="new">
					  <a href=""> 
					    <span class="label label-danger"><i class="fa fa-bolt"></i></span>
						<span class="name">Server #5 overloaded. </span> <em class="small">4 hrs</em>
					  </a>
					</li>
					<li class="new">
					  <a href=""> 
					    <span class="label label-danger"><i class="fa fa-bolt"></i></span>
						<span class="name">Server #31 overloaded. </span> <em class="small">4 hrs</em>
					  </a>
					</li>
					<li class="new"><a href="">See All Notifications</a></li>
				  </ul>
				</div>
			  </li>
			  <li>
			    <a href="#" class="btn btn-default dropdown-toggle" data-toggle="dropdown"> 
			      <img src="images/photos/user-avatar.png" alt="" /> John Doe <span class="caret"></span>
			    </a>
				<ul class="dropdown-menu dropdown-menu-usermenu pull-right">
				  <li><a href="#"><i class="fa fa-user"></i> Profile</a></li>
				  <li><a href="#"><i class="fa fa-cog"></i> Settings</a></li>
				  <li><a href="#"><i class="fa fa-sign-out"></i> Log Out</a></li>
				</ul>
			  </li>
			</ul>
		  </div><!--notification menu end -->
		  
	    </div><!-- header section end-->
	    
	  </div><!-- right main content end -->
	  
	</section>

	<script src="${ctx}/static/js/jquery-1.12.4.min.js"></script>
	<script src="${ctx}/static/js/bootstrap.min.js"></script>
	<script src="${ctx}/static/js/jquery.nicescroll.min.js"></script>
	<script src="${ctx}/static/js/z.a.js"></script>
  </body>
</html>