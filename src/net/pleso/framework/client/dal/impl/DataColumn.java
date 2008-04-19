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
package net.pleso.framework.client.dal.impl;

import net.pleso.framework.client.dal.IDataColumn;
import net.pleso.framework.client.dal.IDataRow;
import net.pleso.framework.client.dal.db.IDBValue;

/**
 * Default {@link IDataColumn} implementation
 * 
 * @author Scater
 *
 */
public class DataColumn implements IDataColumn {
	
	/**
	 * Constructor
	 * @param name database name of column
	 * @param caption localized caption of column 
	 * @param allowNull is column allow null values
	 */
	public DataColumn(String name, String caption, boolean allowNull){
		this.name = name;
		this.caption = caption;
		this.allowNull = allowNull;
	}

	private String caption = new String();
	private String name = new String();
	private boolean allowNull = true;
	protected int order = -1;
	
	/* (non-Javadoc)
	 * @see net.pleso.framework.client.dal.interfaces.IDataColumn#getCaption()
	 */
	public String getCaption() {
		return caption;
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.dal.interfaces.IDataColumn#getName()
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets column order
	 * @return column order
	 */
	public int getOrder() {
		return order;
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.dal.interfaces.IDataColumn#isAllowNull()
	 */
	public boolean isAllowNull() {
		return allowNull;
	}
	
	/**
	 * Sets null value in column
	 * @param row row with value
	 * @param column column of value
	 */
	public static void setNull(IDataRow row, IDataColumn column) {
		IDBValue value = row.getCell(column);
		value.setNull();
		row.setCell(column, value);
	}
}
