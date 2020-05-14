<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Carrinho de Compra</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <!--[if ie]><meta content='IE=8' http-equiv='X-UA-Compatible'/><![endif]-->
        <!-- bootstrap -->
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">      
        <link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">		
        <link href="themes/css/bootstrappage.css" rel="stylesheet"/>

        <!-- global styles -->
        <link href="themes/css/flexslider.css" rel="stylesheet"/>
        <link href="themes/css/main.css" rel="stylesheet"/>

        <!-- scripts -->
        <script src="themes/js/jquery-1.7.2.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>				
        <script src="themes/js/superfish.js"></script>	
        <script src="themes/js/jquery.scrolltotop.js"></script>
        <!--[if lt IE 9]>			
                <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
                <script src="themes/js/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>		
        <div id="top-bar" class="container">
            <div class="row">
                <div class="span4">
                    <form method="POST" class="search_form">
                        <input type="text" class="input-block-level search-query" Placeholder="O que você está procurando?">
                    </form>
                </div>
                <div class="span8">
                    <div class="account pull-right">
                        <ul class="user-menu">				

                            <li><a href="cart.html">Finalizar Compra</a></li>					
                            <li><a href="register.html">Login</a></li>			
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div id="wrapper" class="container">
            <section class="navbar main-menu">
                <div class="navbar-inner main-menu">				
                    <a href="${pageContext.request.contextPath}/land" class="logo pull-left logo-size"><i class="fas fa-shopping-cart"></i> Sports TADS</a>
                    <nav id="menu" class="pull-right">
                        <ul>
                            <li><a href="./products.html">Masculino</a>					
                                <ul>
                                    <li><a href="#">Camisetas</a></li>									
                                    <li><a href="#">Tênis</a></li>
                                    <li><a href="#">Bermudas</a></li>									
                                </ul>
                            </li>
                            <li><a href="./products.html">Feminino</a>					
                                <ul>
                                    <li><a href="#">Camisetas</a></li>									
                                    <li><a href="#">Tênis</a></li>
                                    <li><a href="#">Bermudas</a></li>									
                                </ul>
                            </li>
                            <li><a href="#">Equipamentos</a>
                                <ul>									
                                    <li><a href="#">Academia</a></li>
                                    <li><a href="#">Esportes</a></li>
                                </ul>
                            </li>							
                            <li><a href="#">Mais Vendidos</a></li>
                        </ul>
                    </nav>
                </div>
            </section>				
            <section class="header_text sub">
                <img class="pageBanner" src="${pageContext.request.contextPath}/themes/images/banner1.jpg" alt="New products" >
                <br>
            </section>
            <section class="main-content">				
                <div class="row">
                    <div class="span9">					
                        <h4 class="title"><span class="text"><strong>Meu</strong> Carrinho</span></h4>
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Retirar Produto</th> 
                                    <th> </th>
                                    <th>Nome do Produto</th>
                                    <th>Quantidade</th>
                                    <th>Valor Unitário</th>
                                    <th>Valor Total</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><input type="checkbox" value="option1"></td>
                                    <td><a href="product_detail.html"><img alt="" src="themes/images/ladies/9.jpg"></a></td>
                                    <td>Fusce id molestie massa</td>
                                    <td><input type="text" placeholder="1" class="input-mini"></td>
                                    <td>$2,350.00</td>
                                    <td>$2,350.00</td>
                                </tr>			  
                                <tr>
                                    <td><input type="checkbox" value="option1"></td>
                                    <td><a href="product_detail.html"><img alt="" src="themes/images/ladies/1.jpg"></a></td>
                                    <td>Luctus quam ultrices rutrum</td>
                                    <td><input type="text" placeholder="2" class="input-mini"></td>
                                    <td>$1,150.00</td>
                                    <td>$2,450.00</td>
                                </tr>
                                <tr>
                                    <td><input type="checkbox" value="option1"></td>
                                    <td><a href="product_detail.html"><img alt="" src="themes/images/ladies/3.jpg"></a></td>
                                    <td>Wuam ultrices rutrum</td>
                                    <td><input type="text" placeholder="1" class="input-mini"></td>
                                    <td>$1,210.00</td>
                                    <td>$1,123.00</td>
                                </tr>
                                <tr>
                                    <td>&nbsp;</td>
                                    <td>&nbsp;</td>
                                    <td>&nbsp;</td>
                                    <td>&nbsp;</td>
                                    <td>&nbsp;</td>
                                    <td><strong>$3,600.00</strong></td>
                                </tr>		  
                            </tbody>
                        </table>
                        
                        <p class="buttons center">				
                      
                            <button class="btn btn-inverse" type="submit" id="checkout">Finalizar Compra</button>
                        </p>					
                    </div>
                    <div class="span3 col">
                        <div class="block">	
                            <ul class="nav nav-list">
                                <li class="nav-header">Melhores Marcas</li>
                                <li><a href="#">Nike</a></li>
                                <li class="active"><a href="products.html">Adidas</a></li>
                                <li><a href="#">Puma</a></li>
                                <li><a href="#">Olympikus</a></li>
                                <li><a href="#">Mizuno</a></li>
                            </ul>
                        </div>
                        <div class="block">								
                            <h4 class="title"><strong>Mais</strong> Vendido</h4>
                            <li class="active"><a href="products.html"></a> </li>
                            <ul class="small-product">
                            </ul>
                        </div>
                    </div>

                </div>

            </section>			
            <section id="footer-bar">
                <div class="row">
                    <div class="span3">
                        <h4>Navegação</h4>
                        <ul class="nav">
                            <li><a href="#">Principal</a></li>  
                            <li><a href="#">Sobre nós</a></li>
                            <li><a href="#">Seu carrinho</a></li>
                            <li><a href="#">Login</a></li>							
                        </ul>					
                    </div>
                    <div class="span4">
                        <h4>Minha conta</h4>
                        <ul class="nav">
                            <li><a href="#">Minha conta</a></li>
                            <li><a href="#">Meus pedidos</a></li>
                        </ul>
                    </div>
                    <div class="span5">
                        <p class="logo logo-size-endpage"><i class="fas fa-shopping-cart"></i> Sports TADS</a>
                        <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. the  Lorem Ipsum has been the industry's standard dummy text ever since the you.</p>
                        <br/>
                        <span class="social_icons">
                            <a class="facebook" href="#">Facebook</a>
                            <a class="twitter" href="#">Twitter</a>
                            <a class="skype" href="#">Skype</a>
                            <a class="vimeo" href="#">Vimeo</a>
                        </span>
                    </div>					
                </div>	
            </section>				
                
            <section id="copyright">
                <span>Copyright 2013 bootstrappage template  All right reserved.</span>
            </section>
        </div>
       <script src="${pageContext.request.contextPath}/themes/js/common.js"></script>
        <script src="https://kit.fontawesome.com/1803175e4f.js" crossorigin="anonymous"></script>	
    </body>
</html>