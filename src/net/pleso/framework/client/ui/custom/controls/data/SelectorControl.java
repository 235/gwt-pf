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
package net.pleso.framework.client.ui.custom.controls.data;

import net.pleso.framework.client.bl.ISelector;
import net.pleso.framework.client.dal.IDataColumn;
import net.pleso.framework.client.dal.IDataRow;
import net.pleso.framework.client.dal.db.IDBValue;
import net.pleso.framework.client.localization.FrameworkLocale;
import net.pleso.framework.client.ui.controls.RequiredSignControl;
import net.pleso.framework.client.ui.controls.ValidationErrorControl;
import net.pleso.framework.client.ui.custom.CustomRBType;
import net.pleso.framework.client.ui.custom.CustomRBWindow;
import net.pleso.framework.client.ui.interfaces.HideWindowListener;
import net.pleso.framework.client.ui.interfaces.IEditableDataControl;
import net.pleso.framework.client.ui.interfaces.IFocusControl;
import net.pleso.framework.client.ui.interfaces.IWindow;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Selector data control. 
 * In this control user choose value from external reference book (f.e. by foreigh key).
 * Contains text box and button "Select" (button "Clear" optionally for null values)
 * 
 * <h3>CSS Style Rules</h3>
 * <ul>
 * <li>.pf-selectorControl { control itself }</li>
 * </ul>
 * 
 * @author Scater
 *
 */
public class SelectorControl extends Composite implements ClickListener, HideWindowListener, IEditableDataControl, IFocusControl {
	
	private IDataRow row = null;
	private IDataColumn column = null;
	private IDBValue value = null;
	private ISelector selector;
	private IWindow parentWindow;
	private boolean valid = true;
	
	private HorizontalPanel panel = new HorizontalPanel();
	private VerticalPanel mainPanel = new VerticalPanel();
	private Button btnSelect = new Button(FrameworkLocale.constants().select_button_caption(), this);
	private Button btnClear = new Button(FrameworkLocale.constants().clear_button_caption(), this);
	private TextBox textBox = new TextBox();
	private RequiredSignControl rsc = new RequiredSignControl();
	private ValidationErrorControl validErr = new ValidationErrorControl();
	
	/**
	 * 
	 * @param parentWindow
	 * @param selector
	 */
	public SelectorControl(IWindow parentWindow, ISelector selector) {
		if (selector == null)
			throw new IllegalArgumentException("Selector argument can't be null");
		if (parentWindow == null)
			throw new IllegalArgumentException("ParentWindow argument can't be null");
		
		this.selector = selector;
		this.parentWindow = parentWindow;
		
		this.textBox.setEnabled(false);
		
		this.panel.add(this.rsc);
		this.panel.add(this.textBox);
		this.panel.setCellWidth(this.textBox, "100%");
		this.panel.add(this.btnSelect);
		
		// Initially status is not required so clear button must be placed.
		this.addClearButton();
		
		mainPanel.add(panel);
		
		initWidget(this.mainPanel);
		
		this.setStyleName("pf-selectorControl");
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user.client.ui.Widget)
	 */
	public void onClick(Widget sender) {
		if (sender == this.btnSelect) {
			CustomRBWindow rb = new CustomRBWindow(this.parentWindow.getParentSlider(), this.selector.getRB(this.row), true, CustomRBType.Select);
			rb.addHideWindowListener(this);
			rb.show();
		}
		else
			if (sender == this.btnClear) {
				this.selector.setNull(this.row);
				readData();
			}
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.HideWindowListener#onHideWindow(net.pleso.framework.client.ui.interfaces.IWindow)
	 */
	public void onHideWindow(IWindow sender) {
		CustomRBWindow window = (CustomRBWindow) sender;
		if (window != null && window.getSelectedRow() != null) {
			selector.CopyRow(this.row, window.getSelectedRow());
			readData();
		}
	}

	/**
	 * Bind row and range columns
	 * @param row row instance
	 * @param start_column column instance
	 * @param end_column column instance
	 */
	public void bind(IDataRow row, IDataColumn column) {
		this.row = row;
		this.column = column;
		
		this.value = row.getCell(this.column);
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IEditableDataControl#isValid()
	 */
	public boolean isValid() {
		return this.valid;
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IBindableDataControl#readData()
	 */
	public void readData() {
		this.value = row.getCell(this.column);
		//this.textBox.setText(this.value.getValue());
		if (value.isNull())
			this.textBox.setText("");
		else
			this.textBox.setText(this.value.getValue());
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IEditableDataControl#updateData()
	 */
	public void updateData() {
		// this is empty method for this component because
		// when data from external reference book was selected it must save into row		
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IEditableDataControl#validate()
	 */
	public void validate() {
		hideError();
		if (this.value != null) {
			if (this.value.parseValue(this.textBox.getText())) {
				this.valid = !this.isRequired() || !this.value.isNull();
				if (this.valid == false)
					showError(FrameworkLocale.messages().field_is_required(this.column.getCaption()));
			}
			else
				showError(FrameworkLocale.messages().bad_value_in_field(this.column.getCaption()));
		}
		else {
			showError(FrameworkLocale.messages().field_is_required(this.column.getCaption()));
		}		
	}	
	
	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IEditableDataControl#isRequired()
	 */
	public boolean isRequired() {
		return this.rsc.isRequired();
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IEditableDataControl#setRequired(boolean)
	 */
	public void setRequired(boolean required) {
		if (required != this.isRequired()) {
			this.rsc.setRequired(required);
			
			if (!required) {
				this.addClearButton();
			} else {
				this.panel.remove(this.btnClear);
			}	
		}
	}
	
	private void addClearButton() {
		this.panel.add(this.btnClear);
		this.panel.setCellWidth(this.btnClear, "70px");
	}
	
	/**
	 * Hides validation error
	 */
	private void hideError(){
		if (this.mainPanel.getWidgetIndex(validErr) != -1)
			this.mainPanel.remove(validErr);
		this.validErr.setText("");
	}
	
	/**
	 * Shows validation error
	 * @param errorText error text
	 */
	private void showError(String errorText){
		valid = false;
		this.validErr.setText(errorText);
		this.mainPanel.add(validErr);
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IFocusControl#setFocus(boolean)
	 */
	public void setFocus(boolean focused) {
		this.btnSelect.setFocus(focused);
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.SourcesKeyboardEvents#addKeyboardListener(com.google.gwt.user.client.ui.KeyboardListener)
	 */
	public void addKeyboardListener(KeyboardListener listener) {
		this.textBox.addKeyboardListener(listener);
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.SourcesKeyboardEvents#removeKeyboardListener(com.google.gwt.user.client.ui.KeyboardListener)
	 */
	public void removeKeyboardListener(KeyboardListener listener) {
		this.textBox.removeKeyboardListener(listener);
	}
}
