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
package net.pleso.framework.client.ui.controls.datagrid.interfaces;

import net.pleso.framework.client.ui.controls.datagrid.DataGrid;

import com.google.gwt.user.client.ui.Widget;

/**
 * Interface of row in {@link DataGrid} class
 * 
 * @author Scater
 *
 */
public interface IDataGridRow {

	/**
	 * @return {@link DataGrid} - owner of row
	 */
	DataGrid getDataGrid();

	/**
	 * @return index in grid's dataset
	 */
	int getIndex();

	/**
	 * This function return data of this row from grid's dataset.
	 * 
	 * @return datarow object from dataset of grid
	 */
	Object getDataRow();

	/**
	 * Sets text to specified cell of row.
	 * 
	 * @param columnIndex index of cell's column
	 * @param text text to set
	 */
	void setCellText(int columnIndex, String text);

	/**
	 * Gets text from specified cell of row.
	 * 
	 * @param columnIndex index of cell's column
	 * @return text in cell
	 */
	String getCellText(int columnIndex);

	/**
	 * Sets widget to specified cell of row.
	 * 
	 * @param columnIndex index of cell's column
	 * @param widget widget to set
	 */
	void setCellWidget(int columnIndex, Widget widget);

	/**
	 * Sets widget from specified cell of row.
	 * 
	 * @param columnIndex index of cell's column
	 * @return widget in cell
	 */
	Widget getCellWidget(int columnIndex);
	
	/**
	 * Sets css-style for this row
	 * @param styleName css-style name
	 */
	void setStyleName(String styleName);

}
