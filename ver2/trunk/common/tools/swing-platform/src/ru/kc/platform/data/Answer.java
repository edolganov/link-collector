package ru.kc.platform.data;

public class Answer<T> {
	
	public final T result;
	public final boolean isException;
	public final Exception exception;
	
	public Answer(T result, boolean isException, Exception exception) {
		super();
		this.result = result;
		this.isException = isException;
		this.exception = exception;
	}
	

}
