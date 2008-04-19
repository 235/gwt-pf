package net.pleso.demo.client.bl.auth;

import net.pleso.framework.client.bl.auth.IAuth;
import net.pleso.framework.client.bl.auth.IAuthorizer;

public class Authorizer implements IAuthorizer {

	public boolean isObjectAuthorized(IAuth authorizingObject) {
		return true;
		//return demo.getAuthController().isAuthClassesDictionaryContains(GWT.getTypeName(authorizingObject));
	}
}
