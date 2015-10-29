<%@page import="java.util.Iterator"%><%@page import="java.util.Map"%><%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map.Entry"%><%@page import="modelo.Restaurante"%><%@page import="java.net.URLEncoder"%>


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
<%String tit=(String)request.getAttribute("miparque");
  tit= tit.toUpperCase();%>
<div class="container">
  <div class="row">
	<h1 id="tituloprin"><%=tit %></h1><br><br>
  </div>
</div>
<div class="container">
  <div class="row">
    <div class="col-sm-4 col-md-3">
      <div class="panel panel-info">
         <div class="panel-heading">
             <p>Detalles</p>
        </div>            
            <div id="miPanel"class="list-group">
		      <a href="#" id="todos" onclick="javascript:recargar('todos');" class="list-group-item active"><p>Todos</p></a>
		      <a href="#" id="Servicio_de_Mesa" onclick="javascript:recargar('Servicio_de_Mesa');" class="list-group-item"><p>Servicio de Mesa</p></a>
		      <a href="#" id="Servicio_Rapido" onclick="javascript:recargar('Servicio_Rapido');" class="list-group-item"><p>Servicio Rapido</p></a>
		      <a href="#" id="Kiosko" onclick="javascript:recargar('Kiosko');" class="list-group-item"><p>Kiosko</p></a>
		      <a href="#" id="Personajes" onclick="javascript:recargar('Personajes');" class="list-group-item"><p>Personajes</p></a>
		  	 </div> 
        </div>
      </div>
      <div name="respuesta" id="respuesta" class="col-sm-8 col-md-9" >
      <%ArrayList<Restaurante> milist =(ArrayList<Restaurante>)request.getAttribute("parquenom");
      Iterator<Restaurante> itr = milist.iterator();
      while(itr.hasNext()){
    		Restaurante res = itr.next();
    		String valor = URLEncoder.encode(res.getNombre(), "UTF-8");%>     	
      	   <div id="micol" class="col-sm-6 col-md-3 col-xs-6" >	
      	   	  <div id= "mirow"class="row">  
		         <%-- <a id="midir" href="Resta?restaurante=<%=res.getNombre() %>"><img src="http://lorempixel.com/output/nature-q-c-200-200-10.jpg"  class="img-responsive img-rounded"
		         alt="Generic placeholder thumbnail"></a> --%>
		         <a id="midir" href="Resta?restaurante=<%=valor %>"><img src="http://i1095.photobucket.com/albums/i476/jesfeoro/restaurantes/<%=res.getImagenP() %>"  class="img-responsive img-rounded"
		         alt="Generic placeholder thumbnail"></a>
			      <div  id="micaption" class="caption">
			        <a href="Resta?restaurante=<%=valor %>" style="text-decoration:none"> <p class="descripcion"><b><%=res.getNombre() %></b></p></a>
			          <p class="descripcion"><%=res.getTipoRes() %></p>
			      </div>
		      </div>
		   </div>
		   <%}%>
		</div>
    </div>
  </div>
  <hr>

<%@ include file="/pie.jsp" %>
<script type="text/javascript">
var par ="<%=request.getAttribute("miparque") %>";
console.log("El tipo de parque es-->"+par);
$( "#miPanel a" ).click(function( event ) {
	  event.preventDefault();
	});
// añadimos esta función para codificar la parte del url que queremos codificar
function encode(toEncode) {
    return encodeURIComponent(toEncode)
        .replace(/!/g, '%21')
        .replace(/'/g, '%27')
        .replace(/\(/g, '%28')
        .replace(/\)/g, '%29')
        .replace(/\*/g, '%2A');
}
function recargar(tipos){
	$("#miPanel a").each(function (index){ 				
				if($(this).attr('id')==tipos){					
					$(this).removeAttr("class").attr("class", "list-group-item active");
				}else{
					$(this).removeAttr("class").attr("class", "list-group-item");
				} });		
	 $.ajax({
             url : "BusquedaR",
             type : "GET",
             data : 'opcion='+ tipos+'&codigo='+par,
             dataType : "json",
             success: function(data){
               /*  console.log( "Data: " + JSON.stringify(data) );*/
                  $.map( data, function( item ){
                	/*  console.log("Este item -> "+item.Nombre+" y tipo ->"+ item.TipoRes);*/
                  });
                 $( '#respuesta div' ).remove();
                 $( '#respuesta h2' ).remove();
               /*  $('#respuesta').text(JSON.stringify(data));*/
                 if(data=="" ){
						
						$( '#respuesta' ).append("<h2>No Existen Restaurantes de este tipo</h2>");
					}else{
                 $.each (data, function (dato) {
                	  /* console.log ("Elemento "+dato);
                	    console.log (data[dato]);
                	    console.log ("parte del objeto (nombre usuario)-->"+data[dato].Nombre); 
                	    console.log ("data[dato].password =="+data[dato].TipoRes);
						console.log ("Con encode??-->"+encode(data[dato].Nombre).replace(/%20/g,'+'));*/
						// para codificar la variable usaremos la instruccion de abajo.
						// con encode codificamos incluso los apostrofes '
						// y con replace reemplazamos los %20 que no nos interesan por +
						
						var codif =  encode(data[dato].Nombre).replace(/%20/g,'+'); 
                	   $( '#respuesta' ).append("<div id='micol"+dato+"' class='col-sm-6 col-md-3  col-xs-6' style='margin-bottom: 20px;'>");	
                	   $( "#micol"+dato ).append("<div id= 'mirow"+dato+"' class='row'>" ); 
                	   $( "#mirow"+dato ).append("<a id='midir"+dato+"' href='Resta?restaurante="+codif+"'>"+
                			   "<img src='http://i1095.photobucket.com/albums/i476/jesfeoro/restaurantes/"+data[dato].ImagenP+"'  class='img-responsive img-rounded'</a>");
                	   $( "#midir"+dato ).after("<div class='caption'><a href='Resta?restaurante="+codif+"' style='text-decoration:none'>"+
                			   " <p class='descripcion'><b>"
                			   +data[dato].Nombre+"</b></p></a><p class='descripcion'>"+data[dato].TipoRes+"</p>");
						
                	});}
              },
              error:function (jqXHR, textStatus, errorThrown){
                 console.log(JSON.stringify(jqXHR) + ' ' + textStatus +'  '+errorThrown );
              }
     });

 
}

</script>



</body></html>