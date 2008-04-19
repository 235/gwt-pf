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

import net.pleso.framework.client.bl.forms.items.IInfoFormItem;
import net.pleso.framework.client.dal.IDataColumn;

/**
 * Represents default implementation for {@link IInfoFormItem}
 * 
 * @author Scater
 *
 */
public class InfoFormItem implements IInfoFormItem {
	
	protected IDataColumn dataColumn;
	
	/**
	 * Constructor 
	 * @param dataColumn binded column
	 */
	public InfoFormItem(IDataColumn dataColumn){
		if (dataColumn == null)
			throw new IllegalArgumentException("DataColumn argument can't be null");
		
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

}
