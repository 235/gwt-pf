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

import net.pleso.framework.client.bl.rb.IRB;
import net.pleso.framework.client.dal.IDataRow;

/**
 * Represents provider of reference book.
 */
public interface IRBProvider extends IActionProvider {

	/**
	 * Returns reference book instance for selected in context row. If there is
	 * no context row can be <code>null</code>.
	 * 
	 * @param sourceRow selected in context row or <code>null</code>
	 * @return provided {@link IRB} instance
	 */
	IRB getRB(IDataRow sourceRow);
}
