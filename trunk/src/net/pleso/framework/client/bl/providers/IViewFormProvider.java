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

import net.pleso.framework.client.bl.forms.IViewForm;
import net.pleso.framework.client.dal.IDataRow;

/**
 * Represents provider of view form.
 */
public interface IViewFormProvider extends IActionProvider {

	/**
	 * This function returns new view form instance for some selected in context
	 * row.
	 * 
	 * @param row
	 *            selected row
	 * @return provided {@link IViewForm} instance
	 */
	IViewForm getViewForm(IDataRow row);

}
