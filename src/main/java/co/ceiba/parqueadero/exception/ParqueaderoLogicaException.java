package co.ceiba.parqueadero.exception;

public class ParqueaderoLogicaException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ParqueaderoLogicaException() {
		super();
	}
	
	public ParqueaderoLogicaException (String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message,cause, enableSuppression, writableStackTrace);
	}
	
	public ParqueaderoLogicaException (String message, Throwable cause) {
		super(message,cause);
	}
	
	public ParqueaderoLogicaException (String message) {
		super(message);
	}
	
	public ParqueaderoLogicaException(Throwable cause) {
		super(cause);
	}
}
