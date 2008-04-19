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
package net.pleso.framework.client.bl.rb;

import net.pleso.framework.client.dal.IDataRow;

/**
 * Represents row classifier which can test any row and determine whether
 * specified row can be positive classified by this classifier. Positive
 * classification can be used to set row style retrieved by
 * {@link #getRowStyle()} in visual design.
 */
public interface IRowClassifier {

	/**
	 * Returns row style which positive classified by this classifier.
	 * 
	 * @return row style
	 */
	String getRowStyle();

	/**
	 * Tests row for classification by this classifier. If row is positive
	 * classified returns <code>true</code>.
	 * 
	 * @param row
	 *            row to be tested
	 * @return <code>true</code> if row positive classified
	 */
	boolean testRow(IDataRow row);
}
