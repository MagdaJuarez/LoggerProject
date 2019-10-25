package pe.com.logger.util;

public enum LogDestinationType {
	
	LOG_TO_FILE("1"),
	LOG_TO_CONSOLE("2"),
	LOG_TO_DATABASE("3");
	
	private String codigo;
	
	private LogDestinationType(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

}
