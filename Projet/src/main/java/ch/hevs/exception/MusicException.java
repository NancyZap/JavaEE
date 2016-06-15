package ch.hevs.exception;

public class MusicException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public MusicException() {
		super();
	}

	public MusicException(String arg0) {
		super(arg0);
	}

	public MusicException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public MusicException(Throwable arg0) {
		super(arg0);
	}

}
