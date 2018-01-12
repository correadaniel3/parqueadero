package co.ceiba.parqueadero.exception;

public class VehiculoException extends Exception {

	private static final long serialVersionUID = 1L;

	public VehiculoException() {
		super();
	}
	
	public VehiculoException (String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message,cause, enableSuppression, writableStackTrace);
	}
	
	public VehiculoException (String message, Throwable cause) {
		super(message,cause);
	}
	
	public VehiculoException (String message) {
		super(message);
	}
	
	public VehiculoException(Throwable cause) {
		super(cause);
	}

}
