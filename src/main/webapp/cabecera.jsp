<%@page import="modelo.Usuario"%>
<%!Usuario u1 ; %>
<% 	try{
		u1 =(Usuario)session.getAttribute("Usuario");
		u1.getUsuario();
	}catch(NullPointerException e){
		u1 = new Usuario("Anonimo",0);
		
	}%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container-fluid">
	    <div class="container-fluid">
        <div class="navbar-header">
        <a class="navbar-brand" href="index.htm" style="">Guia <span class="titulo">Walt Disney </span>World Fan</a>  
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
	        <ul class="nav navbar-nav" style="padding-top: 13px; padding-left: 70px;">
	                 
	        <li class="dropdown">
	              <a href="#" class="dropdown-toggle" data-toggle="dropdown">&nbsp;Info </a>
	              <ul class="dropdown-menu" role="menu">
	                <li class="dropdown-header">Los Parques</li>
	                  <li><a href="#">Magic KingDom</a></li>
	                  <li><a href="#">Epcot</a></li>
	                  <li><a href="#">Holliwood Studios</a></li>
	                  <li><a href="#">Animal Kingdom</a></li>
	                  <li><a href="#">Parques de Agua</a></li>
	                 <li class="divider"></li>
	                 <li class="dropdown-header">+ Info</li>
	                <li><a href="#">My Magic Plus y Magic Bands</a></li>
	                <li><a href="#">Fast Pass +</a></li>
	                <li><a href="#">Memory Maker y PhotoConnect</a></li>
	               
	
	              </ul>
	        </li>
	        <li class="dropdown">
	              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Hoteles </a>
	              <ul class="dropdown-menu" role="menu">
	                <li><a href="#">Deluxe</a></li>
	                <li><a href="#">Moderados</a></li>
	                <li><a href="#">Economicos</a></li>
	
	              </ul>
	        </li>
	        <li class="dropdown">
	              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Restaurantes </a>
	              <ul class="dropdown-menu" role="menu">
	                 <li><a href="#">Magic KingDom</a></li>
	                <li><a href="#">Epcot</a></li>
	                <li><a href="#">Holliwood Studios</a></li>
	                <li><a href="#">Animal Kingdom</a></li>
	                <li><a href="#">Restaurantes en los Hoteles</a></li>
	              </ul>
	        </li>
	        
	        <li><a href="Calendario2.jsp">Tu Planning</a></li>
	
	        <form class="navbar-form navbar-right " role="form" id="search-form" action="Busqueda" method="post">
	         <div class="form-group">
	            <input type="text" class="form-control" placeholder="Buscar" id="spotlight" name="spotlight">
	          </div>
	         </form>               
	
	        </ul>
	        <%if (u1.getUsuario()=="Anonimo"){ %>        
	        <ul class=" nav navbar-nav pull-right">
	          <li class="dropdown">
	                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-user" style="font-size: 30px"></i>&nbsp;&nbsp;Usuario</a>
	                <ul class="dropdown-menu" role="menu">
	                  <li><a data-toggle="modal" href="#loginModal"><i class="fa fa-key"></i> Login</a></li>
	                  <li><a data-toggle="modal"href="#RegistroModal"><i class="fa fa-cogs"></i>  Regitrate</a></li>                  
	                </ul>
	          </li>
	         <!--  <li> <a data-toggle="modal" href="#myModal"><i class="glyphicon glyphicon-user" style="font-size: 30px"></i>&nbsp;&nbsp; Usuarioï»¿</a> </li> -->
	        </ul>
	        <%}else{ %>
	        <ul class=" nav navbar-nav pull-right">
	          <li class="dropdown">
	                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-user" style="font-size: 30px"></i>&nbsp;&nbsp;<%=u1.getUsuario()%></a>
	                <ul class="dropdown-menu" role="menu">
	                  <li><a href="#"><i class="fa fa-sign-out"></i> Profile</a></li>	
	                  <li><a href="TratarUser?opcion=logOut" ><i class="fa fa-sign-out"></i> Logout</a></li>
	                </ul>
	          </li>
	         <!--  <li> <a data-toggle="modal" href="#myModal"><i class="glyphicon glyphicon-user" style="font-size: 30px"></i>&nbsp;&nbsp; Usuarioï»¿</a> </li> -->
	        </ul>        
	 		<%} %>       
	        </div><!--/.navbar-collapse -->
	      </div>
</nav>

<!-- Modal  de usuario-->
<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title"><i class="glyphicon glyphicon-user">  </i>Login de usuario</h4>
            </div>

            <div class="modal-body">
                <!-- Pantalla de usuario -->
                  <div style="padding: 20px;" id="form-olvidado" class="form-horizontal">
                    <form accept-charset="UTF-8" role="form" id="AccesoUser" action="TratarUser?opcion=AccesoUser" method="post">
                        <fieldset>
                            <div class="form-group">            
                                <div class="col-sm-12 inputGroupContainer">
                                    <div class="input-group">
                                        <span class="input-group-addon"> @</span>
                                       <input class="form-control" placeholder="Email" name="email" type="email">
                                    </div>
                                </div>
                            </div>
                          
                            <div class="form-group">             
                                <div class="col-sm-12 inputGroupContainer">
                                    <div class="input-group">
                                       <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                       <input class="form-control" placeholder="Password" name="password" type="password" value="" >
                                    </div>
                                </div>
                            </div>

                          <div class="form-group">
                            <button type="submit" class="btn btn-primary btn-block">
                              Accede
                            </button>
                            <p class="help-block">
                              <a class="pull-right text-muted" href="#" id="olvidado"><small>Perdistes tu contraseña?</small></a>
                            </p>
                          </div>
                        </fieldset>
                      </form>
                 </div>
                 <!-- Pantalla con contraseÃ±a perdida-->
                  <div style="display: none;"  id="form-olvidado" style="padding: 20px;">
                    <h4 class="">
                      Perdistes tu contraseña?
                    </h4>
                    
                      <fieldset>
                      <div class="form-group">
                        <span class="help-block">
                          Introduce la dirección de correo electrónico que utilizo para iniciar sesión en su cuenta
                          <br>
                          Te enviaremos un correo electrónico con instrucciones para escoger una nueva contraseña.
                        </span>
                       </div> 
					<form accept-charset="UTF-8" role="form" id="LostPass" action="TratarUser?opcion=LostPass" method="post">	
                        <div class="form-group">            
                                <div class="col-sm-12 inputGroupContainer">
                                    <div class="input-group">
                                        <span class="input-group-addon"> @</span>
                                       <input class="form-control" placeholder="Email" name="email2" type="email2">                                     
                                    </div>
                                </div>
                        </div>

                         <div class="form-group">
                              <br><br>
                              <button type="submit" class="btn btn-primary btn-block" id="btn-olvidado">
                                Continue
                              </button>
                              <p class="help-block">
                                <a class="text-muted" href="#" id="acceso"><small>Acceso a la Cuenta</small></a>
                              </p>
                           
                        </div>
                      </fieldset>
                    </form>
                  </div>
                  <!-- fin pantalla contraseÃ±a perdida -->
            </div>
        </div>
    </div>
</div>
 <!-- Modal  de registro-->
<div class="modal fade" id="RegistroModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" data-keyboard="false" data-backdrop="static">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true" id="close">&times;</span><span class="sr-only" >Close</span></button>
        <h4 class="modal-title" id="exampleModalLabel"><i class="fa fa-cog"></i> Registrate&nbsp; <span style="font-size: 10px" class="label label-success ">GRATIS!!</span></h4>
      </div>
      <div class="modal-body">
         <form id="NuevoUser" accept-charset="UTF-8" class="form-horizontal" action="TratarUser?opcion=NuevoUser"  method="post">

	            <div class="form-group has-feedback">
	               <div class="col-sm-12 ">	
		              <label for="recipient-name" class="control-label">Usuario:</label>
		              <input type="text" class="form-control" id="usuario"  name="usuario">
		              <span class="glyphicon form-control-feedback"></span>
	              </div>
	            </div>
	            
	            <div class="form-group has-feedback">
	            	<div class="col-sm-12">
		              <label for="message-text" class="control-label">E-mail:</label>
		              <input type="text" class="form-control"  name="email" id="email">
		              <span class="glyphicon form-control-feedback"></span>
		              <input type="hidden" class="form-control" name="tipo"  value="busqueda">
		            </div>
	            </div>
	            
	            <div class="form-group has-feedback">
	            	<div class="col-sm-12">       
		                <label class="control-label">Comprobación de Usuario</label><br>
		                <img id="captcha_image" src="EdwCaptcha" alt="captcha image" />
		               <img src="images/refresh_1.png" onclick="reloadCaptcha()" alt="reload"width="20" height="20"/>  
						
		                <input type="text" class="form-control" name="answer" id="answer" />
		                <span class="glyphicon form-control-feedback" id="captcha" name="captcha"></span>
		             </div>            
	            </div>
	            <div class="form-group has-feedback">
	            	<div class="col-sm-12">         
		                <label class="control-label">Password</label>
		                <input type="password" class="form-control" name="pass" id="pass" />
		                <span class="glyphicon form-control-feedback"></span>
		             </div>            
	            </div>
	
	            <div class="form-group has-feedback">
	            	<div class="col-sm-12">
	                  <label class=" control-label">Retype password</label>      
	                  <input type="password" class="form-control" name="pass2" id="pass2" />
	                  <span class="glyphicon form-control-feedback"></span>  
	                </div>            
	            </div>
	             <input type="hidden" class="form-control" name="comprobar" id="comprobar" />
	        
	        
	   </div>
	         <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="reloadCaptcha()" id="cerrar">Cerrar</button>
        <button type="submit" class="btn btn-primary" onclick="reloadCaptcha()" name="send">Registrar</button>
      </div>
      </form> 
      </div>
   </div>
  </div>