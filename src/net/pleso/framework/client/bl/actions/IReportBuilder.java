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

import net.pleso.framework.client.bl.auth.IAuth;
import net.pleso.framework.client.bl.forms.IParametersForm;
import net.pleso.framework.client.bl.providers.IParametersFormProvider;
import net.pleso.framework.client.dal.IDataRow;

/**
 * Represents report with build function.
 * 
 * Implementer also can implement {@link IParametersFormProvider} to provide
 * report specific parameters.
 */
public interface IReportBuilder extends IAuth {

	/**
	 * Builds report by specified report parameters data row which can
	 * be null if no parameters expected.  Parameters data row can be
	 * selected in context row or result of {@link IParametersForm} work. In
	 * this case class should implement {@link IParametersFormProvider}.
	 *  
	 * @param reportParams
	 *            report parameters data row
	 */
	void BuildReport(IDataRow reportParams);
}
