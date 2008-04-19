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
import net.pleso.framework.client.ui.controls.RequiredSignControl;
import net.pleso.framework.client.ui.controls.ValidationErrorControl;
import net.pleso.framework.client.ui.controls.calendar.CalendarPopup;
import net.pleso.framework.client.ui.interfaces.IEditableDataControl;
import net.pleso.framework.client.ui.interfaces.IFocusControl;
import net.pleso.framework.client.ui.interfaces.ISingleColumnBind;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Calendar data control to manipilate values of Date type in custom forms.
 * 
 * <h3>CSS Style Rules</h3>
 * <ul>
 * <li>.pf-calendarDataControl {widget itself}</li>
 *  </ul>
 * 
 * @author Scater
 *
 */
public class CalendarDataControl extends Composite implements IEditableDataControl, ISingleColumnBind, IFocusControl {
	
	private IDataRow row = null;
	private IDataColumn column = null;
	private IDBValue value = null;
	private boolean valid = true;
	
	protected CalendarPopup calendarPopup = new CalendarPopup();
	private HorizontalPanel upPanel = new HorizontalPanel();
	private VerticalPanel panel = new VerticalPanel();
	private RequiredSignControl rsc = new RequiredSignControl();
	private ValidationErrorControl validErr = new ValidationErrorControl();
	
	/**
	 * Constructor
	 */
	public CalendarDataControl(){
		this.upPanel.add(this.rsc);
		this.upPanel.add(this.calendarPopup);
		this.upPanel.setCellWidth(this.calendarPopup, "100%");
		
		this.panel.add(upPanel);
		initWidget(this.panel);
		this.setStyleName("pf-calendarDataControl");
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.ISingleColumnBind#bind(net.pleso.framework.client.dal.interfaces.IDataRow, net.pleso.framework.client.dal.interfaces.IDataColumn)
	 */
	public void bind(IDataRow row, IDataColumn column) {
		this.row = row;
		this.column = column;
		
		this.value = row.getCell(this.column);
		if ((this.value instanceof DBDate) == false){
			throw new RuntimeException("CalendarDataControl works only with DBDate value");
		}
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IBindableDataControl#readData()
	 */
	public void readData() {
		this.value = row.getCell(this.column);
		if (value.isNull() == false)
			this.calendarPopup.setDate(((DBDate) this.value).getDate());
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IEditableDataControl#updateData()
	 */
	public void updateData() {
		if (this.value != null) {
			if (internalParseValue())
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
	 * Hides validation error.
	 */
	private void hideError(){
		if (this.panel.getWidgetIndex(validErr) != -1)
			this.panel.remove(validErr);
		this.validErr.setText("");
	}
	
	/**
	 * Shows validation error
	 * @param errorText text of validation error
	 */
	private void showError(String errorText){
		valid = false;
		this.validErr.setText(errorText);
		this.panel.add(validErr);
	}
	
	/**
	 * Parse {@link IDBValue} in data control.
	 * Gets value from {@link CalendarPopup} widget
	 * @return is valid value 
	 */
	private boolean internalParseValue(){
		return this.value.parseValue(this.calendarPopup.getText());
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IEditableDataControl#validate()
	 */
	public void validate() {
		hideError();
		if (this.value != null) {
			if (internalParseValue()) {
				this.valid = !isRequired() || !this.value.isNull();
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
	 * Gets current {@link IDBValue} in data control.
	 * This method use external controls for view date values range.
	 * @return current value
	 */
	public IDBValue getValue(){
		return value;
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IFocusControl#setFocus(boolean)
	 */
	public void setFocus(boolean focused) {
		this.calendarPopup.setFocus(focused);
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.SourcesKeyboardEvents#addKeyboardListener(com.google.gwt.user.client.ui.KeyboardListener)
	 */
	public void addKeyboardListener(KeyboardListener listener) {
		this.calendarPopup.addKeyboardListener(listener);
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.SourcesKeyboardEvents#removeKeyboardListener(com.google.gwt.user.client.ui.KeyboardListener)
	 */
	public void removeKeyboardListener(KeyboardListener listener) {
		this.calendarPopup.removeKeyboardListener(listener);
	}
}
