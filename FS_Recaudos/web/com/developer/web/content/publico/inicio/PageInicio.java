package com.developer.web.content.publico.inicio;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.developer.core.page.PublicPage;
import com.developer.core.utils.ObjectToXML;
import com.developer.core.utils.SimpleLogger;
import com.developer.logic.modulo.autenticacion.AutenticadorServicio;
import com.developer.logic.modulo.rsa.EncryptionRSA;

public class PageInicio extends PublicPage {

	@Override
	public String getNextPage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StringBuffer executeAction(HttpServletRequest request) {
		
		StringBuffer xmlPage = null;
		xmlPage = new StringBuffer();
		
		try {

	      // Check if the pair of keys are present else generate those.
	      if (!EncryptionRSA.areKeysPresent()) {
	        // Method generates a pair of keys using the RSA algorithm and stores it
	        // in their respective files
	    	  EncryptionRSA.generateKey();
	      }

	      final String originalText = "Text to be encrypted ";
	      ObjectInputStream inputStream = null;

	      // Encrypt the string using the public key
	      inputStream = new ObjectInputStream(new FileInputStream(EncryptionRSA.PUBLIC_KEY_FILE));
	      final PublicKey publicKey = (PublicKey) inputStream.readObject();
	      final byte[] cipherText = EncryptionRSA.encrypt(originalText, publicKey);

	      // Decrypt the cipher text using the private key.
	      inputStream = new ObjectInputStream(new FileInputStream(EncryptionRSA.PRIVATE_KEY_FILE));
	      final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
	      final String plainText = EncryptionRSA.decrypt(cipherText, privateKey);
	      
	      inputStream.close();
	      // Printing the Original, Encrypted and Decrypted Text
	      SimpleLogger.info("Original: " + originalText);
	      SimpleLogger.info("Encrypted: " +cipherText.toString());
	      SimpleLogger.info("Decrypted: " + plainText);
	      
	      xmlPage.append(ObjectToXML.getInstance().getXML(publicKey));

	    } catch (Exception e) {
	      e.printStackTrace();
	    }
		
		
				
		//Se verifica si existen errores
		String error = request.getParameter("error");
		if(!StringUtils.isEmpty(error)){
			//Se establece que la pagina tiene errores
			xmlPage = new StringBuffer();
			xmlPage.append("error");
			
		}
		
		return xmlPage;
		
	
	}
	
	@Override
	public boolean isAccesoValido(HttpServletRequest arg0) {
		return true;

	}
	
	
	
}
