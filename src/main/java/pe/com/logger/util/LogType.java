package pe.com.logger.util;

public enum LogType {
	
	MESSAGE("1", "Message"),
	ERROR("2", "Error"),
	WARNING("3", "Warning");
	
	private String codigo;
	private String name;
	
	private LogType(String codigo, String name) {
		this.codigo = codigo;
		this.name = name;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getName() {
		return name;
	}

}
