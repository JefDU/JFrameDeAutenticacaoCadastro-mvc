package controller.exception;

public class RegraDeNegocioException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public RegraDeNegocioException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
