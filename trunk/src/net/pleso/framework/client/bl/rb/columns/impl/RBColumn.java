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
package net.pleso.framework.client.bl.rb.columns.impl;

import net.pleso.framework.client.bl.rb.columns.IRBDataColumn;
import net.pleso.framework.client.dal.IDataColumn;

/**
 * Represents default {@link IRBDataColumn} implementation 
 * 
 * @author Scater
 *
 */
public class RBColumn implements IRBDataColumn {

	private IDataColumn column = null;
	private int width = 0;
	
	/**
	 * Constructor
	 * @param dataColumn binded column
	 * @param width width of column
	 */
	public RBColumn(IDataColumn dataColumn, int width){
		if (dataColumn == null)
			throw new IllegalArgumentException("DataColumn argument can't be null");
		if (width < 0)
			throw new IllegalArgumentException("Width can't be lower than 0");
		this.column = dataColumn;
		this.width = width;
	}
	
	/* (non-Javadoc)
	 * @see net.pleso.framework.client.bl.rb.columns.IRBColumn#getCaption()
	 */
	public String getCaption() {
		return column.getCaption();
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.bl.rb.columns.IRBDataColumn#getDataColumn()
	 */
	public IDataColumn getDataColumn() {
		return column;
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.bl.rb.columns.IRBColumn#getWidth()
	 */
	public int getWidth() {
		return width;
	}

}
