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
package net.pleso.framework.client.dal.db.types;

import net.pleso.framework.client.dal.db.IDBValue;
import net.pleso.framework.client.dal.db.INullable;

/**
 * Database Float type
 * 
 * @author Scater
 *
 */
public class DBFloat implements IDBValue, INullable {
	
	public static final Float nullValue = null;
	
	private Float value;
	
	public DBFloat() {
		this.value = nullValue;
	}
	
	public DBFloat(Float initialValue) {
		this.value = initialValue;	
	}

	public boolean isNull() {
		return value == nullValue;
	}

	public void setNull() {
		this.value = nullValue; 
	}

	public String getValue() {
		if (!isNull())
			return value.toString();
		else
			return "";
	}

	public boolean parseValue(String value) {
		if (value != null && value.length() > 0)
			try {
				this.value = new Float(Float.parseFloat(value));
			}
			catch (NumberFormatException ex) {
				return false;
			}
		else
			setNull();
		
		return true;
	}
	
	public Float getFloat() {
		return this.value;
	}

}
