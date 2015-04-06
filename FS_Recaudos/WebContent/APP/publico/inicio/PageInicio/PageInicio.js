$(document).ready(function() {
	
	var showPopupError=$('#show_popup_error_auth').val();
	
	if(showPopupError == 'true'){
		$('#btn_popup_error_auth').click();
	}
	
});

function encriptarEnviar(){
	
	// --
	
	var DOCUMENTO_USUARIO = encrypt(osm_getValor("login"));
	var PASSWORD_USUARIO = encrypt(osm_getValor("pass"));

	// --
	
	osm_setValor("documento_usuario",DOCUMENTO_USUARIO);
	osm_setValor("password", PASSWORD_USUARIO);
	
		
	osm_setValor("login","");
	osm_setValor("pass","");

	// --
	
	osm_block_window();
	osm_timeout(function(){	
		osm_enviarFormulario("login_form"); 
		
		}, 1000);
}


function enviarFormulario(){
	
	var msg = validacionCampos();
	
	if(msg==''){
		
		encriptarEnviar();
		
	}
}

function validacionCampos(){
	var inicio ='Por favor ingrese toda la información en los campos obligatorios marcados con *. ';
	var msg = 'Faltan los siguientes campos: ';

	var correcto = true;

	//Validacion nombre de usuario
	if(osm_getValor("login")==''){
		if(!correcto){
			msg = msg + ',';
		}else{
			correcto = false;
		}
		msg = msg + ' Nombre de Usuario';
	}
	//Validacion contraseï¿½a
	if(osm_getValor("pass")==''){
		if(!correcto){
			msg = msg + ',';
		}else{
			correcto = false;
		}
		msg = msg + ' Contraseña';	
	}
	if(!correcto){
		return inicio + msg;
	}else{
		return '';
	}
}



function encrypt(mensaje) {
	
	if(jsonrpc.rsaJSONServicio != null){
		var res=jsonrpc.rsaJSONServicio.encrypt(mensaje);
	}
		
	return res;	
	
}


