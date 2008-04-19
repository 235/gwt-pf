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
package net.pleso.framework.client.bl.auth;


/**
 * Represents base authorization logic functionality. This interface allows to
 * check if some {@link IAuth} instance is allowed to be used by current user
 * rights.
 * 
 * {@link AuthorizationProvider} class holds {@link IAuthorizer} instance to
 * allow generic user interface system check user rights on objects using
 * {@link AuthorizationProvider#isObjectAuthorized(IAuth)} static function.
 */

public interface IAuthorizer {

	/**
	 * @param authorizingObject
	 *            object to check authorization rights on
	 * @return <code>true</code> if current user has right to perform action
	 *         on authorizingObject
	 */
	boolean isObjectAuthorized(IAuth authorizingObject);
}
