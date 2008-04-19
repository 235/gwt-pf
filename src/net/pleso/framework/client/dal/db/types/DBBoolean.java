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
import net.pleso.framework.client.dal.types.BigInt;
import net.pleso.framework.client.localization.FrameworkLocale;

/**
 * Database Boolean type
 * 
 * @author Scater
 * 
 */
public class DBBoolean implements IDBValue, INullable {

	/**
	 * Predefined {@link Boolean} null value.
	 */
	public static final Boolean nullValue = null;

	private Boolean value;

	/**
	 * Constructs new {@link DBBoolean} with null value.
	 */
	public DBBoolean() {
		this.value = nullValue;
	}

	/**
	 * Constructs new {@link DBBoolean} with specified initial value.
	 * 
	 * @param initialValue
	 *            a initial value for newly constructed db value
	 */
	public DBBoolean(Boolean initialValue) {
		this.value = initialValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.pleso.framework.client.dal.db.IDBValue#getValue()
	 */
	public String getValue() {
		if (!isNull()) {
			if (this.value.booleanValue())
				return FrameworkLocale.constants().yes();
			else
				return FrameworkLocale.constants().no();
		} else
			return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.pleso.framework.client.dal.db.IDBValue#parseValue(java.lang.String)
	 */
	public boolean parseValue(String value) {
		if (value != null && value.length() > 0)
			try {
				this.value = Boolean.valueOf(value);
			} catch (NumberFormatException ex) {
				return false;
			}
		else
			setNull();

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.pleso.framework.client.dal.db.INullable#isNull()
	 */
	public boolean isNull() {
		return value == nullValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.pleso.framework.client.dal.db.INullable#setNull()
	 */
	public void setNull() {
		this.value = nullValue;
	}

	/**
	 * @return a {@link Boolean} value of instance
	 */
	public Boolean getBoolean() {
		return value;
	}
}
