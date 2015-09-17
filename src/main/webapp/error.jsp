<%@page import="modelo.Usuario"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
   %>

<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
   

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
      <style type="text/css">
      @import url(http://fonts.googleapis.com/css?family=Droid+Sans:400,700);
		*{
		  cursor: url('http://puu.sh/j1a8v/067742b89d.ico'), default;
		}
		#error{
		      margin-top: 15px;
   			  color: #6F6F6F;
              text-shadow: 0px 2px 0px rgb(247, 0, 0);
              font-family: 'Droid Sans', sans-serif;
              font-size: 65px;
		}
		#err-icon{
		  font-size: 80pt;
		  color: #bdc3c7;
		  margin-top: 80px;
		  text-shadow: 0px 3px 0px rgba(150, 150, 150, 1);
		  -webkit-transition: 0.5s;
		}
		#err-icon:hover{
		  -webkit-transform: scale(1.1);
		  color: #e74c3c;
		  text-shadow: 0px 3px 0px rgba(192, 57, 43, 1);
		}
		#res{
		    color: rgb(129, 129, 130);
		    font-weight: bold;
		    background: aliceblue;
		    border: black 1px solid;
		    border-radius: 5px;
		    font-family: 'Droid Sans', sans-serif;
		    
		}
		#parrafo{
			text-align: center;
		}
      </style>
  </head>
  <body>
    	
<%@ include file="/cabecera.jsp" %>

<div class="container">
  <div class="row">
    <div class="col-md-12" id="parrafo">
      	<i id="err-icon" class="fa fa-exclamation-circle"></i>
		<h1 id ="error">404 Ooops!!</h1>
		<h5>Parece que algo estamos haciendo mal...</h5>
		<p id="res"><%=request.getAttribute("motivo") %></p>
   </div>
 </div>
</div>

<hr>

<%@ include file="/pie.jsp" %>

</body></html>