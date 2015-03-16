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
package net.pleso.framework.client.bl.forms;

import net.pleso.framework.client.bl.forms.items.IFormItemsGroup;

/**
 * Represents abstract form with caption and array of form item groups. 
 */
public interface IForm {
	
	/**
	 * Form caption used as short description of it's functionality for user. 
	 * 
	 * @return form caption
	 */
	String getCaption();
		
	/**
	 * Form consists from grouped items. 
	 * 
	 * @return form item groups array
	 */
	IFormItemsGroup[] getGroups();	
}