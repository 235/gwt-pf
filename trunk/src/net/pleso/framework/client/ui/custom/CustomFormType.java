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
package net.pleso.framework.client.ui.custom;

import net.pleso.framework.client.bl.impl.IntEnum;

/**
 * Type of {@link CustomFormWindow} form
 * 
 * @author Scater
 *
 */
public class CustomFormType extends IntEnum {
	
	protected CustomFormType(int value) {
		super(value);
	}
	
	/**
	 * Form for update values in row
	 */
	public static final CustomFormType Edit = new CustomFormType(0);
	
	/**
	 * Form for insert values into empty row
	 */
	public static final CustomFormType Insert = new CustomFormType(1);
	
	/**
	 * Form for specify search parameters
	 */
	public static final CustomFormType Search = new CustomFormType(2);
	
	/**
	 * Form for showing values from row
	 */
	public static final CustomFormType Show = new CustomFormType(3);
}
