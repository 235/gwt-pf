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

import net.pleso.framework.client.bl.forms.items.IFormItem;
import net.pleso.framework.client.bl.forms.items.IFormItemsGroup;

/**
 * Represents default implementation for {@link IFormItemsGroup}
 * 
 * @author Scater
 *
 */
public class FormItemsGroup implements IFormItemsGroup {

	private String caption = null;
	private IFormItem[] columns = null;
	
	/**
	 * Constructor
	 * @param caption caption of group
	 * @param columns columns in group
	 */
	public FormItemsGroup(String caption, IFormItem[] columns){
		if (columns == null)
			throw new IllegalArgumentException("Argument columns can't be null");
		this.caption = caption;
		this.columns = columns;
	}
	
	/* (non-Javadoc)
	 * @see net.pleso.framework.client.bl.forms.items.IFormItemsGroup#getCaption()
	 */
	public String getCaption() {
		return caption;
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.bl.forms.items.IFormItemsGroup#getItems()
	 */
	public IFormItem[] getItems() {
		return columns;
	}

}
