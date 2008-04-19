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
 * This interface craeted to be extended by {@link IDBValue}. It represents
 * nullable value and allows to check whether value is null and force set it to
 * typed null.
 */

public interface INullable {

	/**
	 * Checks whether value of instance equals null.
	 * 
	 * @return <code>true</code> if instance value is null
	 */
	boolean isNull();

	/**
	 * Sets instance value to null.
	 */
	void setNull();
}
