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

import net.pleso.framework.client.bl.forms.items.IEditRangeFormItem;
import net.pleso.framework.client.dal.IDataColumn;

/**
 * Represents default implementation for {@link IEditRangeFormItem}
 * 
 * @author Scater
 *
 */
public class EditRangeFormItem implements IEditRangeFormItem {
	
	private IDataColumn lowBoundDataColumn;
	private IDataColumn highBoundDataColumn;
	private String caption;
	private boolean required;
	
	/**
	 * Constructor
	 * @param caption caption of form item
	 * @param lowBoundDataColumn low bound data column
	 * @param highBoundDataColumn high bound data column
	 * @param required is column required
	 */
	public EditRangeFormItem(String caption, IDataColumn lowBoundDataColumn, IDataColumn highBoundDataColumn, boolean required){
		if (caption == null)
			throw new IllegalArgumentException("Caption argument can't be null");
		
		if (lowBoundDataColumn == null || highBoundDataColumn == null)
			throw new IllegalArgumentException("DataColumn argument can't be null");
		
		this.required = required;
		this.caption = caption;
		this.lowBoundDataColumn = lowBoundDataColumn;
		this.highBoundDataColumn = highBoundDataColumn;
	}
	
	/**
	 * Constructor
	 * @param caption caption of form item
	 * @param lowBoundDataColumn low bound data column
	 * @param highBoundDataColumn high bound data column
	 */ 
	public EditRangeFormItem(String caption, IDataColumn lowBoundDataColumn, IDataColumn highBoundDataColumn){
		if (caption == null)
			throw new IllegalArgumentException("Caption argument can't be null");
		
		if (lowBoundDataColumn == null || highBoundDataColumn == null)
			throw new IllegalArgumentException("DataColumn argument can't be null");
		
		this.required = !(lowBoundDataColumn.isAllowNull() && highBoundDataColumn.isAllowNull());
		this.caption = caption;
		this.lowBoundDataColumn = lowBoundDataColumn;
		this.highBoundDataColumn = highBoundDataColumn;
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.bl.forms.items.IFormItem#getCaption()
	 */
	public String getCaption() {
		return this.caption;
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.bl.forms.items.IEditFormItem#isRequired()
	 */
	public boolean isRequired() {
		return this.required;
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.bl.forms.items.IEditRangeFormItem#getHighBoundDataColumn()
	 */
	public IDataColumn getHighBoundDataColumn() {
		return this.highBoundDataColumn;
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.bl.forms.items.IEditRangeFormItem#getLowBoundDataColumn()
	 */
	public IDataColumn getLowBoundDataColumn() {
		return this.lowBoundDataColumn;
	}

}
