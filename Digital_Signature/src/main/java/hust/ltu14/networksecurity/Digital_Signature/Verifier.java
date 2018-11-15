package hust.ltu14.networksecurity.Digital_Signature;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public class Verifier {
	public Verifier() throws IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, InvalidKeyException, SignatureException {
		FileInputStream keyfis = new FileInputStream("resources/Public key.txt");
		byte[] encKey = new byte[keyfis.available()];
		keyfis.read(encKey);
		keyfis.close();
		
		X509EncodedKeySpec pubkeySpec = new X509EncodedKeySpec(encKey);
		KeyFactory keyFac = KeyFactory.getInstance("RSA", "BC");
		PublicKey pub = keyFac.generatePublic(pubkeySpec);
		
		FileInputStream sigfis = new FileInputStream("resources/Signature.txt");
		byte[] sigVer = new byte[sigfis.available()];
		sigfis.read(sigVer);
		sigfis.close();
		Signature sig = Signature.getInstance("SHA1withRSA", "BC");
		sig.initVerify(pub);
		
		FileInputStream datafis = new FileInputStream("resources/TextToSign.txt");
		BufferedInputStream bufin = new BufferedInputStream(datafis);

		byte[] buffer = new byte[2048];
		int len;
		while (bufin.available() != 0) {
		    len = bufin.read(buffer);
		    sig.update(buffer, 0, len);
		};

		bufin.close();
		
		boolean verifies = sig.verify(sigVer);

		System.out.println("signature verifies: " + verifies);
	}
}
