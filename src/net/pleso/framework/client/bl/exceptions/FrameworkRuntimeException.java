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
package net.pleso.framework.client.bl.exceptions;

/**
 * Represents base framework runtime exception. All framework exceptions extend
 * this class. This helps outside unrecognized exception handler to recognize
 * framework exceptions.
 */
public class FrameworkRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 5202873329776391004L;

	/**
	 * Constructs a new framework runtime exception with <code>null</code> as
	 * its detail message.
	 */
	public FrameworkRuntimeException() {
	}

	/**
	 * Constructs a new framework runtime exception with the specified detail
	 * message.
	 * 
	 * @param message
	 *            the detail message
	 * @param cause
	 *            the cause
	 */
	public FrameworkRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructs a new framework runtime exception with the specified detail
	 * message and cause.
	 * 
	 * @param message
	 *            the detail message
	 */
	public FrameworkRuntimeException(String message) {
		super(message);
	}

	/**
	 * Constructs a new runtime exception with the specified cause and a detail
	 * message of <code>(cause==null ? null : cause.toString()) </code> (which
	 * typically contains the class and detail message of cause).
	 * 
	 * @param cause
	 *            the cause
	 */
	public FrameworkRuntimeException(Throwable cause) {
		super(cause);
	}

}
