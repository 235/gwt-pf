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
package net.pleso.framework.client.ui.interfaces;

import com.google.gwt.user.client.ui.SourcesKeyboardEvents;

/**
 * Represents editable binded data control.
 * 
 * It can:
 * <ul>
 * <li>bind data (read data from binded row)</li>
 * <li>update data (update data from binded data using user input)</li>
 * <li>validate required for input value</li>
 * <li>validate entered by user value</li>
 * </ul>
 * 
 * @author Scater
 * 
 */
public interface IEditableDataControl extends IBindableDataControl,
		SourcesKeyboardEvents {

	/**
	 * Updates binded data by entered by user value.
	 * 
	 */
	void updateData();

	/**
	 * Sets required status for value in data control.
	 * 
	 * @param required
	 *            required status (is value required)
	 */
	void setRequired(boolean required);

	/**
	 * Gets required status for value in data control.
	 * 
	 * @return required status (is value required)
	 */
	boolean isRequired();

	/**
	 * Validates data in control. Verifies user-specified value for correctness
	 * (f.e. correct date, time, number, etc.). Checks if user entered required
	 * value. If value is not valid control can show corresponding message.
	 * After calling {@link #validate()} outside code can call
	 * {@link #isValid()} to check whether validation was successful.
	 */
	void validate();

	/**
	 * Determines whether control value is valid after {@link #validate()}
	 * calling.
	 * 
	 * @return is value in control valid
	 */
	boolean isValid();
}
