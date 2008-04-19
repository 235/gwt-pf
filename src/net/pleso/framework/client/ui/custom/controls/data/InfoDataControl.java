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
package net.pleso.framework.client.ui.custom.controls.data;

import net.pleso.framework.client.bl.IEnum;
import net.pleso.framework.client.bl.IEnumItem;
import net.pleso.framework.client.dal.IDataColumn;
import net.pleso.framework.client.dal.IDataRow;
import net.pleso.framework.client.ui.interfaces.IBindableDataControl;

import com.google.gwt.user.client.ui.Label;

/**
 * Read-only information data control. Used for any type of value.
 * 
 * <h3>CSS Style Rules</h3>
 * <ul>
 * <li>.pf-infoDataControl { control itself }</li>
 * </ul>
 * 
 * @author Scater
 *
 */
public class InfoDataControl extends Label implements IBindableDataControl {
	
	private IDataRow row = null;
	private IDataColumn column = null;
	private IEnum enumeration = null;
	
	public InfoDataControl() {
		this.setStyleName("pf-infoDataControl");
	}
	
	public InfoDataControl(IEnum enumeration) {
		this();
		this.enumeration = enumeration;
	}
	
	public void bind(IDataRow row, IDataColumn column) {
		this.row = row;
		this.column = column;
	}

	public void readData() {
		if (enumeration != null){
			String key = this.row.getCell(this.column).getValue();
			IEnumItem[] items = this.enumeration.getItems();
			for (int i = 0; i < items.length; i++) {
				if (items[i].getDBValue().getValue().equals(key))
				{
					this.setText(items[i].getCaption());
					return;
				}
			}
		}
		else
			this.setText(this.row.getCell(this.column).getValue());
	}
}
