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

import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 * Controller of AuthenticationException. Call RPC server side for user authentication.
 * Results return to {@link IAuthResultListener} implementation.
 * Contains {@link HashMap} of authenticated objects (to which user has access).
 * 
 * @author Scater
 *
 */
public class AuthController {
	
	/**
	 * Single AuthController instance.
	 */
	public static AuthController instance = new AuthController();
	
	/**
	 * Private contructor. Must be single AuthController instance.
	 */
	private AuthController() {}
	
	/**
	 * {@link IAuthResultListener} implementation.
	 */
	private IAuthResultListener resultListener = null;
	
	/**
	 * Return server-side RPC {@link AuthServiceAsync} implementation.
	 * User must declare URL in his application.
	 * Example: <servlet path='/auth' class='net.pleso.demo.server.auth.DemoAuthServiceImpl'/>.
	 * @return {@link AuthServiceAsync} implementation
	 */
	private AuthServiceAsync getService()
	{
		AuthServiceAsync calService = (AuthServiceAsync) GWT
		.create(AuthService.class);
		ServiceDefTarget target = (ServiceDefTarget) calService;
		String moduleRelativeURL = GWT.getModuleBaseURL() + "auth";
		target.setServiceEntryPoint(moduleRelativeURL);
		return calService;
	}
	
	/**
	 * Login user with login and password information.
	 * @param user - user login and password information
	 */
	public void loginUser(UserInfo user){
		 AuthServiceAsync serv = getService();
		 serv.loginUser(user, new AsyncCallback() {
				public void onFailure(Throwable caught) {
					if (resultListener != null)
						resultListener.errorLogin(caught);
				}
				public void onSuccess(Object result) {
					if (resultListener != null)
					{
						if (result != null)
						{
							String[] authClassesList = (String[]) result;
							
							authClassesDictionary.clear();
							for(int i = 0; i < authClassesList.length; i++) {
								authClassesDictionary.put(authClassesList[i], null);
							}
							
							resultListener.successLogin();
						}
						else
							resultListener.errorLogin(new RuntimeException("Access denied"));
					}
				}
			});
	}
	
	/**
	 * Logout user.
	 */
	public void logout(){
		 AuthServiceAsync serv = getService();
		 serv.logout(new AsyncCallback() {
				public void onFailure(Throwable caught) {
					if (resultListener != null)
						resultListener.errorLogout(caught);
				}
				public void onSuccess(Object result) {
					if (resultListener != null)
						resultListener.successLogout();
				}
			});	
	}
	
    
	/**
	 * Check is user authenticated.
	 */
	public void isAuth(){
		 AuthServiceAsync serv = getService();
		 serv.isAuth(new AsyncCallback() {
				public void onFailure(Throwable caught) {
					if (resultListener != null)
						resultListener.errorLogin(caught);
				}
				public void onSuccess(Object result) {
					if (resultListener != null)
					{
						if (result != null)
						{
							String[] authClassesList = (String[]) result;
							
							authClassesDictionary.clear();
							for(int i = 0; i < authClassesList.length; i++) {
								authClassesDictionary.put(authClassesList[i], null);
							}
							
							resultListener.successLogin();
						}
						else
							resultListener.errorLogin(new RuntimeException("Please enter login and password:"));
					}
				}
			});
	}
	
	/**
	 * Gets authenticated user information.
	 */
	public void getUserInfo(){
		 AuthServiceAsync serv = getService();
		 serv.getUserInfo(new AsyncCallback() {
				public void onFailure(Throwable caught) {
					if (resultListener != null)
						resultListener.errorLogin(caught);
				}
				public void onSuccess(Object result) {
					if (resultListener != null)
					{
						if (result != null)
							resultListener.setUserInfo((UserInfo) result);
						else
							resultListener.errorLogin(new RuntimeException("Please enter login and password:"));
					}
				}
			});
	}

	/**
	 * Gets {@link IAuthResultListener} implementation.
	 * @return {@link IAuthResultListener} implementation
	 */
	public IAuthResultListener getResultListener() {
		return resultListener;
	}

	/**
	 * Sets {@link IAuthResultListener} implementation.
	 * @param resultListener {@link IAuthResultListener} implementation
	 */
	public void setResultListener(IAuthResultListener resultListener) {
		this.resultListener = resultListener;
	}
	
	/**
	 * HashMap of authenticated objects (to which user has access).
	 */
	private HashMap authClassesDictionary = new HashMap();
	
	/**
	 * Return is {@link HashMap} of authenticated objects contains this className.
	 * @param className class name to check
	 * @return is {@link HashMap} of authenticated objects contains this className.
	 */
	public boolean isAuthClassesDictionaryContains(String className) {
		return this.authClassesDictionary.containsKey(className);
	}
}
