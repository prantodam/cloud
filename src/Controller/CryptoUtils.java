package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
 
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
public class CryptoUtils {
	
	 private static final String ALGORITHM = "AES";
	 private static final String TRANSFORMATION = "AES";
	 
	    public static void encrypt(String key, File storeFile, File encryptedFile)
	            throws CryptoException {
	        doCrypto(Cipher.ENCRYPT_MODE, key, storeFile, encryptedFile);
	    }
	 
	    public static void decrypt(String key, File encInFile, File decOutFile)
	            throws CryptoException {
	        doCrypto(Cipher.DECRYPT_MODE, key, encInFile, decOutFile);
	    }
	 
	    private static void doCrypto(int cipherMode, String key, File storeFile,
	            File encryptedFile) throws CryptoException {
	        try {
	            Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
	            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
	            cipher.init(cipherMode, secretKey);
	             
	            FileInputStream inputStream = new FileInputStream(storeFile);
	            byte[] inputBytes = new byte[(int) storeFile.length()];
	            inputStream.read(inputBytes);
	             
	            byte[] outputBytes = cipher.doFinal(inputBytes);
	             
	            FileOutputStream outputStream = new FileOutputStream(encryptedFile);
	            outputStream.write(outputBytes);
	             
	            inputStream.close();
	            outputStream.close();
	             
	        } catch (NoSuchPaddingException | NoSuchAlgorithmException
	                | InvalidKeyException | BadPaddingException
	                | IllegalBlockSizeException | IOException ex) {
	            throw new CryptoException("Error encrypting/decrypting file", ex);
	        }
	    }

}
