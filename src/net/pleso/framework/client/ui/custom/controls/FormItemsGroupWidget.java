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

import net.pleso.framework.client.bl.forms.items.IFormItemsGroup;
import net.pleso.framework.client.dal.IDataRow;
import net.pleso.framework.client.ui.custom.CustomFormWindow;
import net.pleso.framework.client.ui.interfaces.IFocusControl;
import net.pleso.framework.client.ui.interfaces.IUpdateControlListener;
import net.pleso.framework.client.ui.interfaces.IWindow;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Widget with {@link FormItemsWidget} and header. Used in
 * {@link CustomFormWindow}.
 * 
 * <h3>CSS Style Rules</h3>
 * <ul>
 * <li>pf-form-items-group { widget itself }</li>
 * <li>pf-form-items-group-header { header of group }</li>
 * </ul>
 * 
 * @author Scater
 * 
 */
public class FormItemsGroupWidget extends Composite {

	private VerticalPanel panel = new VerticalPanel();

	private FormItemsWidget itemsWidget;

	private HTML groupHeader = new HTML();

	/**
	 * Constructs empty {@link FormItemsGroupWidget} with specified parameters.
	 * 
	 * @param parentWindow parent {@link IWindow}
	 * @param updateControlListenerer a {@link IUpdateControlListener} instance
	 * @param keyboardListener a {@link KeyboardListener} instance
	 * @param slidersPanel a {@link Panel} where sliders should be placed
	 */
	public FormItemsGroupWidget(IWindow parentWindow,
			IUpdateControlListener updateControlListenerer,
			KeyboardListener keyboardListener, Panel slidersPanel) {
		initWidget(panel);

		this.itemsWidget = new FormItemsWidget(parentWindow,
				updateControlListenerer, keyboardListener, slidersPanel);

		panel.add(groupHeader);
		panel.add(itemsWidget);

		this.setStyleName("pf-form-items-group");
		this.groupHeader.setStyleName("pf-form-items-group-header");
	}

	/**
	 * Inits widget whith {@link IFormItemsGroup} and {@link IDataRow}.
	 * 
	 * @param formItemsGroup a {@link IFormItemsGroup} instance
	 * @param dataRow a {@link IDataRow} instance
	 */
	public void initFormItemsGroup(IFormItemsGroup formItemsGroup,
			IDataRow dataRow) {
		groupHeader.setText(formItemsGroup.getCaption());
		this.itemsWidget.initFormControls(formItemsGroup.getItems(), dataRow);
	}

	/**
	 * Updates binded {@link #dataRow} by values from all group controls.
	 */
	public void updateAllData() {
		this.itemsWidget.updateData();
	}

	/**
	 * Forces bindable controls to read binded data.
	 */
	public void reReadBindableControls() {
		this.itemsWidget.reReadBindableControls();
	}

	/**
	 * Hides all windows in child sliders.
	 */
	public void hideWindows() {
		this.itemsWidget.hideSlidersWindows();
	}

	/**
	 * Makes first {@link IFocusControl} controls in all controlls list to be
	 * focused.
	 * 
	 * @return <code>true</code> if some control was focused
	 */
	public boolean focusOnFirstControl() {
		return this.itemsWidget.focusOnFirstControl();
	}

	/**
	 * Returns validation success result.
	 * 
	 * @return <code>true</code> if all form controls' values are valid
	 */
	public boolean isValid() {
		return this.itemsWidget.isValid();
	}

	/**
	 * Make validation on all group controls.
	 */
	public void validate() {
		this.itemsWidget.validate();
	}
}
