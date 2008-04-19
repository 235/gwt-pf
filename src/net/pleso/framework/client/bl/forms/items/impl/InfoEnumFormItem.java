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
import net.pleso.framework.client.bl.forms.items.IInfoEnumColumnFormItem;
import net.pleso.framework.client.dal.IDataColumn;

/**
 * Represents default implementation for {@link IInfoEnumColumnFormItem}
 * 
 * @author Scater
 *
 */
public class InfoEnumFormItem implements IInfoEnumColumnFormItem {

	protected IDataColumn dataColumn;
	private IEnum enumeration;
	
	/**
	 * Constructor 
	 * @param dataColumn binded data column
	 * @param enumeration data enumeration
	 */
	public InfoEnumFormItem(IDataColumn dataColumn, IEnum enumeration){
		if (dataColumn == null)
			throw new IllegalArgumentException("DataColumn argument can't be null");
		if (enumeration == null)
			throw new IllegalArgumentException("Enum argument can't be null");
		
		this.enumeration = enumeration;
		this.dataColumn = dataColumn;
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.bl.forms.items.IFormItem#getCaption()
	 */
	public String getCaption() {
		return this.dataColumn.getCaption();
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.bl.forms.items.IInfoFormItem#getDataColumn()
	 */
	public IDataColumn getDataColumn() {
		return this.dataColumn;
	}
	
	/* (non-Javadoc)
	 * @see net.pleso.framework.client.bl.forms.items.IInfoEnumColumnFormItem#getEnum()
	 */
	public IEnum getEnum() {
		return this.enumeration;
	}

}
