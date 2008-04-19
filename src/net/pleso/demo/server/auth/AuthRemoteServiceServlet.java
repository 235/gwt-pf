package net.pleso.demo.server.auth;

import java.util.Properties;

import net.pleso.auth.client.AuthenticationException;
import net.pleso.demo.client.dal.AuthException;
import net.pleso.demo.client.dal.BaseException;
import net.pleso.demo.client.dal.ServerException;
import net.pleso.demo.server.SqlMapManager;
import net.pleso.demo.server.SqlMapperException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ibatis.sqlmap.client.SqlMapClient;

public class AuthRemoteServiceServlet extends RemoteServiceServlet {
	
	private static final long serialVersionUID = 1L;

	protected SqlMapClient getSqlMapClient() throws BaseException {
		Properties authProps = null;
		try {
			authProps = DemoAuthServiceImpl.getAuthProps(this.getThreadLocalRequest());
		} catch (AuthenticationException e1) {
			throw new AuthException(e1.getMessage(), e1.getCause());
		}
		
		try {
			return SqlMapManager.getSqlMapClient(authProps);
		}
		catch (SqlMapperException e){
			throw new ServerException(e.getMessage(), e);
		}
	}

}
