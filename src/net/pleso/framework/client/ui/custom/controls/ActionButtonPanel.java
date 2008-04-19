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
package net.pleso.framework.client.ui.custom.controls;

import net.pleso.framework.client.ui.custom.controls.data.ActionButtonControl;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;

/**
 * Panel with buttons and {@link ActionButtonControl} instances.
 * Visible if buttons was added
 * 
 * <h3>CSS Style Rules</h3>
 * <ul>
 * <li>.pf-actionButtonPanel { buttons panel itself }</li>
 * </ul>
 * 
 * @author Scater
 *
 */
public class ActionButtonPanel extends Composite {
	
	/**
	 * Main panel
	 */
	private HorizontalPanel panel = new HorizontalPanel();
	
	/**
	 * Inner panel for buttons. Can be aligned
	 */
	private HorizontalPanel innerPanel = new HorizontalPanel();
	
	/**
	 * Constructor 
	 * @param align default horizontal alignment to be used for buttons added to this panel.
	 */
	public ActionButtonPanel(HorizontalAlignmentConstant align) {
		this.initWidget(panel);
		this.setStyleName("pf-actionButtonPanel");
		
		setButtonsAlignment(align);
	}

	/**
	 * Removes all buttons
	 */
	public void clear() {
		innerPanel.clear();
	}
	
	/**
	 * Sets the default horizontal alignment to be used for buttons added to this panel.
	 * 
	 * @see HasHorizontalAlignment#setHorizontalAlignment(HasHorizontalAlignment.HorizontalAlignmentConstant)
	 */
	public void setButtonsAlignment(HorizontalAlignmentConstant align) {
		// check innerPanel presence because horizontal alignment 
		// only applies to widgets added after HorizontalAlignment is set.
		if (this.panel.getWidgetIndex(panel) != -1){
			this.panel.remove(innerPanel);
		}
		this.panel.setHorizontalAlignment(align);
		this.panel.add(innerPanel);
		
		this.setVisible(false);
	}
	
	public void add(Button button){
		this.innerPanel.add(button);
		checkVisible();
	}
	
	public void add(ActionButtonControl button){
		this.innerPanel.add(button);
		checkVisible();
	}
	
	private void checkVisible(){
		// panel is visible if it has buttons
		this.setVisible(innerPanel.getWidgetCount() > 0);
	}
}
