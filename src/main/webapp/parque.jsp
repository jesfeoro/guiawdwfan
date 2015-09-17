<%@page import="modelo.Parque"%><%@page import="modelo.Atraccion"%><%@page import="java.net.URLEncoder"%>
<%@page import="java.util.Iterator"%><%@page import="java.util.Map"%><%@page import="java.util.Map.Entry"%>

<%!Parque p; %>
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
<script type="text/javascript" src="js/jquery.contenthover.js"></script>
    <link rel="stylesheet" href="css/miestilo.css">
<style type="text/css">
	.contenthover { padding:20px 20px 10px 20px; }
	.contenthover, .contenthover h3, contenthover a { color:#fff; }
	.contenthover h3, .contenthover p { margin:0 0 10px 0; line-height:1.4em; padding:0; }
	.contenthover a.mybutton { display:block; float:left; padding:5px 10px; background:#3c9632; color:#fff; -moz-border-radius: 4px; -webkit-border-radius: 4px; border-radius: 4px; }
	.contenthover a.mybutton:hover { background:#34742d }
</style>
  </head>
  <body>    	
<%@ include file="/cabecera.jsp" %>
<% p= (Parque)request.getAttribute("parque"); %>
<div class="container">
  <div class="row"  >
    <div class="col-md-12">
      <img src="http://i1095.photobucket.com/albums/i476/jesfeoro/atracciones/<%=p.getImagen() %>" class="img-responsive img-rounded" alt="" style="box-shadow:  5px 5px 12px 0px rgba(0, 0, 0, 1.46); border: 1px solid whitesmoke; width: inherit;"> 
      <br>
      <h1 id="titulocomun"><%=p.getNombre() %></h1><br>
      <p><%=p.getDescripcion() %></p>
      <p>El parque se compone de distintas zonas:</p>      
    </div>
  </div>
</div>

<%  Map<Integer,String>zonas=p.getZonas();

 Iterator it2 = zonas.keySet().iterator();
	 while(it2.hasNext()){
		   Integer key = (Integer) it2.next();
		   Map<Integer,Atraccion>atr= p.getAtraccion();%>
		   <div class="container hidden-lg">
			  <div class="row"  >
				<div class="col-md-12">
		   			<h1 id="titulozona"><%=zonas.get(key) %></h1><br>		   
		  <%for (Entry<Integer, Atraccion> atrac : ((Map<Integer, Atraccion>) atr).entrySet()){
				Integer clave = atrac.getKey();
				Atraccion valor = atrac.getValue();
				
				if(key==valor.getCodigoZona()) { %>
				  <div class="col-sm-4 col-xs-6 text-center ng-scope">
			          <h4><a href="Atrac?atraccion=<%=valor.getNombre() %>">
			            <img   style="width:120px;" class="img-circle img-responsive img-thumbnail" 
			            		src="http://i1095.photobucket.com/albums/i476/jesfeoro/atracciones/<%=valor.getImagenP() %>">
			          </a></h4>
			          <%String texto = URLEncoder.encode(valor.getNombre(), "UTF-8"); %>
			          <h4 class="text-center"><a href="Atrac?atraccion=<%=texto %>" ><%=valor.getNombre() %></a></h4>
			      </div>
			 <% }
		   } %>
		     	</div>
			  </div>
		</div>
	<%} %> 
	<%Iterator it3 = zonas.keySet().iterator();
	int elemento =0;
	while(it3.hasNext()){
			   Integer key = (Integer) it3.next();
			   Map<Integer,Atraccion>atr= p.getAtraccion();%>	
			 <div class="container visible-lg">
				 <div class="row"  >
				 	<div class="col-md-12">			  		
 					 <h1 id="titulozona"><%=zonas.get(key) %></h1><br> 
		  <% for (Entry<Integer, Atraccion> atrac : ((Map<Integer, Atraccion>) atr).entrySet()){
				Integer clave = atrac.getKey();
				Atraccion valor = atrac.getValue();
				
				if(key==valor.getCodigoZona()) { %>
			   		<div class="col-sm-4 col-xs-6" style="margin-bottom:16px;">
			        <img id="d<%=elemento %>"  src="http://i1095.photobucket.com/albums/i476/jesfeoro/atracciones/<%=valor.getImagenP() %>" width="300" height="300"  />
						<div class="contenthover" >
						    <h3><%=valor.getNombre() %></h3>
						    <%String texto = URLEncoder.encode(valor.getNombre(), "UTF-8"); %>	
						    <p><a href="Atrac?atraccion=<%=texto %>" class="mybutton">Acceder</a></p>
						</div>
	  				</div>
				 <%elemento++; }
			   
			} %>
				</div>
			</div>
		</div>
	<%} %>	
<hr>
<%@ include file="/pie.jsp" %>

<script type="text/javascript">
<%for(int j=0;j<elemento;j++){ %>
	$("#d<%=j%>").contenthover({
	    overlay_background:'#000',
	    overlay_opacity:0.8
	});
<%}%>

</script>
</body></html>