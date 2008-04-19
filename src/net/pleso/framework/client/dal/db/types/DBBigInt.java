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

/**
 * Database BigInt type.
 * 
 * Internally presented like special {@link BigInt} type.
 * 
 * see <a
 * href="http://code.google.com/webtoolkit/documentation/com.google.gwt.doc.DeveloperGuide.Fundamentals.JavaToJavaScriptCompiler.LanguageSupport.html">Language
 * Support</a> However, there is no 64-bit integral type in JavaScript, so
 * variables of type long are mapped onto JavaScript double-precision floating
 * point values.
 * 
 * @author Scater
 * 
 */
public class DBBigInt implements IDBValue, INullable {

	/**
	 * Predefined {@link BigInt} null value.
	 */
	public static final BigInt nullValue = null;

	private BigInt value;

	/**
	 * Constructs new {@link DBBigInt} with null value.
	 */
	public DBBigInt() {
		this.value = nullValue;
	}

	/**
	 * Constructs new {@link DBBigInt} with specified initial value.
	 * 
	 * @param initialValue
	 *            a initial value for newly constructed db value
	 */
	public DBBigInt(BigInt initialValue) {
		this.value = initialValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.pleso.framework.client.dal.db.INullable#isNull()
	 */
	public boolean isNull() {
		return this.value == nullValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.pleso.framework.client.dal.db.INullable#setNull()
	 */
	public void setNull() {
		this.value = nullValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.pleso.framework.client.dal.db.IDBValue#getValue()
	 */
	public String getValue() {
		if (!isNull())
			return value.getAsString();
		else
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
				this.value = new BigInt(value);
			} catch (NumberFormatException ex) {
				return false;
			}
		else
			setNull();

		return true;
	}

	/**
	 * @return a {@link BigInt} value of instance
	 */
	public BigInt getBigInt() {
		return this.value;
	}

}
