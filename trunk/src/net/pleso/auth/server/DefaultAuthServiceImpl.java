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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.pleso.auth.client.AuthService;
import net.pleso.auth.client.AuthenticationException;
import net.pleso.auth.client.UserInfo;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * Default implementation of {@link AuthService}.
 * Use {@link HttpSession} like authentication parameters container.
 * Extend this implementation in your application for {@link IAuthProvider} initialization.
 * 
 * @author Scater
 *
 */
public class DefaultAuthServiceImpl extends RemoteServiceServlet implements AuthService {
	
	private static final long serialVersionUID = 1L;
	private static final String authKey = new String("Auth");

	/* (non-Javadoc)
	 * @see net.pleso.auth.client.AuthService#loginUser(net.pleso.auth.client.UserInfo)
	 */
	public String[] loginUser(UserInfo user) throws AuthenticationException {
		getAuthProvider().fillConnectionInfo(user);
		
		Properties authProps = new Properties();
		authProps.setProperty("host", user.getHost());
		authProps.setProperty("port", user.getPort());
		authProps.setProperty("database", user.getDatabase());
		authProps.setProperty("login", user.getLogin());
		authProps.setProperty("password", user.getPassword());
		
		// получаем список всех класов которые доступны пользователю
		// если здесь згенерируется ошибка и вернется null, то пользователь не может быть залогирован
		String[] authClasses = getAuthProvider().getAuthClasses(authProps);
		
		if (authClasses != null){
			// все прошло успешно. кладем Properties authProps в сесию
			this.getThreadLocalRequest().getSession().setAttribute(authKey, authProps);
			// устанавливаем время сессии 
			this.getThreadLocalRequest().getSession().setMaxInactiveInterval(7200);
			// возвращаем список автентифицированых обьектов
			return authClasses;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see net.pleso.auth.client.AuthService#logout()
	 */
	public void logout() {
		this.getThreadLocalRequest().getSession().invalidate();		
	}

	/* (non-Javadoc)
	 * @see net.pleso.auth.client.AuthService#isAuth()
	 */
	public String[] isAuth() throws AuthenticationException {
		Properties authProps = (Properties) this.getThreadLocalRequest().getSession().getAttribute(authKey);
		if (authProps != null) {
			return getAuthProvider().getAuthClasses(authProps);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see net.pleso.auth.client.AuthService#getUserInfo()
	 */
	public UserInfo getUserInfo() throws AuthenticationException {
		Properties authProps = (Properties) this.getThreadLocalRequest()
				.getSession().getAttribute(authKey);
		UserInfo user = null;
		if (authProps != null) {
			user = new UserInfo();
			user.setHost(authProps.getProperty("host"));
			user.setPort(authProps.getProperty("port"));
			user.setDatabase(authProps.getProperty("database"));
			user.setLogin(authProps.getProperty("login"));
			// password is not send. why ?
		}
		return user;
	}
	
	/**
	 * Statically gets authentication properties by external {@link HttpServletRequest} instance.
	 * Use DefaultAuthServiceImpl.getAuthProps(this.getThreadLocalRequest()) in your RPC servlet.
	 * 
	 * @param request {@link HttpServletRequest} instance from external RPC servlet
	 * @return authentication properties
	 * @throws AuthenticationException
	 */
	public static Properties getAuthProps(HttpServletRequest request) throws AuthenticationException {
		Properties authProps = (Properties) request.getSession().getAttribute(DefaultAuthServiceImpl.authKey);
		if (authProps == null){
			throw new AuthenticationException("Session timeout");
		}
		return authProps;
	}
	
	/**
	 * Internal getter of {@link IAuthProvider} implementation.
	 * Checks authProviderImpl for null value.
	 * 
	 * @return {@link IAuthProvider} implementation.
	 * @throws AuthenticationException
	 */
	private IAuthProvider getAuthProvider() throws AuthenticationException {
		if (authProviderImpl != null)
			return authProviderImpl;
		else
			throw new AuthenticationException("IAuthProvider implementation not found");
	}

	/**
	 * External {@link IAuthProvider} implementation.
	 */
	private IAuthProvider authProviderImpl;
	
	/**
	 * Gets {@link IAuthProvider} implementation.
	 * @return  {@link IAuthProvider} implementation
	 */
	public IAuthProvider getAuthProviderImpl() {
		return authProviderImpl;
	}

	/**
	 * Sets {@link IAuthProvider} implementation.
	 * @param authProviderImpl {@link IAuthProvider} implementation
	 */
	public void setAuthProviderImpl(IAuthProvider authProviderImpl) {
		this.authProviderImpl = authProviderImpl;
	}

}
