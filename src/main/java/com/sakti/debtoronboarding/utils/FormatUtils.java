package com.sakti.debtoronboarding.utils;

import org.apache.commons.lang3.RandomStringUtils;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtils {

	public static Timestamp getCurrentTimestamp() {
		
		 Date date= new Date();
		 long time = date.getTime();
		 Timestamp ts = new Timestamp(time);
		 return ts;
		 
	}
	
	public static long getCurrentTimestampInLong() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp.getTime();
	}
	
	public static String getRandomString() {
		int length = 8;
	    boolean useLetters = true;
	    boolean useNumbers = true;
	    String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
	 
	    return generatedString;
	}
	
	public static Date getDateFromString(String strDate) throws ParseException {
		
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);  
		return date;
	}
}
