package hust.ltu14.networksecurity.Digital_Signature;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class SignatureMaker {
	public SignatureMaker() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException, IOException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		
		File file = new File("resources/TextToSign.txt");
	    Hashmaker hmk = new Hashmaker();
		MessageDigest SHA1digest = MessageDigest.getInstance("SHA-1");
	    String checksum = hmk.getFileChecksum(SHA1digest, file);
	    
	    System.out.print(checksum);
		
	    Security.addProvider(new BouncyCastleProvider());
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA", "BC");
	    keyGen.initialize(1024);
	    
	    KeyPair pair = keyGen.generateKeyPair();
	    PrivateKey priv = pair.getPrivate();
	    PublicKey pub = pair.getPublic();
	    
	    Cipher cipher = Cipher.getInstance("RSA", "BC");
	    cipher.init(Cipher.ENCRYPT_MODE, priv);
	    
	    byte[] encryptedHash = cipher.doFinal(checksum.getBytes());
	    
	    
		/*
	    
	    
	    
	    Signature sig = Signature.getInstance("SHA1withRSA", "BC");
	    sig.initSign(priv);
	    
	    
	    
	    
	        
	    byte[] realSig = sig.sign();
	    System.out.println(realSig);
	    //OutputStream sigout = new FileOutputStream("resources/Signatur.txt");
	    FileWriter sigout = new FileWriter(new File("resources/Signature.txt"));
	    sigout.write(realSig.toString());
	    sigout.close();
	    System.out.println("Signature saved");
	    
	    byte[] pubkey = pub.getEncoded();
	    //FileOutputStream pubkeyout = new FileOutputStream("resources/Public key.txt");
	    FileWriter pubkeyout = new FileWriter("resources/Public key.txt");
	    pubkeyout.write(pubkey.toString());
	    System.out.println(pubkey.toString());
	    pubkeyout.close();
	    System.out.println("Public key saved");
	    //outputStreamWriter.close();*/
	}
    
}
