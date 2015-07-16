 $(document).ready(function(){
			
		    Dropzone.options.myAwesomeDropzone = {

                autoProcessQueue: false,
                uploadMultiple: true,
                parallelUploads: 1,
                maxFiles: 1,

                // Dropzone settings
                init: function() {
                    var myDropzone = this;

                    this.element.querySelector("button[type=submit]").addEventListener("click", function(e) {
                        e.preventDefault();
                        e.stopPropagation();
                        myDropzone.processQueue();
                    });
					
					
					this.on("addedfile", function(file) { 
                        
						$('#btn_registrarArchivos').removeAttr('disabled');
					
					});
					
                    this.on("sendingmultiple", function() {
						
                    });
                    this.on("successmultiple", function(files, response) {
						$('#btn_compararArchivos').removeAttr('disabled');
											
						$('html, body').animate({
							scrollTop: $("#form_iniciarComparacion").offset().top
						}, 1800);
					
                    });
                    this.on("errormultiple", function(files, response) {
						
                    });
                }

            }

       });
	   
function validarEnviar(form) {
	
	
	var isValido = true;
	var cpar_observ = $('#cpar_observ_ini').val();
	
	//Se valida sin todos los campos estan diligenciados
	if(osm_esVacio(cpar_observ)){
		isValido = false;
		
		$('#btn_popup_msg_obl').click();
		
		
	}
	//Si todo es exitoso se envia el formulario
	if(isValido){
		
		osm_enviarFormulario(form);
	}

	
}

function showObject(id_Object){
	
	var Object = osm_getObjeto(id_Object);
	
	if($(Object).is(':hidden')){
		
		$(Object).fadeIn('slow');	
	}
}

function hideObject(id_Object){
	
	var Object = osm_getObjeto(id_Object);
	
	if(!$(Object).is(':hidden')){
		
		$(Object).fadeOut('slow');	
	}
}

function showInstructions(){
	$('#btn_popup_msg_obl').click();
}

   