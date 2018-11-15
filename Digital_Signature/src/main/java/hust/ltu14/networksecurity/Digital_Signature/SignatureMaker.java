package hust.ltu14.networksecurity.Digital_Signature;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class SignatureMaker {
	public SignatureMaker() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException, IOException {
		Security.addProvider(new BouncyCastleProvider());
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA", "BC");
	    /*int maxKeySize = javax.crypto.Cipher.getMaxAllowedKeyLength("AES");
	    System.out.println(maxKeySize);*/
	    keyGen.initialize(2048);
	    
	    KeyPair pair = keyGen.generateKeyPair();
	    PrivateKey priv = pair.getPrivate();
	    PublicKey pub = pair.getPublic();
	    
	    Signature sig = Signature.getInstance("SHA1withRSA", "BC");
	    sig.initSign(priv);
	    
	    FileInputStream file = new FileInputStream("resources/TextToSign.txt");
	    BufferedInputStream buf = new BufferedInputStream(file);
	    byte[] buffer = new byte[2048];
	    int len = 0;
	    while((len = buf.read(buffer)) >= 0) {
	    	sig.update(buffer, 0, len);
	    }
	    buf.close();
	    
	    byte[] realSig = sig.sign();
	    System.out.println(realSig);
	    FileOutputStream sigout = new FileOutputStream("resources/Signature.txt");
	    sigout.write(realSig);
	    sigout.close();
	    System.out.println("Signature saved");
	    
	    byte[] pubkey = pub.getEncoded();
	    FileOutputStream pubkeyout = new FileOutputStream("resources/Public key.txt");
	    System.out.println(pubkey);
	    pubkeyout.write(pubkey);
	    pubkeyout.close();
	    System.out.println("Public key saved");
	}
    
}
