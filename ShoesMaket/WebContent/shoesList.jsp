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
<!-- start slider -->
<div class="slider">
				<!---start-image-slider---->
				<div class="image-slider">
					 <div class="wrapper">
                <div id="ei-slider" class="ei-slider">
                    <ul class="ei-slider-large">
						<li>
                            <img src="images/slider-image1.jpg" alt="image06"/>
                            <div class="ei-title">
                            	<h3 class="btn">$145.99</h3>
                                <h2>pink shoes <br>	2016 collections</h2>
                                <h3 class="active">It is a long established fact that a reader<br>
                                	Lorem Ipsum is that it has a more-or-less 
                                </h3>
                                <h3>
                                	<a class="ei_icons" href="details.jsp"><img src="images/icon_1.png" alt=""></a>
                                	<a class="ei_icons" href="details.jsp"><img src="images/icon_2.png" alt=""></a>
                                	<a class="ei_icons" href="details.jsp"><img src="images/icon_3.png" alt=""></a>
                                	<a class="ei_icons" href="details.jsp"><img src="images/icon_4.png" alt=""></a>
                                </h3>
                            </div>
                        </li>
                        <li>
                            <img src="images/slider-image2.jpg" alt="image01" />
                            <div class="ei-title">
                            	<h3 class="btn">$145.99</h3>
                                <h2>pink shoes <br>	2016 collections</h2>
                                <h3 class="active">It is a long established fact that a reader<br>
                                	Lorem Ipsum is that it has a more-or-less 
                                </h3>
                                <h3>
                                	<a class="ei_icons" href="details.jsp"><img src="images/icon_1.png" alt=""></a>
                                	<a class="ei_icons" href="details.jsp"><img src="images/icon_2.png" alt=""></a>
                                	<a class="ei_icons" href="details.jsp"><img src="images/icon_3.png" alt=""></a>
                                	<a class="ei_icons" href="details.jsp"><img src="images/icon_4.png" alt=""></a>
                                </h3>
                            </div>
                        </li>
                        <li>
                            <img src="images/slider-image3.jpg" alt="image02" />
                            <div class="ei-title">
                            	<h3 class="btn">$145.99</h3>
                                <h2>pink shoes <br>	2016 collections</h2>
                                <h3 class="active">It is a long established fact that a reader<br>
                                	Lorem Ipsum is that it has a more-or-less 
                                </h3>
                                <h3>
                                	<a class="ei_icons" href="details.jsp"><img src="images/icon_1.png" alt=""></a>
                                	<a class="ei_icons" href="details.jsp"><img src="images/icon_2.png" alt=""></a>
                                	<a class="ei_icons" href="details.jsp"><img src="images/icon_3.png" alt=""></a>
                                	<a class="ei_icons" href="details.jsp"><img src="images/icon_4.png" alt=""></a>
                                </h3>
                            </div>
                        </li>
                        <li>
                            <img src="images/slider-image4.jpg" alt="image03"/>
                            <div class="ei-title">
                            	<h3 class="btn">$145.99</h3>
                                <h2>pink shoes <br>	2016 collections</h2>
                                <h3 class="active">It is a long established fact that a reader<br>
                                	Lorem Ipsum is that it has a more-or-less 
                                </h3>
                                <h3>
                                	<a class="ei_icons" href="details.jsp"><img src="images/icon_1.png" alt=""></a>
                                	<a class="ei_icons" href="details.jsp"><img src="images/icon_2.png" alt=""></a>
                                	<a class="ei_icons" href="details.jsp"><img src="images/icon_3.png" alt=""></a>
                                	<a class="ei_icons" href="details.jsp"><img src="images/icon_4.png" alt=""></a>
                                </h3>
                            </div>
                        </li>
                        <li>
                            <img src="images/slider-image1.jpg" alt="image04"/>
                            <div class="ei-title">
                            	<h3 class="btn">$145.99</h3>
                                <h2>pink shoes <br>	2016 collections</h2>
                                <h3 class="active">It is a long established fact that a reader<br>
                                	Lorem Ipsum is that it has a more-or-less 
                                </h3>
                                <h3>
                                	<a class="ei_icons" href="details.jsp"><img src="images/icon_1.png" alt=""></a>
                                	<a class="ei_icons" href="details.jsp"><img src="images/icon_2.png" alt=""></a>
                                	<a class="ei_icons" href="details.jsp"><img src="images/icon_3.png" alt=""></a>
                                	<a class="ei_icons" href="details.jsp"><img src="images/icon_4.png" alt=""></a>
                                </h3>
                            </div>
                        </li>
                        <li>
                            <img src="images/slider-image5.jpg" alt="image05"/>
                            <div class="ei-title">
                            	<h3 class="btn">$145.99</h3>
                                <h2>pink shoes <br>	2016 collections</h2>
                                <h3 class="active">It is a long established fact that a reader<br>
                                	Lorem Ipsum is that it has a more-or-less 
                                </h3>
                                <h3>
                                	<a class="ei_icons" href="details.jsp"><img src="images/icon_1.png" alt=""></a>
                                	<a class="ei_icons" href="details.jsp"><img src="images/icon_2.png" alt=""></a>
                                	<a class="ei_icons" href="details.jsp"><img src="images/icon_3.png" alt=""></a>
                                	<a class="ei_icons" href="details.jsp"><img src="images/icon_4.png" alt=""></a>
                                </h3>
                            </div>
                        </li>
                        <li>
                            <img src="images/slider-image3.jpg" alt="image07"/>
                            <div class="ei-title">
                            	<h3 class="btn">$145.99</h3>
                                <h2>pink shoes <br>	2016 collections</h2>
                                <h3 class="active">It is a long established fact that a reader<br>
                                	Lorem Ipsum is that it has a more-or-less 
                                </h3>
                                <h3>
                                	<a class="ei_icons" href="details.jsp"><img src="images/icon_1.png" alt=""></a>
                                	<a class="ei_icons" href="details.jsp"><img src="images/icon_2.png" alt=""></a>
                                	<a class="ei_icons" href="details.jsp"><img src="images/icon_3.png" alt=""></a>
                                	<a class="ei_icons" href="details.jsp"><img src="images/icon_4.png" alt=""></a>
                                </h3>
                            </div>
                        </li>
                    </ul><!-- ei-slider-large -->
                    <ul class="ei-slider-thumbs">
                        <li class="ei-slider-element">Current</li>
						<li>
							<a href="#">
								<span class="active">pink shoes</span>
								<p>now of $145.99</p> 
							</a>
							<img src="images/thumbs/1.jpg" alt="thumb01" />
						</li>
                        <li class="hide"><a href="#"><span>anns field</span><p>limited edition</p> </a><img src="images/thumbs/2.jpg" alt="thumb01" /></li>
                        <li  class="hide1"><a href="#"><span>prada</span><p>summer is coming</p></a><img src="images/thumbs/3.jpg" alt="thumb02" /></li>
                        <li class="hide1"><a href="#"><span>casa devi</span><p>all colors available</p> </a><img src="images/thumbs/4.jpg" alt="thumb03" /></li>
                        <li><a href="#"><span>mellow yellow</span><p>free delivery</p> </a><img src="images/thumbs/1.jpg" alt="thumb04" /></li>
                        <li><a href="#"><span>anns field</span><p>limited edition</p> </a><img src="images/thumbs/5.jpg" alt="thumb05" /></li>
                        <li><a href="#"><span>anns field</span><p>limited edition</p> </a><img src="images/thumbs/3.jpg" alt="thumb07" /></li>
                    </ul><!-- ei-slider-thumbs -->
                </div><!-- ei-slider -->
            </div><!-- wrapper -->
		</div>
		<!---End-image-slider---->	
</div>
<!-- start image1_of_3 -->
<div class="copyrights">Collect from <a href="http://www.cssmoban.com/" >åè´¹ç½ç«æ¨¡æ¿</a></div>
<div class="top_bg">
<div class="wrap">
<div class="main1">
	<div class="image1_of_3">
		<img src="images/img1.jpg" alt=""/>
	    <a href="details.jsp"><span class="tag">on sale</span></a>
	</div>
	<div class="image1_of_3">
		<img src="images/img2.jpg" alt=""/>
		<a href="details.jsp"><span class="tag">special offers</span></a>
	</div>
	<div class="image1_of_3">
		<img src="images/img3.jpg" alt=""/>
		<a href="details.jsp"><span class="tag">must have</span></a>
	</div>
	<div class="clear"></div>
</div>
</div>
</div>
<!-- start main -->
<div class="main_bg">
<div class="wrap">
<div class="main">
	<div class="top_main">
		<h2>new arrivals on fooseshoes</h2>
		<a href="#">show all</a>
		<div class="clear"></div>
	</div>
	
	<!-- start grids_of_3 -->
	
	
	<c:forEach var='s' items="${requestScope.shoes}">
	<div class="grids_of_3">
	<div class="grid1_of_3">
		<a href="ShoesServlet?method=details&shoeid=${s.shoeid}">
			<img src="${s.xiezitupian}" alt=""/>
			<h3 style="text-align:center;">${s.pinpaiming}</h3>
			<span class="price">$${s.shoujia}</span>
		</a>
	</div>
	</c:forEach>
	</div>
	
	
	
	<div class="clear"></div>
	
	<div>
	<!-- 控制页码 -->
	<div style="position:relative;left:-300px">
					  <div style="width: 100%;margin: auto;text-align: center;">
						
						<a  href="ShoesServlet?method=listShoesByPage&page=1&count=3"  class="pages">首页</a>
						<a  href="ShoesServlet?method=listShoesByPage&page=${requestScope.pageBean.previousPage}&count=3"  class="pages">上一页</a>
						<a  href="ShoesServlet?method=listShoesByPage&page=${requestScope.pageBean.nextPage}&count=3"  class="pages">下一页</a>
						<a  href="ShoesServlet?method=listShoesByPage&page=${requestScope.pageBean.allPages}&count=3"  class="pages">尾页</a>
						
						当前第${requestScope.pageBean.nowPage}页/总共${requestScope.pageBean.allPages}页，
						每页${requestScope.pageBean.everPageCount}条/总共${requestScope.pageBean.allCount}条,
						跳到第
						
						<select  id="goto"  style="width: 20px;height: 16px; position: relative;top: 6px;"  class="">
						<%
						
							for(int n=1;n<=((PageBean)request.getAttribute("pageBean")).getAllPages();n++)
							{
								%>
								<option value="<%=n%>"><%=n %></option>
								<%
							}
						%>
						
						</select>页
	</div>
	</div>
			
	<!-- start grids_of_2 -->
	<div class="grids_of_2">
		<div class="grid1_of_2">
			<div class="span1_of_2">
				<h2>free shipping</h2>
				<p>Lorem Ipsum is simply dummy  typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.</p>
			</div>
			<div class="span1_of_2">
				<h2>testimonials</h2>
				<p>It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using [...]</p>
			</div>
		</div>
		<div class="grid1_of_2 bg">
			<h2>blog news</h2>
			<div class="grid_date">
				<div class="date1"> 
					<h4>apr 01</h4>
				</div>
				<div class="date_text">
					<h4><a href="#"> Donec vehicula est ac augue consectetur,</a></h4>
					<p>There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form</p> 
				</div>
				<div class="clear"></div>
			</div>
			<div class="grid_date">
				<div class="date1"> 
					<h4>feb 01</h4>
				</div>
				<div class="date_text">
					<h4><a href="#"> The standard chunk of Lorem Ipsum used since ,,</a></h4>
					<p>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from </p> 
				</div>
				<div class="clear"></div>
			</div>
		</div>
		<div class="clear"></div>
	</div>
</div>
</div>
</div>
<!-- start footer -->
<div class="footer_top">
<div class="wrap">
<div class="footer">
	<!-- start grids_of_3 -->
	<div class="span_of_3">
		<div class="span1_of_3">
			<h3>text edit</h3>
			<p>But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness.</p>
		</div>
		<div class="span1_of_3">
			<h3>twitter widget</h3>
			<p><a href="#">@Contrarypopular</a> I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give <a href="#">@accountofsystem</a> </p>
			<p class="top">19 days ago</p>
			<p class="top"><a href="#">@Contrarypopular</a> I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give <a href="#">@accountofsystem</a> </p>
			<p class="top">19 days ago</p>
		</div>
		<div class="span1_of_3">
			<h3>flickr widget</h3>
			<ul class="f_nav">
				<li><a href="#"><img src="images/f_pic1.jpg" alt="" /> </a></li>
				<li><a href="#"><img src="images/f_pic2.jpg" alt="" /> </a></li>
				<li><a href="#"><img src="images/f_pic3.jpg" alt="" /> </a></li>
				<li><a href="#"><img src="images/f_pic4.jpg" alt="" /> </a></li>
				<li><a href="#"><img src="images/f_pic5.jpg" alt="" /> </a></li>
				<li><a href="#"><img src="images/f_pic6.jpg" alt="" /> </a></li>
			</ul>
		</div>
		<div class="clear"></div>
	</div>
</div>
</div>
</div>
<!-- start footer -->
<div class="footer_mid">
<div class="wrap">
<div class="footer">
	<div class="f_search">
		<form>
			<input type="text" value="" placeholder="Enter email for newsletter" />
			<input type="submit" value=""/>
		</form>
	</div>
	<div class="soc_icons">
			<ul>
				<li><a class="icon1" href="#"></a></li>
				<li><a class="icon2" href="#"></a></li>
				<li><a class="icon3" href="#"></a></li>
				<li><a class="icon4" href="#"></a></li>
				<li><a class="icon5" href="#"></a></li>
			</ul>	
	</div>
	<div class="clear"></div>
</div>
</div>
</div>
<!-- start footer -->
<div class="footer_bg">
<div class="wrap">
<div class="footer">
		<!-- scroll_top_btn -->
	    <script type="text/javascript">
			$(document).ready(function() {
			
				var defaults = {
		  			containerID: 'toTop', // fading element id
					containerHoverID: 'toTopHover', // fading element hover id
					scrollSpeed: 1200,
					easingType: 'linear' 
		 		};
				
				
				$().UItoTop({ easingType: 'easeOutQuart' });
				
			});
		</script>
		 <a href="#" id="toTop" style="display: block;"><span id="toTopHover" style="opacity: 1;"></span></a>
		<!--end scroll_top_btn -->
	<div class="f_nav1">
		<ul>
			<li><a href="#">home /</a></li>
			<li><a href="#">support /</a></li>
			<li><a href="#">Terms and conditions /</a></li>
			<li><a href="#">Faqs /</a></li>
			<li><a href="#">Contact us</a></li>
		</ul>
		</div>
	<div class="copy">
		<p class="link"><span>Copyright &copy; 2016.Company name All rights reserved.More Templates <a href="http://www.cssmoban.com/" target="_blank" title="æ¨¡æ¿ä¹å®¶">æ¨¡æ¿ä¹å®¶</a> - Collect from <a href="http://www.cssmoban.com/" title="ç½é¡µæ¨¡æ¿" target="_blank">ç½é¡µæ¨¡æ¿</a></p>
	</div>
	<div class="clear"></div>
</div>
</div>
</div>

<script type="text/javascript">
			$(document).ready(function(){
				$("#goto").change(function(){
					location.href='ShoesServlet?method=listShoesByPage&page='+$(this).val()+'&count=3';//用js发起请求
				})
			})
</script>
</body>
</html>