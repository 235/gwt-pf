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
package net.pleso.framework.client.ui.custom.controls.datagrid;

import net.pleso.framework.client.dal.db.types.DBBoolean;

import com.google.gwt.user.client.ui.HTML;

/**
 * Shows different css-styles by {@link DBBoolean} value
 * 
 * <h3>CSS Style Rules</h3>
 * <ul>
 * <li>.pf-booleanWidget-true { if {@link DBBoolean} value is true }</li>
 * <li>.pf-booleanWidget-false { if {@link DBBoolean} value is false }</li>
 * </ul>
 * 
 * @author Scater
 *
 */
public class BooleanValueWidget extends HTML {
	
	public BooleanValueWidget(DBBoolean value) {
		super("&nbsp;");
		
		if (value != null && !value.isNull()) {
			if (value.getBoolean().booleanValue())
				setStyleName("pf-booleanWidget-true");
			else
				setStyleName("pf-booleanWidget-false");
		}
	}

}
