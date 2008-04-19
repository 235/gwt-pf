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

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Represents wrapper for server exceptions. When server returns exception
 * through {@link AsyncCallback#onFailure(Throwable)} framework should rethrow
 * {@link Throwable} as {@link RuntimeException}. Usually it looks as follows:
 * 
 * <pre>
 * public void onFailure(Throwable caught) {
 * 	 throw new AsyncCallbackFailureException(&quot;Error&quot;, caught);
 * }
 * </pre>
 */
public class AsyncCallbackFailureException extends FrameworkRuntimeException {

	private static final long serialVersionUID = 2843798762174355222L;

	/**
	 * Constructs a new exception with the specified detail message and cause.
	 * 
	 * @param message
	 *            the detail message
	 * @param cause
	 *            the cause
	 */
	public AsyncCallbackFailureException(String message, Throwable cause) {
		super(message, cause);
	}

}
