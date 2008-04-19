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
package net.pleso.framework.client.ui.custom;

import net.pleso.framework.client.bl.actions.IAddRowExecuter;
import net.pleso.framework.client.bl.actions.IEmptyRowCreator;
import net.pleso.framework.client.bl.actions.IRowLoader;
import net.pleso.framework.client.bl.actions.IUpdateRowExecutor;
import net.pleso.framework.client.bl.auth.AuthorizationProvider;
import net.pleso.framework.client.bl.exceptions.AsyncCallbackFailureException;
import net.pleso.framework.client.bl.forms.IAddForm;
import net.pleso.framework.client.bl.forms.IEditForm;
import net.pleso.framework.client.bl.forms.IForm;
import net.pleso.framework.client.bl.forms.IParametersForm;
import net.pleso.framework.client.bl.forms.IViewForm;
import net.pleso.framework.client.bl.forms.items.IFormItemsGroup;
import net.pleso.framework.client.bl.providers.IAddFormProvider;
import net.pleso.framework.client.dal.IDataRow;
import net.pleso.framework.client.localization.FrameworkLocale;
import net.pleso.framework.client.ui.custom.controls.ActionButtonPanel;
import net.pleso.framework.client.ui.custom.controls.FormItemsGroupWidget;
import net.pleso.framework.client.ui.custom.controls.FormItemsGroupWidgetCollection;
import net.pleso.framework.client.ui.custom.controls.data.ActionSliderControl;
import net.pleso.framework.client.ui.interfaces.IBindableDataControl;
import net.pleso.framework.client.ui.interfaces.IFocusControl;
import net.pleso.framework.client.ui.interfaces.IUpdateControlListener;
import net.pleso.framework.client.ui.windows.Slider;
import net.pleso.framework.client.ui.windows.Window;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Customizable form for any type of forms (see {@link CustomFormType}). Builds
 * action controls (text, numbers, enums, etc.), action sliders (search, edit,
 * insert, etc.) and buttons by business-logic interfaces.
 * 
 * <h3>CSS Style Rules</h3>
 * <ul>
 * <li>.pf-custom-form { custom form itself }</li>
 * <li>.pf-custom-form-header { header text of custom form )</li>
 * <li>.pf-custom-form-status { status text of custom form }</li>
 * <li>.pf-custom-form-datagroups-grid {grid with form items groups}</li>
 * <li>.pf-custom-form-datagroups-grid-cell {each cell in grid with form items
 * groups}</li>
 * </ul>
 * 
 * 
 * @author Scater
 * 
 */
public class CustomFormWindow extends Window implements ClickListener,
		IUpdateControlListener, KeyboardListener {

	/**
	 * Main widget of this composite widget
	 */
	private VerticalPanel panel = new VerticalPanel();

	/**
	 * Grid with form items groups
	 */
	private Grid formItemsGroupsGrid = new Grid();

	/**
	 * Panel with buttons
	 */
	private ActionButtonPanel buttonsPanel = new ActionButtonPanel(
			HorizontalPanel.ALIGN_RIGHT);

	/**
	 * Default cancel button (closes form without changes)
	 */
	private Button btnCancel = new Button(FrameworkLocale.constants()
			.cancel_button_caption(), this);

	/**
	 * Default ok button (comfirm user input)
	 */
	private Button btnOk = new Button(FrameworkLocale.constants()
			.ok_button_caption(), this);

	/**
	 * Default reset button (resets form to initial state)
	 */
	private Button btnReset = new Button(FrameworkLocale.constants()
			.reset_button_caption(), this);

	/**
	 * Default "save and add" button. Used in insert forms. Workes like Ok
	 * button, but after that opens new insert form. Convenient when user
	 * inserts many values into single data source.
	 */
	private Button btnSaveAndAdd = new Button(FrameworkLocale.constants()
			.save_and_add_button_caption(), this);

	/**
	 * Current data row in form
	 */
	private IDataRow dataRow;

	private FormItemsGroupWidgetCollection groupWidgets = null;

	private IForm form = null;

	private IAddForm addForm = null;

	private IEditForm editForm = null;

	private IParametersForm searchForm = null;

	private IViewForm viewForm = null;

	private Boolean canceled = null;

	/**
	 * Indicates that data in form was changed
	 */
	private boolean dataChanged = false;

	/**
	 * Status text of custom form
	 */
	private HTML status = new HTML();

	/**
	 * Header of custom form
	 */
	private HTML header = new HTML();

	/**
	 * Save data callback handler
	 * 
	 * @author Scater
	 */
	private class SaveDataAsyncCallback implements AsyncCallback {

		/**
		 * Form will be shown next after this form
		 */
		private IForm nextForm;

		/**
		 * Constructor
		 * 
		 * @param nextForm
		 *            form will be shown next after this form
		 */
		public SaveDataAsyncCallback(IForm nextForm) {
			this.nextForm = nextForm;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.google.gwt.user.client.rpc.AsyncCallback#onFailure(java.lang.Throwable)
		 */
		public void onFailure(Throwable caught) {
			if (!isHidden()) {
				status.setText(FrameworkLocale.messages().error());
				setButtonsEnable(true);
				throw new AsyncCallbackFailureException(FrameworkLocale
						.messages().asyncerror_save(), caught);
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.google.gwt.user.client.rpc.AsyncCallback#onSuccess(java.lang.Object)
		 */
		public void onSuccess(Object result) {
			if (!isHidden()) {
				// Fix that something changed. This should be able to reload
				// reference book.
				dataChanged = true;

				// If data was saved and handler must show next form
				if (this.nextForm != null) {
					// reset status
					status.setText("");
					// build next form
					buildForm(this.nextForm);
					setButtonsEnable(true);
				} else
					// If next form is not avaliable - only close this form
					hideWindow();
			}
		}
	}

	/**
	 * Constructor for {@link IAddForm}
	 * 
	 * @param parentSlider
	 *            slider to show form in it
	 * @param form
	 *            {@link IAddForm} instance
	 */
	public CustomFormWindow(Slider parentSlider, IAddForm form) {
		this(parentSlider, (IForm) form);
	}

	/**
	 * Constructor for {@link IEditForm}
	 * 
	 * @param parentSlider
	 *            slider to show form in it
	 * @param form
	 *            {@link IEditForm} instance
	 */
	public CustomFormWindow(Slider parentSlider, IEditForm form) {
		this(parentSlider, (IForm) form);
	}

	/**
	 * Constructor for {@link IParametersForm}
	 * 
	 * @param parentSlider
	 *            slider to show form in it
	 * @param form
	 *            {@link IParametersForm} instance
	 */
	public CustomFormWindow(Slider parentSlider, IParametersForm form) {
		this(parentSlider, (IForm) form);
	}

	/**
	 * Constructor for {@link IViewForm}
	 * 
	 * @param parentSlider
	 *            slider to show form in it
	 * @param form
	 *            {@link IViewForm} instance
	 */
	public CustomFormWindow(Slider parentSlider, IViewForm form) {
		this(parentSlider, (IForm) form);
	}

	/**
	 * Universal constructor
	 * 
	 * @param parentSlider
	 *            slider to show form in it
	 * @param form
	 *            {@link IForm} instance
	 */
	private CustomFormWindow(Slider parentSlider, IForm form) {
		super(parentSlider);
		
		if (form == null)
			throw new IllegalArgumentException("Form can`t be null.");

		this.form = form;
		
		// TODO Таб менеджер не вміє міняти заголовки табів. Нюанс у тому, що табний віндоу менеджер забиває в себе кепшн вікна зразу після конструктора і коли він у нас потім поміняється - ефекту не буде.
		this.setCaption(this.form.getCaption());

		// Add header
		this.panel.add(header);
		this.header.setStyleName("pf-custom-form-header");

		// Add status text widget
		this.panel.add(status);
		this.status.setStyleName("pf-custom-form-status");

		// Add grid for data groups
		this.panel.add(formItemsGroupsGrid);
		this.formItemsGroupsGrid.setStyleName("pf-custom-form-datagroups-grid");

		// init composite widget
		initWidget(this.panel);
		this.setStyleName("pf-custom-form");
	}

	/**
	 * Adds button panel into main widget if it was not added
	 */
	private void addButtonsPanel() {
		// if panel with buttons is not added
		if (this.panel.getWidgetIndex(buttonsPanel) == -1) {
			this.panel.add(buttonsPanel);
		}
	}

	/**
	 * Sets enabled for action buttons
	 * 
	 * @param enabled
	 *            is enabled
	 */
	private void setButtonsEnable(boolean enabled) {
		this.btnOk.setEnabled(enabled);
		this.btnSaveAndAdd.setEnabled(enabled);
		this.btnReset.setEnabled(enabled);
	}

	/**
	 * Build panel with buttons
	 */
	private void buildButtonsPanel() {
		this.buttonsPanel.clear();

		if (form instanceof IAddFormProvider) {
			// check authorization
			if (AuthorizationProvider
					.isObjectAuthorized(((IAddFormProvider) form)
							.getAddForm(null)))
				buttonsPanel.add(this.btnSaveAndAdd);
		}

		if (this.searchForm != null || this.addForm != null
				|| this.editForm != null)
			this.buttonsPanel.add(this.btnOk);

		this.buttonsPanel.add(this.btnReset);
		this.buttonsPanel.add(this.btnCancel);
	}

	/**
	 * Builds form by {@link IForm}
	 * 
	 * @param form
	 *            {@link IForm} instance
	 */
	private void buildForm(IForm form) {
		if (form == null)
			throw new IllegalArgumentException("Form can`t be null.");

		this.form = form;

		// check possible interface implementation
		if (form instanceof IAddForm)
			this.addForm = (IAddForm) form;
		else
			this.addForm = null;

		if (form instanceof IEditForm)
			this.editForm = (IEditForm) form;
		else
			this.editForm = null;

		if (form instanceof IParametersForm)
			this.searchForm = (IParametersForm) form;
		else
			this.searchForm = null;

		if (form instanceof IViewForm)
			this.viewForm = (IViewForm) form;
		else
			this.viewForm = null;

		// Sets header and form caption
		this.header.setText(this.form.getCaption());
		this.setCaption(this.form.getCaption());

		// Builds button panel
		buildButtonsPanel();

		// Build form depending on implemented interfaces
		if (this.addForm != null) {
			createFormForEmptyRow(this.addForm);
		} else if (this.editForm != null) {
			createFormForExistingRow(this.editForm);
		} else if (this.searchForm != null) {
			createFormForEmptyRow(this.searchForm);
		} else if (this.viewForm != null) {
			createFormForExistingRow(this.viewForm);
		}
	}

	/**
	 * Builds form for empty row.
	 * 
	 * @param rowCreator
	 *            {@link IEmptyRowCreator} instance for creating empty row
	 */
	private void createFormForEmptyRow(IEmptyRowCreator rowCreator) {
		this.dataRow = null;

		if (rowCreator != null)
			this.dataRow = rowCreator.createEmptyRow();

		if (this.dataRow == null)
			throw new NullPointerException(FrameworkLocale.messages()
					.error_emptyrowisnull());

		buildFormItemsGroups();
		addButtonsPanel();

		// size of form is changed - scroll it to top
		scrollToTop();
	}

	/**
	 * Builds form for existing row (for edit forms). Loads row from
	 * asynchronous {@link IRowLoader} instance.
	 * 
	 * @param rowLoader
	 *            {@link IRowLoader} instance
	 */
	private void createFormForExistingRow(IRowLoader rowLoader) {
		// Set "loading" status
		status.setText(FrameworkLocale.messages().loading());
		setButtonsEnable(false);

		// Loading data row
		rowLoader.GetData(new AsyncCallback() {

			public void onFailure(Throwable caught) {
				if (!isHidden()) {
					status.setText(FrameworkLocale.messages().error());
					throw new AsyncCallbackFailureException(FrameworkLocale
							.messages().asyncerror_loadrow(), caught);
				}
			}

			public void onSuccess(Object result) {
				if (!isHidden()) {
					// Sets result row
					dataRow = (IDataRow) result;
					// Reset status
					status.setText("");
					buildFormItemsGroups();
					setButtonsEnable(true);
					addButtonsPanel();
					// size of form is changed - scroll it to top
					scrollToTop();
					focusOnFirstControl();
				}
			}
		});
	}

	/**
	 * Builds form item groups by {@link IFormItemsGroup} array in form
	 */
	private void buildFormItemsGroups() {
		// Removing all sliders.
		for (int i = 0; i < this.panel.getWidgetCount();)
			if (this.panel.getWidget(i) instanceof ActionSliderControl)
				this.panel.remove(i);
			else
				i++;

		// Clears grid with groups
		this.formItemsGroupsGrid.clear();
		// Gets groups array from form
		IFormItemsGroup[] groups = form.getGroups();
		// create
		this.groupWidgets = new FormItemsGroupWidgetCollection();
		// check data row
		if (this.dataRow == null)
			throw new NullPointerException(FrameworkLocale.messages()
					.error_rowisnull());
		// Builds groups in group grid:
		for (int i = 0; i < groups.length; i++) {
			if (groups[i] == null)
				throw new IllegalArgumentException("Group cann`t be null.");

			// Create FormItemsGroupWidget for this group.
			FormItemsGroupWidget groupWidget = new FormItemsGroupWidget(this,
					this, this, this.panel);
			groupWidget.initFormItemsGroup(groups[i], this.dataRow);
			this.groupWidgets.addFormItemsGroupWidget(groupWidget);

			// Adds group to grid
			if (i == 0) {
				// if this is first group, than create first row
				this.formItemsGroupsGrid.resize(1, 2);
				this.formItemsGroupsGrid.getCellFormatter().setStyleName(0, 0,
						"pf-custom-form-datagroups-grid-cell");
				// sets into first cell of first row current group widget
				this.formItemsGroupsGrid.setWidget(0, 0, groupWidget);
			} else {
				if ((i % 2) > 0) {
					// If index of group in not paired number (widget must be on
					// right), then
					// put group widget into right cell (grid row must be exists
					// already).
					this.formItemsGroupsGrid.setWidget(this.formItemsGroupsGrid
							.getRowCount() - 1, 1, groupWidget);
					this.formItemsGroupsGrid.getCellFormatter().setStyleName(
							this.formItemsGroupsGrid.getRowCount() - 1, 1,
							"pf-custom-form-datagroups-grid-cell");
				} else {
					// If index of group in paired number (widget must be on
					// left), then
					// create new row in grid.
					this.formItemsGroupsGrid
							.resizeRows(this.formItemsGroupsGrid.getRowCount() + 1);
					// put group widget into left cell of new row
					this.formItemsGroupsGrid.setWidget(this.formItemsGroupsGrid
							.getRowCount() - 1, 0, groupWidget);
					this.formItemsGroupsGrid.getCellFormatter().setStyleName(
							this.formItemsGroupsGrid.getRowCount() - 1, 0,
							"pf-custom-form-datagroups-grid-cell");
				}
			}
		}
	}

	/**
	 * Updates {@link #dataRow} by values from all form controls.
	 */
	private void updateAllData() {
		this.groupWidgets.updateAllData();
	}

	/**
	 * Forces bindable controls to read binded data.
	 */
	private void readReadableControls() {
		this.groupWidgets.reReadBindableControls();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user.client.ui.Widget)
	 */
	public void onClick(Widget sender) {
		// If cancel button.
		if (sender == this.btnCancel) {
			// Setting cancelled status.
			this.canceled = Boolean.TRUE;
			// Hiding window.
			hideWindow();
		} else
		// If "save" button pressed.
		if (sender == this.btnOk || sender == this.btnSaveAndAdd) {
			// Disabling action buttons.
			setButtonsEnable(false);
			// Setting not cancelled status.
			this.canceled = Boolean.FALSE;
			// If validation us successful.
			if (internalValidate()) {
				// Update form data row by control values.
				updateAllData();

				// If form can create new entity then preparing next form for
				// "save and add new" feature.
				IForm nextForm = null;
				if (sender == this.btnSaveAndAdd
						&& this.form instanceof IAddFormProvider)
					nextForm = ((IAddFormProvider) this.form)
							.getAddForm(this.dataRow);

				// Calling appropriate method.
				if (this.addForm != null) {
					insertRow(nextForm);
				} else if (this.editForm != null) {
					editRow(nextForm);
				} else if (this.searchForm != null) {
					// if this was search form then hiding it.
					hideWindow();
				}
			} else {
				// If validation failed enabling buttons to continue user's
				// work.
				setButtonsEnable(true);
			}
		} else
		// If "reset" button pressed then rebuilding form.
		if (sender == this.btnReset) {
			buildForm(this.form);
		}
	}

	/**
	 * Inserts row to specified by form data set.
	 * 
	 * @param nextForm
	 *            next form to build after operation success. Can be null if no
	 *            next form expected.
	 */
	private void insertRow(IForm nextForm) {
		// Checking form for implementing required interface.
		if ((form instanceof IAddRowExecuter) == false)
			throw new UnsupportedOperationException(
					"Can't insert row. Form is not implement IAddRowExecuter.");

		// Setting visual "loading" status.
		status.setText(FrameworkLocale.messages().loading());
		// Executing add operation.
		((IAddRowExecuter) form).addRow(dataRow, new SaveDataAsyncCallback(
				nextForm));
	}

	/**
	 * Updates row in specified by form data set.
	 * 
	 * @param nextForm
	 *            next form to build after operation success. Can be null if no
	 *            next form expected.
	 */
	private void editRow(IForm nextForm) {
		// Checking form for implementing required interface.
		if ((form instanceof IUpdateRowExecutor) == false)
			throw new UnsupportedOperationException(
					"Can't update row. Form is not implement IUpdateRowExecutor.");

		// Setting visual "loading" status.
		status.setText(FrameworkLocale.messages().loading());
		// Executing add operation.
		((IUpdateRowExecutor) form).updateRow(dataRow,
				new SaveDataAsyncCallback(nextForm));
	}

	/**
	 * Validates form input controls and returns validation success result.
	 * 
	 * @return <code>true</code> if all form controls' values are valid
	 */
	private boolean internalValidate() {
		return this.groupWidgets.validate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.pleso.framework.client.ui.interfaces.IUpdateControlListener#controlUpdated(net.pleso.framework.client.ui.interfaces.IBindableDataControl)
	 */
	public void controlUpdated(IBindableDataControl sender) {
		readReadableControls();
	}

	/**
	 * @return a {@link IDataRow} being edited in form
	 */
	public IDataRow getDataRow() {
		return dataRow;
	}

	/**
	 * @return a {@link CustomFormType} indicating form type
	 */
	public CustomFormType getCustomFormType() {

		if (this.addForm != null)
			return CustomFormType.Insert;
		else if (this.editForm != null)
			return CustomFormType.Edit;
		else if (this.searchForm != null)
			return CustomFormType.Search;
		else if (this.viewForm != null)
			return CustomFormType.Show;

		return null;
	}

	/**
	 * @return <code>true</code> if form changed data set
	 */
	public boolean dataChanged() {
		return this.dataChanged;
	}

	/**
	 * @return <code>true</code> if form was closed because of cancel
	 *         operation; <code>false</code> if form was closed on user "ok"
	 *         button click.
	 */
	public boolean isCanceled() {
		if (canceled != null)
			return canceled.booleanValue();
		else
			// If cancelled status not set explicitly than it is cancelled in
			// deed.
			return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.pleso.framework.client.ui.windows.Window#hideWindow()
	 */
	public void hideWindow() {
		// Hiding all windows in chils sliders.
		this.groupWidgets.hideWindows();

		// If cancelled status not set then setting it to true.
		if (canceled == null)
			canceled = Boolean.TRUE;

		super.hideWindow();
	}

	/**
	 * Makes first {@link IFocusControl} controls in all controlls list to be
	 * focused.
	 */
	private void focusOnFirstControl() {
		if (this.groupWidgets != null)
			this.groupWidgets.focusOnFirstControl();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.pleso.framework.client.ui.windows.Window#showEvent()
	 */
	protected void showEvent() {
        // build this form
		buildForm(this.form);
		focusOnFirstControl();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.KeyboardListener#onKeyDown(com.google.gwt.user.client.ui.Widget,
	 *      char, int)
	 */
	public void onKeyDown(Widget sender, char keyCode, int modifiers) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.KeyboardListener#onKeyPress(com.google.gwt.user.client.ui.Widget,
	 *      char, int)
	 */
	public void onKeyPress(Widget sender, char keyCode, int modifiers) {
		switch (keyCode) {
		case 13:
		case 10:
			onClick(this.btnOk);
			break;
		case 27:
			onClick(this.btnCancel);
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.KeyboardListener#onKeyUp(com.google.gwt.user.client.ui.Widget,
	 *      char, int)
	 */
	public void onKeyUp(Widget sender, char keyCode, int modifiers) {
	}
}
