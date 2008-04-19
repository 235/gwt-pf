package net.pleso.demo.client.dal;

import com.google.gwt.user.client.rpc.IsSerializable;

public class BaseException extends RuntimeException implements IsSerializable {
	
	protected static final long serialVersionUID = 1L;
	
	protected String message = new String();
	
	public String getMessage() {
		return message;
	}
	
	public BaseException() {
	    super();
	  }
	
	public BaseException(String s) {
		    super(s, null);
		    this.message = s;
		  }
	
    public BaseException(String s, Throwable cause) {
		    super(s, cause);
		    this.message = s;
		  }

}
