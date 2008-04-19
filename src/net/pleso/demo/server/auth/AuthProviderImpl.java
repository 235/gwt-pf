package net.pleso.demo.server.auth;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import net.pleso.auth.client.AuthenticationException;
import net.pleso.auth.client.UserInfo;
import net.pleso.auth.server.IAuthProvider;
import net.pleso.demo.server.SqlMapManager;
import net.pleso.demo.server.SqlMapperException;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;

public class AuthProviderImpl implements IAuthProvider {

	public String[] getAuthClasses(Properties authProps) throws AuthenticationException {
		com.ibatis.common.logging.LogFactory.selectLog4JLogging();
		
		if (authProps == null)
			return null;
		SqlMapClient sqlMapper = null;
		// creating SqlMapper. there is no database access here
		try {
			sqlMapper = SqlMapManager.getSqlMapClient(authProps);
		}
		catch (SqlMapperException e){
			e.printStackTrace();
			throw new AuthenticationException(e.getMessage(), e);
		}
		// execute function auth(). if user has no rights or database is not avaliable - get error
        try {
        	List li = sqlMapper.queryForList("auth");			
        	return (String[])li.toArray(new String[li.size()]);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AuthenticationException(e.getMessage(), e);
		}	
	}

	public void fillConnectionInfo(UserInfo user) {
		Properties props;
		try {
			props = Resources.getResourceAsProperties("net/pleso/demo/server/connection.properties");
			user.setHost(props.getProperty("host"));
			user.setPort(props.getProperty("port"));
			user.setDatabase(props.getProperty("database"));
		} catch (IOException e) {
			new AuthenticationException("Connection properties file was not found");
		}
	}

}
