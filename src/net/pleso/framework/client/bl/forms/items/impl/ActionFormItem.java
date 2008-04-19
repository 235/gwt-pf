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

import net.pleso.framework.client.bl.forms.items.FormItemSize;
import net.pleso.framework.client.bl.forms.items.IActionFormItem;
import net.pleso.framework.client.bl.providers.IActionProvider;

/**
 * Represents default implementation for {@link IActionFormItem}.
 */
public class ActionFormItem implements IActionFormItem {
	
	private IActionProvider actionProvider;
	private FormItemSize itemSize;
	
	/**
	 * Constructor with all parameters.
	 * 
	 * @param actionProvider {@link IActionFormItem} implementation
	 * @param itemSize action item size
	 */
	public ActionFormItem(IActionProvider actionProvider, FormItemSize itemSize) {
		if (actionProvider == null)
			throw new IllegalArgumentException("ActionProvider argument can't be null");
		
		this.itemSize = itemSize;
		this.actionProvider = actionProvider;
	}
	
	/**
	 * Constructor with defaul item size value.
	 * 
	 * @param actionProvider actionProvider {@link IActionFormItem} implementation
	 */
	public ActionFormItem(IActionProvider actionProvider) {
		this(actionProvider, FormItemSize.Normal);
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.bl.forms.items.IActionFormItem#getAction()
	 */
	public IActionProvider getAction() {
		return this.actionProvider;
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.bl.forms.items.IFormItem#getCaption()
	 */
	public String getCaption() {
		return "";
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.bl.forms.items.IActionFormItem#getSize()
	 */
	public FormItemSize getSize() {
		return itemSize;
	}
	
}
