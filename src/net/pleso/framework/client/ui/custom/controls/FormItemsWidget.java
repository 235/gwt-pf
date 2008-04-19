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

import java.util.ArrayList;

import net.pleso.framework.client.bl.forms.items.FormItemSize;
import net.pleso.framework.client.bl.forms.items.IActionFormItem;
import net.pleso.framework.client.bl.forms.items.IEditColumnFormItem;
import net.pleso.framework.client.bl.forms.items.IEditEnumColumnFormItem;
import net.pleso.framework.client.bl.forms.items.IEditFormItem;
import net.pleso.framework.client.bl.forms.items.IEditRangeFormItem;
import net.pleso.framework.client.bl.forms.items.IFormItem;
import net.pleso.framework.client.bl.forms.items.IFormItemsGroup;
import net.pleso.framework.client.bl.forms.items.IInfoEnumColumnFormItem;
import net.pleso.framework.client.bl.forms.items.IInfoFormItem;
import net.pleso.framework.client.bl.forms.items.IMultilineEditFormItem;
import net.pleso.framework.client.bl.forms.items.IQuickSearchSelectorFormItem;
import net.pleso.framework.client.bl.forms.items.ISelectorFormItem;
import net.pleso.framework.client.dal.IDataRow;
import net.pleso.framework.client.dal.db.IDBValue;
import net.pleso.framework.client.dal.db.types.DBBigInt;
import net.pleso.framework.client.dal.db.types.DBBoolean;
import net.pleso.framework.client.dal.db.types.DBDate;
import net.pleso.framework.client.dal.db.types.DBFloat;
import net.pleso.framework.client.dal.db.types.DBHTMLString;
import net.pleso.framework.client.dal.db.types.DBInteger;
import net.pleso.framework.client.dal.db.types.DBString;
import net.pleso.framework.client.dal.db.types.DBStringTime;
import net.pleso.framework.client.ui.NotImplementedFeatureException;
import net.pleso.framework.client.ui.custom.controls.data.ActionButtonControl;
import net.pleso.framework.client.ui.custom.controls.data.ActionSliderControl;
import net.pleso.framework.client.ui.custom.controls.data.BooleanComboBoxDataControl;
import net.pleso.framework.client.ui.custom.controls.data.BooleanDataControl;
import net.pleso.framework.client.ui.custom.controls.data.CalendarDataControl;
import net.pleso.framework.client.ui.custom.controls.data.DateRangeControl;
import net.pleso.framework.client.ui.custom.controls.data.EnumComboBoxDataControl;
import net.pleso.framework.client.ui.custom.controls.data.FloatDataControl;
import net.pleso.framework.client.ui.custom.controls.data.FloatRangeControl;
import net.pleso.framework.client.ui.custom.controls.data.InfoDataControl;
import net.pleso.framework.client.ui.custom.controls.data.IntegerDataControl;
import net.pleso.framework.client.ui.custom.controls.data.RichTextAreaDataControl;
import net.pleso.framework.client.ui.custom.controls.data.SelectorControl;
import net.pleso.framework.client.ui.custom.controls.data.SuggestBoxSelectorControl;
import net.pleso.framework.client.ui.custom.controls.data.TextAreaDataControl;
import net.pleso.framework.client.ui.custom.controls.data.TextBoxDataControl;
import net.pleso.framework.client.ui.custom.controls.data.TimeRangeControl;
import net.pleso.framework.client.ui.interfaces.IBindableDataControl;
import net.pleso.framework.client.ui.interfaces.IEditableDataControl;
import net.pleso.framework.client.ui.interfaces.IFocusControl;
import net.pleso.framework.client.ui.interfaces.ISingleColumnBind;
import net.pleso.framework.client.ui.interfaces.IUpdateControlListener;
import net.pleso.framework.client.ui.interfaces.IWindow;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SourcesKeyboardEvents;
import com.google.gwt.user.client.ui.Widget;

/**
 * Represents composite widget (grid) that works with {@link IFormItem}
 * instances array. Builds corresponding data controls in itself. Grid has two
 * columns and number of rows that equal length of {@link IFormItem} instances
 * array. In the left column - form item caption. In the right cell -
 * corresponding data control.
 * 
 * <h3>CSS Style Rules</h3>
 * <ul>
 * <li>pf-form-items-widget {widget itself}</li>
 * <li>pf-form-items-widget-caption-column {left column with captions}</li>
 * <li>pf-form-items-widget-control-column {right column with data controls}</li>
 * <li>pf-form-items-widget-row {each row in grid. contains caption and control
 * cells.}</li>
 * <li>pf-form-items-widget-item-caption {caption of each item in widget (text
 * in left column) }</li>
 * </ul>
 * 
 * @author Scater
 * 
 */
public class FormItemsWidget extends Composite {

	/**
	 * Main widget grid that contains data controls
	 */
	private Grid controlsGrid = new Grid(0, 2);

	private KeyboardListener keyboardListener;

	private IUpdateControlListener updateControlListenerer;

	private IWindow parentWindow;

	private Panel slidersPanel;

	/**
	 * Array of data controls in widget
	 */
	private ArrayList controls = new ArrayList();

	/**
	 * Constructs empty {@link FormItemsWidget}.
	 * 
	 * @param parentWindow
	 *            parent {@link IWindow}
	 * @param updateControlListenerer
	 *            a {@link IUpdateControlListener} instance
	 * @param keyboardListener
	 *            a {@link KeyboardListener} instance
	 * @param slidersPanel
	 *            a {@link Panel} where sliders should be placed
	 */
	public FormItemsWidget(IWindow parentWindow,
			IUpdateControlListener updateControlListenerer,
			KeyboardListener keyboardListener, Panel slidersPanel) {

		if (updateControlListenerer == null)
			throw new IllegalArgumentException(
					"updateControlListenerer can't be null.");

		if (keyboardListener == null)
			throw new IllegalArgumentException(
					"keyboardListener can't be null.");

		if (parentWindow == null)
			throw new IllegalArgumentException("parentWindow can't be null.");

		if (slidersPanel == null)
			throw new IllegalArgumentException("slidersPanel can't be null.");

		this.keyboardListener = keyboardListener;
		this.updateControlListenerer = updateControlListenerer;
		this.parentWindow = parentWindow;
		this.slidersPanel = slidersPanel;

		this.initWidget(this.controlsGrid);

		this.setStyleName("pf-form-items-widget");
		this.controlsGrid.getColumnFormatter().setStyleName(0,
				"pf-form-items-widget-caption-column");
		this.controlsGrid.getColumnFormatter().setStyleName(1,
				"pf-form-items-widget-control-column");

	}

	/**
	 * Inits widget whith {@link IFormItemsGroup} and {@link IDataRow}.
	 * 
	 * @param formItemsGroup
	 *            a {@link IFormItemsGroup} instance
	 * @param dataRow
	 *            a {@link IDataRow} instance
	 */
	public void initFormControls(IFormItem[] items, IDataRow dataRow) {

		if (items == null)
			throw new IllegalArgumentException("items parameter can't be null.");

		if (dataRow == null)
			throw new IllegalArgumentException(
					"dataRow parameter can't be null.");

		this.controlsGrid.resizeRows(0);
		this.controls.clear();

		// loop by all form items
		for (int i = 0; i < items.length; i++) {

			// this is local variable, that will contains new data control
			// instance
			IBindableDataControl addedControl = null;

			// Created control depends on form item interface type. Descendant
			// interfaces checked first.

			// If item is action.
			if (items[i] instanceof IActionFormItem) {
				// Item type casting.
				IActionFormItem actionFormItem = (IActionFormItem) items[i];
				// If controls must be small.
				if (actionFormItem.getSize() == FormItemSize.Small) {
					// Creating control.
					ActionButtonControl ctrl = new ActionButtonControl(
							this.parentWindow, actionFormItem.getAction(),
							this.updateControlListenerer);
					// Binding row to control.
					ctrl.bind(dataRow, null);

					addedControl = ctrl;
				} else {
					// Creating control.
					ActionSliderControl ctrl = new ActionSliderControl(
							this.parentWindow, actionFormItem.getAction(),
							this.updateControlListenerer);
					// Binding row to control.
					ctrl.bind(dataRow, null);

					addedControl = ctrl;
				}
			} else
			// If item is quick search selector.
			if (items[i] instanceof IQuickSearchSelectorFormItem) {
				// Interface type cast.
				IQuickSearchSelectorFormItem anItem = (IQuickSearchSelectorFormItem) items[i];

				// Creating control.
				SuggestBoxSelectorControl ctrl = new SuggestBoxSelectorControl(
						anItem.getSearchField(), anItem.getShownRBColumn(), anItem
								.getSelector());

				// Binding row and column to control.
				ctrl.bind(dataRow, anItem.getShownColumn());
				addedControl = ctrl;
			} else
			// If item is selector.
			if (items[i] instanceof ISelectorFormItem) {
				// Interface type cast.
				ISelectorFormItem item = (ISelectorFormItem) items[i];

				// Creating control.
				SelectorControl ctrl = new SelectorControl(this.parentWindow,
						item.getSelector());

				// Binding row and column to control.
				ctrl.bind(dataRow, item.getShownColumn());

				addedControl = ctrl;
			} else
			// If item is enumeration selector.
			if (items[i] instanceof IEditEnumColumnFormItem) {
				// Interface type cast.
				IEditEnumColumnFormItem item = (IEditEnumColumnFormItem) items[i];

				// Creating control.
				EnumComboBoxDataControl ctrl = new EnumComboBoxDataControl(item
						.getEnum());
				// Binding row and column to control.
				ctrl.bind(dataRow, item.getDataColumn());
				addedControl = ctrl;
			} else
			// If this is range edit item.
			if (items[i] instanceof IEditRangeFormItem) {
				// Interface type cast.
				IEditRangeFormItem item = (IEditRangeFormItem) items[i];
				// Getting values for DB type detecting.
				IDBValue valueLow = dataRow.getCell(item
						.getLowBoundDataColumn());
				IDBValue valueHigh = dataRow.getCell(item
						.getHighBoundDataColumn());

				// If this is date range.
				if (valueLow instanceof DBDate && valueHigh instanceof DBDate) {
					DateRangeControl ctrl = new DateRangeControl();
					ctrl.bind(dataRow, item.getLowBoundDataColumn(), item
							.getHighBoundDataColumn());
					addedControl = ctrl;
				}
				// If this is floats range.
				else if (valueLow instanceof DBFloat
						&& valueHigh instanceof DBFloat) {
					FloatRangeControl ctrl = new FloatRangeControl();
					ctrl.bind(dataRow, item.getLowBoundDataColumn(), item
							.getHighBoundDataColumn());
					addedControl = ctrl;
				}
				// If this is time range.
				else if (valueLow instanceof DBStringTime
						&& valueHigh instanceof DBStringTime) {
					TimeRangeControl ctrl = new TimeRangeControl();
					ctrl.bind(dataRow, item.getLowBoundDataColumn(), item
							.getHighBoundDataColumn());
					addedControl = ctrl;
				} else
					throw new NotImplementedFeatureException(
							"There is no implementation to edit range of types "
									+ GWT.getTypeName(valueLow) + " and "
									+ GWT.getTypeName(valueHigh) + ".");
				
			} else
			// If this is simple editable column.
			if (items[i] instanceof IEditColumnFormItem) {
				// Interface type cast.
				IEditColumnFormItem item = (IEditColumnFormItem) items[i];
				// Getting value for DB type detecting.
				IDBValue value = dataRow.getCell(item.getDataColumn());

				ISingleColumnBind ctrl = null;

				// If this is multiline edited item.
				if (item instanceof IMultilineEditFormItem
						&& value instanceof DBString) {
					ctrl = new TextAreaDataControl(
							((IMultilineEditFormItem) item).getRowsCount());
				} else {
					// Creating control depending on retrieved value type.
					if (value instanceof DBHTMLString)
						ctrl = new RichTextAreaDataControl();
					if (value instanceof DBString)
						ctrl = new TextBoxDataControl();
					else if (value instanceof DBInteger)
						ctrl = new IntegerDataControl();
					else if (value instanceof DBBoolean) {
						if (item.isRequired())
							ctrl = new BooleanDataControl();
						else
							ctrl = new BooleanComboBoxDataControl();
					} else if (value instanceof DBBigInt)
						ctrl = new IntegerDataControl();
					else if (value instanceof DBDate)
						ctrl = new CalendarDataControl();
					else if (value instanceof DBStringTime)
						ctrl = new TextBoxDataControl();
					else if (value instanceof DBFloat)
						ctrl = new FloatDataControl();
				}

				// Binding row and column to control.
				ctrl.bind(dataRow, item.getDataColumn());
				addedControl = (IBindableDataControl) ctrl;

			}
			// If this is read only information enumeration item.
			else if (items[i] instanceof IInfoEnumColumnFormItem) {
				// Creating control.
				InfoDataControl ctrl = new InfoDataControl(
						((IInfoEnumColumnFormItem) items[i]).getEnum());
				// Binding row and column to control.
				ctrl.bind(dataRow, ((IInfoFormItem) items[i]).getDataColumn());
				addedControl = ctrl;
			} else
			// If this is read only information item.
			if (items[i] instanceof IInfoFormItem) {
				// Creating control.
				InfoDataControl ctrl = new InfoDataControl();
				// Binding row and column to control.
				ctrl.bind(dataRow, ((IInfoFormItem) items[i]).getDataColumn());
				addedControl = ctrl;
			} else
				// Throwing exception for unknown item type.
				throw new NotImplementedFeatureException(
						"Can't build control for type "
								+ GWT.getTypeName(items[i]) + ".");

			// Throwing exception if no control created.
			if (addedControl == null)
				throw new NullPointerException(
						"Can`t find user control for item type "
								+ GWT.getTypeName(items[i]) + ".");

			// if control and item supports required staus.
			if (items[i] instanceof IEditFormItem
					&& addedControl instanceof IEditableDataControl)
				((IEditableDataControl) addedControl)
						.setRequired(((IEditFormItem) items[i]).isRequired());

			// Check authorization for action controls.
			if (addedControl instanceof ActionButtonControl) {
				if (!((ActionButtonControl) addedControl).isAuth()) {
					continue;
				}
			}
			if (addedControl instanceof ActionSliderControl) {
				if (!((ActionSliderControl) addedControl).isAuth()) {
					continue;
				}
			}

			// Adding controls to controls list.
			controls.add(addedControl);

			if (addedControl instanceof ActionSliderControl) {
				this.slidersPanel.add((ActionSliderControl) addedControl);
			} else {
				// Resizing grid.
				controlsGrid.resizeRows(controlsGrid.getRowCount() + 1);
				// Adding item caption to firsh column.
				HTML itemCaption = new HTML(items[i].getCaption());
				itemCaption.setStyleName("pf-form-items-widget-item-caption");
				controlsGrid.setWidget(controlsGrid.getRowCount() - 1, 0,
						itemCaption);
				// Adding created control to second column.
				controlsGrid.setWidget(controlsGrid.getRowCount() - 1, 1,
						(Widget) addedControl);
				// Setting style.
				controlsGrid.getRowFormatter().setStyleName(
						controlsGrid.getRowCount() - 1,
						"pf-form-items-widget-row");
			}

			// Adding keyboard listener.
			if (addedControl instanceof SourcesKeyboardEvents)
				((SourcesKeyboardEvents) addedControl)
						.addKeyboardListener(keyboardListener);

			// Reading data from row to control.
			addedControl.readData();
		}
	}

	/**
	 * Make validation on all controls.
	 */
	public void validate() {
		for (int i = 0; i < controls.size(); i++)
			if (controls.get(i) instanceof IEditableDataControl)
				((IEditableDataControl) controls.get(i)).validate();
	}

	/**
	 * Returns validation success result.
	 * 
	 * @return <code>true</code> if all form controls' values are valid
	 */
	public boolean isValid() {
		for (int i = 0; i < controls.size(); i++)
			if (controls.get(i) instanceof IEditableDataControl)
				if (!((IEditableDataControl) controls.get(i)).isValid())
					return false;

		return true;
	}

	/**
	 * Hides all windows in child sliders.
	 */
	public void hideSlidersWindows() {
		for (int i = 0; i < controls.size(); i++)
			if (controls.get(i) instanceof ActionSliderControl)
				((ActionSliderControl) controls.get(i)).hideChildWindow();
	}

	/**
	 * Forces bindable controls to read binded data.
	 */
	public void reReadBindableControls() {
		for (int i = 0; i < controls.size(); i++) {
			IBindableDataControl control = (IBindableDataControl) controls
					.get(i);
			if (control != null && !(control instanceof IEditableDataControl))
				control.readData();
		}
	}

	/**
	 * Updates binded {@link #dataRow} by values from all controls.
	 */
	public void updateData() {
		for (int i = 0; i < controls.size(); i++) {
			if (controls.get(i) instanceof IEditableDataControl)
				((IEditableDataControl) controls.get(i)).updateData();
		}
	}

	/**
	 * Makes first {@link IFocusControl} controls in all controlls list to be
	 * focused.
	 * 
	 * @return <code>true</code> if some control was focused
	 */
	public boolean focusOnFirstControl() {
		boolean focused = false;
		for (int i = 0; i < controls.size() && !focused; i++)
			if (controls.get(i) instanceof IFocusControl) {
				((IFocusControl) controls.get(i)).setFocus(true);
				focused = true;
			}
		return focused;
	}

}