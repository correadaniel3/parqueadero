package co.ceiba.parqueadero.exception;

import lombok.Generated;

/**
 * Maneja las excepciones de la capa service
 * @author daniel.correa
 *
 */
@Generated
public class ParqueaderoServiceException extends Exception {
	private static final long serialVersionUID = 1L;
	
	@Generated
	public ParqueaderoServiceException() {
		super();
	}
	
	@Generated
	public ParqueaderoServiceException (String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message,cause, enableSuppression, writableStackTrace);
	}
	
	@Generated
	public ParqueaderoServiceException (String message, Throwable cause) {
		super(message,cause);
	}
	
	@Generated
	public ParqueaderoServiceException (String message) {
		super(message);
	}
	
	@Generated
	public ParqueaderoServiceException(Throwable cause) {
		super(cause);
	}
}
