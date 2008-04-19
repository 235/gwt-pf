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

import com.google.gwt.user.client.ui.Widget;

import net.pleso.framework.client.ui.controls.datagrid.DataGrid;

/**
 * Interface of header in {@link DataGrid} class
 * 
 * @author Scater
 * 
 */
public interface IDataGridHeader {

	/**
	 * @return {@link DataGrid} - owner of row
	 */
	DataGrid getDataGrid();

	/**
	 * Sets text to specified cell in header row.
	 * 
	 * @param columnIndex index of cell's column
	 * @param text text to set
	 */
	void setHeaderText(int columnIndex, String text);

	/**
	 * Gets text from specified cell in header row.
	 * 
	 * @param columnIndex index of cell's column
	 * @return text in cell
	 */
	String getHeaderText(int columnIndex);
	
	/**
	 * Sets widget to specified cell in header row.
	 * 
	 * @param columnIndex index of cell's column
	 * @param widget widget to set
	 */
	void setHeaderWidget(int columnIndex, Widget widget);

	/**
	 * Gets widget from specified cell in header row.
	 * 
	 * @param columnIndex index of cell's column
	 * @return widget in cell
	 */
	Widget getHeaderWidget(int columnIndex);

}
