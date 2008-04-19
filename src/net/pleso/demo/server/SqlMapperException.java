package net.pleso.demo.server;

import net.pleso.demo.client.dal.ServerException;

public class SqlMapperException extends ServerException {
	
	private static final long serialVersionUID = 1L;

	public SqlMapperException() {
	    super();
	  }
	
	public SqlMapperException(String s) {
		    super(s, null);
		  }
	
    public SqlMapperException(String s, Throwable cause) {
		    super(s, cause);
		  }

}
