package absproj.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import com.google.gson.Gson;

public class EmailParams {
	public String basgeURI = "https://emailvalidation.abstractapi.com/v1/";
	public String valid_api_key = "1a6504f943504648858ee15dcdc2b977";
	public String inValid_api_key = "1a6504f94350464e15dcdc2b977!om";
	public String valid_email = "itzikv3@gmail.com";
	public String inValid_email = "itzikv3@gmail.c!om";
	public String emapty_email = "";

}
