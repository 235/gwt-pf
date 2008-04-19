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
package net.pleso.auth.server;

import java.util.Properties;

import net.pleso.auth.client.AuthenticationException;
import net.pleso.auth.client.UserInfo;

/**
 * Interface of auth results provider.
 * Check database or other source for authentication results.
 * Must be implemented externally.
 * 
 * @author Scater
 *
 */
public interface IAuthProvider {

	/**
	 * Returns a array of classes to which the user has access.
	 * Generates exception if user don't has access to data.
	 * 
	 * @param authProps authentication properties
	 * @return array of classes to which the user has access
	 * @throws AuthenticationException
	 */
	String[] getAuthClasses(Properties authProps) throws AuthenticationException;
	
	/**
	 * Fills connection properties into user information (database, host, etc.).
	 * @param user user information 
	 */
	void fillConnectionInfo(UserInfo user);
}
