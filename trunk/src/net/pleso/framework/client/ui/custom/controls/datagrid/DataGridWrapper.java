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
package net.pleso.framework.client.ui.custom.controls.datagrid;

import net.pleso.framework.client.bl.IAuthDataSource;
import net.pleso.framework.client.bl.IEnum;
import net.pleso.framework.client.bl.IEnumItem;
import net.pleso.framework.client.bl.auth.AuthorizationProvider;
import net.pleso.framework.client.bl.exceptions.AccessViolationException;
import net.pleso.framework.client.bl.exceptions.AsyncCallbackFailureException;
import net.pleso.framework.client.bl.rb.IRowClassifier;
import net.pleso.framework.client.bl.rb.columns.ILinkRBDataColumn;
import net.pleso.framework.client.bl.rb.columns.IRBColumn;
import net.pleso.framework.client.bl.rb.columns.IRBDataColumn;
import net.pleso.framework.client.bl.rb.columns.IRBEnumDataColumn;
import net.pleso.framework.client.dal.IDataRow;
import net.pleso.framework.client.dal.SelectParams;
import net.pleso.framework.client.dal.db.IDBValue;
import net.pleso.framework.client.dal.db.types.DBBoolean;
import net.pleso.framework.client.dal.db.types.DBString;
import net.pleso.framework.client.localization.FrameworkLocale;
import net.pleso.framework.client.ui.controls.Hyperlink;
import net.pleso.framework.client.ui.controls.StatusPanel;
import net.pleso.framework.client.ui.controls.datagrid.DataGrid;
import net.pleso.framework.client.ui.controls.datagrid.DataGridColumn;
import net.pleso.framework.client.ui.controls.datagrid.SortableColumnHeader;
import net.pleso.framework.client.ui.controls.datagrid.interfaces.IDataBinder;
import net.pleso.framework.client.ui.controls.datagrid.interfaces.IDataGridEventListener;
import net.pleso.framework.client.ui.controls.datagrid.interfaces.IDataGridHeader;
import net.pleso.framework.client.ui.controls.datagrid.interfaces.IDataGridRow;
import net.pleso.framework.client.ui.controls.datagrid.interfaces.ISortableColumnHeaderListener;
import net.pleso.framework.client.ui.custom.CustomRBWindow;
import net.pleso.framework.client.ui.interfaces.IWindow;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * This class is wrapper around {@link DataGrid} for custom controls.
 * Impements IDataBinder interface for own {@link DataGrid} instance
 * This is composite control with {@link DataGrid} and Pager.
 * Used in {@link CustomRBWindow}
 * It implements:
 * - work with {@link IAuthDataSource}
 * - anisochronous data manipulation
 * - work with Pager
 * - sorting data with {@link SortableColumnHeader} widget
 * 
 * <h3>CSS Style Rules</h3>
 * <ul>
 * <li>.pf-dataGridWrapper { the data grid wrapper itself }</li>
 * <ul>
 * 
 * @author Scater
 */
public class DataGridWrapper extends Composite implements IDataBinder, IPagerEventListener, ISortableColumnHeaderListener {
	
	/**
	 * Constructor 
	 * @param dataSource {@link IAuthDataSource} implementation instance
	 * @param columns {@link IRBColumn} array
	 */
	public DataGridWrapper(IWindow parentWindow, IAuthDataSource dataSource, IRBColumn[] columns) {
		this.parentWindow = parentWindow;
		this.dataSource = dataSource;
		this.columns = columns;
		fillDataGridColumns();
		mainPanel.add(pager);
		mainPanel.setCellHorizontalAlignment(pager, VerticalPanel.ALIGN_RIGHT);
		mainPanel.add(dataGrid);
		
		initWidget(mainPanel);
		setStyleName("pf-dataGridWrapper");
		statusPanel.setCenterByWidget(this);
		dataGrid.setDataBinder(this);
		pager.setEventListener(this);
	}
	
	/**
	 * Main widget of this composite widget.
	 */
	private final VerticalPanel mainPanel = new VerticalPanel();
	
	/**
	 * Panel for showing 'loading' status
	 */
	private final StatusPanel statusPanel = new StatusPanel();
	
	/**
	 * {@link DataGrid} instance for view data
	 */
	private DataGrid dataGrid = new DataGrid();
	
	/**
	 * Data source instance.
	 */
	private IAuthDataSource dataSource;
	
	/**
	 * Gets data source instance
	 * @return data source instance
	 */
	public IAuthDataSource getDataSource() {
		return dataSource;
	}
	
	/**
	 * Columns of grid's data
	 */
	private IRBColumn[] columns;
	
	/**
	 * Gets data columns
	 * @return data columns
	 */
	public IRBColumn[] getColumns() {
		return columns;
	}
	
	/**
	 * Data rows array
	 */
	private IDataRow[] rbRows = null;
	
	/**
	 * Parameters of selection data from datasource into grid
	 */
	private SelectParams params = new SelectParams();
	
	/**
	 * Pager widget for manipulating pages of data
	 */
	private Pager pager = new Pager(params);
	
	/**
	 * Wrapper has row classifiers for its datasource. 
	 * So it can change row styles by this classifiers 
	 */
	private IRowClassifier[] rowClassifiers = null;
	
	/**
	 * Parent window. 
	 */
	private IWindow parentWindow;
	
	/**
	 * @return the params
	 */
	public SelectParams getParams() {
		return params;
	}

	/**
	 * Gets row classifiers
	 * @return row classifiers
	 */
	public IRowClassifier[] getRowClassifiers() {
		return rowClassifiers;
	}

	/**
	 * Sets row classifiers
	 * @param rowClassifiers row classifiers
	 */
	public void setRowClassifiers(IRowClassifier[] rowClassifiers) {
		this.rowClassifiers = rowClassifiers;
	}
	
	/**
	 * Search over {@link IRBColumn} array and create corresponding {@link DataGridColumn}
	 */
	private void fillDataGridColumns(){
		if (columns != null){
			DataGridColumn[] dataGridColumns = new DataGridColumn[columns.length];
			for (int i = 0; i < columns.length; i++) {
				dataGridColumns[i] = new DataGridColumn(columns[i].getWidth());
				dataGrid.setColumns(dataGridColumns);
			}
		}
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.controls.dataGrid.interfaces.IDataBinder#bindHeader(net.pleso.framework.client.ui.controls.dataGrid.interfaces.IDataGridHeader)
	 */
	public void bindHeader(IDataGridHeader dataGridHeader) {
		if (columns != null && columns.length > 0) {
			for (int i = 0; i < columns.length; i++) {				
				boolean sortBy = false;
				// if current column is sortable
				if (columns[i] instanceof IRBDataColumn)
					sortBy = ((IRBDataColumn)columns[i]).getDataColumn().getName().equals(params.getOrderByColumnName());
				
				if (sortBy) {
					// current column is sortable. create SortableColumnHeader with Boolean order parameter
					dataGridHeader.setHeaderWidget(i, 
							new SortableColumnHeader(i, columns[i].getCaption(), new Boolean(params.getOrderByDirection()), this));
				} else {
					//  current column is NOT sortable. create SortableColumnHeader with null order parameter
					dataGridHeader.setHeaderWidget(i, 
							new SortableColumnHeader(i, columns[i].getCaption(), null, this));
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.controls.dataGrid.interfaces.IDataBinder#bindRow(net.pleso.framework.client.ui.controls.dataGrid.interfaces.IDataGridRow)
	 */
	public void bindRow(IDataGridRow dataGridRow) {
		for (int i = 0; i < columns.length; ++i) {
			// by default value in cell is empty string value
			IDBValue value = new DBString();

			if (columns[i] instanceof ILinkRBDataColumn) {
				IDBValue title = ((IDataRow) dataGridRow.getDataRow())
				.getCell(((ILinkRBDataColumn) columns[i]).getDataColumn());
				IDBValue reference = ((IDataRow) dataGridRow.getDataRow())
				.getCell(((ILinkRBDataColumn) columns[i]).getLinkReferenceColumn());
				
				dataGridRow.setCellWidget(i, new Hyperlink(title.getValue(), reference.getValue()));
				
				continue;
			}
			
			// first check parent data column type - IRBDataColumn
			if (columns[i] instanceof IRBDataColumn) {
				value = ((IDataRow) dataGridRow.getDataRow())
						.getCell(((IRBDataColumn) columns[i]).getDataColumn());

				if (value != null) {
					// if value is boolean value
					if (value instanceof DBBoolean) {
						// set special widget for boolean values
						// all another types shows default handler
						dataGridRow.setCellWidget(i, new BooleanValueWidget((DBBoolean) value));
						continue;
					}
				}
			}

			// check IRBEnumDataColumn
			if (columns[i] instanceof IRBEnumDataColumn) {
				String stringValue = getEnumValue(value,
						((IRBEnumDataColumn) columns[i]).getEnum());

				if (stringValue == null)
					dataGridRow.setCellWidget(i, new HTML("&nbsp;"));
				else
					dataGridRow.setCellText(i, stringValue);
				continue;
			}

			// insert handlers for your IRB data columns types HERE !

			// default handler of value without check columns types
			if (value == null) {
				dataGridRow.setCellWidget(i, new HTML("&nbsp;"));
			} else {
				if (value.isNull())
					dataGridRow.setCellWidget(i, new HTML("&nbsp;"));
				else
					dataGridRow.setCellText(i, value.getValue());
			}
		}

		checkRowClassifier(dataGridRow);
	}
	
	
	/**
	 * Sets specific style into row if it has classifier
	 * 
	 * @param row
	 *            grid row to check classifier
	 */
	private void checkRowClassifier(IDataGridRow row) {
		if (rowClassifiers != null) {
			for (int i = 0; i < rowClassifiers.length; i++) {
				if (rowClassifiers[i].testRow((IDataRow) row.getDataRow())) {
					row.setStyleName(rowClassifiers[i].getRowStyle());
					return;
				}
			}
		}
	}
	
	private String getEnumValue(IDBValue cellValue, IEnum enumeration) {
		IEnumItem[] items = enumeration.getItems();
		if (cellValue.isNull()) {
			return enumeration.getNullItem().getCaption();
		} else {
			for (int i = 0; i < items.length; i++) {
				if (items[i].getDBValue().getValue().equals(
						cellValue.getValue()))
					return items[i].getCaption();
			}
		}
		return null;
	}

	/**
	 * Gets data (total count of record and data array) from {@link IAuthDataSource}
	 * by asynchronous interface and bind it into {@link DataGrid}.
	 * Also checks authorization and shows 'loading' status panel. 
	 */
	public void loadData() {
		if (dataSource == null) {
			throw new NullPointerException(FrameworkLocale.messages().error_datasource_cant_be_null());
		}
		
		// check authorization
		if (!AuthorizationProvider.isObjectAuthorized(dataSource)) {
			throw new AccessViolationException(FrameworkLocale.messages().error_not_authorized());
		}
		
		if (dataGrid.getDataSource() == null) {
			// Fake binding empty data for header rendering.
			dataGrid.setDataSource(new IDataRow[0]);
			dataGrid.dataBind();
			// Here we can add empty row for nice data loading visualisation.
		}
		
		showStatusPanel(FrameworkLocale.messages().loading());
		
		dataSource.selectCount(params, new AsyncCallback() {
			public void onFailure(Throwable caught) {
				if (!parentWindow.isHidden()) {
					pager.setErrorMessage(FrameworkLocale.messages().asyncerror_loadrowscount());
					throw new AsyncCallbackFailureException(FrameworkLocale.messages().asyncerror_loadrowscount(), caught);
				}
			}
			public void onSuccess(Object result) {
				if (!parentWindow.isHidden()) {
					pager.setTotalCount(((Integer)result).intValue());
					pager.refresh();
				}
			}
		});
		
		dataSource.select(params,  new AsyncCallback() {
			public void onFailure(Throwable caught) {
				if (!parentWindow.isHidden()) {
					showStatusPanel((FrameworkLocale.messages().asyncerror_loadrows()));
					throw new AsyncCallbackFailureException(FrameworkLocale.messages().asyncerror_loadrows(), caught);
				}
			}

			public void onSuccess(Object result) {
				if (!parentWindow.isHidden()) {
					rbRows = (IDataRow[]) result;
					dataGrid.setDataSource(rbRows);
					dataGrid.dataBind();
					
					hideStatusPanel();
					
					parentWindow.scrollToTop();
				}
			}
		});		
	}
	
	/**
	 * Showing status panel with text in the center of widget
	 * @param text text on panel
	 */
	private void showStatusPanel(String text){
		statusPanel.setText(text);
		statusPanel.show();
	}
	
	/**
	 * Hides status panel
	 */
	private void hideStatusPanel(){
		statusPanel.hide();
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.controls.dataGrid.interfaces.IPagerEventListener#pagerParamsChanged()
	 */
	public void pagerParamsChanged() {
		loadData();
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.controls.dataGrid.interfaces.ISortableColumnHeaderListener#fireOrder(net.pleso.framework.client.ui.controls.dataGrid.SortableColumnHeader)
	 */
	public void fireOrder(SortableColumnHeader columnHeader) {
		if (columns[columnHeader.getColumnIndex()] instanceof IRBDataColumn) {
			// get IRBDataColumn from columns array by columnHeader
			IRBDataColumn rbDataColumn = (IRBDataColumn) columns[columnHeader.getColumnIndex()];
			// set new column for order
			params.setOrderByColumnName(rbDataColumn.getDataColumn().getName());
			// if this is same column, than change order direction
			if (rbDataColumn.getDataColumn().getName().equals(params.getOrderByColumnName()))
				params.setOrderByDirection(!params.getOrderByDirection());
			// reload data
			loadData();
		}
	}
	
	/**
	 * Gets current selected row
	 * @return
	 */
	public IDataRow getSelectedRow() {
		IDataGridRow row = dataGrid.getSelectedRow();
		if (row != null)
			return rbRows[dataGrid.getSelectedRow().getIndex()];
		else
			return null;
	}
	
	/**
	 * Gets events listener of {@link DataGrid}
	 * 
	 * @return listener instance
	 */
	public IDataGridEventListener getEventListener() {
		return dataGrid.getEventListener();
	}

	/**
	 * Sets events listener of {@link DataGrid}
	 * 
	 * @param selectionChangedListener {@link IDataGridEventListener} implementation instance
	 */
	public void setEventListener(IDataGridEventListener eventListener) {
		dataGrid.setEventListener(eventListener);
	}
}
