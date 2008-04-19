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
import com.google.gwt.user.client.ui.KeyboardListenerCollection;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Data control with text area for multi-line text values
 * 
 * <h3>CSS Style Rules</h3>
 * <ul>
 * <li>.pf-textAreaDataControl { control itself }</li>
 * </ul>
 * @author Scater
 *
 */
public class TextAreaDataControl extends Composite implements IEditableDataControl, ISingleColumnBind, IFocusControl, KeyboardListener {
	
	private IDataRow row = null;
	private IDataColumn column = null;
	protected IDBValue value = null;
	private boolean isValid = true;
	
	protected TextArea textArea = new TextArea();
	private HorizontalPanel upPanel = new HorizontalPanel();
	private VerticalPanel panel = new VerticalPanel();
	private RequiredSignControl rsc = new RequiredSignControl();
	private ValidationErrorControl validErr = new ValidationErrorControl();
	
	private KeyboardListenerCollection keyboardListeners;
	
	/**
	 * Constructs not binded text area data control.
	 * 
	 * @param rowsCount count of rows in {@link TextArea} widget
	 */
	public TextAreaDataControl(int rowsCount){
		this.upPanel.add(this.rsc);
		this.textArea.setVisibleLines(rowsCount);
		this.textArea.addKeyboardListener(this);
		this.upPanel.add(this.textArea);
		this.upPanel.setCellWidth(this.textArea, "100%");
		
		this.panel.add(upPanel);
		initWidget(this.panel);
		this.setStyleName("pf-textAreaDataControl");
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
			this.textArea.setText("");
		else
			this.textArea.setText(this.value.getValue());
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IEditableDataControl#updateData()
	 */
	public void updateData() {
		if (this.value != null) {	
			if (this.value.parseValue(this.textArea.getText()))
				row.setCell(this.column, this.value);
		}
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IEditableDataControl#isValid()
	 */
	public boolean isValid() {
		return isValid;
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
	 * Shows validation error.
	 * 
	 * @param errorText error text
	 */
	private void showError(String errorText){
		isValid = false;
		this.validErr.setText(errorText);
		this.panel.add(validErr);
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IEditableDataControl#validate()
	 */
	public void validate() {
		hideError();
		if (this.value != null) {
			if (this.value.parseValue(this.textArea.getText())) {
				this.isValid = !this.isRequired() || !this.value.isNull();
				if (!this.isValid)
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

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IFocusControl#setFocus(boolean)
	 */
	public void setFocus(boolean focused) {
		this.textArea.setFocus(focused);
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.SourcesKeyboardEvents#addKeyboardListener(com.google.gwt.user.client.ui.KeyboardListener)
	 */
	public void addKeyboardListener(KeyboardListener listener) {
		if (keyboardListeners == null)
			keyboardListeners = new KeyboardListenerCollection();
		
		keyboardListeners.add(listener);
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.SourcesKeyboardEvents#removeKeyboardListener(com.google.gwt.user.client.ui.KeyboardListener)
	 */
	public void removeKeyboardListener(KeyboardListener listener) {
		if (keyboardListeners != null)
			keyboardListeners.remove(listener);
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.KeyboardListener#onKeyDown(com.google.gwt.user.client.ui.Widget, char, int)
	 */
	public void onKeyDown(Widget sender, char keyCode, int modifiers) {
		if (keyboardListeners != null && (keyCode != 13 || modifiers != 0))
			keyboardListeners.fireKeyDown(sender, keyCode, modifiers);
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.KeyboardListener#onKeyPress(com.google.gwt.user.client.ui.Widget, char, int)
	 */
	public void onKeyPress(Widget sender, char keyCode, int modifiers) {
		if (keyboardListeners != null && (keyCode != 13 || modifiers != 0))
			keyboardListeners.fireKeyPress(sender, keyCode, modifiers);
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.KeyboardListener#onKeyUp(com.google.gwt.user.client.ui.Widget, char, int)
	 */
	public void onKeyUp(Widget sender, char keyCode, int modifiers) {
		if (keyboardListeners != null && keyCode != 13 || modifiers != 0)
			keyboardListeners.fireKeyUp(sender, keyCode, modifiers);
	}
}
