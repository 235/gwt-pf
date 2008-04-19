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
 * This exception is thrown when some unauthorized action could be performed.
 * Usually user controls block access to unauthorized actions by disabling or
 * hiding corresponging buttons or controls. Nevertheless if some unaurhorized
 * action has been started it should throw {@link AccessViolationException} to
 * prevent access violation.
 */
public class AccessViolationException extends FrameworkRuntimeException {

	private static final long serialVersionUID = -7416974256096274922L;

	/**
	 * Constructs a new access violation exception with <code>null</code> as
	 * its detail message.
	 */
	public AccessViolationException() {
	}

	/**
	 * Constructs a new runtime exception with the specified detail message.
	 * 
	 * @param message the detail message
	 */
	public AccessViolationException(String message) {
		super(message);
	}
}
