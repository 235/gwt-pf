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
package net.pleso.framework.client.bl.providers;

import net.pleso.framework.client.dal.IDataColumn;
import net.pleso.framework.client.dal.IDataRow;

/**
 * Represents provider for availability check. This interface is extended by
 * {@link IActionProvider} implementers where action can be enabled or disabled
 * depending on what data row or column is selected now.
 */
public interface IAvailabilityProvider {

	/**
	 * Checks for availability depending on data row and column.
	 * 
	 * @param row
	 * @param column
	 * @return <code>true</code> if specified row and column represents availability
	 */
	boolean isAvailable(IDataRow row, IDataColumn column);

}
