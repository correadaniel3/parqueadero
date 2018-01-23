package co.ceiba.parqueadero.exception;

import lombok.Generated;

/**
 * Maneja las excepciones de la capa repository para parqueadero
 * @author daniel.correa
 *
 */
public class ParqueaderoException extends Exception{

	private static final long serialVersionUID = 1L;
	
	@Generated
	public ParqueaderoException() {
		super();
	}
	
	@Generated
	public ParqueaderoException (String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message,cause, enableSuppression, writableStackTrace);
	}
	
	@Generated
	public ParqueaderoException (String message, Throwable cause) {
		super(message,cause);
	}
	
	@Generated
	public ParqueaderoException (String message) {
		super(message);
	}
	
	@Generated
	public ParqueaderoException(Throwable cause) {
		super(cause);
	}
}
