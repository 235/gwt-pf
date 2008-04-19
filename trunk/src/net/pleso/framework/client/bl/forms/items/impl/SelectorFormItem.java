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

import net.pleso.framework.client.bl.ISelector;
import net.pleso.framework.client.bl.forms.items.ISelectorFormItem;
import net.pleso.framework.client.dal.IDataColumn;

/**
 * Represents default implementation for {@link ISelectorFormItem}
 * 
 * @author Scater
 *
 */
public class SelectorFormItem implements ISelectorFormItem {
	
	private ISelector selector;
	protected IDataColumn shownColumn;
	private boolean required;
	
	/**
	 * Constructor
	 * @param selector selector instance
	 * @param shownColumn column to show in selector (f.e. "name" column)
	 * @param required is column required
	 */
	public SelectorFormItem(ISelector selector, IDataColumn shownColumn, boolean required) {	
		
		if (shownColumn == null)
			throw new IllegalArgumentException("ShownColumn argument can't be null");
		
		if (selector == null)
			throw new IllegalArgumentException("Selector argument can't be null");
		
		this.required = required;
		this.selector = selector;
		this.shownColumn = shownColumn;
	}
	
	/**
	 * Constructor
	 * @param selector selector instance
	 * @param shownColumn column to show in selector (f.e. "name" column)
	 */
	public SelectorFormItem(ISelector selector, IDataColumn shownColumn) {	
		
		if (shownColumn == null)
			throw new IllegalArgumentException("ShownColumn argument can't be null");
		
		if (selector == null)
			throw new IllegalArgumentException("Selector argument can't be null");
		
		this.required = !shownColumn.isAllowNull();
		this.selector = selector;
		this.shownColumn = shownColumn;
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.bl.forms.items.ISelectorFormItem#getSelector()
	 */
	public ISelector getSelector() {
		return selector;
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.bl.forms.items.IFormItem#getCaption()
	 */
	public String getCaption() {
		return this.shownColumn.getCaption();
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.bl.forms.items.ISelectorFormItem#getShownColumn()
	 */
	public IDataColumn getShownColumn() {
		return this.shownColumn;
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.bl.forms.items.IEditFormItem#isRequired()
	 */
	public boolean isRequired() {
		return this.required;
	}
}
