package com.log.parser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) throws IOException, ParseException {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the log file path you want to parse, \nExample: C:\\Users\\Rami\\OneDrive\\Desktop\\api.log\nthen press Enter");
		String inPath = scan.nextLine();
		System.out.println("Enter the path where you want to save the parsed log csv file and name it, \nExample: C:\\Users\\api-parsed\nthen press Enter");
		String outPath = scan.nextLine().concat(".csv");
		System.out.println(outPath);
		
		Parser ps = new Parser();
		ps.logParser(inPath, outPath);
		
	}
}
