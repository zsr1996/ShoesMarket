<%@page import="com.oracle.shoesMaket.model.bean.Shoes"%>
<%@page import="com.oracle.shoesMaket.model.bean.User"%>
<%@page import="com.oracle.shoesMaket.model.bean.PageBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager" %>
<%@page import="java.sql.Connection" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><!-- EL的函数库=jstl的函数 -->
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>

<!-- jsp网页打开时就要显示鞋子的信息，因此首页必须有代码去查看数据库 -->



<html>
<head>
<title>Home</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link href='https://fonts.googleapis.com/css?family=Maven+Pro:400,900,700,500' rel='stylesheet' type='text/css'>
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<!--- start-mmmenu-script---->
<script src="js/jquery.min.js" type="text/javascript"></script>
<link type="text/css" rel="stylesheet" href="css/jquery.mmenu.all.css" />
<script type="text/javascript" src="js/jquery.mmenu.js"></script>
		<script type="text/javascript">
			//	The menu on the left
			$(function() {
				$('nav#menu-left').mmenu();
			});
		</script>
<!-- start slider -->
	<link href="css/slider.css" rel="stylesheet" type="text/css" media="all" />
        <script type="text/javascript" src="js/jquery.eislideshow.js"></script>
        <script type="text/javascript" src="js/easing.js"></script>
        <script type="text/javascript">
            $(function() {
                $('#ei-slider').eislideshow({
					animation			: 'center',
					autoplay			: true,
					slideshow_interval	: 3000,
					titlesFactor		: 0
                });
            });
        </script>
<!-- start top_js_button -->
<script type="text/javascript" src="js/move-top.js"></script>
   <script type="text/javascript">
		jQuery(document).ready(function($) {
			$(".scroll").click(function(event){		
				event.preventDefault();
				$('html,body').animate({scrollTop:$(this.hash).offset().top},1200);
			});
		});
	</script>
</head>
<body>


<!-- start header -->
<div class="top_bg">
<div class="wrap">
	<div class="header">
		<div class="logo">
			<a href="index.jsp"><img src="images/logo.png" alt=""/></a>
		</div>
		
	<!-- 
		<% if(session.getAttribute("loginedUser")==null){ %>
				<%}else{ %>
				<img src="<%=((User)session.getAttribute("loginedUser")).getImage() %>" style="width: 20px;height: 20px;border-radius: 10px;"/>
				<span style="position: relative;top: -5px;color:white;'"><%=((User)session.getAttribute("loginedUser")).getFirstname() %></span>
				<%} %>
	 -->	
		
		 <div class="log_reg">
				<ul>
					<li><a href="login.jsp">Login</a> </li>
					<span class="log"> or </span>
					<li><a href="register.jsp">Register</a> </li>								   
					<div class="clear"></div>
				</ul>
		</div>	
		<div class="web_search">
		 	<form action="ShoesServlet">
		 		<input type="hidden" name="method" value="search">
		 	
				<input type="text" placeholder="请输入品牌名" value="" name="manufacturer" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '';}">				
				<input type="submit" value=" " />
			
			</form>
	    </div>						
		<div class="clear"></div>
	</div>	
</div>
</div>
<!-- start header_btm -->
<div class="wrap">
<div class="header_btm">
		<div class="menu">
			<ul>
				<li class="active"><a href="index.jsp">Home</a></li>
				<li><a href="products.jsp">products</a></li>
				<li><a href="about.jsp">about</a></li>
				<li><a href="index.jsp">pages</a></li>
				<li><a href="blog.jsp">blog</a></li>
				<li><a href="contact.jsp">Contact</a></li>
				<div class="clear"></div>
			</ul>
		</div>
		<div id="smart_nav">
			<a class="navicon" href="#menu-left"> </a>
		</div>
		<nav id="menu-left">
			<ul>
				<li><a href="index.jsp">Home</a></li>
				<li><a href="products.jsp">products</a></li>
				<li><a href="about.jsp">about</a></li>
				<li><a href="index.jsp">pages</a></li>
				<li><a href="blog.jsp">blog</a></li>
				<li><a href="contact.jsp">Contact</a></li>
				<div class="clear"></div>
			</ul>
		</nav>
	<div class="header_right">
		<ul>
			<li><a href="#"><i  class="art"></i><span class="color1">30</span></a></li>
			<li><a href="#"><i  class="cart"></i><span>0</span></a></li>
		</ul>
	</div>
	<div class="clear"></div>
</div>
</div>

<div class="wrap">

	
	
	<div class="main" style="position:relative;top:-51px">

		<!-- - - - - - - - - - - - - - - Container - - - - - - - - - - - - - - - - -->	

		 <section  style="border:1px solid #E2E2E2">
			
			<h3 style="position:relative;top:30px;left:20px;font-size:20px"><font color="orange">个人中心</font></h3>
			
			<section style="position:relative;top:40px;left:20px">
				
				<div style="width: 280px">
					<form action="UserServlet" enctype="multipart/form-data">
					<input  type="hidden"  name="userid"  value="${user.userid }"/>
					<header >
						<h2 ><font color="orange">email:</font><span class="description">${user.email}</span></h2>
						<%-- <input type="hidden" name="username" value="${user.username }" /> --%>
<%-- 						<h3 class="description">${user.username }</h3> --%>
					</header><!--/ .header -->
					
					<div >
						
						<img src="${user.image }"  width="200" height="200"  style="border:1px solid gray;border-radius: 100px;"/>
						<input type="file"  name="headImage" />
<!-- 						<dl> -->
<!-- 							<dt><span class="currency">$</span></dt> -->
<!-- 							<dd><span class="int">25</span></dd> -->
<!-- 							<dd><span class="sup" data-month="p/m">99</span></dd> -->
<!-- 						</dl> -->
						
					</div><!--/ .price-->

					<ul>
						<li><span>firstname:<input   name="firstname"  value="${user.firstname }"/></span></li>
						<li><span>lastname: <input   name="lastname"  value="${user.lastname }"/></span></li>
						<li><span>sex:
							<c:choose>
								<c:when test="${user.sex eq Male }">
									<input type="radio" name="sex" value="Male" checked="checked"/>Male
									<input type="radio" name="sex" value="Female" />Female
								</c:when>
								<c:otherwise>
										<input type="radio" name="sex" value="Male" />Male
									<input type="radio" name="sex" value="Female"checked="checked" />Female
								</c:otherwise>
							</c:choose>
						</span></li>
						<li><span>age:
						<select name="age">
							<c:forEach var="age" begin="1" end="100">
								<c:if test="${age eq user.age }">
										<option selected="selected" value="${age}">${age }</option>
								</c:if>
								<c:if test="${age ne user.age }">
										<option value="${age}">${age }</option>
								</c:if>
							</c:forEach>
						</select>
						</span></li>
						<li><span>phone:<input   name="phone"  value="${user.phone }"/></span></li>
						<li ><span>resitertime:
							<select name="jialing">
							<c:forEach var="j" begin="1" end="100">
								<c:if test="${j eq 2 }">
										<option selected="selected" value="${j}">${j }</option>
								</c:if>
								<c:if test="${j ne 2 }">
										<option value="${j}">${j }</option>
								</c:if>
							</c:forEach>
						</select>
						</span></li>
						
					</ul><!--/ .features -->

					<footer >
						<input type="submit" value="修改"  /><input type="reset" value="清空" />
					</footer><!--/.footer -->
					</form>
				</div><!--/ .col -->

				</div><!--/ .col -->

			</section><!--/ .pricing-table -->
	

		</section><!--/ .container -->

		<!-- - - - - - - - - - - - - end Container - - - - - - - - - - - - - - - - -->			
		
	</div><!--/ .main--> 
	<!-- 
	<c:import url="bottom.jsp"></c:import>
	 -->
	

</body>
</html>
