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
import net.pleso.framework.client.dal.db.types.DBBoolean;
import net.pleso.framework.client.localization.FrameworkLocale;
import net.pleso.framework.client.ui.controls.RequiredSignControl;
import net.pleso.framework.client.ui.controls.ValidationErrorControl;
import net.pleso.framework.client.ui.interfaces.IEditableDataControl;
import net.pleso.framework.client.ui.interfaces.IFocusControl;
import net.pleso.framework.client.ui.interfaces.ISingleColumnBind;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Data control with combobox for boolean values
 * 
 * <h3>CSS Style Rules</h3>
 * <ul>
 * <li>.pf-booleanComboboxControl { control itself }</li>
 * </ul>
 * 
 * @author Scater
 *
 */
public class BooleanComboBoxDataControl extends Composite implements IEditableDataControl, ISingleColumnBind, IFocusControl {

	private RequiredSignControl rsc = new RequiredSignControl();
	private ListBox listBox = new ListBox();
	private HorizontalPanel panel = new HorizontalPanel();
	private VerticalPanel mainPanel = new VerticalPanel();
	private ValidationErrorControl validErr = new ValidationErrorControl();
	
	private IDataRow row = null;
	private IDataColumn column = null;
	private IDBValue value = null;
	private boolean valid = true;
	
	private final int yesIndex = 0;
	private final int noIndex = 1;
	private final int unknownIndex = 2;
	
	/**
	 * Constructor
	 */
	public BooleanComboBoxDataControl(){
	    this.listBox.setVisibleItemCount(1);
	    this.listBox.addItem(FrameworkLocale.constants().yes());
	    this.listBox.addItem(FrameworkLocale.constants().no());
	    this.listBox.addItem(FrameworkLocale.constants().unknown());
	    
	    this.panel.add(this.rsc);
	    this.panel.add(this.listBox);
	    
	    mainPanel.add(panel);
	    
	    initWidget(this.mainPanel);
	    this.setStyleName("pf-booleanComboboxControl");
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
		if (this.value.isNull())
		{
			this.listBox.setItemSelected(unknownIndex, true);
		}
		else {
			if (((DBBoolean) this.value).getBoolean().booleanValue())
				this.listBox.setItemSelected(yesIndex, true);
			else
				this.listBox.setItemSelected(noIndex, true);
		}
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IEditableDataControl#updateData()
	 */
	public void updateData() {
		if (this.value != null) {
			if (this.listBox.getSelectedIndex() == unknownIndex)
			{
				value.setNull();
				row.setCell(this.column, this.value);
			}
			else {				
				if (internalParseValue())
					row.setCell(this.column, this.value);
			}
		}
	}
	
	/**
	 * Parse boolean value
	 * @return is parsed
	 */
	private boolean internalParseValue() {
		boolean result = false;
		if (this.listBox.getSelectedIndex() == yesIndex)
			result = true;
		else if (this.listBox.getSelectedIndex() == noIndex)
			result = false;
		
		return this.value.parseValue(Boolean.toString(result));
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IEditableDataControl#isValid()
	 */
	public boolean isValid() {
		return valid;
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IEditableDataControl#validate()
	 */
	public void validate() {
		hideError();
		if (this.value != null) {
			
			if (internalParseValue()) 
			{
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
		this.rsc.setRequired(required);
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
	 * @param errorText text of error
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
		this.listBox.setFocus(focused);
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.SourcesKeyboardEvents#addKeyboardListener(com.google.gwt.user.client.ui.KeyboardListener)
	 */
	public void addKeyboardListener(KeyboardListener listener) {
		this.listBox.addKeyboardListener(listener);
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.SourcesKeyboardEvents#removeKeyboardListener(com.google.gwt.user.client.ui.KeyboardListener)
	 */
	public void removeKeyboardListener(KeyboardListener listener) {
		this.listBox.removeKeyboardListener(listener);
	}
}
