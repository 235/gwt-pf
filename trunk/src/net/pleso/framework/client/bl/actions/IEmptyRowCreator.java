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
package net.pleso.framework.client.bl.actions;

import net.pleso.framework.client.bl.forms.IAddForm;
import net.pleso.framework.client.bl.forms.IParametersForm;
import net.pleso.framework.client.dal.IDataRow;

/**
 * Represents empty row creator which can create new empty data row of some
 * type. This inteface is extended by {@link IAddForm} and {@link IParametersForm}
 * which have deal with empty data rows.
 */
public interface IEmptyRowCreator {

	/**
	 * Returns new empty data row instance which will be used later for putting
	 * some data in it.
	 * 
	 * @return new empty data row instance
	 */
	IDataRow createEmptyRow();
}
