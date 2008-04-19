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
import net.pleso.framework.client.dal.db.types.DBStringTime;
import net.pleso.framework.client.localization.FrameworkLocale;
import net.pleso.framework.client.ui.controls.ValidationErrorControl;
import net.pleso.framework.client.ui.interfaces.IEditableDataControl;
import net.pleso.framework.client.ui.interfaces.IFocusControl;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Data control with two text boxes for range of time type values
 * 
 * <h3>CSS Style Rules</h3>
 * <ul>
 * <li>.pf-timeRangeControl { widget if self }</li>
 * </ul>
 * 
 * @author Scater
 *
 */
public class TimeRangeControl extends Composite implements IEditableDataControl, IFocusControl {
	
	protected TextBoxDataControl textBoxDataControlStart = new TextBoxDataControl();
	protected TextBoxDataControl textBoxDataControlEnd = new TextBoxDataControl();
	private ValidationErrorControl validErr = new ValidationErrorControl();
	
	private VerticalPanel panel = new VerticalPanel();
	
	/**
	 * Constructor
	 */
	public TimeRangeControl(){
		this.panel.add(textBoxDataControlStart);
		this.panel.setCellWidth(this.textBoxDataControlStart, "100%");
		this.panel.add(textBoxDataControlEnd);
		this.panel.setCellWidth(this.textBoxDataControlEnd, "100%");
		initWidget(this.panel);
		this.setStyleName("pf-timeRangeControl");
	}

	/**
	 * Bind row and range columns
	 * @param row row instance
	 * @param start_column column instance
	 * @param end_column column instance
	 */
	public void bind(IDataRow row, IDataColumn start_column, IDataColumn end_column) {
		textBoxDataControlStart.bind(row, start_column);
		textBoxDataControlEnd.bind(row, end_column);
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IBindableDataControl#readData()
	 */
	public void readData() {
		textBoxDataControlStart.readData();
		textBoxDataControlEnd.readData();
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IEditableDataControl#updateData()
	 */
	public void updateData() {
		textBoxDataControlStart.updateData();
		textBoxDataControlEnd.updateData();
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IEditableDataControl#isValid()
	 */
	public boolean isValid() {
		return textBoxDataControlStart.isValid() && textBoxDataControlEnd.isValid() && internalValid;
	}

	boolean internalValid = true;
	
	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IEditableDataControl#validate()
	 */
	public void validate() {
		textBoxDataControlStart.validate();
		textBoxDataControlEnd.validate();
		
		// if validation was successful, must run internal validations also
		if (textBoxDataControlStart.isValid() && textBoxDataControlEnd.isValid()){
			internalValid = true;
			hideError();
			validateOneNotNull();
			validateStartLessEqualEnd();
		}
	}
	
	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IEditableDataControl#isRequired()
	 */
	public boolean isRequired() {
		return textBoxDataControlStart.isRequired() && textBoxDataControlEnd.isRequired();
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IEditableDataControl#setRequired(boolean)
	 */
	public void setRequired(boolean required) {
		textBoxDataControlStart.setRequired(required);
		textBoxDataControlEnd.setRequired(required);
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
		internalValid = false;
		this.validErr.setText(errorText);
		this.panel.add(validErr);
	}

	/**
	 * Validation of rule "all values must be not null or no one"
	 */
	private void validateOneNotNull(){
		if (textBoxDataControlStart.getValue().isNull() && !textBoxDataControlEnd.getValue().isNull()){
			showError(FrameworkLocale.messages().start_range_field_is_required());
		}
		if (!textBoxDataControlStart.getValue().isNull() && textBoxDataControlEnd.getValue().isNull()){
			showError(FrameworkLocale.messages().end_range_field_is_required());
		}
	}
	
	/**
	 * Validation of rule "start time must be earlier than end time"
	 */
	private void validateStartLessEqualEnd(){
		IDBValue startValue = textBoxDataControlStart.getValue();
		IDBValue endValue = textBoxDataControlEnd.getValue();
		if (!startValue.isNull() && !endValue.isNull()){
			// the value 0 if the argument Date is equal to this Date; 
			// a value less than 0 if this Date is before the Date argument; 
			// and a value greater than 0 if this Date is after the Date argument.
			if (((DBStringTime) startValue).getStringDate().compareTo(((DBStringTime) endValue).getStringDate()) > 0){
				showError(FrameworkLocale.messages().starttime_less_or_equal_enddate());
			}
		}
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IFocusControl#setFocus(boolean)
	 */
	public void setFocus(boolean focused) {
		this.textBoxDataControlStart.setFocus(focused);
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.SourcesKeyboardEvents#addKeyboardListener(com.google.gwt.user.client.ui.KeyboardListener)
	 */
	public void addKeyboardListener(KeyboardListener listener) {
		this.textBoxDataControlStart.addKeyboardListener(listener);
		this.textBoxDataControlEnd.addKeyboardListener(listener);
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.SourcesKeyboardEvents#removeKeyboardListener(com.google.gwt.user.client.ui.KeyboardListener)
	 */
	public void removeKeyboardListener(KeyboardListener listener) {
		this.textBoxDataControlStart.removeKeyboardListener(listener);
		this.textBoxDataControlEnd.removeKeyboardListener(listener);
	}

}
