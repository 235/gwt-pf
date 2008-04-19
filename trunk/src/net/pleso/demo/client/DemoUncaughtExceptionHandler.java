package net.pleso.demo.client;

import net.pleso.auth.client.IAuthResultListener;
import net.pleso.demo.client.dal.AuthException;
import net.pleso.framework.client.bl.exceptions.AsyncCallbackFailureException;
import net.pleso.framework.client.ui.windows.ErrorMessageWindow;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;

public class DemoUncaughtExceptionHandler implements UncaughtExceptionHandler {

	private IAuthResultListener loginResultListener = null;
	public DemoUncaughtExceptionHandler(IAuthResultListener loginResultListener)
	{
		this.loginResultListener = loginResultListener;
	}

	public void onUncaughtException(Throwable e) {
		e.printStackTrace();
		
		String m = "";
		Throwable exception = e;
		
		if (e instanceof AsyncCallbackFailureException) {
			exception = e.getCause();
			m = e.getMessage() + ". " + exception.getMessage();
		}
		
		if (exception instanceof AuthException) {
			if (loginResultListener != null){
				loginResultListener.errorLogin(exception);
			} else {
				m += exception.getMessage();
			}
		}
		else {
			if (m == "")
				m = exception.getMessage();
		}
		
		ErrorMessageWindow errorMessageWindow = new ErrorMessageWindow();
		errorMessageWindow.showError(m, e);
		GWT.log(m, e);
	}
}
