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
package net.pleso.framework.client.ui;

import net.pleso.framework.client.bl.exceptions.FrameworkRuntimeException;

/**
 * This exception is thrown by framework user interface component when it can
 * not support some business logic class implementation.
 */
public class NotImplementedFeatureException extends FrameworkRuntimeException {

	private static final long serialVersionUID = -7140979746752710966L;

	/**
	 * Constructs a new exception with the specified detail message.
	 * 
	 * @param message
	 *            the detail message
	 */
	public NotImplementedFeatureException(String message) {
		super(message);
	}

	/**
	 * Constructs a new exception with the specified cause and a detail message
	 * of <code>(cause==null ? null : cause.toString()) </code> (which
	 * typically contains the class and detail message of cause).
	 * 
	 * @param cause
	 *            the cause
	 */
	public NotImplementedFeatureException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructs a new runtime exception with the specified detail message.
	 * 
	 * @param message
	 *            the detail message
	 * @param cause
	 *            the cause
	 */
	public NotImplementedFeatureException(String message, Throwable cause) {
		super(message, cause);
	}

}
