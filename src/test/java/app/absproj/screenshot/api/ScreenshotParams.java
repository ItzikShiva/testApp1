package app.absproj.screenshot.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import com.google.gson.Gson;

public class ScreenshotParams {
	//TODO -ask Hod, the two different urls bring different errors, what you suggest
	public String basgeURI = "https://screenshot.abstractapi.com/v1/";
	public String valid_api_key = "9a1aac928f9d495e94f4033d7db32d57";
	public String inValid_api_key = "1a6504f94350464e15dcdc2b977!om";
	public String valid_url = "https://news.ycombinator.com";
	public String inValid_url = "itzikv3@gmail.c!om";
	public String inValid_url_page = "https://news.ycombinator";
	public String empty_url = "";

}
