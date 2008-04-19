package net.pleso.demo.client.ui;

import java.util.Date;

import net.pleso.auth.client.AuthController;
import net.pleso.auth.client.UserInfo;
import net.pleso.demo.client.localization.Locale;

import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class LoginForm extends DialogBox implements ClickListener {
	
	private final VerticalPanel panel = new VerticalPanel();
	private final Grid grid = new Grid(3,2);
	private final Button btnLogin = new Button(Locale.constants().login());
	private final HTML statusMessage = new HTML();
	private final HTML loginLb = new HTML(Locale.constants().login());
	private final HTML passwordLb = new HTML(Locale.constants().password());
	private final TextBox loginBox = new TextBox();
	private final CheckBox savePassword = new CheckBox(Locale.constants().save_password());
	private final PasswordTextBox passwordBox = new PasswordTextBox();
	
	private boolean isPopup = false;
	
	public boolean isPopup() {
		return isPopup;
	}
	
	public void show(){
		super.show();
		isPopup = true;
	    center();
	    this.btnLogin.setEnabled(true);
	}
	
	public void hide(){
		super.hide();
		isPopup = false;
	}
	
	private final String loginCookieName = "login";
	private final String paswordCookieName = "password";
	
	private AuthController controller;

	public LoginForm(String statusMessage, AuthController controller){
		this.controller = controller;
		this.statusMessage.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		this.statusMessage.setText(statusMessage);
		
		setText(Locale.constants().login_form_caption());
		grid.setWidget(0, 0, loginLb);
		grid.setWidget(1, 0, passwordLb);
		grid.setWidget(0, 1, loginBox);
		grid.setWidget(1, 1, passwordBox);
		grid.setWidget(2, 1, savePassword);
		
		String login = Cookies.getCookie(loginCookieName);
		String password = Cookies.getCookie(paswordCookieName);
		
		if (login == null)
			login = "";
		if (password == null)
			password = "";
		
		savePassword.setChecked(login.length() > 0 || password.length() > 0);
		
		loginBox.setText(login);
		passwordBox.setText(password);
		
		btnLogin.addClickListener(this);
		
		panel.add(this.statusMessage);
		panel.add(grid);
		panel.add(btnLogin);
		panel.setSpacing(4);
		
		setWidget(panel);
	}
	
	public void setStatusMessage(String text){
		this.statusMessage.setText(text);
		this.btnLogin.setEnabled(true);
	}
	
	public String getUserName(){
		return loginBox.getText();
	}

	public void onClick(Widget sender) {
		if (sender == btnLogin)
		{
			Date currentDate = new Date();
			if (savePassword.isChecked()) {
				Date expires = new Date(currentDate.getYear() + 1, currentDate.getMonth(), currentDate.getDay());
				
				Cookies.setCookie(loginCookieName, loginBox.getText(), expires);
				Cookies.setCookie(paswordCookieName, passwordBox.getText(), expires);
			} else {
				Cookies.setCookie(loginCookieName, "", currentDate);
				Cookies.setCookie(paswordCookieName, "", currentDate);			
			}
			
			this.setStatusMessage(Locale.messages().loading());
			this.btnLogin.setEnabled(false);
			
			if (controller != null) {
				controller.loginUser(new UserInfo(loginBox.getText(), passwordBox.getText()));
			}
		}
	}

}
