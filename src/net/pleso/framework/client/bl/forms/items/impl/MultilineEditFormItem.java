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
package net.pleso.framework.client.bl.forms.items.impl;

import net.pleso.framework.client.bl.forms.items.IMultilineEditFormItem;
import net.pleso.framework.client.dal.IDataColumn;

/**
 * Represents default implementation for {@link IMultilineEditFormItem}
 * 
 * @author Scater
 *
 */
public class MultilineEditFormItem extends EditFormItem implements IMultilineEditFormItem {

	/**
	 * Constructor
	 * @param dataColumn binded column
	 * @param rowsCount count of rows in multiline html widget
	 * @param required is column required
	 */
	public MultilineEditFormItem(IDataColumn dataColumn, int rowsCount, boolean required) {
		super(dataColumn, required);
		
		this.rowsCount = rowsCount; 
	}
	
	/**
	 * Constructor
	 * @param dataColumn binded column
	 * @param rowsCount count of rows in multiline html widget
	 */
	public MultilineEditFormItem(IDataColumn dataColumn, int rowsCount) {
		super(dataColumn);
		
		this.rowsCount = rowsCount;
	}
	
	/**
	 * Constructor
	 * @param dataColumn binded column
	 * @param required is column required
	 */
	public MultilineEditFormItem(IDataColumn dataColumn, boolean required) {
		super(dataColumn, required);
	}

	/**
	 * Constructor
	 * @param dataColumn binded column
	 */
	public MultilineEditFormItem(IDataColumn dataColumn) {
		super(dataColumn);
	}
	
	private int rowsCount = 10;

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.bl.forms.items.IMultilineEditFormItem#getRowsCount()
	 */
	public int getRowsCount() {
		return rowsCount;
	}

}
