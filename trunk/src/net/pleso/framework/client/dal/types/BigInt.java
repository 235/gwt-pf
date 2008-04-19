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

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Special Long-like type with internal String realization.
 * 
 * see <a href="http://code.google.com/webtoolkit/documentation/com.google.gwt.doc.DeveloperGuide.Fundamentals.JavaToJavaScriptCompiler.LanguageSupport.html">Language Support</a>
 * However, there is no 64-bit integral type in JavaScript, so variables of type long are 
 * mapped onto JavaScript double-precision floating point values. 
 * Thats why this type has internal String realization.
 * 
 * @author Scater
 *
 */
public class BigInt implements IsSerializable {
	
	private String value;
	
	public BigInt() {
		
	}
	
	public BigInt(long initialValue) {
		this(new Long(initialValue));
	}
	
	public BigInt(String initialValue) {
		setAsString(initialValue);
	}
	
	public BigInt(Long initialValue) {
		if (initialValue != null)
			this.value = initialValue.toString();
		else
			this.value = null;
	}
	
	public String getAsString() {
		return this.value;
	}
	
	public Long getAsLong() {
		if (this.value != null)
			return new Long(Long.parseLong(this.value));
		else
			return null;
	}
	
	public void setAsString(String value) {
		this.value = (new Long(Long.parseLong(value))).toString();
	}
	
	public void setAsLong(Long value) {
		if (value != null)
			this.value = value.toString();
		else
			this.value = null;
	}
	
	public String toString() {
		return getAsString();
	}
}
