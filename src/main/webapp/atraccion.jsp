<%@page import="modelo.Atraccion"%><%@page import="modelo.ImagenA"%><%@page import="java.util.Iterator"%><%@page import="java.util.Map"%>
<%@page import="java.util.Map.Entry"%><%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%!Atraccion at; %>
<% at= (Atraccion)request.getAttribute("atrac"); 
String valor = URLEncoder.encode(at.getNombre(), "UTF-8");
String url = request.getRequestURL().toString();
String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) +
request.getContextPath() + "/"+"Atrac?atraccion="+valor; %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" prefix="og: http://ogp.me/ns#" itemscope itemtype="http://schema.org/Blog">
<head>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

<title>Guia Walt Disney World Fan/<%=at.getNombre() %></title>
<meta name="description" content="<%=at.getBDescripcion()%>">
    <!-- Bootstrap core CSS -->
    
    
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href ="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="js/bootstrap-media-lightbox.css" rel="stylesheet">
    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <!--<script src="./Jumbotron Template for Bootstrap_files/ie-emulation-modes-warning.js"></script>-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!--  CSS Iconos-->
    <link rel="stylesheet" href="font-awesome-4.2.0/css/font-awesome.css">
  <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script> 
   

    <link href='http://fonts.googleapis.com/css?family=Fjalla+One' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,600' rel='stylesheet' type='text/css'>
     <link href='http://fonts.googleapis.com/css?family=Raleway' rel='stylesheet' type='text/css'>
    <link href="http://fonts.googleapis.com/css?family=Kaushan+Script" rel="stylesheet" type="text/css">
 
        <script src="https://apis.google.com/js/platform.js" async defer>
      {lang: 'es'}
    </script>
      <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>       
  <script type="text/javascript" src="http://ajax.microsoft.com/ajax/jquery.validate/1.7/jquery.validate.min.js"></script>
  
   <link href="http://ajax.googleapis.com//ajax/libs/jqueryui/1.7.2/themes/start/jquery-ui.css" rel="stylesheet">

      <script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
      <link rel="stylesheet" href="css/miestilo.css">
      <!-- Añade las tres etiquetas siguientes en la sección "head". -->
<meta itemprop="name" content="Guia Walt Disney World Fan - <%=at.getNombre() %>">
<meta itemprop="description" content="<%=at.getBDescripcion()%>">
<meta itemprop="image" content="http://i1095.photobucket.com/albums/i476/jesfeoro/atracciones/<%=at.getImagenG() %>">

<!-- Open Graph para Facebook--> 
<meta property="og:title" content="Guia Walt Disney World Fan - <%=at.getNombre() %>" /> 
<meta property="og:type" content="article" /> 
<meta property="og:url" content="<%=baseURL %> " />
<meta property="og:image" content="http://i1095.photobucket.com/albums/i476/jesfeoro/atracciones/<%=at.getImagenG() %>" />
<meta property="og:description" content="<%=at.getBDescripcion()%>" /> 
<meta property="og:site_name" content="Guia Walt Disney Fan" />
<meta property="og:locale" content="es_ES">

 
<!-- Twitter Card --> 
<meta name="twitter:card" content="summary_large_image">  
<meta name="twitter:title" content="Guia Walt Disney World Fan - <%=at.getNombre() %>"> 
<meta name="twitter:description" content="<%=at.getBDescripcion()%>">  
<meta name="twitter:image:src" content="http://i1095.photobucket.com/albums/i476/jesfeoro/atracciones/<%=at.getImagenG() %>">
      
</head>
<style>
	.polaroid:after{
	content: '';
	position:absolute;
	top:-15px; left: 20%;
	width: 130px;
	height: 40px;
	background: rgba(0, 0, 0, 0.1);
	background: -webkit-gradient(linear, 555% 20%, 0% 92%, from(rgba(0, 0, 0, 0.1)), to(rgba(0, 0, 0, 0.0)), color-stop(.1,rgba(0, 0, 0, 0.2)));
	background: -moz-linear-gradient(555% 0 180deg, rgba(0,0,0,0.1), rgba(0,0,0,0.2) 10%, rgba(0,0,0,0.0));
	border-left: 1px dashed rgba(0, 0, 0, 0.1);
	border-right: 1px dashed rgba(0, 0, 0, 0.1);
	-webkit-box-shadow: 0px 0px 12px rgba(0, 0, 0, 0.2);
	-moz-box-shadow: 0px 0px 12px rgba(0,0,0,0.2);
	box-shadow: 0px 0px 12px rgba(0,0,0,0.2);
}
</style>
<body>
<%@ include file="/cabecera.jsp" %>
<div class="container">
  <div class="row"  >
    <div class="col-md-12">
      <img src="http://i1095.photobucket.com/albums/i476/jesfeoro/atracciones/<%=at.getImagenG() %>" class="img-responsive img-rounded" alt="" style="box-shadow:  5px 5px 12px 0px rgba(0, 0, 0, 1.46); border: 1px solid whitesmoke; width: inherit;">
      <h2 id="titulocomun"><%=at.getNombre() %></h2>
      <p><em><%=at.getBDescripcion() %></em></p>
	  	
     <div id="social">
     <a class="facebookBtn smGlobalBtn" href="https://www.facebook.com/sharer/sharer.php?u=<%=baseURL %>" ></a>
      <a class="twitterBtn smGlobalBtn" href="http://twitter.com/home?status=<%=baseURL %>" ></a>
      <a class="googleplusBtn smGlobalBtn" href="https://plus.google.com/share?url=<%=baseURL %>"></a>

    </div>
    </div>
  </div>
</div>

<div class="container">
  <div class="row">
    <div class="col-md-12">
      <div class="panel panel-default">
   <!--       <div class="panel-heading">
             <p style="font-size: 18px;">  <b >DETALLES</b> </p>
        </div>-->
              <div class="panel-body">
	  <%    String []caracteristica  = at.getTipos();
			Map<Integer,Atraccion>Micaracter =at.getCaracter();
 			for (int j = 0; j < caracteristica.length; j++) {%>
 	            <div class="col-md-6">
 	            <div class="panel panel-info">
 	             <div class="panel-heading">
 	           		<p style="font: 400 25px/1 'Kaushan Script', cursive;"><%=caracteristica[j]%></p>
 	           	</div>
 	           	<div class="panel-body">	
 	              <table class="table  table-hover">
 	                <tbody>

			<% 	Iterator it = Micaracter.keySet().iterator();
				while(it.hasNext()){
						  Integer key = (Integer) it.next();
						  Atraccion m= Micaracter.get(key);
						  if(caracteristica[j].equals(m.getCaracteristica())) { %>
					  <tr>
	                    <td><p style="font-size:13px"><b><%=m.getTipoC() %> </b></p></td>
	                        <%if(m.getValorC().equals("Si")){ %>
	                    <td><i class="fa fa-check-circle-o fa-2x" style="color: #270;"></i></td>
	                    
						   <%}else if(m.getValorC().equals("No")){ %>
		                    <td><i class="fa fa-times-circle-o fa-2x"style="color: #D8000C;"></i></td>  
							<%}else{  %>
							<td><p style="font-size:13px"><%=m.getValorC() %></p></td>  
						 <% } %>
					 </tr>
				<%  }}%>
					</tbody>
                </table>
               </div>
              </div>  
             </div>		 
		<% 	}
		
			Atraccion m= Micaracter.get(19);
			if (m.getValorC().equals("Si")){ %>
			<div class="col-md-12">
				<p style="font-size:13px;"><i class="fa fa-exclamation-triangle fa-3x" style="color: darkorange; float:left; margin-right: 10px;"></i>  Por razones de 
				seguridad, debe estar en buen estado de salud y no debe tener presión arterial alta, problemas
				cardiacos, de columna o de cuello, enfermedad motriz ni cualquier otra afección que pueda
				agravarse por esta atracción</p>
			</div> 
		<%} %>
  			   
        </div>
      </div>
    </div>
  </div>
</div>
<div class="container">
  <div class="row">
    <div class="col-md-12">
		<%=at.getDescripcion() %>
    </div>
  </div>
</div>
<br><br>
<div class="container">
  <div class="row">
    <div class="col-md-12">
       <!--  <p style="text-align: center;   font-size: xx-large;"> Imagenes de la atracción</p> -->
        <%Map<Integer,ImagenA>Miatrac =at.getImagen(); 
         	Iterator it2 = Miatrac.keySet().iterator();
				while(it2.hasNext()){
						  Integer key = (Integer) it2.next();
						  ImagenA img= Miatrac.get(key); %>
        <div class="col-md-4" style="margin-bottom: 40px; text-align: -webkit-center;">
          <div class="polaroid">
          		<!-- <span></span> -->
                <p><%=img.getDescripcion() %></p>
                <a class="lightbox" href="http://i1095.photobucket.com/albums/i476/jesfeoro/atracciones/<%=img.getImagenG() %>"><!--Este el grande-->
                  <img src="http://i1095.photobucket.com/albums/i476/jesfeoro/atracciones/<%=img.getImagenP() %>"><!-- Este el pequeño -->
                </a>
                
          </div>
        </div> 
        <%} %>
  <!--       <div class="col-md-4" style="margin-bottom: 18px; text-align: -webkit-center;">
          <div class="polaroid">
                <p>Sarah, Dec '02</p>     
                <a class="lightbox" href="http://i1095.photobucket.com/albums/i476/jesfeoro/atracciones/StarTours_2.jpg" title="Esto es la imagen 2" >
                  <img src="http://i4.minus.com/jbkYbOncSecoFt.jpg">
                </a>
          </div>
        </div>
        <div class="col-md-4" style="margin-bottom: 18px; text-align: -webkit-center;">
          <div class="polaroid">
                <p>Sarah, Dec '02</p>
                <img src="http://lorempixum.com/200/200/people/1">
          </div>
        </div>
        -->       
	  </div>
	</div>
</div>
<br>
<div class="container">
	<div class="row">
		<div class="col-md-12">
			<%=at.getSonido() %>
		</div>
	</div>
</div>
<br>
<br>

 <script src="js/micomment.js"></script>
<div class="container">
  <div class="row">
    <div class="col-md-12" style="background-color: white;">
			<!-- begin htmlcommentbox.com -->
			 <div id="HCB_comment_box"><a href="http://www.htmlcommentbox.com">HTML Comment Box</a> is loading comments...</div>
			 <link rel="stylesheet" type="text/css" href="//bawksbox.com/static/skins/bootstrap/twitter-bootstrap.css?v=0" />
			 <script type="text/javascript" id="hcb"> /*<!--*/ if(!window.hcb_user){hcb_user={};} (function(){var s=document.createElement("script"), l=hcb_user.PAGE || (""+window.location).replace(/'/g,"%27"), h="//bawksbox.com";s.setAttribute("type","text/javascript");s.setAttribute("src", h+"/jread?page="+encodeURIComponent(l).replace("+","%2B")+"&mod=%241%24wq1rdBcg%245Y0h7SyFh18UJtssPdK2M1"+"&opts=16734&num=10");if (typeof s!="undefined") document.getElementsByTagName("head")[0].appendChild(s);})(); /*-->*/ </script>
			<!-- end htmlcommentbox.com -->
	</div>
  </div>
</div>	
<hr>

<%@ include file="/pie.jsp" %>
</body>
</html>