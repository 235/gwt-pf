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

import net.pleso.framework.client.bl.providers.IActionProvider;
import net.pleso.framework.client.bl.providers.IActionProviders;
import net.pleso.framework.client.bl.providers.IDeleteRowProvider;
import net.pleso.framework.client.bl.providers.ISearchFormProvider;
import net.pleso.framework.client.bl.providers.IViewFormProvider;
import net.pleso.framework.client.bl.rb.IRB;
import net.pleso.framework.client.bl.rb.IRowClassifiersProvider;
import net.pleso.framework.client.dal.IDataRow;
import net.pleso.framework.client.dal.SelectParams;
import net.pleso.framework.client.localization.FrameworkLocale;
import net.pleso.framework.client.ui.controls.datagrid.DataGrid;
import net.pleso.framework.client.ui.controls.datagrid.interfaces.IDataGridEventListener;
import net.pleso.framework.client.ui.custom.controls.ActionButtonPanel;
import net.pleso.framework.client.ui.custom.controls.data.ActionButtonControl;
import net.pleso.framework.client.ui.custom.controls.data.ActionSliderControl;
import net.pleso.framework.client.ui.custom.controls.datagrid.DataGridWrapper;
import net.pleso.framework.client.ui.interfaces.HideWindowListener;
import net.pleso.framework.client.ui.interfaces.IBindableDataControl;
import net.pleso.framework.client.ui.interfaces.IUpdateControlListener;
import net.pleso.framework.client.ui.interfaces.IWindow;
import net.pleso.framework.client.ui.windows.Slider;
import net.pleso.framework.client.ui.windows.Window;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Customizable form for reference books. Builds action sliders (search, edit,
 * insert, etc.), buttons and {@link DataGridWrapper} by business-logic
 * interfaces (see {@link IRB} etc.)
 * 
 * <h3>CSS Style Rules</h3>
 * <ul>
 * <li>pf-customrb { custom reference book form itself }</li> 
 * <li>pf-customrb-header { form header }</li>
 * </ul>
 * 
 * @author Scater
 * 
 */
public class CustomRBWindow extends Window implements ClickListener,
		HideWindowListener, IDataGridEventListener, IUpdateControlListener {

	/**
	 * Main widget of this composite widget (see {@link Window}).
	 */
	private VerticalPanel panel = new VerticalPanel();

	/**
	 * Panel with buttons added by actions.
	 */
	private ActionButtonPanel buttonsPanel = new ActionButtonPanel(HorizontalPanel.ALIGN_LEFT);

	/**
	 * Default close form button.
	 */
	private Button btnClose = new Button(FrameworkLocale.constants()
			.close_button_caption(), this);

	/**
	 * Default select button for selector forms (CustomRBType.Select).
	 */
	private Button btnSelect = new Button(FrameworkLocale.constants()
			.select_button_caption(), this);
	
	/**
	 * Default refresh button.
	 */
	private Button btnRefresh = new Button(FrameworkLocale.constants()
			.refresh_button_caption(), this);

	/**
	 * {@link DataGridWrapper} instance for data view.
	 */
	private DataGridWrapper dataGridWrapper;

	/**
	 * Header of form.
	 */
	private HTML header = new HTML();

	/**
	 * Business-logic class of reference book.
	 */
	private IRB rb;

	/**
	 * If forceSearch = true, reference book not loads the data and shows search
	 * form If forceSearch = false, reference book loads the data immediately.
	 */
	private boolean forceSearch = false;

	/**
	 * Type of custom form
	 */
	private CustomRBType type = CustomRBType.Normal;

	/**
	 * Current selected row in reference book
	 */
	private IDataRow selectedRow;
	
	/**
	 * Array of buttons by actions
	 */
	private ActionButtonControl[] actionButtons;

	/**
	 * Array of sliders by actions
	 */
	private ActionSliderControl[] actionSliders;
	
	/**
	 * Search slider (for search action)
	 */
	private ActionSliderControl defaultSearchFormSlider;

	/**
	 * Constructor.
	 * 
	 * @param parentSlider
	 *            parent-slider for this from.
	 * @param rb
	 *            business-logic class of reference book.
	 * @param forceSearch
	 *            if forceSearch = true, reference book not loads the data and
	 *            shows search form. If forceSearch = false, reference book
	 *            loads the data immediately.
	 * @param type
	 *            type of custom form.
	 */
	public CustomRBWindow(Slider parentSlider, IRB rb, boolean forceSearch,
			CustomRBType type) {
		super(parentSlider);

		// Initialization properties of class by parameters from constructor
		this.rb = rb;
		this.setCaption(rb.getCaption());
		this.type = type;
		this.forceSearch = forceSearch;

		// format header
		this.panel.add(header);
		if (this.type != null) {
			// Sets header's text by CustomRBType.
			if (this.type == CustomRBType.Normal) {
				header.setText(rb.getCaption());
			} else if (this.type == CustomRBType.Select)
				header.setText(FrameworkLocale.constants()
						.select_item_and_click_select());
		}

		// Sets header style
		header.setStyleName("pf-customrb-header");

		// Create grid wrapper and add it into form
		dataGridWrapper = new DataGridWrapper(this, rb.getDataSource(), rb
				.getColumns());
		dataGridWrapper.setEventListener(this);
		this.panel.add(dataGridWrapper);

		// check IRowClassifiersProvider implementation
		if (rb instanceof IRowClassifiersProvider)
			dataGridWrapper.setRowClassifiers(((IRowClassifiersProvider) rb)
					.getRowClassifiers());

		// Builds button panel with buttons by actions
		buildButtonsPanel();
		// Add button panel into main widget 
		this.panel.add(buttonsPanel);

		buildSliders();

		initWidget(this.panel);
		setStyleName("pf-customrb");		
	}

	/**
	 * Overloaded constructor for normal (CustomRBType.Normal) reference books.
	 * @param parentSlider
	 *            parent-slider for this from.
	 * @param rb
	 *            business-logic class of reference book.
	 * @param forceSearch
	 *            if forceSearch = true, reference book not loads the data and
	 *            shows search form. If forceSearch = false, reference book
	 *            loads the data immediately.
	 */
	public CustomRBWindow(Slider parentSlider, IRB rb, boolean forceSearch) {
		this(parentSlider, rb, forceSearch, CustomRBType.Normal);
	}
	
	/**
	 * Overloaded constructor for normal (CustomRBType.Normal) reference books without parent sliders and search.
	 * @param rb business-logic class of reference book.
	 */
	public CustomRBWindow(IRB rb) {
		this(null, rb, false, CustomRBType.Normal);
	}

	/**
	 * Loads data without select parameters
	 */
	private void loadData() {
		this.dataGridWrapper.loadData();
	}

	/**
	 * Builds action sliders (insert, edit etc.) by IActionProviders implementation
	 */
	private void buildSliders() {
		// if implemented IActionProviders ...
		if (this.rb instanceof IActionProviders) {
			// gets the actions
			IActionProvider[] actions = ((IActionProviders) this.rb).getActionProviders();
			// actions array must be not null
			if (actions == null)
				return;
			// creating array of sliders for all actions
			this.actionSliders = new ActionSliderControl[actions.length];
			int lastSearchSliderIndex = 0;
			for (int i = 0; i < actions.length; i++) {
				// delete action CustomRB form provides like button 
				// (so if this is IDeleteRowProvider action we don't need slider)
				if (actions[i] instanceof IDeleteRowProvider){
					continue;
				}
				// creating slider control for each action
				ActionSliderControl aSlider = new ActionSliderControl(this,
						actions[i], this,
						actions[i] instanceof IViewFormProvider);
				// by default slider with current index is null
				this.actionSliders[i] = null;
				// slider must be authenticated.
				if (aSlider.isAuth()) {
					aSlider.readData();
					// set slider into array
					this.actionSliders[i] = aSlider;
					// search action slider must be on top of window
					if (actions[i] instanceof ISearchFormProvider) {
						if (this.defaultSearchFormSlider == null)
							this.defaultSearchFormSlider = aSlider;
						panel.insert(aSlider, ++lastSearchSliderIndex);
					} else {
						// another sliders - on bottom by order
						panel.add(aSlider);
					}
				}
			}
		}
	}

	/**
	 * Builds buttons panel and buttons by actions
	 */
	private void buildButtonsPanel() {
		this.buttonsPanel.clear();
		
		// If this is selector form
		if (type == CustomRBType.Select) {
			// Add button "Close form"
			buttonsPanel.add(this.btnClose);
		}
		
		buttonsPanel.add(this.btnRefresh);
		
		// If this is selector form
		if (type == CustomRBType.Select) {
			// Add button "Select row"
			buttonsPanel.add(this.btnSelect);
		}

		if (type == CustomRBType.Normal && this.rb instanceof IActionProviders) {
			IActionProvider[] actions = ((IActionProviders) this.rb)
					.getActionProviders();
			// actions array must be not null
			if (actions == null)
				return;
			// creating array of buttons for all actions
			actionButtons = new ActionButtonControl[actions.length];
			for (int i = 0; i < actions.length; i++) {
				// by default button with current index is null
				actionButtons[i] = null;
				// CustomRB form provides like button ONLY delete action
				if (actions[i] instanceof IDeleteRowProvider) {
					ActionButtonControl aButton = new ActionButtonControl(this,
							actions[i], this);
					if (aButton.isAuth()) {
						aButton.readData();
						buttonsPanel.add(aButton);
						actionButtons[i] = aButton;
					}
				}
			}
		}
	}

	/* Click handler on any widget in form
	 * @see com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user.client.ui.Widget)
	 */
	public void onClick(Widget sender) {
		// If close button was clicked - close form
		if (sender == this.btnClose) {
			// Explicit setting selected row to null.
			this.selectedRow = null;
			this.hideWindow();
		}
		else
		// If "select" button was clicked
		if (sender == this.btnSelect) {
			// gets current select row from grid wrapper
			this.selectedRow = dataGridWrapper.getSelectedRow();
			if (this.selectedRow == null)
				// User don't select any row. Showing alert
				com.google.gwt.user.client.Window.alert(FrameworkLocale
						.messages().no_selected_row());
			else
				// form knows current selected row. Some high handler will ask it. so close form
				this.hideWindow();
		} else
		if (sender == this.btnRefresh) {
			this.loadData();
		}
		
	}

	/**
	 * Shows search form in search slider
	 */
	private void showSearchForm() {
		if (this.defaultSearchFormSlider == null)
			throw new NullPointerException(FrameworkLocale.messages().error_cant_show_search_form());
			
		this.defaultSearchFormSlider.onClick(this);
	}

	protected void showEvent() {
		if (this.forceSearch && this.defaultSearchFormSlider != null)
			showSearchForm();
		else
			// If this reference book don't force search or don't have search action
			// it loads the data immediately.
			this.loadData();
	}

	/**
	 * Gets current selected row in reference book
	 * @return selected row
	 */
	public IDataRow getSelectedRow() {
		return selectedRow;
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.controls.dataGrid.interfaces.IDataGridEventListener#selectionChanged(net.pleso.framework.client.ui.controls.dataGrid.DataGrid)
	 */
	public void selectionChanged(DataGrid sender) {
		// gets current selection row from grid
		IDataRow selectedRow = dataGridWrapper.getSelectedRow();
		// check all action buttons controls and rebind each control with new selected row
		if (this.actionButtons != null) {
			for (int i = 0; i < this.actionButtons.length; i++) {
				if (this.actionButtons[i] != null) {
					this.actionButtons[i].bind(selectedRow, null);
					this.actionButtons[i].readData();
				}
			}
		}
		// check all action sliders controls and rebind each control with new selected row
		if (this.actionSliders != null) {
			for (int i = 0; i < this.actionSliders.length; i++) {
				if (this.actionSliders[i] != null) {
					this.actionSliders[i].bind(selectedRow, null);
					this.actionSliders[i].readData();
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.HideWindowListener#onHideWindow(net.pleso.framework.client.ui.interfaces.IWindow)
	 */
	public void onHideWindow(IWindow sender) {
		// Make assumption that custom form window was closing
		CustomFormWindow window = (CustomFormWindow) sender;

		// if this is really custom form window
		if (window != null) {
			// If it was search form and "close" button was not clicked, then
			if (window.getCustomFormType() == CustomFormType.Search
					&& !window.isCanceled()) {
				// Reload grid with new search parameters
				SelectParams params = this.dataGridWrapper.getParams();
				params.setSearchRow(window.getDataRow());
				this.loadData();
			} else {
				// Else simply reload grid (if data was changed)
				if (window.dataChanged()) 
					this.loadData();
			}
		}
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IUpdateControlListener#controlUpdated(net.pleso.framework.client.ui.interfaces.IBindableDataControl)
	 */
	public void controlUpdated(IBindableDataControl sender) {
		this.loadData();
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.windows.Window#hideWindow()
	 */
	public void hideWindow() {
		// For all sliders call hideWindows()
		if (actionSliders != null)
			for (int i = 0; i < actionSliders.length; i++) {
				if (actionSliders[i] != null)
					actionSliders[i].hideChildWindow();
			}
		// hides reference book itself
		super.hideWindow();
	}
}
