package pe.com.logger;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import pe.com.logger.util.LogDestinationType;
import pe.com.logger.util.LogType;

public class JobLogger {

	private static Map dbParams;
	private static Logger logger;

	public static void initializeVariables(Map dbParamsMap) {
		logger = Logger.getLogger("MyLog");
		dbParams = dbParamsMap;
		if (dbParams == null) {
			throw new NullPointerException();
		}
	}

	public static void logMessage(String messageText, LogType logType, LogDestinationType logDestinationType)
			throws Exception {

		if (messageText == null || messageText.trim().isEmpty()) {
			return;
		}
		if (logDestinationType == null) {
			throw new Exception("Invalid configuration");
		}
		if ((logType == null)) {
			throw new Exception("Error or Warning or Message must be specified");
		}

		String logMessage = "";
		if (logType != null) {
			logMessage = logMessage + logType.getName() + " "
					+ DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + " " + messageText;
		}

		writeLogMessage(logType, logDestinationType, logMessage);

	}

	private static void writeLogMessage(LogType logType, LogDestinationType logDestinationType, String logMessage)
			throws IOException, SQLException {
		switch (logDestinationType) {
		case LOG_TO_FILE:
			logMessageToFile(logMessage);
			break;
		case LOG_TO_CONSOLE:
			logMessageToConsole(logMessage);
			break;
		case LOG_TO_DATABASE:
			logMessageToDatabase(logType, logMessage);
			break;
		}
	}

	private static void logMessageToDatabase(LogType tipoLogParam, String logMessage) throws SQLException {
		Properties connectionProps = new Properties();
		connectionProps.put("user", dbParams.get("userName"));
		connectionProps.put("password", dbParams.get("password"));
		String strConnection = "jdbc:" + dbParams.get("dbms") + "://" + dbParams.get("serverName")
		+ ":" + dbParams.get("portNumber") + "/" + dbParams.get("dbName");
		
		try(Connection connection = DriverManager.getConnection( strConnection, connectionProps);
			Statement stmt = connection.createStatement()){
			stmt.executeUpdate("insert into Log_Values (log_message, log_code) values ('" + logMessage + "', "
					+ String.valueOf(tipoLogParam.getCodigo()) + ")");
		}catch(SQLException sqle) {
			throw new SQLException();
		}
	}

	private static void logMessageToConsole(String logMessage) {
		ConsoleHandler consoleHandler = new ConsoleHandler();
		logger.addHandler(consoleHandler);
		logger.log(Level.INFO, logMessage);
		logger.removeHandler(consoleHandler);
	}

	private static void logMessageToFile(String logMessage) throws IOException {
		createLogFile();
		FileHandler fileHandler = new FileHandler(dbParams.get("logFileFolder") + "/logFile.txt");
		logger.addHandler(fileHandler);
		logger.log(Level.INFO, logMessage);
		logger.removeHandler(fileHandler);
	}

	private static void createLogFile() throws IOException {
		File logFile = new File(dbParams.get("logFileFolder") + "/logFile.txt");
		if (!logFile.exists()) {
			logFile.createNewFile();
		}
	}
}
