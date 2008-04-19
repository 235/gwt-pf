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
package net.pleso.framework.client.ui.controls;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.impl.PopupImpl;

/**
 * Status panel widget. It works like popup panel, but without block user input
 * inside. Can show text and centre by external widget.
 * 
 * <h3>CSS Style Rules</h3>
 * <ul>
 * <li>.pf-statusPanel { the status panel itself }</li>
 * <li>.pf-statusPanel-imageHTML { HTML for left image near text }</li>
 * <li>.pf-statusPanel-label { label with text }</li>
 * </ul>
 */
public class StatusPanel extends SimplePanel {
	
	/**
	 * Private simple composite class with image and label
	 * Using in setWidget() for this panel
	 * 
	 * @author Scater
	 *
	 */
	private class ImageLabel extends Composite {
		
		HorizontalPanel panel = new HorizontalPanel();
		
		private Label label = new Label();
		private HTML image = new HTML("&nbsp;");
		
		public ImageLabel() {
			panel.add(image);
			panel.add(label);
			
			initWidget(panel);
			
			image.setStyleName("pf-statusPanel-imageHTML");
			label.setStyleName("pf-statusPanel-label");
		}
		
		public String getText() {
			return label.getText();
		}

		public void setText(String text) {
			label.setText(text);
		}
		
	}

	private ImageLabel imageLabel = new ImageLabel();
	
	/**
	 * Constructor
	 */
	public StatusPanel() {
		super(impl.createElement());
		setStyleName("pf-statusPanel");
		setWidget(imageLabel);
	}

	/**
	 * Using GWT implementation from
	 * {@link com.google.gwt.user.client.ui.PopupPanel}.
	 */
	private static PopupImpl impl = (PopupImpl) GWT.create(PopupImpl.class);
	
	private boolean showing;

	/**
	 * Gets the popup's left position relative to the browser's client area.
	 * 
	 * @return the popup's left position
	 */
	public int getPopupLeft() {
		return DOM.getIntAttribute(getElement(), "offsetLeft");
	}

	/**
	 * Gets the popup's top position relative to the browser's client area.
	 * 
	 * @return the popup's top position
	 */
	public int getPopupTop() {
		return DOM.getIntAttribute(getElement(), "offsetTop");
	}

	public boolean remove(Widget w) {
		if (!super.remove(w)) {
			return false;
		}
		return true;
	}

	/**
	 * Sets the popup's position relative to the browser's client area. 
	 * Implementation like in {@link com.google.gwt.user.client.ui.PopupPanel#setPopupPosition(int, int)}
	 * @param left the left position, in pixels
	 * @param top the top position, in pixels
	 */
	public void setPopupPosition(int left, int top) {
		if (left < 0) {
			left = 0;
		}
		if (top < 0) {
			top = 0;
		}

		Element elem = getElement();
		DOM.setStyleAttribute(elem, "left", left + "px");
		DOM.setStyleAttribute(elem, "top", top + "px");
	}

	/**
	 * Shows the popup. It have a child widget Label.
	 * Also centres status panel by user specific widget
	 */
	public void show() {
		if (showing) {
			return;
		}
		
		int newLeft = 0;
		int newTop = 0;
		if (centerByWidget != null && centerByWidget.isVisible()) {
			// Calculates popup position of panel by centerByWidget instance
			newLeft = centerByWidget.getAbsoluteLeft() + centerByWidget.getOffsetWidth() / 2
				- imageLabel.getOffsetWidth() / 2;
			newTop = centerByWidget.getAbsoluteTop() + centerByWidget.getOffsetHeight() / 2
				- imageLabel.getOffsetHeight() / 2;
			// if centerByWidget is not on screen panel can't be displayed
			if (newLeft <= 0 || newTop <= 0) 
				return;
		}
		
		showing = true;

		RootPanel.get().add(this);
		DOM.setStyleAttribute(getElement(), "position", "absolute");
		impl.onShow(getElement());
		
		// if centerByWidget is not null, newLeft and newTop are the correct coordinates
		// in another case we have newLeft = 0 and newTop = 0;
		setPopupPosition(newLeft, newTop);
	}

	/**
	 * Hides the popup.
	 */
	public void hide() {
		if (!showing) {
			return;
		}
		showing = false;

		RootPanel.get().remove(this);
		impl.onHide(getElement());
	}

	/**
	 * Widget to centre panel by
	 */
	private Widget centerByWidget;

	/**
	 * Gets widget to centre panel by.
	 * 
	 * @return widget to centre panel by
	 */
	public Widget getCenterByWidget() {
		return centerByWidget;
	}

	/**
	 * Sets widget to centre panel by.
	 * 
	 * @param centerByWidget
	 *            widget to centre panel by
	 */
	public void setCenterByWidget(Widget centerByWidget) {
		this.centerByWidget = centerByWidget;
	}

	/**
	 * Gets text on panel.
	 * 
	 * @return text on panel
	 */
	public String getText() {
		return imageLabel.getText();
	}

	/**
	 * Sets text on panel.
	 * 
	 * @param text
	 *            text on panel
	 */
	public void setText(String text) {
		imageLabel.setText(text);
	}
	
	public boolean isShowing() {
		return showing;
	}

}
