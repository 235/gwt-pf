package net.pleso.demo.client.ui;

import net.pleso.demo.client.demo;
import net.pleso.demo.client.localization.Locale;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Widget;

public class AdminPanelHeader extends Composite implements ClickListener{
	
	private HorizontalPanel panel = new HorizontalPanel();
	private HTML header = new HTML();
	private HTML userText = new HTML();
	private HorizontalPanel userPanel = new HorizontalPanel();
	private Hyperlink logout = new Hyperlink();
	
	public AdminPanelHeader(){
		header.setText(Locale.constants().admin_panel_caption());
		header.setStyleName("header-text");
		panel.add(header);
		panel.setCellHorizontalAlignment(header, HorizontalPanel.ALIGN_LEFT);
		
		userText.setText(Locale.messages().admin_panel_welcome(""));
		userText.setStyleName("user-text");
		logout.setText(Locale.constants().logout());
		logout.setStyleName("user-logout");
		logout.addClickListener(this);
		
		userPanel.add(userText);
		userPanel.add(logout);
		
		userPanel.setStyleName("admin-panel-header-user-panel");
		
		panel.add(userPanel);
		panel.setCellHorizontalAlignment(userPanel, HorizontalPanel.ALIGN_RIGHT);
		
		initWidget(panel);
		setStyleName("admin-panel-header");
	}
	
	public void setUserName(String userName) {
		userText.setText(Locale.messages().admin_panel_welcome(userName));
	}

	public void onClick(Widget sender) {
		if (sender == logout) {
			demo.getAuthController().logout();
		}
	}

}
