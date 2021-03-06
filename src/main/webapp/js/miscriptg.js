function reloadCaptcha(){
    var d = new Date();
    $("#captcha_image").attr("src", "EdwCaptcha?"+d.getTime());
}

$(document).ready(function(){
	
    var jumboHeight = $('.jumbotron').outerHeight();
    function parallax(){
        var scrolled = $(window).scrollTop();
        $('.bg').css('height', (jumboHeight-scrolled) + 'px');
    }

    $(window).scroll(function(e){
        parallax();
    }); 


	$(function() {
		 
	    $("#spotlight").autocomplete({
			source: function(request, response) {
            $.ajax({
                    url : "ConBusqueda",
                    type : "GET",
                    data : {
                            term : request.term
                    },
                    dataType : "json",
                    success : function(data) {
                    	response($.map( data, function( item ){
                      	return {
                            nombre: item.nombre,//corresponds with json object built above
                            tipo: item.tipos,
                            img: item.direccion
                          }
                        }));
                    }
            });},
			appendTo: $("form:first"),
			/*focus: function( event, ui ) {
	            $(this).val( ui.item.nombre );
	            return false;
	        },*/
	        select: function( event, ui ) {
	            $(this).val( ui.item.nombre );
	            return false;
	        }
		});
	    $("#spotlight") .data( "ui-autocomplete" )._renderItem = function( ul, item ) {
	    	  return $( "<li>" )
	    	    .data( "ui-autocomplete-item", item )
	    	    .append( '<a><img src="http://i1095.photobucket.com/albums/i476/jesfeoro/atracciones/'+item.img+'" class="img-responsive img-circle" alt="" style="float:left; margin-right: 5px;" > ' + item.nombre + "<br><span style='font-size:9px;'>" + item.tipo + "</span></a>" )
	    	    .appendTo( ul );
	    	};
	});
	$('#olvidado').click(function(e) {
        e.preventDefault();
        $('div#form-olvidado').toggle('500');
      });
      $('#acceso').click(function(e) {
        e.preventDefault();
        $('div#form-olvidado').toggle('500');
      });
	  
	
	jQuery.fn.reset = function () {
		  $(this).each (function() { this.reset(); });
		};
	
    $.validator.addMethod("regex",function(value,element,regexp){
        var re= new RegExp(regexp);
        return this.optional(element) || re.test(value);
    },"Solo caracteres alfanumericos");

    $("#LostPass").validate({
		 rules:{
 			email2:{
 				email:true,
 				required:true,
 				 remote:"TratarUser?opcion=EmailCorrecto&tipos=lost"
 			     
 			}
 		},
 		 highlight: function(element) {
             $(element).closest('.form-group').addClass('has-error');
         },
         unhighlight: function(element) {
             $(element).closest('.form-group').removeClass('has-error');
         },
         errorElement: 'span',
         errorClass: 'help-block',
         errorPlacement: function(error, element) {
             if(element.parent('.input-group').length) {
                 error.insertAfter(element.parent());
             } else {
                 error.insertAfter(element);
             }
         },
 		messages:{
 			email2:{
                 email:"El email no es valido",
                 required:"Campo obligatorio",
                 remote: "Email no existe en la base de datos(lostPass)"
             }
 		}
    
    });
    
    $("#AccesoUser").validate({
		 rules:{
			email:{
				email:true,
				required:true				 			     
			},
			pass:{
            	required: true,
            	minlength: 3,
            	maxlength: 8
            }
		},
		 highlight: function(element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        unhighlight: function(element) {
            $(element).closest('.form-group').removeClass('has-error');
        },
        errorElement: 'span',
        errorClass: 'help-block',
        errorPlacement: function(error, element) {
            if(element.parent('.input-group').length) {
                error.insertAfter(element.parent());
            } else {
                error.insertAfter(element);
            }
        },
		messages:{
			email:{
                email:"El email no es valido",
                required:"Campo obligatorio",
            },
            pass:{
                required:"Campo obligatorio",
                minlength:"minimo 3 caracteres",
                maxlength:"maximo 8 caracteres"
            }
		}, submitHandler:function(form){
        	//Crearemos  el registro en la base de datos
        	$.ajax({
				type: $(form).attr("method"),
				url: $(form).attr("action"),
				data: $(form).serialize(),
				dataType : "json",
				success: function(result){
					if (result == 1) {
						location.reload();			         	
					}else{
						$("#AccesoUser").reset();
						$('<span for="email" style="color:red" class="help-block">'+
								'Usuario y/o contraseñas incorrectas</span>').insertAfter('#refolvidado');
					}
				
				}, 				       	
        	});                        
        }
   });
    $('#close').on('click', function () {
        $("#RegistroModal").validate().resetForm();
        $('.form-group').removeClass('has-error has-feedback');
        $('.form-group').removeClass('has-success has-feedback');
        $('.form-control-feedback').removeClass('glyphicon-remove');
        $('.form-control-feedback').removeClass('glyphicon-ok');
        $("span.help-block").remove();
        $("#AccesoUser").reset();
        $("#LostPass").reset();
    });  
    $("#NuevoUser").validate({
        rules:{
                usuario:{
                    required:true,
                    minlength: 5,
                    regex:"^[a-zA-Z0-9_]+$",
                    remote:"TratarUser?opcion=UserCorrecto"
                },
                email:{
                    email:true,
                    required:true,
                    remote:"TratarUser?opcion=EmailCorrecto&tipos=registro"
                },
                answer:{
                    required:true,
                    remote:"EnvioCaptcha"
                },
                pass:{
                	required: true,
                	minlength: 3,
                	maxlength: 8
                },
                pass2:{
                	required: true,
                    equalTo:"#pass",
                    minlength: 3,
                    maxlength: 8
                }
        },
        messages:{
            usuario:{
                required:"Campo obligatorio",
                minlength:"minimo 5 caracteres",
                remote:"Ese nombre esta en uso."
            },
            pass:{
                required:"Campo obligatorio",
                minlength:"minimo 3 caracteres",
                maxlength:"maximo 8 caracteres"
            },
            pass2:{
            	required:"Campo obligatorio",
                equalTo:"La contrase�a no es igual",
                minlength:"minimo 3 caracteres",
                maxlength:"maximo 8 caracteres"
            },
            answer:{
                required:"Campo es obligatorio",
                remote:"Captcha incorrecto. Click en refrescar para uno nuevo"
            },
            email:{
                email:"El email no es valido",
                required:"Campo obligatorio",
                remote:"Ese email esta en uso."
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
			},
        submitHandler:function(form){
        	//Crearemos  el registro en la base de datos
        	$.ajax({
				type: $(form).attr("method"),
				url: $(form).attr("action"),
				data: $(form).serialize(),
				dataType : "json",
				success: function(result){
					//Aqui se evalua result, si tenemos fallos en la inserccion en la base de datos (true o false)
		            $('#RegistroModal').modal('hide');          
		            // borraremos las classes de colores y los glyphicons que teniamos en el formulario de ok
		            $('.form-group').removeClass('has-error has-feedback');
		            $('.form-group').removeClass('has-success has-feedback');
		            $('.form-control-feedback').removeClass('glyphicon-ok');
					// borramos los datos del formulario
		            $("#NuevoUser").reset();	
					// ahora creamos un alert para mostrar el resultado del registro insertado con exito
		            $('<div class="alert alert-success" id="success-alert">'+
            	    '<a href="#cerraralert" class="close" data-dismiss="alert">&times;</a>'+
	            	   ' <strong>Bienvenido!</strong>Ya Puedes acceder a descubrir mas cosas de este sitio.'+
	            	'</div>	').insertAfter('nav');
					// por si no quiere cerrar la ventana se cerrara automaticamente
		            $("#success-alert").fadeTo(7000, 500).slideUp(500, function(){
		                $("#success-alert").alert('close');
		                location.reload();
		            });
		            $('.close').click(function(e){
		            	location.reload();
		            }); 
					
				},

 				       	
        	});
            
            
        }
        	
    });
    $('#close').on('click', function () {
        $("#RegistroModal").validate().resetForm();
        $('.form-group').removeClass('has-error has-feedback');
        $('.form-group').removeClass('has-success has-feedback');
        $('.form-control-feedback').removeClass('glyphicon-remove');
        $('.form-control-feedback').removeClass('glyphicon-ok');
        $("span.help-block").remove();
        $("#NuevoUser").reset();
    });
    $('#cerrar').on('click', function () {
        $("#RegistroModal").validate().resetForm();
        $('.form-group').removeClass('has-error has-feedback');
        $('.form-group').removeClass('has-success has-feedback');
        $('.form-control-feedback').removeClass('glyphicon-remove');
        $('.form-control-feedback').removeClass('glyphicon-ok');
        $("span.help-block").remove();
        $("#NuevoUser").reset();
    });
});
