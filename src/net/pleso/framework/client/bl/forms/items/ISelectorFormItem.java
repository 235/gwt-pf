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
package net.pleso.framework.client.bl.forms.items;

import net.pleso.framework.client.bl.ISelector;
import net.pleso.framework.client.dal.IDataColumn;

/**
 * Represents form item with selector to data from related reference book.
 */
public interface ISelectorFormItem extends IEditFormItem {

	/**
	 * Returns selector for related reference book.
	 * 
	 * @return selector for related reference book
	 */
	ISelector getSelector();

	/**
	 * Returns binded data column with representation of selectable data. This
	 * column should be used for validation and other user interactive actions.
	 * 
	 * @return binded data column
	 */
	IDataColumn getShownColumn();
}
