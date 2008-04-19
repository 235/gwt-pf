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
 * Type of {@link CustomRBWindow} form
 * 
 * @author Scater
 *
 */
public class CustomRBType extends IntEnum {

	protected CustomRBType(int value) {
		super(value);
	}
	
	/**
	 * Normal reference book with grid, sliders and buttons
	 */
	public static final CustomRBType Normal = new CustomRBType(0);
	
	/**
	 * Selector type reference book. Using when user whants to select value by reference (foreigh key).
	 * In this case form mandatory has "Select row" and "Close" buttons. 
	 */
	public static final CustomRBType Select = new CustomRBType(1);
	
}
