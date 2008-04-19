/*
 * Copyright 2007 Pleso.net
 * 
 * Licensed under the GNU Lesser General Public License, Version 2.1 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.gnu.org/licenses/lgpl.html
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package net.pleso.auth.client;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Authentication exception class.
 * 
 * @author Scater
 *
 */
public class AuthenticationException extends Exception implements IsSerializable {

	private static final long serialVersionUID = 1L;
	
	protected String message = new String();
	
	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Constructor.
	 */
	public AuthenticationException() {
	    super();
	  }
	
	/**
	 * Constructor with message text.
	 * @param s message text
	 */
	public AuthenticationException(String s) {
		    super(s, null);
		    this.message = s;
		  }
	
    /**
     * Constructor with message text and {@link Throwable} instance.
     * @param s message text
     * @param cause {@link Throwable} instance
     */
    public AuthenticationException(String s, Throwable cause) {
		    super(s, cause);
		    this.message = s;
		  }
}
