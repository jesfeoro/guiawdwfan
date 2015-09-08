<%@page import="modelo.Usuario"%><%@page import="modelo.Busqueda"%><%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%><%@page import="java.util.Map.Entry"%><%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
   

    <title>Guia Walt Disney World Fan/Resultados de la Busqueda</title>

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
    <link rel="stylesheet" href="css/miestilo.css">
    <link href='http://fonts.googleapis.com/css?family=Fjalla+One' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,600' rel='stylesheet' type='text/css'>
    
    <link href='http://fonts.googleapis.com/css?family=Raleway' rel='stylesheet' type='text/css'>
  
   <!-- <script src="./Jumbotron Template for Bootstrap_files/jquery.min.js"></script>-->
  <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
  <script type="text/javascript" src="http://ajax.microsoft.com/ajax/jquery.validate/1.7/jquery.validate.min.js"></script>
  
   <link href="http://ajax.googleapis.com//ajax/libs/jqueryui/1.7.2/themes/start/jquery-ui.css" rel="stylesheet">

      <script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
  </head>
  <body>
    	
<%@ include file="/cabecera.jsp" %>

<%!Integer num; %>
<% num = (Integer)request.getAttribute("resultados");  %>
<div class="container">
	<div class="row">
		<div class="col-md-12">
			<h1><%=num %> Paginas encontradas</h1>			
			<hr>
		</div>
	</div>
</div>
<div class="container">
	<div class="row">
		<div class="col-md-12">		
		<% if(num >0){
			 Busqueda bu= (Busqueda)request.getAttribute("busqueda");
			 Map<Integer,Busqueda>Mipag =bu.getPagina();
			 Iterator it = Mipag.keySet().iterator();
				while(it.hasNext()){/* Acordarse que tiene que ir con urlencode*/
						  Integer key = (Integer) it.next();
						  Busqueda m= Mipag.get(key);
						  String valor = URLEncoder.encode(m.getNombre(), "UTF-8");%>
				<a href="<%=m.getDireccion()+valor%>"><h3><%=m.getTipos().toUpperCase() %> - <%=m.getNombre() %></h3></a>		
				
				<% }
	
}%>
		</div>
	</div>
</div>


<hr>

<%@ include file="/pie.jsp" %>
</body></html>