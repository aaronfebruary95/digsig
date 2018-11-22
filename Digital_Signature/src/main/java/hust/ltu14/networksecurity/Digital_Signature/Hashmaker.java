package hust.ltu14.networksecurity.Digital_Signature;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Base64;


public class Hashmaker {
	public Hashmaker() {
		
	}
	public String getFileChecksum(MessageDigest digest, File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		byte[] byteArray = new byte[2048];
		int len = 0;
		while((len = fis.read(byteArray)) != -1) {
			digest.update(byteArray, 0, len);
		}
		fis.close();
		System.out.println(byteArray.toString());
		byte[] hash = digest.digest();
		return Base64.getEncoder().encodeToString(hash);
	}
}
