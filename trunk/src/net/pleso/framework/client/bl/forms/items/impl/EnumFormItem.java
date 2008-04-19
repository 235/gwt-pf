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

import net.pleso.framework.client.bl.IEnum;
import net.pleso.framework.client.bl.forms.items.IEditEnumColumnFormItem;
import net.pleso.framework.client.dal.IDataColumn;

/**
 * Represents default implementation for {@link IEditEnumColumnFormItem}
 * 
 * @author Scater
 *
 */
public class EnumFormItem implements IEditEnumColumnFormItem {
	
	private IDataColumn dataColumn;
	private boolean required;
	private IEnum enumeration; 
	
	/**
	 * Constructor
	 * @param dataColumn binded data column
	 * @param enumeration data enumeration
	 * @param required is column required
	 */
	public EnumFormItem(IDataColumn dataColumn, IEnum enumeration, boolean required) {
		if (dataColumn == null)
			throw new IllegalArgumentException("DataColumn argument can't be null");
		if (enumeration == null)
			throw new IllegalArgumentException("Enum argument can't be null");		
		
		this.enumeration = enumeration;
		this.required = required;
		this.dataColumn = dataColumn;
	}
	
	/**
	 * Constructor
	 * @param dataColumn binded data column
	 * @param enumeration data enumeration
	 */
	public EnumFormItem(IDataColumn dataColumn, IEnum enumeration) {
		if (dataColumn == null)
			throw new IllegalArgumentException("DataColumn argument can't be null");
		if (enumeration == null)
			throw new IllegalArgumentException("Enum argument can't be null");
		
		this.enumeration = enumeration;
		this.required = !dataColumn.isAllowNull();
		this.dataColumn = dataColumn;
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.bl.forms.items.IEditEnumColumnFormItem#getEnum()
	 */
	public IEnum getEnum() {
		return this.enumeration;
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.bl.forms.items.IEditColumnFormItem#getDataColumn()
	 */
	public IDataColumn getDataColumn() {
		return this.dataColumn;
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.bl.forms.items.IEditFormItem#isRequired()
	 */
	public boolean isRequired() {
		return this.required;
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.bl.forms.items.IFormItem#getCaption()
	 */
	public String getCaption() {
		return this.dataColumn.getCaption();
	}

}
