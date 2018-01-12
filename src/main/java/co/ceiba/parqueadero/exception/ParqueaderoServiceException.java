package co.ceiba.parqueadero.exception;

public class ParqueaderoServiceException extends Exception {
	private static final long serialVersionUID = 1L;

	public ParqueaderoServiceException() {
		super();
	}
	
	public ParqueaderoServiceException (String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message,cause, enableSuppression, writableStackTrace);
	}
	
	public ParqueaderoServiceException (String message, Throwable cause) {
		super(message,cause);
	}
	
	public ParqueaderoServiceException (String message) {
		super(message);
	}
	
	public ParqueaderoServiceException(Throwable cause) {
		super(cause);
	}
}
