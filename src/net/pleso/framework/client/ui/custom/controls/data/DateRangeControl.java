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
import net.pleso.framework.client.dal.db.types.DBDate;
import net.pleso.framework.client.localization.FrameworkLocale;
import net.pleso.framework.client.ui.controls.ValidationErrorControl;
import net.pleso.framework.client.ui.interfaces.IEditableDataControl;
import net.pleso.framework.client.ui.interfaces.IFocusControl;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Data control with two date boxes for range of dates
 * 
 * <h3>CSS Style Rules</h3>
 * <ul>
 * <li>.pf-dateRangeControl {control itself}</li>
 * </ul>
 * 
 * @author Scater
 *
 */
public class DateRangeControl extends Composite implements IEditableDataControl, IFocusControl {
	
	protected CalendarDataControl calendarDataControlStart = new CalendarDataControl();
	protected CalendarDataControl calendarDataControlEnd = new CalendarDataControl();
	private ValidationErrorControl validErr = new ValidationErrorControl();
	
	private VerticalPanel panel = new VerticalPanel();
	
	/**
	 * Constructor
	 */
	public DateRangeControl(){
		this.panel.add(calendarDataControlStart);
		this.panel.setCellWidth(this.calendarDataControlStart, "100%");
		this.panel.add(calendarDataControlEnd);
		this.panel.setCellWidth(this.calendarDataControlEnd, "100%");
		initWidget(this.panel);
		this.setStyleName("pf-dateRangeControl");
	}

	/**
	 * Bind row and range columns
	 * @param row row instance
	 * @param start_column column instance
	 * @param end_column column instance
	 */
	public void bind(IDataRow row, IDataColumn start_column, IDataColumn end_column) {
		calendarDataControlStart.bind(row, start_column);
		calendarDataControlEnd.bind(row, end_column);
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IBindableDataControl#readData()
	 */
	public void readData() {
		calendarDataControlStart.readData();
		calendarDataControlEnd.readData();
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IEditableDataControl#updateData()
	 */
	public void updateData() {
		calendarDataControlStart.updateData();
		calendarDataControlEnd.updateData();
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IEditableDataControl#isValid()
	 */
	public boolean isValid() {
		return calendarDataControlStart.isValid() && calendarDataControlEnd.isValid() && internalValid;
	}

	boolean internalValid = true;
	
	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IEditableDataControl#validate()
	 */
	public void validate() {
		calendarDataControlStart.validate();
		calendarDataControlEnd.validate();
		
		// if validation was successful, must run internal validations also
		if (calendarDataControlStart.isValid() && calendarDataControlEnd.isValid()){
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
		return calendarDataControlStart.isRequired() && calendarDataControlEnd.isRequired();
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IEditableDataControl#setRequired(boolean)
	 */
	public void setRequired(boolean required) {
		calendarDataControlStart.setRequired(required);
		calendarDataControlEnd.setRequired(required);
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
		if (calendarDataControlStart.getValue().isNull() && !calendarDataControlEnd.getValue().isNull()){
			showError(FrameworkLocale.messages().start_range_field_is_required());
		}
		if (!calendarDataControlStart.getValue().isNull() && calendarDataControlEnd.getValue().isNull()){
			showError(FrameworkLocale.messages().end_range_field_is_required());
		}
	}
	
	/**
	 * Validation of rule "start date must be earlier than end date"
	 */
	private void validateStartLessEqualEnd(){
		IDBValue startValue = calendarDataControlStart.getValue();
		IDBValue endValue = calendarDataControlEnd.getValue();
		if (!startValue.isNull() && !endValue.isNull()){
			// the value 0 if the argument Date is equal to this Date; 
			// a value less than 0 if this Date is before the Date argument; 
			// and a value greater than 0 if this Date is after the Date argument.
			if (((DBDate) startValue).getDate().compareTo(((DBDate) endValue).getDate()) > 0){
				showError(FrameworkLocale.messages().startdate_less_or_equal_enddate());
			}
		}
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IFocusControl#setFocus(boolean)
	 */
	public void setFocus(boolean focused) {
		this.calendarDataControlStart.setFocus(focused);
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.SourcesKeyboardEvents#addKeyboardListener(com.google.gwt.user.client.ui.KeyboardListener)
	 */
	public void addKeyboardListener(KeyboardListener listener) {
		this.calendarDataControlStart.addKeyboardListener(listener);
		this.calendarDataControlEnd.addKeyboardListener(listener);
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.SourcesKeyboardEvents#removeKeyboardListener(com.google.gwt.user.client.ui.KeyboardListener)
	 */
	public void removeKeyboardListener(KeyboardListener listener) {
		this.calendarDataControlStart.removeKeyboardListener(listener);
		this.calendarDataControlEnd.removeKeyboardListener(listener);
	}
}
