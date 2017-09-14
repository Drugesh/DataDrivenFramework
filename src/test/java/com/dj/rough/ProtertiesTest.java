package com.dj.rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ProtertiesTest {

	
	public static void main(String[] args) throws IOException {
		
		Properties config=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//properties//Config.properties");
		config.load(fis);
		System.out.println(config.getProperty("browser"));
		Properties or=new Properties();
		FileInputStream fis1=new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//properties//OR.properties");
		or.load(fis1);
		System.out.println(or.getProperty("bmlbtn"));
		
		
	}
}
