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
package net.pleso.framework.client.bl.impl;

import net.pleso.framework.client.bl.IEnum;
import net.pleso.framework.client.bl.IEnumItem;
import net.pleso.framework.client.dal.db.IDBValue;
import net.pleso.framework.client.dal.db.types.DBInteger;

/**
 * Represents base class for {@link DBInteger} enumerations.
 * 
 * @author Scater
 *
 */
public abstract class DBIntEnum implements IEnum, IEnumItem {
	
	private DBInteger value;
	private String caption;
	
	/**
	 * Constructor 
	 * @param value integer enumeration value
	 * @param caption caption of enumeration
	 */
	protected DBIntEnum(int value, String caption) {
		this(new DBInteger(new Integer(value)), caption);
	}
	
	/**
	 * Constructor 
	 * @param value {@link DBInteger} enumeration value
	 * @param caption caption of enumeration
	 */
	protected DBIntEnum(DBInteger value, String caption) {
		
		if (value == null)
			throw new IllegalArgumentException("Value cant be null.");
		if (caption == null)
			throw new IllegalArgumentException("Caption cant be null.");
		
		this.value = value;
		this.caption = caption;
		
		addItem(this);		
	}
	
	/**
	 * Default constructor without parameters
	 */
	protected DBIntEnum() {
	}
	
	/**
	 * Constructor
	 * @param nullCaption caption of null value in enum
	 */
	protected DBIntEnum(String nullCaption) {
		this(new DBInteger(), nullCaption);
	}

	/**
	 * Gets {@link DBInteger} current value
	 * @return DBInteger current value
	 */
	public DBInteger getDBIntegerValue() {
		return value;
	}
	
	/**
	 * Gets integer current value
	 * @return integer current value
	 */
	public int getInt() {
		return value.getInteger().intValue();
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.bl.IEnumItem#getCaption()
	 */
	public String getCaption() {
		return caption;
	}
	
	/* (non-Javadoc)
	 * @see net.pleso.framework.client.bl.IEnumItem#getDBValue()
	 */
	public IDBValue getDBValue() {
		return this.value;
	}
	
	protected abstract void addItem(DBIntEnum item);
}
