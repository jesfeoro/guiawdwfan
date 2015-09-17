<%@page import="modelo.Usuario"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
   %>

<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta http-equiv="Expires" content="0" />
	<meta http-equiv="Pragma" content="no-cache" />

    <title>Guia Walt Disney World Fan</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.css" rel="stylesheet">
    

    <!-- Custom styles for this template -->
    <!--<link href="./Jumbotron Template for Bootstrap_files/jumbotron.css" rel="stylesheet">-->

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
   
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!--  CSS Iconos-->
    <link rel="stylesheet" href="font-awesome-4.2.0/css/font-awesome.css">

    <link href='http://fonts.googleapis.com/css?family=Fjalla+One' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,600' rel='stylesheet' type='text/css'>
    
    <link href='http://fonts.googleapis.com/css?family=Raleway' rel='stylesheet' type='text/css'>
  
   <!-- <script src="./Jumbotron Template for Bootstrap_files/jquery.min.js"></script>-->
  <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
  <script type="text/javascript" src="http://ajax.microsoft.com/ajax/jquery.validate/1.7/jquery.validate.min.js"></script>
  
   <link href="http://ajax.googleapis.com//ajax/libs/jqueryui/1.7.2/themes/start/jquery-ui.css" rel="stylesheet">

      <script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
     <link rel="stylesheet" href="css/miestilo.css">
  </head>
  <body>
    	
<%@ include file="/cabecera.jsp" %>

<div class="container">
  <div class="row">
    <div class="col-md-12">
      <div class="panel panel-default">
      <div class="">
           <div class="panel-heading hidden-lg">
             <p> <i class="fa fa-info-circle fa-2x" style="color: #2ECC6F;" ></i> <b> Recuperación de contraseña</b></p>
        </div>
       </div>
        <div class="panel-body">
        <div class="col-md-12">
        	<p>Bienvenido <b><%=request.getParameter("email") %></b>. Introduce la clave que te hemos proporcionado, y cambia tú password de entrada y Listo!!</p>
        </div>  
          <div class="col-md-6">
          
			<form id="passmiss" accept-charset="UTF-8" class="form-horizontal" action="TratarUser?opcion=RestauraPass"  method="post">

	            <div class="form-group has-feedback">
	               <div class="col-sm-12 ">	
		              <label for="recipient-name" class="control-label">clave:</label>
		              <input type="text" class="form-control" id="clave"  name="clave">
		              <span style="top:25px" class="glyphicon form-control-feedback"></span>
	              </div>
	            </div>
	            
	            
	            <div class="form-group has-feedback">
	            	<div class="col-sm-12">       
		                <label class="control-label">Comprobación de Usuario</label><br>
		                <img id="captcha_image" src="EdwCaptcha" alt="captcha image" />
		               <img src="images/refresh_1.png" onclick="reloadCaptcha()" alt="reload"width="20" height="20"/>  
						
		                <input type="text" class="form-control" name="answer" id="answer" />
		                <span style="top:75px" class="glyphicon form-control-feedback" id="captcha" name="captcha"></span>
		             </div>            
	            </div>
	            <div class="form-group has-feedback">
	            	<div class="col-sm-12">         
		                <label class="control-label">Password</label>
		                <input type="password" class="form-control" name="passR" id="passR" />
		                <span style="top:25px" class="glyphicon form-control-feedback"></span>
		             </div>            
	            </div>
	
	            <div class="form-group has-feedback">
	            	<div class="col-sm-12">
	                  <label class=" control-label">Retype password</label>      
	                  <input type="password" class="form-control" name="passR2" id="passR2" />
	                  <span style="top:25px"class="glyphicon form-control-feedback"></span>  
	                </div>            
	            </div>
	            <input type="hidden" id="email" name="email" value="<%=request.getParameter("email") %>"/>
	            <input type="hidden" id="token" name="token" value="<%=request.getParameter("token") %>"/>
	         <div class="modal-footer">
       			 <button type="button" class="btn btn-default" id="cerrar">Cancelar</button>
       			 <button type="submit" class="btn btn-primary"  name="send">Registrar</button>
     		 </div>
        </form>
      </div>
      <div class=" ">
	      <div class="col-md-6 visible-lg">
	      	 <p class="simbolo"  style="position: absolute; left: 25%; top: 50px;"> <i class="fa fa-info-circle fa-5x" style="font-size: 296px; color: #2ECC6F;" ></i><br>
	      	<b> Recuperación de contraseña</b></p>
	      </div>
     </div>
      </div>
        </div>
     </div>
   </div>
 </div>
</div>

<hr>

<%@ include file="/pie.jsp" %>

<script type="text/javascript">

if(history.forward(1))
	location.replace(history.forward(1));

$("#passmiss").validate({
    rules:{
            clave:{
                required:true,
                remote:"TratarUser?opcion=passperdida&email=<%=request.getParameter("email")%>"
            },
            answer:{
                required:true,
                remote:"EnvioCaptcha"
            },
            passR:{
            	required: true,
            	minlength: 3,
            	maxlength: 8
            },
            passR2:{
            	required: true,
                equalTo:"#passR",
                minlength: 3,
                maxlength: 8
            }
    },
    messages:{
        clave:{
            required:"Campo obligatorio",
            minlength:"minimo 5 caracteres",
            remote:"La clave no corresponde con ninguna petición"
        },
        passR:{
            required:"Campo obligatorio",
            minlength:"minimo 3 caracteres",
            maxlength:"maximo 8 caracteres"
        },
        passR2:{
        	required:"Campo obligatorio",
            equalTo:"La contraseña no es igual",
            minlength:"minimo 3 caracteres",
            maxlength:"maximo 8 caracteres"
        },
        answer:{
            required:"Campo es obligatorio",
            remote:"Captcha incorrecto. Click en refrescar para uno nuevo"
        }
    },
    highlight: function(element) {
       // var id_attr = "#" + $( element ).attr("id") + "1";
        $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
        $(element).parent().find('.form-control-feedback').removeClass('glyphicon-ok').addClass('glyphicon-remove');        
    },
    unhighlight: function(element) {
       // var id_attr = "#" + $( element ).attr("id") + "1";
        $(element).closest('.form-group').removeClass('has-error').addClass('has-success');
        $(element).parent().find('.form-control-feedback').removeClass('glyphicon-remove').addClass('glyphicon-ok');        
    },
    errorElement: 'span',
        errorClass: 'help-block',
        errorPlacement: function(error, element) {
            if(element.length) {
                error.insertAfter(element);
            } else {
            error.insertAfter(element);
            }
        } ,
    success: function(element) {
			$(element).closest('.form-group').removeClass('has-error');
		}    	
});

</script>
</body></html>