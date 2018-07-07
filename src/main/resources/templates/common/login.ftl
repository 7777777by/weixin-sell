<!DOCTYPE HTML>
<html>
<head>
    <title>卖家后端管理系统</title>
    <script src="../js/jquery.min.js"></script>
    <!-- Custom Theme files -->
    <link href="../css/login.css" rel="stylesheet" type="text/css" media="all"/>
    <!-- for-mobile-apps -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Classy Login form Responsive, Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
    <!-- //for-mobile-apps -->
    <!--Google Fonts-->
    <link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:400,700' rel='stylesheet' type='text/css'>
</head>
<body>
<!--header start here-->
<div class="header">
    <div class="header-main">
        <h1>卖家后端管理系统</h1>
        <div class="header-bottom">
            <div class="header-right w3agile">

                <div class="header-left-bottom agileinfo">

                    <form action="/sell/user/login" method="post">
                        <input type="text"  value="User name" name="userName" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'User name';}"/>
                        <input type="password"  value="Password" name="password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'password';}"/>
                        <div class="remember">
			             <span class="checkbox1">
							   <label class="checkbox"><input type="checkbox" name="" checked=""><i> </i>记住我</label>
						 </span>
                            <div class="forgot">
                                <h6><a href="#">忘记密码?</a></h6>
                            </div>
                            <div class="clear"> </div>
                        </div>

                        <input type="submit" value="Login">
                    </form>
                    <div class="header-left-top">
                        <div class="sign-up"> <h2>or</h2> </div>

                    </div>
                    <div class="header-social wthree">
                        <a href="#" class="face"><h5>Facebook</h5></a>
                        <a href="#" class="twitt"><h5>Twitter</h5></a>
                    </div>

                </div>
            </div>

        </div>
    </div>
</div>
<!--header end here-->
<div class="copyright">
    <p>© 2016 Classy Login Form. All rights reserved | Design by  <a href="http://www.smallseashell.com">小阳子</a></p>
</div>
<!--footer end here-->
</body>
</html>