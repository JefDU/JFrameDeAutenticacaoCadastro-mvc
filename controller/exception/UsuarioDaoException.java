package controller.exception;

public class UsuarioDaoException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public UsuarioDaoException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
