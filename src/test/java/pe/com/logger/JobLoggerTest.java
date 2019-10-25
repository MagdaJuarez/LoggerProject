package pe.com.logger;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import pe.com.logger.util.LogDestinationType;
import pe.com.logger.util.LogType;

public class JobLoggerTest {
	
	private Map<String, String> dbParamsMap = new HashMap<String, String>();
	
	@Before
	public void init() {
		dbParamsMap.put("userName", "root");
		dbParamsMap.put("password", "1234AbcD");
		dbParamsMap.put("dbms", "mysql");
		dbParamsMap.put("serverName", "localhost");
		dbParamsMap.put("portNumber", "3306");
		dbParamsMap.put("logFileFolder", "/Users/magdajuarez/Documents/log");
		JobLogger.initializeVariables(dbParamsMap);
	}
	

	@Test(expected = NullPointerException.class)
	public void whenInitilizeVariablesNullShouldThrowsException() {
		dbParamsMap = null;
		JobLogger.initializeVariables(dbParamsMap);		
	}
	
	@Test(expected= Exception.class)
	public void logTypeNullShouldThrowException() throws Exception {
		String messageText = "This is a info message.";
		JobLogger.logMessage(messageText, null, LogDestinationType.LOG_TO_FILE);
	}

	@Test(expected= Exception.class)
	public void logDestinationTypeNullShouldThrowException() throws Exception {
		String messageText = "This is a warning message.";
		JobLogger.logMessage(messageText, LogType.WARNING, null);
	}

	@Test(expected= Exception.class)
	public void logTypeAndLogDestinationTypeNullShouldThrownException() throws Exception {
		String messageText = "This is a warning message.";
		JobLogger.logMessage(messageText, null, null);
	}
}
