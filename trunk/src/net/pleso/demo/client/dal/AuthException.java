package net.pleso.demo.client.dal;


import com.google.gwt.user.client.rpc.IsSerializable;

public class AuthException extends BaseException implements IsSerializable {
	
	
	private static final long serialVersionUID = 1L;

	public AuthException() {
	    super();
	  }
	
	public AuthException(String s) {
		    super(s, null);
		    this.message = s;
		  }
	
    public AuthException(String s, Throwable cause) {
		    super(s, cause);
		    this.message = s;
		  }
}
