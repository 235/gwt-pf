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
package net.pleso.framework.client.bl;

import net.pleso.framework.client.dal.IDataRow;

/**
 * Represents row copier which copies some data from selected row to source row.
 */
public interface IRowCopier {
	
	/**
	 * Copies some data from selectedRow to sourceRow
	 * 
	 * @param sourceRow row which will receive data
	 * @param selectedRow row from which data will be retrieved
	 */
	void CopyRow(IDataRow sourceRow, IDataRow selectedRow);
}
