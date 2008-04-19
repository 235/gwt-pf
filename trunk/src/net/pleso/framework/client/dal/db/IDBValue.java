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
package net.pleso.framework.client.dal.db;

/**
 * Represents database value with possibility to be converted to string and
 * parsed from string. As {@link IDBValue} extends {@link INullable} it can hold
 * typed null value.
 */
public interface IDBValue extends INullable {

	/**
	 * Parses string representation of value to represented type and assigns
	 * parsed value to self. If string value equals <code>null</code>
	 * implementation must try to set itself to null.
	 * 
	 * @param value
	 *            string representation of value
	 * @return <code>true</code> if value parsed and assigned successfully
	 */
	boolean parseValue(String value);

	/**
	 * Returns string representation of current value, held in implementation.
	 * 
	 * @return string representation of value
	 */
	String getValue();
}
