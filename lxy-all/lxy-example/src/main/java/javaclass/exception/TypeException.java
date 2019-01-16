package javaclass.exception;

public class TypeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3167447850920996257L;

	public TypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public TypeException(String message) {
		super(message);
	}

	public TypeException(Throwable cause) {
		super(cause);
	}

	public TypeException(){
		super();
	}
}
