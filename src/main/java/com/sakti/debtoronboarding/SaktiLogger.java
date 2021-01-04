package com.sakti.debtoronboarding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class SaktiLogger {
	
	public void addNewException(String log) throws Exception {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");  
	    Date date = new Date();  
	    
		Logger logger = Logger.getLogger("Exception");
		Handler handler = new FileHandler(String.format("exception_%s", formatter.format(date)));
		logger.addHandler(handler);
		
		logger.info(log);
		
	}

}
