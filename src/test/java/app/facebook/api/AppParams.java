package app.facebook.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


public class AppParams {
	//BRING THIS CODE FROM CHROME --> https://www.facebook.com/v15.0/dialog/oauth?client_id=471577415127450&redirect_uri=https://task-day.onrender.com/%23/&response_type=code
	private String CODE = "AQB6lvPQkFWhkgyWKLSsUZjT4ogocn3KAEp3pss5iYDmyXKmDDrKLLmxo5gSS28kIuXYmvYJHheIZKO0bZUfBqOI3P8bhP8sk-ykqN-bK7hddV0QHpWr6OASzCmr1yqTHQpjqmZ4I14s4bfvZXWb_zZAwYwSXJ_1g3xdgVzUOwcToG9btmbMmgDK_hKWZ5OurFjzr-jy2DTAvovMdcKtowcK4wAfPnb8Hladsh0qWGkm2y_e2nsQ4s2Y_AR-gYTEAcTF6lZN3d0uvG-sAqkv4HqSASwHRGwPtSEMK-fopVeJ6Kk8ti1BxeP70mzSIt8u2HkgvDpW_zFNiNv70_8bmFLmygEHZDLd1OwWaa6f-zMDdIWPzur-s4jDnuxTfUdasokGcCC3OlFcJnWQ-QL6R7sSjEwdflHfBVRenpz_MHoENw";
	private String clientId = "471577415127450";
	private String redirect_uri = "https://task-day.onrender.com/#/";
	private String response_type = "code";
	private static String client_secret = "b5c404be464567af8bb3c71403bd7415";
	private static String ACCESS_TOKEN = null;
	private String appsecret_proof = null;	
	private String field_path = "C:\\Users\\itzik vaknin\\Downloads\\KashCourse\\zmani\\fileds.txt";
	
	public  String getAppsecret_proof() throws NoSuchAlgorithmException {
		generateAppsecret_proof();
		return appsecret_proof;
	}
	
	public String getACCESS_TOKEN() throws FileNotFoundException {
		if (ACCESS_TOKEN == null || ACCESS_TOKEN == "") {
			loadAccessTokenFromFile();			
		}
		return ACCESS_TOKEN;
	}
	
	public void setACCESS_TOKEN(String access_token) throws FileNotFoundException {
		File file = new File("C:\\Users\\itzik vaknin\\Downloads\\KashCourse\\zmani\\secrets.txt");
		PrintWriter pw = new PrintWriter(file);
	    pw.println(access_token);
	    pw.close();
		ACCESS_TOKEN = access_token;
	}
		
	public String getField_path() {
		return field_path;
	}
	public  String getCODE() {
		return CODE;
	}
	public  String getClientId() {
		return clientId;
	}
	public  String getRedirect_uri() {
		return redirect_uri;
	}
	public  String getResponse_type() {
		return response_type;
	}
	public  String getClient_secret() {
		return client_secret;
	}
	
	private static void loadAccessTokenFromFile() throws FileNotFoundException {
		File file = new File("C:\\Users\\itzik vaknin\\Downloads\\KashCourse\\zmani\\secrets.txt");
		Scanner sc = new Scanner(file);
		ACCESS_TOKEN = sc.nextLine();
		sc.close();
	}
	
	private static final char[] HEX_ARRAY = "0123456789abcdef".toCharArray();

	private static byte[] xor(byte[] a, byte[] b) {
	    byte[] result = new byte[a.length];
	    for (int i = 0; i < a.length; i++) {
	        result[i] = (byte) (a[i] ^ b[i]);
	    }
	    return result;
	}
	
	public static String toHexString(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for (int j = 0; j < bytes.length; j++) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = HEX_ARRAY[v >>> 4];
	        hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
	    }
	    return new String(hexChars);
	}
	
	public void generateAppsecret_proof() throws NoSuchAlgorithmException {
		
		//FIRST WAY
		byte[] appSecret = client_secret.getBytes();
		byte[] accessToken = ACCESS_TOKEN.getBytes();
		byte[] appSecretProof = MessageDigest.getInstance("SHA-256").digest(
		    xor(appSecret, accessToken));

		appsecret_proof = toHexString(appSecretProof);

	
//SECOND WAY
//	    Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
//	    SecretKeySpec secret_key = new SecretKeySpec(client_secret.getBytes(), "HmacSHA256");
//	    sha256_HMAC.init(secret_key);
//
//	    byte[] appsecret_proof = sha256_HMAC.doFinal(ACCESS_TOKEN.getBytes());
//	    String appsecret_proof_hex = javax.xml.bind.DatatypeConverter.printHexBinary(appsecret_proof).toLowerCase();

		
		
	}
}
