package co.ceiba.parqueadero.exception;

public class ParqueaderoException extends Exception{

	private static final long serialVersionUID = 1L;

	public ParqueaderoException() {
		super();
	}
	
	public ParqueaderoException (String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message,cause, enableSuppression, writableStackTrace);
	}
	
	public ParqueaderoException (String message, Throwable cause) {
		super(message,cause);
	}
	
	public ParqueaderoException (String message) {
		super(message);
	}
	
	public ParqueaderoException(Throwable cause) {
		super(cause);
	}
}
