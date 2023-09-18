package controller.exception;

public class UsuarioException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public UsuarioException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
