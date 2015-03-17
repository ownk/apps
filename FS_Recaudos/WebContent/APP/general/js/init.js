/**
 * =========================================================
 * Se crea el objetos necesarios para el uso en general
 * ========================================================
 */

try {
		//JSON
		jsonrpc = createJSONRpcClient();
		
				
} catch (e) {
	alert(e);
}

function createJSONRpcClient(){
	
	return new JSONRpcClient( GLOBAL_CONTEXTPATH + "/JSON-RPC" );
}

