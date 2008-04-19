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

import com.google.gwt.user.client.rpc.RemoteService;

/**
 * Server-side RPC authentication service interface.
 * 
 * @author Scater
 *
 */
public interface AuthService extends RemoteService {

	/**
	 * Login user with login and password information. 
	 * @param user user information
	 * @return array of authenticated objects (to which user has access).
	 * @throws AuthenticationException
	 */
	String[] loginUser(UserInfo user) throws AuthenticationException;
	
	/**
	 * Logout user. 
	 */
	void logout(); 
	
	/**
	 * Check is user authenticated. Return null if user is not authenticated.
	 * @return array of authenticated objects (to which user has access).
	 * @throws AuthenticationException
	 */
	String[] isAuth() throws AuthenticationException;
	
	/**
	 * Gets authenticated user information.
	 * @return authenticated user information
	 * @throws AuthenticationException
	 */
	UserInfo getUserInfo() throws AuthenticationException;
}
