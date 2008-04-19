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
package net.pleso.framework.client.dal.types;

import java.util.Date;

import net.pleso.framework.client.util.DateUtil;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Special Time-like type with internal implementation like String
 * 
 * @author Scater
 *
 */
public class StringTime implements IsSerializable {
	
	private String value;
	
	public StringTime() {
		
	}
	
	public StringTime(String initialValue) {
		setTimeAsString(initialValue);
	}
	
	public StringTime(Date initialValue) {
		if (initialValue != null)
			this.value = DateUtil.formatTime(initialValue);
		else
			this.value = null;
	}
	
	public String getAsString() {
		return this.value;
	}
	
	public void setTimeAsString(String time) {
		if (time != null)
			this.value = DateUtil.formatTime(DateUtil.parseTime(time));
		else
			this.value = null;
	}

	public Date getAsTime() {
		if (this.value != null)
			return DateUtil.parseTime(this.value);
		else
			return null;
	}
	
	public String toString() {
		return getAsString();
	}
	
	public int compareTo(StringTime compared) {
		if (this.value != null && compared != null && compared.value != null)
			return this.getAsTime().compareTo(compared.getAsTime());
		else
			return 0;
	}
}
