package net.pleso.demo.client;

import net.pleso.auth.client.AuthController;
import net.pleso.auth.client.IAuthResultListener;
import net.pleso.auth.client.UserInfo;
import net.pleso.demo.client.bl.auth.Authorizer;
import net.pleso.demo.client.bl.main_menu.MainMenu;
import net.pleso.demo.client.dal.AuthException;
import net.pleso.demo.client.ui.AdminPanelHeader;
import net.pleso.demo.client.ui.LoginForm;
import net.pleso.framework.client.bl.auth.AuthorizationProvider;
import net.pleso.framework.client.ui.custom.CustomMenu;
import net.pleso.framework.client.ui.windows.TabbedSliderManager;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class demo implements EntryPoint, IAuthResultListener {
	
	public static AuthController getAuthController(){
		return AuthController.instance;
	}
	
	private static LoginForm loginForm = null;
	private CustomMenu mainMenu = null;
	private AdminPanelHeader header = null;
	TabbedSliderManager windowsManager = TabbedSliderManager.getInstance();
	
	private static DemoUncaughtExceptionHandler unknownErrorListener = null; 

	public static DemoUncaughtExceptionHandler getUnknownErrorListener() {
		return unknownErrorListener;
	}

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		getAuthController().setResultListener(this);
		getAuthController().isAuth();
		unknownErrorListener = new DemoUncaughtExceptionHandler(this);
		AuthorizationProvider.initAuthorizer(new Authorizer());
		
		GWT.setUncaughtExceptionHandler(unknownErrorListener);
	}
	
	public void errorLogin(Throwable caught) {
		if (loginForm == null)
			loginForm = new LoginForm(caught.getMessage(), getAuthController());
		if (loginForm.isPopup())
			loginForm.setStatusMessage(caught.getMessage());
		else
			loginForm.show();
	}

	public void successLogin() {
		if (loginForm != null) {
			loginForm.hide();
		}
		
		if (mainMenu == null)
		{
			header = new AdminPanelHeader();
			RootPanel.get("app-header").add(header);
			mainMenu = new CustomMenu(new MainMenu().getMainMenu());
			RootPanel.get("menu").add(mainMenu);
			RootPanel.get("windows-panel").add(windowsManager);
		}
		
		if (loginForm != null) {
			header.setUserName(loginForm.getUserName());}
		else {getAuthController().getUserInfo();}
	}
	
	public void setUserInfo(UserInfo info) {
		header.setUserName(info.getLogin());
	}

	public void errorLogout(Throwable caught) {
		throw new AuthException(caught.getMessage(), caught);
	}

	public void successLogout() {
		reloadPage();
	}
	
    private native void reloadPage() /*-{
    	$wnd.location.reload();
	}-*/;
}
