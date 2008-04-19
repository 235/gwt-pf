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

import net.pleso.framework.client.bl.rb.columns.IRBDataColumn;

/**
 * Represents selector with quick search by one field option.
 */
public interface IQuickSearchSelectorFormItem extends ISelectorFormItem {

	/**
	 * This column represents field by which search must be achieved. It must be
	 * a column of search form of reference book provided by selector.
	 * 
	 * @return a search form column of selector's reference book search form.
	 */
	IEditColumnFormItem getSearchField();

	/**
	 * @return a shown reference book column in search results.
	 */
	IRBDataColumn getShownRBColumn();
}

