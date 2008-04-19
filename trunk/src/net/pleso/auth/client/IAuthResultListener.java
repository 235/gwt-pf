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

/**
 * Interface of class-handler of authentication requests.
 * {@link AuthController} calls methods of this interface implementation by results of requests to server-side.
 * {@link AuthController} needs this interface external implementation.
 * 
 * @author Scater
 *
 */
public interface IAuthResultListener {
	
	/**
	 * Calls when was exception in login.
	 * 
	 * @param caught exception instance
	 */
	void errorLogin(Throwable caught);
	
	/**
	 * Calls on success login.
	 */
	void successLogin();
	
	/**
	 * Calls when user information was received.
	 * 
	 * @param info received user information
	 */
	void setUserInfo(UserInfo info);
	
	/**
	 * Calls when was exception in logout.
	 * 
	 * @param caught exception instance
	 */
	void errorLogout(Throwable caught);
	
	/**
	 * Calls on success logout.
	 */
	void successLogout();
}
