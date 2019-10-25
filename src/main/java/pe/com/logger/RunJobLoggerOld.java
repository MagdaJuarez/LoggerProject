package pe.com.logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class RunJobLoggerOld 
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
		JobLoggerOld jobLoggerOld1 = new JobLoggerOld(true, false, false,
				true, false, false, dbParamsMap);
		printLogMessage("This is a info message. ", true, false, false);
		JobLoggerOld jobLoggerOld2 = new JobLoggerOld(false, true, false,
				false, true, false, dbParamsMap);
		printLogMessage("This is a warning message. ", false, true, false);
		JobLoggerOld jobLoggerOld3 = new JobLoggerOld(false, false, true,
				false, false, true, dbParamsMap);
		printLogMessage("This is a error message. ", false, false, true);
    }

	private static void printLogMessage(String logMessage, boolean message, boolean warning, boolean error) {
		try {
			JobLoggerOld.LogMessage( logMessage, message, warning, error);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
