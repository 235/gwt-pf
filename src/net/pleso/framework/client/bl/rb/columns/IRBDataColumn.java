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
package net.pleso.framework.client.bl.rb.columns;

import net.pleso.framework.client.dal.IDataColumn;

/**
 * Represents reference book column with caption, reference for data column
 * and proportional width. 
 */
public interface IRBDataColumn extends IRBColumn {

	/**
	 * Each {@link IRBDataColumn} wraps one data column of the rows retrieved by
	 * reference book data source. So one reference book column binds through 
	 * this function to one data column.
	 * 
	 * @return binded data column
	 */
	IDataColumn getDataColumn();
}
