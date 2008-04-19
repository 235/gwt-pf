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
package net.pleso.framework.client.ui.windows;

import net.pleso.framework.client.localization.FrameworkLocale;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Represents error message window widget.
 * 
 * <h3>CSS Style Rules</h3>
 * <ul>
 * <li>.pf-error-message {widget itself}</li>
 * <li>.pf-error-message-scroll-stack-open {scroll panel with stack trace when
 * it opened}</li>
 * <li>.pf-error-message-scroll-stack-closed {scroll panel with stack trace
 * when it closed}</li>
 * <li>.pf-error-message-error-text {error text}</li>
 * </ul>
 * 
 * @author Scater
 * 
 */
public class ErrorMessageWindow extends DialogBox implements ClickListener {

	private HTML caption = new HTML();

	private Button btnMore = new Button();

	private Button btnClose = new Button(FrameworkLocale.constants()
			.close_button_caption());

	private HTML stackTrace = new HTML();

	private ScrollPanel scrollStackTrace = new ScrollPanel(stackTrace);

	private VerticalPanel panel = new VerticalPanel();

	private HorizontalPanel buttonPanel = new HorizontalPanel();

	/**
	 * Constructs a newly allocated ErrorMessageWindow object.
	 */
	public ErrorMessageWindow() {
		this.setText(FrameworkLocale.constants().error_box_caption());

		this.caption.setStyleName("pf-error-message-error-text");
		this.panel.add(this.caption);

		this.btnMore.setText(FrameworkLocale.constants()
				.error_box_details_closed());
		this.btnMore.addClickListener(this);
		this.buttonPanel.add(this.btnMore);
		this.buttonPanel.setWidth("100%");

		this.btnClose.addClickListener(this);
		this.buttonPanel.add(this.btnClose);

		this.panel.add(this.buttonPanel);

		this.scrollStackTrace
				.setStyleName("pf-error-message-scroll-stack-close");
		this.panel.add(this.scrollStackTrace);
		this.stackTrace.setVisible(false);

		this.setStyleName("pf-error-message");

		this.setWidget(this.panel);
	}

	/**
	 * Prints stack trace of {@link Throwable} into specified
	 * {@link StringBuffer}.
	 * 
	 * @param sb
	 *            a {@link StringBuffer} to hold the result
	 * @param e
	 *            a {@link Throwable} to be printed
	 */
	private void printStackTrace(StringBuffer sb, Throwable e) {
		sb.append(e.toString());
		sb.append("<br />");
		StackTraceElement[] stes = e.getStackTrace();
		for (int i = 0; i < stes.length; i++) {
			sb.append("&nbsp;&nbsp;&nbsp;&nbsp;at ");
			sb.append(stes[i].getMethodName());
			sb.append(" (");
			sb.append(stes[i].getFileName());
			sb.append(")<br />");

		}
	}

	/**
	 * Show window with specified {@link Throwable} with friendly user message.
	 * 
	 * @param message
	 *            a {@link String} with error message text
	 * @param cause
	 *            a {@link Throwable} to be shown in details
	 */
	public void showError(String message, Throwable cause) {
		this.caption.setText(message);

		StringBuffer sb = new StringBuffer();
		while (cause != null) {
			printStackTrace(sb, cause);
			cause = cause.getCause();
		}

		this.stackTrace.setHTML(sb.toString());

		this.show();
	}

	/**
	 * Show window with specified {@link Throwable} error message.
	 * 
	 * @param e
	 *            a {@link Throwable} to be shown in details
	 */
	public void showError(Throwable e) {
		caption.setHTML(e.getMessage());
		this.show();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user.client.ui.Widget)
	 */
	public void onClick(Widget sender) {
		if (sender == this.btnClose) {
			this.hide();
		} else if (sender == this.btnMore) {
			this.stackTrace.setVisible(!this.stackTrace.isVisible());
			if (this.stackTrace.isVisible()) {
				this.btnMore.setText(FrameworkLocale.constants()
						.error_box_details_open());
				this.scrollStackTrace
						.setStyleName("pf-error-message-scroll-stack-open");
			} else {
				this.btnMore.setText(FrameworkLocale.constants()
						.error_box_details_closed());
				this.scrollStackTrace
						.setStyleName("pf-error-message-scroll-stack-close");
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.PopupPanel#show()
	 */
	public void show() {
		super.show();
		center();
	}
}
