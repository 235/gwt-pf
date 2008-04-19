package net.pleso.demo.server.auth;

import net.pleso.auth.client.AuthService;
import net.pleso.auth.server.DefaultAuthServiceImpl;

public class DemoAuthServiceImpl extends DefaultAuthServiceImpl implements AuthService {
	
	private static final long serialVersionUID = 1L;
	
	public DemoAuthServiceImpl(){
		setAuthProviderImpl(new AuthProviderImpl());
	}

}
