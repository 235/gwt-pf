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
 * Database String type
 * 
 * @author Scater
 *
 */
public class DBString implements IDBValue, INullable {
	
	public static final String nullValue = null;
	
	private String value;
	
	public DBString() {
		this.value = nullValue;
	};
	
	public DBString(String initialValue) {
		if (initialValue != nullValue && initialValue.length() > 0)
			this.value = initialValue;
		else
			setNull();
	};

	// Цей метод НЕ МОЖНА юзати при зміні значення поля інфокласа.
	public String getValue() {
		if (!isNull())
			return value.toString();
		else
			return "";
	}

	public boolean parseValue(String value) {
		if (value != nullValue && value.length() > 0)
			this.value = value;
		else
			setNull();
		
		return true;
	}

	public boolean isNull() {
		return this.value == nullValue || this.value.length() == 0;
	}

	public void setNull() {
		this.value = nullValue;				
	}
	
	// Цей метод треба юзати при зміні значення поля інфокласа.
	public String getString() {
		return this.value;
	}
}
