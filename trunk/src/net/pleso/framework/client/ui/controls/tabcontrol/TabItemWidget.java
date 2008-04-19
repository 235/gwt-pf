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
package net.pleso.framework.client.ui.controls.tabcontrol;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * Widget with caption and "close" button used on {@link TabBar}
 * 
 * <h3>CSS Style Rules</h3>
 * <ul class='css'>
 * <li>.pf-TabCaption { caption text }</li>
 * <li>.pf-TabCloseButtonHTML { button "close" html }</li>
 * </ul>
 * 
 * @author Scater
 *
 */
public class TabItemWidget extends Composite implements ClickListener {
	
	private HorizontalPanel panel = new HorizontalPanel();
	private Label item = null;
	private HTML closeButton = new HTML("&nbsp;");
	
	/**
	 * Constructor 
	 * @param tabBar {@link TabBar} container
	 * @param text caption text 
	 * @param asHTML item like {@link Label} or like {@link HTML}
	 */
	public TabItemWidget(final TabBar tabBar, String text, boolean asHTML){
	    if (asHTML) {
	      item = new HTML(text);
	    } else {
	      item = new Label(text);
	    }
	    item.setWordWrap(false);
	    item.setStyleName("pf-TabCaption");
	    item.addClickListener(this);
	    
	    closeButton.setStyleName("pf-TabCloseButtonHTML");
	    closeButton.addClickListener(this);
	    
	    panel.add(item);
	    panel.add(closeButton);
	    
		initWidget(panel);
	}
	
	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user.client.ui.Widget)
	 */
	public void onClick(final Widget sender) {
		if (sender == closeButton){
			if (this.tabItemWidgetEventListener != null)
				this.tabItemWidgetEventListener.clickCloseButton(this);
		}
		
		if (sender == item){
			if (this.tabItemWidgetEventListener != null)
				this.tabItemWidgetEventListener.clickLabel(this);
		}
    }
	
	/**
	 * Sets caption text of item
	 * @param text caption text
	 */
	public void setTabHTML(String text){
		if (item instanceof HTML) {
		      ((HTML) item).setHTML(text);
		    } else {
		      ((Label) item).setText(text);
		    }
	}
	
	/**
	 * Gets caption text of item
	 * @return caption text
	 */
	public String getTabHTML(){
		if (item instanceof HTML) {
		      return ((HTML) item).getHTML();
		    } else {
		      return ((Label) item).getText();
		    }
	}
	
	private TabItemWidgetEventListener tabItemWidgetEventListener = null;

	/**
	 * Gets close event listener
	 * @return
	 */
	public TabItemWidgetEventListener getTabItemWidgetEventListener() {
		return tabItemWidgetEventListener;
	}

	/**
	 * Sets close event listener
	 * @param tabItemWidgetEventListener {@link TabItemWidgetEventListener} implementation
	 */
	public void setTabItemWidgetEventListener(
			TabItemWidgetEventListener tabItemWidgetEventListener) {
		this.tabItemWidgetEventListener = tabItemWidgetEventListener;
	}

}
