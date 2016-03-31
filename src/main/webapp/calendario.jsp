<%@page import="com.dhtmlx.planner.controls.*"%>
<%@ page import="com.dhtmlx.planner.extensions.*"%>
<%@ page import="java.util.*"%>
<%@page import="modelo.Usuario"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html>
<head>
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1"> 
    <meta name="description" content="">
    <meta name="author" content="">
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Expires", "0");
	response.setDateHeader("Expires", -1);
%>


<!-- Estos dos scripts sirven para poner el idioma en el JavaPlanner -->
<script src="codebase/dhtmlxscheduler.js" type="text/javascript" type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet" href="codebase/dhtmlxscheduler.css" type="text/css" media="screen" title="no title" charset="utf-8">

<style type="text/css" media="screen">
html,body {
	margin: 0;
	padding: 0;
	height: 100%;
	overflow: hidden;
}

/*****************************************************/
.blue_section {
	background-color: #2babf5;
	opacity: 0.27;
	filter: alpha(opacity = 27);
}

.dhx_cal_event div.dhx_footer,.dhx_cal_event.past_event div.dhx_footer,.dhx_cal_event.event_english div.dhx_footer,.dhx_cal_event.event_math div.dhx_footer,.dhx_cal_event.event_science div.dhx_footer
	{
	background-color: transparent !important;
}

.dhx_cal_event .dhx_body {
	-webkit-transition: opacity 0.1s;
	transition: opacity 0.1s;
	opacity: 0.7;
}

.dhx_cal_event .dhx_title {
	line-height: 12px;
}

.dhx_cal_event_line:hover,.dhx_cal_event:hover .dhx_body,.dhx_cal_event.selected .dhx_body,.dhx_cal_event.dhx_cal_select_menu .dhx_body
	{
	opacity: 1;
}

.dhx_cal_event.event_math div,.dhx_cal_event_line.event_math {
	background-color: orange !important;
	border-color: #a36800 !important;
}

.dhx_cal_event_clear.event_math {
	color: orange !important;
}

.dhx_cal_event.event_science div,.dhx_cal_event_line.event_science {
	background-color: #36BD14 !important;
	border-color: #698490 !important;
}

.dhx_cal_event_clear.event_science {
	color: #36BD14 !important;
}

.dhx_cal_event.event_english div,.dhx_cal_event_line.event_english {
	background-color: #FC5BD5 !important;
	border-color: #839595 !important;
}

.dhx_cal_event_clear.event_english {
	color: #B82594 !important;
}
/**********************************************************/
.highlight_area {
	opacity: 0.25;
	z-index: 0;
	filter: alpha(opacity = 30);
}

.highlight_area:hover {
	background-color: #90ee90;
}
/*********************************************************/
br { clear: both; }
      .cntSeparator {
        font-size: 54px;
        margin: 10px 7px;
        color: #000;
      }
      .desc { margin: 7px 3px; }
      .desc div {
        float: left;
        font-family: Arial;
        width: 70px;
        margin-right: 65px;
        font-size: 13px;
        font-weight: bold;
        color: #000;
      }
</style>
<title>Insert title here</title>
<script src="codebase/locale/locale_es.js" type="text/javascript"
	charset="utf-8"></script>
<script src="codebase/locale/recurring/locale_recurring_es.js"
	type="text/javascript" charset="utf-8"></script>
<script src="codebase/ext/dhtmlxscheduler_recurring.js"
	type="text/javascript" charset="utf-8"></script>
<script src="codebase/ext/dhtmlxscheduler_readonly.js"
	type="text/javascript" charset="utf-8"></script>
	

 <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/SchedulerAdaptive.css"/> 

  <link rel="stylesheet" href="css/bootstrap.min.css">
<!--  <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script> -->
<!--  <script src="js/bootstrap.min.js"></script>-->

 <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.9/themes/base/jquery-ui.css" type="text/css" rel="stylesheet" />

<!-- we want to force people to click a button, so hide the close link in the toolbar -->
<style type="text/css">a.ui-dialog-titlebar-close { display:none }</style>



</head>
<%-- <%!Usuario u1 ; %> --%>
<%-- <%  --%>
	
<!-- // 		u1 =(Usuario)session.getAttribute("Usuario"); -->
<!-- // 		HttpSession sesion =request.getSession(); -->
<!-- // 		System.out.println("Usuario : "+ u1.getUsuario()+" "+u1.getPassword()+" "); -->
<!-- // /* 		System.out.println("la sesion Atributes names->"+u1.getUsuario()); -->
<!-- // 		System.out.println("la sesion es ->"+sesion.getLastAccessedTime()); -->
<!-- // 		System.out.println("la id sesion es ->"+sesion.getId()); -->
<!-- // 		System.out.println("Creación: "+sesion.getCreationTime());  -->
<!-- // 		Date momento=new Date(sesion.getCreationTime());  -->
<!-- // 		System.out.println("Creación: "+momento);  -->
<!-- // 		Date acceso=new Date(sesion.getLastAccessedTime());  -->
<!-- // 		System.out.println("Último acceso: "+acceso); -->
<!-- // 		System.out.println("nueva sds: "+sesion.isNew()); */ -->
	
<%-- %> --%>

<body>
<%-- <img alt="imagen 1" src="<%=u1.getDimagen()%>"> --%>

<input id="user_activity" name="user_activity" type="hidden" value="active" />
<input id="user_loged_in" name="user_loged_in" type="hidden" value="true" />


<div id="inactivity_warning" class="modal fade"  style="top:30%" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
  <div class="modal-header">
    <button type="button" class="close inactivity_ok" data-dismiss="modal" aria-hidden="true">&times;</button>
  </div>
  <div class="modal-body">
    <div class="row-fluid">
      <div id="custom_alert_message" class="span12">
       You will be logged out in 5 minutes due to inactivity. Please save your credit 
       application if you have not already done so.
     </div>
       <div id="counter_2"></div>
  <div class="desc">
    <div>Minutos</div>
    <div>Segundos</div>
  </div>
   </div>
  <div class="modal-footer">
    <a href="javascript:void(0)" class="btn inactivity_ok" data-dismiss="modal" aria-hidden="true">O.K</a>
   </div>
  </div>
</div> 
</div>
</div>
	<div><h4 style="font-size: 14px; color: blue; " align="right">Bienvenido!! </h4></div>
	
	
	<div class="planner" id="scheduler_here" class="dhx_cal_container" style='width:100%;height:100%;'><%=getPlanner(request)%></div>
	<%@ page
		import="com.dhtmlx.planner.*,com.dhtmlx.planner.api.*,com.dhtmlx.planner.data.*,com.dhtmlx.planner.controls.*,com.dhtmlx.planner.extensions.*"%>
	<%!String getPlanner(HttpServletRequest request) throws Exception {
		//String user = u1.getUsuario();
		DHXPlanner s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
		//s.extensions.add(DHXExtension.CONTAINER_AUTORESIZE);
		//Date fechaActual = new Date();
		//s.setWidth(900); //Ancho del calendario
		//s.setHeight(800);//Alto del calendario
		s.config.setFirstHour(0);
		s.calendars.attachMiniCalendar();
		
		// s.setInitialDate(fechaActual);
		s.setInitialDate(2013, 0, 29); //Inicializacion del Calendario en string
		s.config.setDetailsOnCreate(true);
		s.config.setDblClickCreate(true);
		s.config.setDetailsOnDblClick(true);
		//s.config.setHourSizePx(84);
		//s.lightbox.add(new DHXLightboxMiniCalendar("cal", "Tiempo Dias"));
		s.localizations.set("es"); //Asi pondremos el idioma
		//s.localizations.set(DHXLocalization.Spanish,true);
		s.highlight.enable("highlight_area", 30);
		
		s.load("events.jsp", DHXDataFormat.JSON);
		s.data.dataprocessor.setURL("events.jsp");
		
		DHXLightboxSelect select = new DHXLightboxSelect("subject", "Sujetos");
		select.setServerList("subject");
			s.lightbox.add(select);
	
		s.extensions.add(DHXExtension.READONLY);
		s.toPDF("http://dhtmlxscheduler.appspot.com/export/pdf",DHXExportMode.FULLCOLOR);
		
		return s.render();

	}%>
	<label>El pie de pagina</label>
	
	
	</body>

	<script type="text/javascript" charset="utf-8">

	
		// template to display as tooltip
/* 	scheduler.templates.tooltip_date_format = scheduler.date
				.date_to_str("%Y-%m-%d %H:%i");
		scheduler.templates.tooltip_text = function(start, end, event) {
			return "<b>Comienzo hora:</b> "
					+ scheduler.templates.tooltip_date_format(start)
					+ "<br/><b>Fin hora:</b> "
					+ scheduler.templates.tooltip_date_format(end)
					+ "<br/><b>Evento:</b> " + event.text
					+ "<br/><b>Sujeto:</b> " + event.subject;
		}; */
		// bloque para el readonly solo los del administrador
				function block_readonly(id){
					if (!id) return true;
					return !this.getEvent(id).readonly;
				}
				scheduler.attachEvent("onBeforeDrag",block_readonly);
				scheduler.attachEvent("onClick",block_readonly);		
		// bloque para identificar los colores de subject
		scheduler.templates.event_class = function(start, end, event,id) {
			var css = "";
			
			if (event.subject) // if event has subject property then special class should be assigned
				css += "event_" + event.subject;
				
			if (event.id == scheduler.getState().select_id) {
				css += " selected";
			}
			return css; // default return
			
		}
		
	</script>
	 <script type="text/javascript">
	 
	 if (screen.width < 1200){
		 set_hav_h();
	 }
        function set_hav_h() {
        	
            try {
            	
                if (typeof scheduler !== "undefined") {
                    if (window.innerWidth < 768) {
                        if (scheduler.skin == "glossy" || scheduler.skin == "classic") {
                            scheduler.xy.nav_height = 81;

                        } else {
                            scheduler.xy.nav_height = 120;
                        }
                    } else {
                        scheduler.xy.nav_height = 59;
                    }

                    return true;
                }
            } catch (err) {
                console.log(err.message);
            }
        }

        scheduler.attachEvent("onSchedulerResize", set_hav_h);
        scheduler.attachEvent("onBeforeViewChange", set_hav_h);
    </script>
	<script src="codebase/ext/dhtmlxscheduler_limit.js"></script>
    <!--  <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script> -->
    
  <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script> 
 <script src="js/bootstrap.min.js"></script>
 <script src="js/jquery.countdown.js" type="text/javascript" charset="utf-8"></script>
 
 
<script type="text/javascript">

		// reset main timer i,e idle time to 0 on mouse move, keypress or reload
		window.onload = reset_main_timer;
		document.onmousemove = reset_main_timer;
		document.onkeypress = reset_main_timer;
		
 		var d = new Date();
	    var t = d.toLocaleTimeString();
	    console.log("mi tiempo real:  "+d);
	    var timeout = new Date().getTime(); 
	    console.log ("mi tiempo entero: "+timeout);
	    
<%-- 	    <%Date acceso2=new Date(sesion.getLastAccessedTime());%> --%>
<%-- 	    var j="<%=acceso2.getHours()%>"+":"+"<%=acceso2.getMinutes()%>"+":"+"<%=acceso2.getSeconds()%>"; --%>
<%-- 	   	var y ="<%=acceso2%>"; --%>
// 	    console.log("mi acceso real: "+y);
<%-- 	    var h="<%=sesion.getLastAccessedTime()%>"; --%>
// 	    console.log("mi acceso entero(milisegundos): "+ h);
// 	    var resta=(timeout-h);
	    
// 	    resta=resta/ (1000*60);
	    
// 	    console.log("en formato hora --> "+resta); 
	    
<%-- 		console.log("desde el request "+"<%=request.getAttribute("rimage")%>"); --%>
<%-- 		console.log("desde la sesion  "+"<%=sesion.getAttribute("Usuario")%>"); --%>
		
		// create main_timer and sub_timer variable with 0 value, we will assign them setInterval object
		var main_timer = 0;
		var sub_timer = 0;
		
		// this will ensure that timer will start only when user is loged in
		var user_loged_in = $("#user_loged_in").val();
		console.log ("user_loged --> "+ user_loged_in);
		
		// within dilog_set_interval function we have created object of setInterval and assigned it to main_timer.
		// within this we have again created an object of setInterval and assigned it to sub_timer. for the main_timer
		// value is set to 15000000 i,e 25 minute.note that if subtimer repeat itself after 5 minute we set user_activity
		// flag to inactive

		function dialog_set_interval(){
			
		    main_timer = setInterval(function(){
		    	console.log("dentro del set interval")
		        if(user_loged_in == "true"){
		        	console.log("dentro del true");
		        	//$( "#counter_2" ).add();
		            $("#inactivity_warning").modal("show");
		    		$('#counter_2').countdown({
		    		    image: 'img/digits.png',
		    		    startTime: '01:00',
		    		    //timerEnd: function(){ alert('end!'); },
		    		    format: 'mm:ss'
		    		  });
		    		
		            sub_timer = setInterval(function(){
		                $("#user_activity").val("inactive");
		                window.location="Logout";
		            },60000);
		        }
		    },120000);
		}
		// maintimer is set to 0 by calling the clearInterval function. note that clearInterval function takes
		// setInterval object as argument, which it then set to 0
		function reset_main_timer(){
		    clearInterval(main_timer);
		    dialog_set_interval();
		}
		
		// logout user if user_activity flag set to inactive, when he click ok on popup. whenuser click O.K
		// on the warning the subtimer is reset to 0
		$(".inactivity_ok").click(function(){
		    clearInterval(sub_timer);
		    $( "#counter_2" ).empty();
		    if($("#user_activity").val() == "inactive"){
		    	console.log("dentro de inactive");
		        window.location =window.location; // if your application not manage session expire 
		                                          //automatically. clear cookies and session her
		                                          
		    }
		});

</script>

</body>
</html>