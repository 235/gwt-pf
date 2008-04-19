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
 * This class holds static field with preinitialized {@link IAuthorizer}
 * instance. This field is initialized by entry poin class before user
 * authentication. After initialization all generic user interface controls can
 * use static function {@link #isObjectAuthorized(IAuth)} to check whether some
 * {@link IAuth} object instance can be performed by current user rights.
 * 
 * All objects that represent some database action implements {@link IAuth}
 * interface, so each data effective action must be checked for current user
 * authorization rights.
 */
public class AuthorizationProvider {

	private static IAuthorizer authorizer = null;

	private AuthorizationProvider() {
	}

	/**
	 * Initializes static field with IAuthorizer instance.
	 * 
	 * This initialization must be performed before user authentication to make
	 * authorization system work properly.
	 * 
	 * @param authorizer
	 *            {@link IAuthorizer} instance
	 */
	public static void initAuthorizer(IAuthorizer authorizer) {
		AuthorizationProvider.authorizer = authorizer;
	}

	/**
	 * Checks if {@link IAuth} object instance is allowed to be performed by
	 * rights of current user.
	 * 
	 * This function is used by user interface controls to check access rights
	 * for used objects.
	 * 
	 * @param authorizingObject
	 *            object to check authorization rights on
	 * @return <code>true</code> if current user has right to perform actions
	 *         on specified object
	 */
	public static boolean isObjectAuthorized(IAuth authorizingObject) {
		if (authorizer != null)
			return authorizer.isObjectAuthorized(authorizingObject);
		else
			// if user not implemented authorizer than he don't need authorization - so all objects are visible
			return true;
	}
}
