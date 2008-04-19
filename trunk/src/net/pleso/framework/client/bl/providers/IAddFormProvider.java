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

import net.pleso.framework.client.bl.forms.IAddForm;
import net.pleso.framework.client.dal.IDataRow;

/**
 * Represents provider of add form.
 */
public interface IAddFormProvider extends IActionProvider {

	/**
	 * This function returns new add form instance by some selected in context
	 * row, which can be <code>null</code> if there is no context or row selected.
	 * 
	 * @param row
	 *            selected in context row or <code>null</code>
	 * @return provided {@link IAddForm} instance
	 */
	IAddForm getAddForm(IDataRow row);
}
