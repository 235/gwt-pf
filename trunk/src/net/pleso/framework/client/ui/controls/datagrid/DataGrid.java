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
package net.pleso.framework.client.ui.controls.datagrid;

import net.pleso.framework.client.ui.controls.datagrid.interfaces.IDataBinder;
import net.pleso.framework.client.ui.controls.datagrid.interfaces.IDataGridEventListener;
import net.pleso.framework.client.ui.controls.datagrid.interfaces.IDataGridHeader;
import net.pleso.framework.client.ui.controls.datagrid.interfaces.IDataGridRow;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.SourcesTableEvents;
import com.google.gwt.user.client.ui.TableListener;
import com.google.gwt.user.client.ui.Widget;

/**
 * Basic DataGrid class. This is simple table-like container with customizable
 * data view. It needs columns, data source (not typified array) and data
 * binder.
 * 
 * <h3>CSS Style Rules</h3>
 * <ul>
 * <li>.pf-dataGrid { DataGrid itself }</li>
 * <li>.pf-dataGrid-headerRow { header row in grid table }</li>
 * <li>.pf-dataGrid-headerCell { each cell in header row }</li>
 * <li>.pf-dataGrid-column { each column in grid table }</li>
 * <li>.pf-dataGrid-rowCell { each cell in row with data }</li>
 * <li>.pf-dataGrid-row { row with data in grid table }</li>
 * <li>.pf-dataGrid-selectedRow { selected data row by user }</li>
 * </ul>
 * 
 * @author Scater
 * 
 */
public class DataGrid extends Composite implements TableListener {

	/**
	 * Constructor
	 */
	public DataGrid() {
		initWidget(grid);
		setStyleName("pf-dataGrid");
		grid.setCellPadding(0);
		grid.setCellSpacing(0);
		grid.addTableListener(this);
	}

	/**
	 * {@link DataGrid} row class
	 */
	private class DataGridRow implements IDataGridRow {

		/**
		 * Constructor
		 * 
		 * @param index index of row in dataset
		 */
		public DataGridRow(int index) {
			this.index = index;
		}

		/**
		 * Index in grid's dataset.
		 */
		private int index;

		/**
		 * This function return data of this row from grid's dataset
		 * 
		 * @return datarow object from dataset of grid
		 */
		public Object getDataRow() {
			return getDataSource()[index];
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see net.pleso.framework.client.ui.controls.dataGrid.interfaces.IDataGridRow#getDataGrid()
		 */
		public DataGrid getDataGrid() {
			return getInstance();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see net.pleso.framework.client.ui.controls.dataGrid.interfaces.IDataGridRow#getIndex()
		 */
		public int getIndex() {
			return index;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see net.pleso.framework.client.ui.controls.dataGrid.interfaces.IDataGridRow#setCellText(int,
		 *      java.lang.String)
		 */
		public void setCellText(int columnIndex, String text) {
			grid.setText(index + 1, columnIndex, text);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see net.pleso.framework.client.ui.controls.dataGrid.interfaces.IDataGridRow#getCellText(int)
		 */
		public String getCellText(int columnIndex) {
			return grid.getText(index + 1, columnIndex);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see net.pleso.framework.client.ui.controls.dataGrid.interfaces.IDataGridRow#setCellWidget(int,
		 *      com.google.gwt.user.client.ui.Widget)
		 */
		public void setCellWidget(int columnIndex, Widget widget) {
			grid.setWidget(index + 1, columnIndex, widget);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see net.pleso.framework.client.ui.controls.dataGrid.interfaces.IDataGridRow#getCellWidget(int)
		 */
		public Widget getCellWidget(int columnIndex) {
			return grid.getWidget(index + 1, columnIndex);
		}

		public void setStyleName(String styleName) {
			grid.getRowFormatter().setStyleName(index + 1, styleName);
		}
	}

	/**
	 * {@link DataGrid} header row class.
	 */
	private class DataGridHeader implements IDataGridHeader {

		/*
		 * (non-Javadoc)
		 * 
		 * @see net.pleso.framework.client.ui.controls.dataGrid.interfaces.IDataGridHeader#getDataGrid()
		 */
		public DataGrid getDataGrid() {
			return getInstance();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see net.pleso.framework.client.ui.controls.dataGrid.interfaces.IDataGridHeader#getHeaderText(int)
		 */
		public String getHeaderText(int columnIndex) {
			return grid.getText(0, columnIndex);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see net.pleso.framework.client.ui.controls.dataGrid.interfaces.IDataGridHeader#setHeaderText(int,
		 *      java.lang.String)
		 */
		public void setHeaderText(int columnIndex, String text) {
			grid.setText(0, columnIndex, text);
		}

		/* (non-Javadoc)
		 * @see net.pleso.framework.client.ui.controls.dataGrid.interfaces.IDataGridHeader#getHeaderWidget(int)
		 */
		public Widget getHeaderWidget(int columnIndex) {
			return grid.getWidget(0, columnIndex);
		}

		/* (non-Javadoc)
		 * @see net.pleso.framework.client.ui.controls.dataGrid.interfaces.IDataGridHeader#setHeaderWidget(int, com.google.gwt.user.client.ui.Widget)
		 */
		public void setHeaderWidget(int columnIndex, Widget widget) {
			grid.setWidget(0, columnIndex, widget);
		}

	}

	/**
	 * This method is required by private classes to take access to a grid
	 * instance.
	 * 
	 * @return instance of this Grid
	 */
	private DataGrid getInstance() {
		return this;
	}

	/**
	 * Main grid component for data view.
	 */
	private final Grid grid = new Grid();

	/**
	 * Columns of grid.
	 */
	private DataGridColumn[] columns;

	/**
	 * Gets columns of grid.
	 * 
	 * @return columns of grid
	 */
	public DataGridColumn[] getColumns() {
		return columns;
	}

	/**
	 * Sets columns of grid.
	 * 
	 * @param columns
	 *            of grid
	 */
	public void setColumns(DataGridColumn[] columns) {
		this.columns = columns;
	}

	/**
	 * Set of data for grid to view. it is not typified array of grid data rows.
	 */
	private Object[] dataSource;

	/**
	 * Gets grid datasource.
	 * 
	 * @return grid datasource
	 */
	public Object[] getDataSource() {
		return dataSource;
	}

	/**
	 * Sets grid datasource.
	 * 
	 * @param dataSource datasource instance
	 */
	public void setDataSource(Object[] dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * {@link IDataBinder} implementation instance.
	 */
	private IDataBinder dataBinder;

	/**
	 * Gets grid {@link IDataBinder} implementation instance.
	 * 
	 * @return grid {@link IDataBinder} implementation instance
	 */
	public IDataBinder getDataBinder() {
		return dataBinder;
	}

	/**
	 * Sets grid {@link IDataBinder} implementation instance.
	 * 
	 * @param dataBinder {@link IDataBinder} implementation instance
	 */
	public void setDataBinder(IDataBinder dataBinder) {
		this.dataBinder = dataBinder;
	}

	/**
	 * Starts data bind process grid will ask dataBinder for each row to show it
	 * for user.
	 */
	public void dataBind() {
		paintHeader();
		paintData();
		
		if (eventListener != null)
			eventListener.selectionChanged(this);
	}

	/**
	 * Starts data bind process with new {@link IDataBinder} implementation instance.
	 * 
	 * @param dataBinder {@link IDataBinder} implementation instance
	 */
	public void dataBind(IDataBinder dataBinder) {
		setDataBinder(dataBinder);
		dataBind();
	}

	/**
	 * Paints header of grid by columns.
	 */
	private void paintHeader() {
		if (columns != null && columns.length > 0) {
			grid.resize(1, columns.length);
			grid.getRowFormatter().setStyleName(0,
					"pf-dataGrid-headerRow");

			// calculate total width
			int totalWidth = 0;
			for (int i = 0; i < columns.length; i++) {
				if (columns[i] != null)
					totalWidth += columns[i].getColumnWidth();
			}
			if (totalWidth <= 0) {
				throw new IllegalArgumentException(
						"Total size of columns must be higher than 0");
			}

			float columnWidth = 0;
			for (int i = 0; i < columns.length; i++) {
				// calculate width of current column by proportion
				columnWidth = (float) columns[i].getColumnWidth() / totalWidth
						* 100;
				grid.getColumnFormatter().setWidth(i,
						new Float(columnWidth).intValue() + "%");

				grid.getCellFormatter().setStyleName(0, i,
						"pf-dataGrid-headerCell");
				grid.getColumnFormatter().setStyleName(0,
						"pf-dataGrid-column");
			}

			if (dataBinder != null) {
				dataBinder.bindHeader(new DataGridHeader());
			}
		}
	}

	/**
	 * Paints data in grid from datasource by dataBinder.
	 */
	private void paintData() {
		if (dataSource != null && dataSource.length > 0) {
			for (int i = 0; i < dataSource.length; i++) {
				grid.resizeRows(grid.getRowCount() + 1);
				grid.getRowFormatter().setStyleName(grid.getRowCount() - 1,
						"pf-dataGrid-row");

				for (int j = 0; j < columns.length; j++) {
					grid.getCellFormatter().setStyleName(
							grid.getRowCount() - 1, j,
							"pf-dataGrid-rowCell");
				}

				if (dataBinder != null) {
					dataBinder.bindRow(new DataGridRow(i));
				}
			}
		}
	}

	/**
	 * Selected row index in datasource.
	 */
	private int selectedRowIndex = -1;

	/**
	 * Gets selected row.
	 * 
	 * @return current selected data row
	 */
	public IDataGridRow getSelectedRow() {
		if (selectedRowIndex > -1 && selectedRowIndex < this.dataSource.length)
			return new DataGridRow(selectedRowIndex);
		else
			return null;
	}

	/**
	 * Variable for memorization row's css-class before selection. For set back
	 * the old-one.
	 */
	private String cssClassBeforeSelection;

	/*
	 * Grid cell click handler changes selectedRowIndex and css-class of
	 * selected row.
	 * 
	 * @see com.google.gwt.user.client.ui.TableListener#onCellClicked(com.google.gwt.user.client.ui.SourcesTableEvents,
	 *      int, int)
	 */
	public void onCellClicked(SourcesTableEvents sender, int row, int cell) {
		if (row > 0) {
			if (this.selectedRowIndex != -1) {
				if (this.selectedRowIndex + 1 < grid.getRowCount()) {
					if (cssClassBeforeSelection != null
							&& cssClassBeforeSelection != "") {
						grid.getRowFormatter().setStyleName(
								this.selectedRowIndex + 1,
								cssClassBeforeSelection);
					} else {
						grid.getRowFormatter().setStyleName(
								this.selectedRowIndex + 1,
								"pf-dataGrid-row");
					}
				}
			}
			cssClassBeforeSelection = grid.getRowFormatter().getStyleName(row);
			grid.getRowFormatter().setStyleName(row,
					"pf-dataGrid-selectedRow");
			this.selectedRowIndex = row - 1;

			if (eventListener != null)
				eventListener.selectionChanged(this);
		}
	}

	/**
	 * Handler of events.
	 */
	IDataGridEventListener eventListener = null;

	/**
	 * Gets events listener.
	 * 
	 * @return listener instance
	 */
	public IDataGridEventListener getEventListener() {
		return eventListener;
	}

	/**
	 * Sets events listener.
	 * 
	 * @param selectionChangedListener {@link IDataGridEventListener} implementation instance
	 */
	public void setEventListener(IDataGridEventListener eventListener) {
		this.eventListener = eventListener;
	}

}
