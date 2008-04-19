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
package net.pleso.framework.client.bl.rb;

import net.pleso.framework.client.bl.IAuthDataSource;
import net.pleso.framework.client.bl.rb.columns.IRBColumn;
import net.pleso.framework.client.bl.rb.columns.IRBDataColumn;


/**
 * Represents reference book with caption, columns and authorized data source.
 */
public interface IRB {

	/**
	 * Use this function to retrieve reference book caption, which can be
	 * displayed as short description of this data set.
	 * 
	 * @return reference book caption
	 */
	String getCaption();

	/**
	 * Defines array of columns which must represent data. Each column has some
	 * information about item displaying rules. For more info look for
	 * {@link IRBDataColumn} extenders.
	 * 
	 * @return array of reference book columns
	 */
	IRBColumn[] getColumns();

	/**
	 * Grants access for authorized data source with reference book data.
	 * 
	 * @return authorized data source
	 */
	IAuthDataSource getDataSource();

}