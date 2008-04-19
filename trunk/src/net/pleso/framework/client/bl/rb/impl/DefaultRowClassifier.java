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
package net.pleso.framework.client.bl.rb.impl;

import net.pleso.framework.client.bl.rb.IRowClassifier;
import net.pleso.framework.client.dal.IDataRow;

/**
 * Default {@link IRowClassifier} implementation 
 * @author Scater
 *
 */
public class DefaultRowClassifier implements IRowClassifier {
	
	private String rowStyle;
	
	/**
	 * Constructor
	 * @param rowStyle row's css-style
	 */
	public DefaultRowClassifier(String rowStyle) {
		this.rowStyle = rowStyle;
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.bl.rb.IRowClassifier#getRowStyle()
	 */
	public String getRowStyle() {
		return this.rowStyle;
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.bl.rb.IRowClassifier#testRow(net.pleso.framework.client.dal.interfaces.IDataRow)
	 */
	public boolean testRow(IDataRow row) {
		return true;
	}

}
