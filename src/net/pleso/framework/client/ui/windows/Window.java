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

import net.pleso.framework.client.ui.interfaces.ChangeWindowCaptionListener;
import net.pleso.framework.client.ui.interfaces.HideWindowListener;
import net.pleso.framework.client.ui.interfaces.IWindow;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * Represents base class for window widgets.
 */
public abstract class Window extends Composite implements IWindow {
	
	private String caption = "";
	private WindowCaptionChangeListenerCollection windowCaptionChangeListeners = null; 
	private HideWindowListenerCollection hideWindowListeners = null;
	private Slider parentSlider = null;
	
	private boolean isHidden = true;	
	
	/**
	 * Constructs new window with specified parent {@link Slider}.
	 * 
	 * @param parentSlider window's parent slider
	 */
	public Window(Slider parentSlider) {
		if (parentSlider != null)
			this.parentSlider = parentSlider;
	}
	
	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IWindow#setParentSlider(net.pleso.framework.client.ui.windows.Slider)
	 */
	public void setParentSlider(Slider parentSlider) {
		if (this.parentSlider == null)
			this.parentSlider = parentSlider;
		else
			throw new IllegalArgumentException("Cant change parentSlider value");
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IWindow#getCaption()
	 */
	public String getCaption() {
		return this.caption;
	}
	
	/**
	 * Sets window caption.
	 * 
	 * @param caption string caption to be set
	 */
	public void setCaption(String caption) {
		this.caption = caption;
		if (this.windowCaptionChangeListeners != null) {
			this.windowCaptionChangeListeners.fireChange(this);
		}
	}
	
	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.SourcesWindowEvents#addWindowCaptionChangeListener(net.pleso.framework.client.ui.interfaces.ChangeWindowCaptionListener)
	 */
	public void addWindowCaptionChangeListener(ChangeWindowCaptionListener listener) {
		if (this.windowCaptionChangeListeners == null) {
			this.windowCaptionChangeListeners = new WindowCaptionChangeListenerCollection();
	    }
		this.windowCaptionChangeListeners.add(listener);
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.SourcesWindowEvents#removeWindowCaptionChangeListener(net.pleso.framework.client.ui.interfaces.ChangeWindowCaptionListener)
	 */
	public void removeWindowCaptionChangeListener(ChangeWindowCaptionListener listener) {
		if (this.windowCaptionChangeListeners != null) {
			this.windowCaptionChangeListeners.remove(listener);
		}
	}
	
	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.SourcesWindowEvents#addHideWindowListener(net.pleso.framework.client.ui.interfaces.HideWindowListener)
	 */
	public void addHideWindowListener(HideWindowListener listener) {
		if (this.hideWindowListeners == null) {
			this.hideWindowListeners = new HideWindowListenerCollection();
		}
		
		this.hideWindowListeners.add(listener);
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.SourcesWindowEvents#removeHideWindowListener(net.pleso.framework.client.ui.interfaces.HideWindowListener)
	 */
	public void removeHideWindowListener(HideWindowListener listener) {
		if (this.hideWindowListeners != null) {
			this.hideWindowListeners.remove(listener);
		}
	}
	
	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IWindow#hideWindow()
	 */
	public void hideWindow() {
		
		this.isHidden = true;
		
		this.parentSlider.HideModalWindow(this);
		if (this.hideWindowListeners != null)
			this.hideWindowListeners.fireHide(this);
	}
	
	/**
	 * Shows this window.
	 */
	public void show() {
		if (this.parentSlider != null)
			this.parentSlider.ShowModalWindow(this);
		else
			TabbedSliderManager.getInstance().showWindow(this);
		
		this.isHidden = false;
		
		showEvent();
	}	
	
	/**
	 * Base virual method for using as show window event in descendant classes.   
	 */
	protected void showEvent() {
		
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IWindow#getWidget()
	 */
	public Widget getWidget() {
		return this;
	}
	
	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IWindow#getParentSlider()
	 */
	public Slider getParentSlider() {
		return this.parentSlider;
	}
	
	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IWindow#scrollToTop()
	 */
	public void scrollToTop() {
		if (this.parentSlider != null)
			this.parentSlider.scrollToTop();
	}
	
	public boolean isHidden() {
		return this.isHidden;
	}
}
