package com.log.parser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Parser {

	public void logParser(String inFilePath, String csvFilePath)  throws IOException, ParseException{
		
		File file = new File(inFilePath);
		FileWriter fw = new FileWriter(csvFilePath);
		
		try {
			
			
			Scanner scan = new Scanner(file);

			String log = "";
			List<String> start = new ArrayList<String>();
			List<String> end = new ArrayList<>();
			String key1, key2;
			String parsedLog = "Log message	 ,	Start time 	, End time  , Time difference"+"\n";
			
			while(scan.hasNextLine()) {

				log = scan.nextLine().replace("templogger   WARNING ", "").replace("\"", "").replace(",", ".");

				boolean isStart = log.endsWith("starts");

				if(isStart == true) {
					start.add(log);
				}else {
					end.add(log);
				}
				}
			
			for(int i =0; i<start.size(); i++) {
				key1 = start.get(i).substring(25, 61);
				for(int j = 0 ; j<end.size(); j++) {
					key2 = end.get(j).substring(25, 61);
					if(key1.equals(key2)) {
						String stClrKey = start.get(i).replace(start.get(i).subSequence(25, 62), "");
						String endClrKey = end.get(j).replace(end.get(j).subSequence(25, 62), "");
						String stMsg = stClrKey.substring(25);
						String endMsg = endClrKey.substring(25);
						String stTime = stClrKey.substring(0, 23);
						String endTime = endClrKey.substring(0, 23);

						SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");
						Date date1 = format.parse(stTime.substring(11));
						Date date2 = format.parse(endTime.substring(11));
						
						
						double time = date2.getTime() - date1.getTime();
						
						
						double difference = ( time / 1000) % 60;
						
						
						String diff = String.valueOf(difference);

						
						String fLog = stMsg.concat(" - "+endMsg+" , ").concat(stTime+" , ").concat(endTime+" , ").concat(diff);
								
						parsedLog = parsedLog.concat(fLog+"\n");
						

					}
				}
			}

			System.out.println(parsedLog);
			fw.write(parsedLog);
			
			System.out.println("File has been created successfully");
		}catch(Exception e) {
			System.out.println(e);
		}finally {
			fw.close();
		}
		
	}
}
