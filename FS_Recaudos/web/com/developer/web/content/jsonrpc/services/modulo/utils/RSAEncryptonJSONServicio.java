package com.developer.web.content.jsonrpc.services.modulo.utils;

import com.developer.logic.modulo.rsa.EncryptionRSA;

public class RSAEncryptonJSONServicio {
	
	
	public String encrypt(String textToEncrypt) {
		
		String textEncrypt = EncryptionRSA.getInstance().encrypt(textToEncrypt);
		
		return textEncrypt;
	}
	
	


	

}



