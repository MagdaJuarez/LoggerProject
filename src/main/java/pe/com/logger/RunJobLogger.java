package pe.com.logger;

import java.util.HashMap;
import java.util.Map;

import pe.com.logger.util.LogDestinationType;
import pe.com.logger.util.LogType;

/**
 * Hello world!
 *
 */
public class RunJobLogger 
{
    public static void main( String[] args )
    {
		Map<String, String> dbParamsMap = new HashMap<String, String>();
		dbParamsMap.put("userName", "root");
		dbParamsMap.put("password", "1234AbcD");
		dbParamsMap.put("dbms", "mysql");
		dbParamsMap.put("serverName", "localhost");
		dbParamsMap.put("portNumber", "3306");
		dbParamsMap.put("dbName", "LoggerDB");
		dbParamsMap.put("logFileFolder", "/Users/magdajuarez/Documents/log");
		JobLogger.initializeVariables(dbParamsMap);
		printLogMessage("This is a warning message", LogDestinationType.LOG_TO_CONSOLE, LogType.WARNING);
		printLogMessage("This is an info message. ", LogDestinationType.LOG_TO_FILE, LogType.MESSAGE);
		printLogMessage("This is an error message. ", LogDestinationType.LOG_TO_DATABASE, LogType.ERROR);
    }

	private static void printLogMessage(String logMessage, LogDestinationType tipoDestinoLogParam, LogType tipoLogParam) {
		try {
			JobLogger.logMessage( logMessage, tipoLogParam, tipoDestinoLogParam);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
