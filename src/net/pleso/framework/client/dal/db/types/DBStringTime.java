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
import net.pleso.framework.client.dal.types.StringTime;

/**
 * Database Time type internally implemented like String
 * see {@link StringTime}
 * 
 * @author Scater
 *
 */
public class DBStringTime implements IDBValue, INullable {
	
	public static final StringTime nullValue = null;
	
	protected StringTime value;
	
	public DBStringTime() {
		this.value = nullValue;
	}
	
	public DBStringTime(StringTime initialValue) {
		this.value = initialValue;
	}

	public boolean isNull() {
		return this.value == nullValue;
	}

	public void setNull() {
		this.value = nullValue; 
	}

	public String getValue() {
		if (!isNull())
			return value.getAsString();
		else
			return "";
	}

	public boolean parseValue(String value) {
		if (value != null && value.length() > 0)
			try {
				this.value = new StringTime(value);
			}
			catch (Exception ex) {
				return false;
			}
		else
			setNull();
		
		return true;
	}
	
	public StringTime getStringDate() {
		return this.value;
	}
}