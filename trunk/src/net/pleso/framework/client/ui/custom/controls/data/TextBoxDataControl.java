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

import net.pleso.framework.client.dal.IDataColumn;
import net.pleso.framework.client.dal.IDataRow;
import net.pleso.framework.client.dal.db.IDBValue;
import net.pleso.framework.client.localization.FrameworkLocale;
import net.pleso.framework.client.ui.controls.RequiredSignControl;
import net.pleso.framework.client.ui.controls.ValidationErrorControl;
import net.pleso.framework.client.ui.interfaces.IEditableDataControl;
import net.pleso.framework.client.ui.interfaces.IFocusControl;
import net.pleso.framework.client.ui.interfaces.ISingleColumnBind;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Data control with text box
 * 
 * <h3>CSS Style Rules</h3>
 * <ul>
 * <li>.pf-textBoxDataControl { widget itself }</li>
 * </ul>
 * 
 * @author Scater
 *
 */
public class TextBoxDataControl extends Composite implements IEditableDataControl, ISingleColumnBind, IFocusControl {
	
	private IDataRow row = null;
	private IDataColumn column = null;
	protected IDBValue value = null;
	private boolean valid = true;
	
	protected TextBox textBox = new TextBox();
	private HorizontalPanel upPanel = new HorizontalPanel();
	private VerticalPanel panel = new VerticalPanel();
	private RequiredSignControl rsc = new RequiredSignControl();
	private ValidationErrorControl validErr = new ValidationErrorControl();
	
	/**
	 * Constructor
	 */
	public TextBoxDataControl(){
		this.upPanel.add(this.rsc);
		this.upPanel.add(this.textBox);
		this.upPanel.setCellWidth(this.textBox, "100%");
		
		this.panel.add(upPanel);
		initWidget(this.panel);
		this.setStyleName("pf-textBoxDataControl");
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.ISingleColumnBind#bind(net.pleso.framework.client.dal.IDataRow, net.pleso.framework.client.dal.IDataColumn)
	 */
	public void bind(IDataRow row, IDataColumn column) {
		this.row = row;
		this.column = column;
		
		this.value = row.getCell(this.column);
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IBindableDataControl#readData()
	 */
	public void readData() {
		this.value = row.getCell(this.column);
		if (value.isNull())
			this.textBox.setText("");
		else
			this.textBox.setText(this.value.getValue());
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IEditableDataControl#updateData()
	 */
	public void updateData() {
		if (this.value != null) {	
			if (this.value.parseValue(this.textBox.getText()))
				row.setCell(this.column, this.value);
		}
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IEditableDataControl#isValid()
	 */
	public boolean isValid() {
		return valid;
	}
	
	/**
	 * Hides validation error
	 */
	private void hideError(){
		if (this.panel.getWidgetIndex(validErr) != -1)
			this.panel.remove(validErr);
		this.validErr.setText("");
	}
	
	/**
	 * Shows validation error
	 * @param errorText error text
	 */
	private void showError(String errorText){
		valid = false;
		this.validErr.setText(errorText);
		this.panel.add(validErr);
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
			{
				showError(FrameworkLocale.messages().bad_value_in_field(this.column.getCaption()));
			}
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
		this.rsc.setRequired(required);
	}
	
	/**
	 * Gets current value in control
	 * @return current value
	 */
	public IDBValue getValue(){
		return this.value;
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IFocusControl#setFocus(boolean)
	 */
	public void setFocus(boolean focused) {
		this.textBox.setFocus(focused);
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
