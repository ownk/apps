 $(document).ready(function(){
			
			$('#form_iniciarProceso .input-group.date').datepicker({
                keyboardNavigation: false,
                forceParse: false,
                autoclose: true,
				format: 'dd/mm/yyyy' 
            });
			
		
		
			
            Dropzone.options.myAwesomeDropzone = {

                autoProcessQueue: false,
                uploadMultiple: true,
                parallelUploads: 100,
                maxFiles: 100,

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
						$('#btn_unificarArchivos').removeAttr('disabled');
											
						$('html, body').animate({
							scrollTop: $("#form_iniciarProceso").offset().top
						}, 1800);
					
                    });
                    this.on("errormultiple", function(files, response) {
						
                    });
                }

            }

       });
	   
function validarEnviar(form) {
	
	
	var isObserAnulActive = false;
	var isValido = true;
	var prun_fini = $('#prun_fini').val();
	var prun_ffin = $('#prun_ffin').val();
	var prun_observ_anul = $('#prun_observ_anul').val();
	
	//Se valida sin todos los campos estan diligenciados
	if(osm_esVacio(prun_fini) || osm_esVacio(prun_ffin)){
		isValido = false;
		
		$('#btn_popup_msg_obl').click();
		
		
	}else if( !osm_esVacio(prun_fini) && !osm_esVacio(prun_ffin) ){
    
        var fini = getDate(prun_fini);
        var ffin = getDate(prun_ffin);
        
        if(fini > ffin){
            isValido = false;
            
            $('#btn_popup_msg_obl').click();
            
        }else if(jsonrpc.prunJSONServicio != null){
		
            var res=jsonrpc.prunJSONServicio.getProcesosPorEstadoFechaIniFin(prun_fini, prun_ffin);
            
            if(res.list.length > 0){
                isObserAnulActive = true;	
                showObject('div_prun_observ');
                
            
                if(osm_esVacio(prun_observ_anul)){
                    isValido = false;
                    
                    $('#prun_observ_anul').focus();
                }
            }else{
                isObserAnulActive = false;
                hideObject('div_prun_observ');
                
            }
            
        }
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

function getDate(fechaString){
	
	var objDate = new Date();
	try {
        
        anho  =fechaString.substring(6, 10);
        mes = fechaString.substring(3, 5);
        dia = fechaString.substring(0, 2);
		objDate = new Date(Number(anho), Number(mes), Number(dia));
	} catch (e) {
	}
	
	return objDate;
}	   