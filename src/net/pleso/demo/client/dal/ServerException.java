package net.pleso.demo.client.dal;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ServerException extends BaseException implements IsSerializable {
	
	public ServerException() {
	    super();
	  }
	
	public ServerException(String s) {
		    super(s, null);
		    this.message = s;
		  }
	
    public ServerException(String s, Throwable cause) {
		    super(s, cause);
		    this.message = s;
		  }
}
