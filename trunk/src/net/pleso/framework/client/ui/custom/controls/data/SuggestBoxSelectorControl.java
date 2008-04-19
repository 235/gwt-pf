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

import java.util.ArrayList;

import net.pleso.framework.client.bl.ISelector;
import net.pleso.framework.client.bl.exceptions.AsyncCallbackFailureException;
import net.pleso.framework.client.bl.forms.IParametersForm;
import net.pleso.framework.client.bl.forms.items.IEditColumnFormItem;
import net.pleso.framework.client.bl.forms.items.IFormItem;
import net.pleso.framework.client.bl.forms.items.IFormItemsGroup;
import net.pleso.framework.client.bl.providers.IActionProvider;
import net.pleso.framework.client.bl.providers.IActionProviders;
import net.pleso.framework.client.bl.providers.ISearchFormProvider;
import net.pleso.framework.client.bl.rb.IRB;
import net.pleso.framework.client.bl.rb.columns.IRBDataColumn;
import net.pleso.framework.client.dal.IDataColumn;
import net.pleso.framework.client.dal.IDataRow;
import net.pleso.framework.client.dal.SelectParams;
import net.pleso.framework.client.dal.db.IDBValue;
import net.pleso.framework.client.dal.db.types.DBString;
import net.pleso.framework.client.localization.FrameworkLocale;
import net.pleso.framework.client.ui.controls.RequiredSignControl;
import net.pleso.framework.client.ui.controls.ValidationErrorControl;
import net.pleso.framework.client.ui.interfaces.IEditableDataControl;
import net.pleso.framework.client.ui.interfaces.IFocusControl;
import net.pleso.framework.client.ui.interfaces.ISingleColumnBind;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.KeyboardListenerCollection;
import com.google.gwt.user.client.ui.SourcesKeyboardEvents;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.SuggestionEvent;
import com.google.gwt.user.client.ui.SuggestionHandler;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.SuggestOracle.Callback;
import com.google.gwt.user.client.ui.SuggestOracle.Request;
import com.google.gwt.user.client.ui.SuggestOracle.Response;

/**
 * Selector data control based on {@link SuggestBox} In this control user choose
 * value from external reference book (f.e. by foreigh key). Contains
 * {@link SuggestBox} and button "Clear" optionally for null values
 * 
 * <h3>CSS Style Rules</h3>
 * <ul>
 * <li>.pf-suggestBox-selectorControl { control itself }</li>
 * </ul>
 * 
 * @author Scater
 * 
 */
public class SuggestBoxSelectorControl extends Composite implements
		ClickListener, IFocusControl, SuggestionHandler, FocusListener,
		SourcesKeyboardEvents, KeyboardListener, IEditableDataControl, ISingleColumnBind {

	private class SelectorSuggestion implements SuggestOracle.Suggestion {

		private IDataRow dataRow;

		public SelectorSuggestion(IDataRow dataRow) {
			this.dataRow = dataRow;
		}

		public String getDisplayString() {
			return this.dataRow.getCell(shownRBColumn.getDataColumn())
					.getValue();
		}

		public String getReplacementString() {
			return this.getDisplayString();
		}

		public IDataRow getDataRow() {
			return this.dataRow;
		}

	}

	private class SelectorSuggestOracle extends SuggestOracle {

		public void requestSuggestions(Request request, Callback callback) {
			doRequest(request.getQuery(), request.getLimit(), request, callback);
		}

	}

	private abstract class OracleAsyncCallback implements AsyncCallback {

		private Callback callback;
		private Request request;

		public OracleAsyncCallback(Request request, Callback callback) {
			this.callback = callback;
			this.request = request;
		}

		public abstract void onFailure(Throwable caught);

		public abstract void onSuccess(Object result);

		public Callback getCallback() {
			return callback;
		}

		public Request getRequest() {
			return request;
		}

	}

	private IEditColumnFormItem searchField;
	private IRBDataColumn shownRBColumn;
	private ISelector selector;

	private HorizontalPanel upPanel = new HorizontalPanel();
	private Button btnClear = new Button(FrameworkLocale.constants()
			.clear_button_caption(), this);

	private SelectorSuggestOracle oracle = new SelectorSuggestOracle();
	private SuggestBox suggestBox = new SuggestBox(oracle);
	
	private KeyboardListenerCollection keyboardListeners;

	private IParametersForm searchForm;

	/**
	 * Make request to bl-layer to show drop-down list from external reference
	 * book
	 * 
	 * @param query
	 * @param limit
	 * @return
	 */
	private void doRequest(String query, int limit, Request request,
			Callback callback) {
		// request form to create empty row for search
		IDataRow row = searchForm.createEmptyRow();
		// set to row value from user
		row.setCell(this.searchField.getDataColumn(), new DBString(query));
		SelectParams params = new SelectParams();
		params.setLimit(limit);
		params.setSearchRow(row);

		OracleAsyncCallback asynccallback = new OracleAsyncCallback(request,
				callback) {

			public void onSuccess(Object result) {
				ArrayList res = new ArrayList();
				IDataRow[] rows = (IDataRow[]) result;
				for (int i = 0; i < rows.length; i++) {
					res.add(new SelectorSuggestion(rows[i]));
				}
				Response response = new Response(res);
				this.getCallback().onSuggestionsReady(this.getRequest(),
						response);
			}

			public void onFailure(Throwable caught) {
				throw new AsyncCallbackFailureException(FrameworkLocale
						.messages().asyncerror_loadrowscount(), caught);
			}

		};

		this.selector.getRB(this.row).getDataSource().select(params,
				asynccallback);
	}

	private void initSearchForm() {
		// gets rb from ISelectorFormItem
		IRB rb = this.selector.getRB(this.row);
		// search business logic search-form with column, that method
		// getSearchField() returns
		if (!(rb instanceof IActionProviders)) {
			throw new RuntimeException(
					"Can't find search form. RB must implement IActionProviders");
		}
		IActionProvider[] providers = ((IActionProviders) rb)
				.getActionProviders();
		for (int i = 0; i < providers.length; i++) {
			IActionProvider prov = providers[i];
			if (prov instanceof ISearchFormProvider) {
				IFormItemsGroup[] groups = ((ISearchFormProvider) prov)
						.getSearchForm().getGroups();
				if (this.initSearchFormByGroups(groups)) {
					this.searchForm = ((ISearchFormProvider) prov)
							.getSearchForm();
					return;
				}
			}
		}
		if (searchForm == null) {
			throw new RuntimeException(
					"Can't find search form with search field.");
		}
	}

	private boolean initSearchFormByGroups(IFormItemsGroup[] groups) {
		for (int i = 0; i < groups.length; i++) {
			IFormItem[] items = ((IFormItemsGroup) groups[i]).getItems();
			for (int j = 0; j < items.length; j++) {
				if (items[j].equals(this.searchField)) {
					return true;
				}
			}
		}

		return false;
	}

	public SuggestBoxSelectorControl(IEditColumnFormItem searchField,
			IRBDataColumn shownRBColumn, ISelector selector) {

		if (searchField == null)
			throw new IllegalArgumentException(
					"searchField form item argument can't be null");

		if (shownRBColumn == null)
			throw new IllegalArgumentException(
					"shownRBColumn form item argument can't be null");

		if (selector == null)
			throw new IllegalArgumentException(
					"selector form item argument can't be null");

		this.searchField = searchField;
		this.selector = selector;
		this.shownRBColumn = shownRBColumn;

		this.suggestBox.addEventHandler(this);
		this.suggestBox.addFocusListener(this);
		this.suggestBox.addKeyboardListener(this);

		this.initSearchForm();

		this.upPanel.add(this.rsc);
		this.upPanel.add(this.suggestBox);
		this.upPanel.setCellWidth(this.suggestBox, "100%");
		
		// Initially status is not required so clear button must be placed.
		this.addClearButton();

		this.panel.add(upPanel);

		initWidget(this.panel);

		this.setStyleName("pf-suggestBox-selectorControl");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user.client.ui.Widget)
	 */
	public void onClick(Widget sender) {
		if (sender == this.btnClear) {
			this.selector.setNull(this.row);
			this.readData();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.pleso.framework.client.ui.interfaces.IBindableDataControl#readData()
	 */
	public void readData() {
		this.value = row.getCell(this.column);
		if (value.isNull())
			this.suggestBox.setText("");
		else
			this.suggestBox.setText(this.value.getValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.pleso.framework.client.ui.interfaces.IEditableDataControl#updateData()
	 */
	public void updateData() {
		// this is empty method for this component because
		// when data from external reference book was selected it must save into
		// row
	}

	protected boolean internalParseValue() {
		return this.value.parseValue(this.suggestBox.getText());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.pleso.framework.client.ui.interfaces.IEditableDataControl#validate()
	 */
	public void validate() {
		hideError();

		if (this.value == null)
			throw new IllegalArgumentException(
					"Control is not properly binded.");

		if (this.internalParseValue()) {
			this.valid = !this.isRequired() || !this.value.isNull();
			if (this.valid == false)
				showError(FrameworkLocale.messages().field_is_required(
						this.column.getCaption()));
		} else
			showError(FrameworkLocale.messages().bad_value_in_field(
					this.column.getCaption()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.pleso.framework.client.ui.interfaces.IEditableDataControl#setRequired(boolean)
	 */
	public void setRequired(boolean required) {
		if (required != this.isRequired()) {
			this.rsc.setRequired(required);

			if (!required) {
				this.addClearButton();
			} else {
				this.upPanel.remove(this.btnClear);
			}
		}
	}
	
	private void addClearButton() {
		this.upPanel.add(this.btnClear);
		this.upPanel.setCellWidth(this.btnClear, "70px");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.pleso.framework.client.ui.interfaces.IFocusControl#setFocus(boolean)
	 */
	public void setFocus(boolean focused) {
		this.suggestBox.setFocus(focused);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.SourcesKeyboardEvents#addKeyboardListener(com.google.gwt.user.client.ui.KeyboardListener)
	 */
	public void addKeyboardListener(KeyboardListener listener) {
		if (keyboardListeners == null) {
			keyboardListeners = new KeyboardListenerCollection();
		}
		keyboardListeners.add(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.SourcesKeyboardEvents#removeKeyboardListener(com.google.gwt.user.client.ui.KeyboardListener)
	 */
	public void removeKeyboardListener(KeyboardListener listener) {
		if (keyboardListeners != null) {
			keyboardListeners.remove(listener);
		}
	}

	public void onSuggestionSelected(SuggestionEvent event) {
		this.selector.CopyRow(this.row, ((SelectorSuggestion) event
				.getSelectedSuggestion()).getDataRow());
		this.readData();
	}

	public void onFocus(Widget sender) {
		// Do nothing.
	}

	public void onLostFocus(Widget sender) {
		this.readData();
	}

	public void onKeyDown(Widget sender, char keyCode, int modifiers) {
		if (keyboardListeners != null && ((keyCode != 13 && keyCode != 27) || modifiers != 0))
			this.keyboardListeners.fireKeyDown(sender, keyCode, modifiers);
	}

	public void onKeyPress(Widget sender, char keyCode, int modifiers) {
		if (keyboardListeners != null && ((keyCode != 13 && keyCode != 27) || modifiers != 0))
			this.keyboardListeners.fireKeyPress(sender, keyCode, modifiers);
	}

	public void onKeyUp(Widget sender, char keyCode, int modifiers) {
		if (keyboardListeners != null && ((keyCode != 13 && keyCode != 27) || modifiers != 0))
			this.keyboardListeners.fireKeyUp(sender, keyCode, modifiers);
	}
	
	protected IDataRow row = null;
	protected IDataColumn column = null;
	protected IDBValue value = null;
	protected IDBValue initialValue = null;
	protected boolean valid = true;
	
	protected VerticalPanel panel = new VerticalPanel();
	protected RequiredSignControl rsc = new RequiredSignControl();
	protected ValidationErrorControl validErr = new ValidationErrorControl();

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.pleso.framework.client.ui.interfaces.ISingleColumnBind#bind(net.pleso.framework.client.dal.IDataRow,
	 *      net.pleso.framework.client.dal.IDataColumn)
	 */
	public void bind(IDataRow row, IDataColumn column) {
		this.row = row;
		this.column = column;

		this.value = row.getCell(this.column);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.pleso.framework.client.ui.interfaces.IEditableDataControl#isValid()
	 */
	public boolean isValid() {
		return valid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.pleso.framework.client.ui.interfaces.IEditableDataControl#fixInitialValue()
	 */
	public void fixInitialValue() {
		if (this.row == null || this.column == null)
			throw new IllegalArgumentException(
					"Using fixInitialValue() before proper binding.");

		this.initialValue = this.row.getCell(this.column);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.pleso.framework.client.ui.interfaces.IEditableDataControl#hasChanges()
	 */
	public boolean hasChanges() {
		if (internalParseValue()) {
			return !this.value.getValue().equals(this.initialValue.getValue());
		} else
			return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.pleso.framework.client.ui.interfaces.IEditableDataControl#isRequired()
	 */
	public boolean isRequired() {
		return this.rsc.isRequired();
	}

	/**
	 * Hides validation error.
	 */
	protected void hideError() {
		if (this.panel.getWidgetIndex(validErr) != -1)
			this.panel.remove(validErr);
		this.validErr.setText("");
	}

	/**
	 * Shows validation error.
	 * 
	 * @param errorText
	 *            error text
	 */
	protected void showError(String errorText) {
		this.valid = false;
		this.validErr.setText(errorText);
		this.panel.add(validErr);
	}

	/**
	 * Gets current value in control.
	 * 
	 * @return current value
	 */
	public IDBValue getValue() {
		return this.value;
	}
}