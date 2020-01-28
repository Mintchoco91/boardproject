package com.project.boardproject.bc.util;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.UUID;

public class BlockChainUtils {
    /**
     * Returns a hexadecimal encoded SHA-256 hash for the input String.
     * @param data
     * @return
     */
    public static String getSHA256Hash(String data) {
		/*
		 * try { MessageDigest digest = MessageDigest.getInstance("SHA-256"); byte[]
		 * hash = digest.digest(data.getBytes("UTF-8"));
		 * 
		 * return DatatypeConverter.printHexBinary(hash); } catch (Exception
		 * NoSuchAlgorithmException) { throw new
		 * RuntimeException(NoSuchAlgorithmException); }
		 */
        StringBuffer result = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(data.getBytes());
			byte bytes[] = md.digest();
			for(int i=0;i<bytes.length;i++) {
				result.append(Integer.toString((bytes[i]&0xff)+0x100, 16).substring(1));
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return result.toString();
    }

    /**
     * Returns a UUID(type 4) string
     * @return
     */
    public static String generateTransactionId() {
        return UUID.randomUUID().toString();
    }

    /**
     * Using privateKey and input data to generate ECDSA signature using Elliptic Curve algorithm
     * @param privateKey
     * @param data
     * @return realSig
     */
    public static byte[] applySignature(PrivateKey privateKey, String data) {
        Signature signature;
        byte[] realSig;
        try {
            signature = Signature.getInstance("SHA1withECDSA","SunEC");
            signature.initSign(privateKey);
            byte[] strByte = data.getBytes();
            signature.update(strByte);
            realSig = signature.sign();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return realSig;
    }

    /**
     * Verify the signature is valid or not
     * @param publicKey
     * @param data
     * @param signature
     * @return boolean
     */
    public static boolean verifySignature(PublicKey publicKey, String data, byte[] signature) {
        try {
            Signature ecdsaVerify = Signature.getInstance("SHA1withECDSA","SunEC");
            ecdsaVerify.initVerify(publicKey);
            ecdsaVerify.update(data.getBytes());
            return ecdsaVerify.verify(signature);
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static String convertKeytoString(Key key) {
    	return Base64.getEncoder().encodeToString(key.getEncoded());
    }
    
    public static PrivateKey convertStringtoPrivateKey(String privateKeyString){
    	PrivateKey privateKey = null;
    	try {
	    	byte[] decoded = Base64.getDecoder().decode(privateKeyString);
	    	PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
	    	KeyFactory factory = KeyFactory.getInstance("EC","SunEC");
	    	privateKey = factory.generatePrivate(spec);
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	return privateKey;
    }
    
    public static PublicKey convertStringtoPublicKey(String publicKeyString) {
    	PublicKey publicKey = null;
    	try {
    		byte[] decoded = Base64.getDecoder().decode(publicKeyString);
    		X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
    		KeyFactory factory = KeyFactory.getInstance("EC","SunEC");
	    	publicKey = factory.generatePublic(spec);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return publicKey;
    }
}
