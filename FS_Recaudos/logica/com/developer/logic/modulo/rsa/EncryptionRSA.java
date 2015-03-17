package com.developer.logic.modulo.rsa;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import com.developer.logic.modulo.notificaciones.NotificacionServicio;

/**
 * @author JavaDigest
 * 
 */
public class EncryptionRSA {

  /**
   * String to hold name of the encryption algorithm.
   */
  private  final String ALGORITHM = "RSA";

  /**
   * String to hold the name of the private key file.
   */
  private  final String PRIVATE_KEY_FILE = "private.key";

  /**
   * String to hold name of the public key file.
   */
  private  final String PUBLIC_KEY_FILE = "public.key";

  /**
   * Generate key which contains a pair of private and public key using 1024
   * bytes. Store the set of keys in Prvate.key and Public.key files.
   * 
   * @throws NoSuchAlgorithmException
   * @throws IOException
   * @throws FileNotFoundException
   */
  
  private static EncryptionRSA instance;
  

  private Map<String, byte[]> mapEncrypts = null;
  
  
  private PublicKey publicKey;
  
  private PrivateKey privateKey;
  
  private EncryptionRSA(){
	  
	  mapEncrypts = new HashMap<String, byte[]>();
	  // Check if the pair of keys are present else generate those.
      if (!areKeysPresent()) {
        // Method generates a pair of keys using the RSA algorithm and stores it
        // in their respective files
    	  generateKey();
      }
	  
  
      try{
	      ObjectInputStream inputStreamPublicKey = null;
	
	      //  public key
	      inputStreamPublicKey = new ObjectInputStream(new FileInputStream(this.PUBLIC_KEY_FILE));
	      this.publicKey = (PublicKey) inputStreamPublicKey.readObject();
	      inputStreamPublicKey.close();
	       
	      //  private key.
	      ObjectInputStream inputStreamPrivateKey = null;
	      inputStreamPrivateKey = new ObjectInputStream(new FileInputStream(this.PRIVATE_KEY_FILE));
	      this.privateKey = (PrivateKey) inputStreamPrivateKey.readObject();
	      
	      inputStreamPrivateKey.close();
      
	  } catch (Exception e) {
		  e.printStackTrace();
	  }
	  
	  
  }
  
  
  public static EncryptionRSA getInstance() {
		if (instance == null) {
			instance = new EncryptionRSA();
		}
		
		return instance;
  }
  
  
  private void generateKey() {
    try {
      final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
      keyGen.initialize(1024);
      final KeyPair key = keyGen.generateKeyPair();

      File privateKeyFile = new File(PRIVATE_KEY_FILE);
      File publicKeyFile = new File(PUBLIC_KEY_FILE);

      // Create files to store public and private key
      if (privateKeyFile.getParentFile() != null) {
        privateKeyFile.getParentFile().mkdirs();
      }
      privateKeyFile.createNewFile();

      if (publicKeyFile.getParentFile() != null) {
        publicKeyFile.getParentFile().mkdirs();
      }
      publicKeyFile.createNewFile();

      // Saving the Public key in a file
      ObjectOutputStream publicKeyOS = new ObjectOutputStream(
          new FileOutputStream(publicKeyFile));
      publicKeyOS.writeObject(key.getPublic());
      publicKeyOS.close();

      // Saving the Private key in a file
      ObjectOutputStream privateKeyOS = new ObjectOutputStream(
          new FileOutputStream(privateKeyFile));
      privateKeyOS.writeObject(key.getPrivate());
      privateKeyOS.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  /**
   * The method checks if the pair of public and private key has been generated.
   * 
   * @return flag indicating if the pair of keys were generated.
   */
  private boolean areKeysPresent() {

    File privateKey = new File(PRIVATE_KEY_FILE);
    File publicKey = new File(PUBLIC_KEY_FILE);

    if (privateKey.exists() && publicKey.exists()) {
      return true;
    }
    return false;
  }

  /**
   * Encrypt the plain text using public key.
   * 
   * @param text
   *          : original plain text
   * @param key
   *          :The public key
   * @return Encrypted text
   * @throws java.lang.Exception
   */
  public String encrypt(String text) {
	  
	String textEncrypt = null;   
    byte[] cipherText = null;
    try {
      // get an RSA cipher object and print the provider
      final Cipher cipher = Cipher.getInstance(ALGORITHM);
      // encrypt the plain text using the public key
      cipher.init(Cipher.ENCRYPT_MODE, this.publicKey);
      cipherText = cipher.doFinal(text.getBytes());
      textEncrypt =cipherText.toString();
      mapEncrypts.put(textEncrypt, cipherText);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    return textEncrypt;
  }

  /**
   * Decrypt text using private key.
   * 
   * @param textEncrypt
   *          :encrypted text
   * @param key
   *          :The private key
   * @return plain text
   * @throws java.lang.Exception
   */
  public String decrypt(String textEncrypt) {
	  
	  
    byte[] dectyptedText = null;
    try {
      // get an RSA cipher object and print the provider
      final Cipher cipher = Cipher.getInstance(ALGORITHM);

      
      byte[] text = mapEncrypts.get(textEncrypt);
      
      // decrypt the text using the private key
      cipher.init(Cipher.DECRYPT_MODE, this.privateKey);
      dectyptedText = cipher.doFinal(text);

    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return new String(dectyptedText);
  }
  
  public byte[] getByteEncrypt(String textEncrypt){
	  
	  return mapEncrypts.get(textEncrypt);
  }

  /**
   * Test the EncryptionUtil
   */
  public static void main(String[] args) {

    try {

      final String originalText = "Text to be encrypted ";
      
      String textoEncrypt = EncryptionRSA.getInstance().encrypt(originalText);
       
      final String plainText = EncryptionRSA.getInstance().decrypt(textoEncrypt);

      // Printing the Original, Encrypted and Decrypted Text
      
      System.out.println("Original: " + originalText);
      System.out.println("Encrypted: " +textoEncrypt);
      System.out.println("Decrypted: " + plainText);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}