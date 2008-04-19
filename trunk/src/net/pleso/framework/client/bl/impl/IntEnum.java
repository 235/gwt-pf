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
package net.pleso.framework.client.bl.impl;

/**
 * Represents abstract base class for int enumerations.
 */
public abstract class IntEnum {
	
	private int value;
	
	/**
	 * Constructor for enumeration item.
	 * 
	 * @param item value
	 */
	protected IntEnum(int value) {
		this.value = value;		
	}

	/**
	 * Returns enumeration item int value.
	 * 
	 * @return item value
	 */
	public int getValue() {
		return value;
	}
}
