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
package net.pleso.framework.client.bl;

/**
 * Represents predefined enumeration of {@link IEnumItem}. It was created
 * because GWT 1.3.3 uses Java 1.4 which doesn't supports enums.
 */
public interface IEnum {

	/**
	 * Returns enumeration items array.
	 * 
	 * @return enumeration items array
	 */
	IEnumItem[] getItems();

	/**
	 * Returns item that represent null value of this enumeration item or
	 * <code>null</code> if there is such item doesn't exists.
	 * 
	 * @return null value item
	 */
	IEnumItem getNullItem();
}
