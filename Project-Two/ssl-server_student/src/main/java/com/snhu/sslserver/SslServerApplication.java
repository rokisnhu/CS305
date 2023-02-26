package com.snhu.sslserver;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}

}
//FIXME: Add route to enable check sum return of static data example:  String data = "Hello World Check Sum!";

@RestController
class ServerController{
	 public static String calculateHash(String name) throws NoSuchAlgorithmException 
	    {  
	        MessageDigest md = MessageDigest.getInstance("SHA-256");  
	        byte[] hash =  md.digest(name.getBytes(StandardCharsets.UTF_8));
	        BigInteger number = new BigInteger(1, hash);  
	        StringBuilder hexString = new StringBuilder(number.toString(16));  
	        while (hexString.length() < 32)  
	        {  
	            hexString.insert(0, '0');  
	        }  
	        return hexString.toString();  
	     
	    }     @RequestMapping("/hash")
    public String myHash() throws NoSuchAlgorithmException{
    	String data = "christina roki";
		String hash = calculateHash(data);

		return "<p>data:"+data+"--Name of Cipher Algorithm Used: SHA-256--"+"CheckSum Value: "+hash;
    }
}
