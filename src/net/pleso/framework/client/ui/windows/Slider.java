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

import net.pleso.framework.client.ui.interfaces.IWindow;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventPreview;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.ClickListenerCollection;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.KeyboardListenerCollection;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SourcesClickEvents;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Represents widget with clickable header with caption and contents panel wich
 * can be hidden or shown on header click.
 * 
 * <h3>CSS Style Rules</h3>
 * <ul>
 * <li>.pf-slider {widget itself when it simple slider}</li>
 * <li>.pf-main-slider {widget itself when it main slider (topmost parent for
 * other childrens)}</li>
 * <li>.pf-slider-titlePanel {slider's title horizontal panel}</li>
 * <li>.pf-slider-arrow-open {style of arrow (left from slider's caption) when slider is opened}</li>
 * <li>.pf-slider-arrow-close {style of arrow (left from slider's caption) when slider is closed}</li>
 * <li>.pf-slider-arrow-disabled {style of arrow (left from slider's caption) when slider is disabled}</li>
 * <li>.pf-slider-caption {slider caption text}</li>
 * <li>.pf-slider-caption-disabled {slider caption text when slider is disabled}</li>
 * </ul>
 */
public class Slider extends Composite implements ClickListener, EventPreview,
		SourcesClickEvents {

	private VerticalPanel mainPanel = new VerticalPanel();

	private HorizontalPanel titlePanel = new HorizontalPanel();

	private HorizontalPanel clickPanel = new HorizontalPanel();

	private Label arrow = new Label();

	private Label caption = new Label();

	private boolean isClosed = true;

	private ModalWindowDeck modalWindowDeck = new ModalWindowDeck();

	private TabbedSliderManager parentSlideManager = null;

	private boolean isModalAfterOpen = true;

	private boolean isMainSlider = false;

	private boolean allowParentClose = false;

	private ClickListenerCollection clickListeners;

	/**
	 * Constructs a new {@link Slider} with specified parameters.
	 * 
	 * @param captionText
	 *            caption string
	 * @param isMainSlider
	 *            indicates whether this must be main slider (topmost parent for
	 *            other childrens)
	 */
	public Slider(String captionText, boolean isMainSlider) {
		this(captionText, isMainSlider, false);
	}

	/**
	 * Constructs a new {@link Slider} with specified parameters.
	 * 
	 * @param captionText
	 *            caption string
	 * @param isMainSlider
	 *            indicates whether this must be main slider (topmost parent for
	 *            other childrens)
	 * @param allowParentClose
	 *            indicates whether in exclusive modal mode parent slider can be
	 *            closed
	 */
	public Slider(String captionText, boolean isMainSlider,
			boolean allowParentClose) {

		this.allowParentClose = allowParentClose;

		this.isMainSlider = isMainSlider;

		if (!isMainSlider) {
			caption.setText(captionText);
			caption.setWordWrap(false);

			setEnabled(true);
			arrow.addClickListener(this);
			caption.addClickListener(this);

			clickPanel.add(arrow);
			clickPanel.setCellHorizontalAlignment(arrow,
					HorizontalPanel.ALIGN_LEFT);
			clickPanel.setCellVerticalAlignment(arrow,
					VerticalPanel.ALIGN_MIDDLE);

			clickPanel.add(caption);
			clickPanel.setCellHorizontalAlignment(caption,
					HorizontalPanel.ALIGN_LEFT);
			clickPanel.setCellVerticalAlignment(caption,
					VerticalPanel.ALIGN_MIDDLE);

			clickPanel.setHeight("100%");

			titlePanel.setStyleName("pf-slider-titlePanel");
			titlePanel.add(clickPanel);
			titlePanel.setWidth("100%");

			mainPanel.add(titlePanel);
		}

		mainPanel.add(this.modalWindowDeck);
		this.setClosed(true);

		initWidget(mainPanel);

		if (!isMainSlider)
			setStyleName("pf-slider");
		else
			setStyleName("pf-main-slider");
	}

	/**
	 * Constructs a simple new {@link Slider} object with specified caption
	 * text.
	 * 
	 * @param captionText
	 *            caption string
	 */
	public Slider(String captionText) {
		this(captionText, false);
	}

	/**
	 * Constructs a new main {@link Slider} object based on specified
	 * {@link TabbedSliderManager}.
	 * 
	 * @param parentSlideManager
	 *            parent {@link TabbedSliderManager}
	 */
	public Slider(TabbedSliderManager parentSlideManager) {
		this("", true);
		this.parentSlideManager = parentSlideManager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.SourcesClickEvents#addClickListener(com.google.gwt.user.client.ui.ClickListener)
	 */
	public void addClickListener(ClickListener listener) {
		if (clickListeners == null) {
			clickListeners = new ClickListenerCollection();
		}
		clickListeners.add(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.SourcesClickEvents#removeClickListener(com.google.gwt.user.client.ui.ClickListener)
	 */
	public void removeClickListener(ClickListener listener) {
		if (clickListeners != null) {
			clickListeners.remove(listener);
		}
	}

	/**
	 * Shows modal window on this {@link Slider} by specified {@link IWindow}
	 * object.
	 * 
	 * @param window
	 *            the specified {@link IWindow} objec to be shown
	 */
	public void ShowModalWindow(IWindow window) {
		this.modalWindowDeck.addWindow(window);
		this.setClosed(false);
	}

	/**
	 * Hides modal window placed on this {@link Slider} by specified
	 * {@link IWindow} object.
	 * 
	 * @param window
	 *            the specified {@link IWindow} objec to be hidden
	 */
	public void HideModalWindow(IWindow window) {
		this.modalWindowDeck.removeWindow(window);
		if (!this.modalWindowDeck.hasWindows()) {
			this.setClosed(true);

			if (this.parentSlideManager != null) {
				this.parentSlideManager.HideSlider(this);
			}
		}
	}

	/**
	 * Gets {@link Slider} caption.
	 * 
	 * @return caption string
	 */
	public String getCaption() {
		return caption.getText();
	}

	/**
	 * Sets {@link Slider} caption.
	 * 
	 * @param text
	 *            caption string
	 */
	public void setCaption(String text) {
		caption.setText(text);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user.client.ui.Widget)
	 */
	public void onClick(Widget sender) {
		if (isClosed) {
			if (isEnabled) {
				if (clickListeners != null) {
					clickListeners.fireClick(this);
				}
			}
		} else {
			this.modalWindowDeck.hideWindows();
			setClosed(true);
		}
	}

	/**
	 * Determines whether this {@link Slider} is closed and its contents is
	 * hidden.
	 * 
	 * @return <code>true</code> if {@link Slider} is closed
	 */
	public boolean isClosed() {
		return isClosed;
	}

	/**
	 * Sets value indicating whether this {@link Slider} is closed and its
	 * contents is hidden.
	 * 
	 * @param isClosed
	 *            new closed state value
	 */
	private void setClosed(boolean isClosed) {
		if (isClosed)
			DOM.removeEventPreview(this);
		else {
			if (isModalAfterOpen) {
				if (!isMainSlider || this.modalWindowDeck.getWindowCount() > 1) {
					DOM.removeEventPreview(this);
					DOM.addEventPreview(this);
				}
			}
		}

		this.isClosed = isClosed;
		this.modalWindowDeck.setVisible(!isClosed);
		if (isClosed) {
			refreshEnableStyle();
		} else {
			arrow.setStyleName("pf-slider-arrow-open");
		}
	}

	private boolean isEnabled = true;

	/**
	 * Sets value indicating whether this {@link Slider} is enabled for clicking
	 * and opening.
	 * 
	 * @param isEnabled
	 *            new enabled status value
	 */
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
		if (isClosed) {
			refreshEnableStyle();
		}
	}

	/**
	 * Determines whether this {@link Slider} is enabled for clicking and
	 * opening.
	 * 
	 * @return <code>true</code> if {@link Slider} is enabled
	 */
	public boolean isEnabled() {
		return isEnabled;
	}

	/**
	 * Sets appropriate element styles according to enabled state.
	 */
	private void refreshEnableStyle() {
		if (isEnabled) {
			arrow.setStyleName("pf-slider-arrow-close");
			caption.setStyleName("pf-slider-caption");
		} else {
			arrow.setStyleName("pf-slider-arrow-disabled");
			caption.setStyleName("pf-slider-caption-disabled");
		}
	}

	public boolean onEventPreview(Event event) {
		int type = DOM.eventGetType(event);
		switch (type) {
		case Event.ONKEYDOWN: {
			return onKeyDownPreview((char) DOM.eventGetKeyCode(event),
					KeyboardListenerCollection.getKeyboardModifiers(event));
		}
		case Event.ONKEYUP: {
			return onKeyUpPreview((char) DOM.eventGetKeyCode(event),
					KeyboardListenerCollection.getKeyboardModifiers(event));
		}
		case Event.ONKEYPRESS: {
			return onKeyPressPreview((char) DOM.eventGetKeyCode(event),
					KeyboardListenerCollection.getKeyboardModifiers(event));
		}

		case Event.ONMOUSEDOWN:
		case Event.ONMOUSEUP:
		case Event.ONMOUSEMOVE:
		case Event.ONCLICK:
		case Event.ONDBLCLICK: {
			// Don't eat events if event capture is enabled, as this can
			// interfere
			// with dialog dragging, for example.
			if (DOM.getCaptureElement() == null) {
				// Disallow mouse events outside of the slider.
				Element target = DOM.eventGetTarget(event);
				if (!elementEnabled(target)) {
					return false;
				}
			}
			break;
		}
		}
		return true;
	}

	/**
	 * Determines whether specified {@link Element} is enabled for user input in
	 * modal mode.
	 * 
	 * @param element
	 *            the {@link Element} to be checked
	 * @return <code>true</code> if {@link Element} can receive input
	 */
	private boolean elementEnabled(Element element) {
		if (DOM.isOrHasChild(getElement(), element))
			return true;

		Widget cursor = this.getParent();
		Widget lastSlider = this;
		Widget firstSlider = null;
		while (!(cursor instanceof SliderTabPanel)
				&& !(cursor instanceof RootPanel)) {
			if (cursor instanceof Slider) {
				lastSlider = cursor;
				if (firstSlider == null)
					firstSlider = cursor;
			}
			cursor = cursor.getParent();
		}

		if (allowParentClose || firstSlider != null) {
			Slider parentSlider = (Slider) firstSlider;

			if (DOM.isOrHasChild(parentSlider.titlePanel.getElement(), element))
				return true;
		}

		if (DOM.isOrHasChild(lastSlider.getElement(), element))
			return false;
		else
			return true;
	}

	public boolean onKeyDownPreview(char key, int modifiers) {
		return true;
	}

	public boolean onKeyPressPreview(char key, int modifiers) {
		return true;
	}

	public boolean onKeyUpPreview(char key, int modifiers) {
		return true;
	}

	/**
	 * @return value indicating whether {@link Slider} blocks input on other
	 *         widgets when opened
	 */
	public boolean isModalAfterOpen() {
		return isModalAfterOpen;
	}

	/**
	 * Sets value indicating whether {@link Slider} blocks input on other
	 * widgets when opened.
	 * 
	 * @param isModalAfterOpen
	 */
	public void setModalAfterOpen(boolean isModalAfterOpen) {
		this.isModalAfterOpen = isModalAfterOpen;
	}

	/**
	 * Hides all {@link IWindow} objects placed on this {@link Slider}.
	 */
	public void hideWindows() {
		this.modalWindowDeck.hideWindows();
	}

	/**
	 * Scrolls browser's window to top of this {@link Slider}.
	 */
	public void scrollToTop() {
		// here we can use only "this" for getAbsoluteTop() method, because in main slider 
		// there are no any child panels
		window_scroll(0, this.getAbsoluteTop());
	}

	/**
	 * Scrolls browser's window to specified by coordinates position.
	 * 
	 * @param x
	 *            left based x scroll coordinate
	 * @param y
	 *            top based y scroll coordinate
	 */
	public static native void window_scroll(int x, int y) /*-{
	 $wnd.scroll(x, y);
	 }-*/;
}
