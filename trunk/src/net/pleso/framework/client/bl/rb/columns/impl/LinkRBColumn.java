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
package net.pleso.framework.client.bl.rb.columns.impl;

import net.pleso.framework.client.bl.rb.columns.ILinkRBDataColumn;
import net.pleso.framework.client.dal.IDataColumn;

/**
 * Represents default {@link ILinkRBDataColumn} implementation 
 * 
 */
public class LinkRBColumn implements ILinkRBDataColumn {

	private String caption = null;
	private IDataColumn titleColumn = null;
	private IDataColumn referenceColumn = null;
	private int width = 0;
	
	/**
	 * Constructor with column caption.
	 * 
	 * @param caption
	 * @param titleColumn binded link title column
	 * @param referenceColumn binded link column
	 * @param width
	 */
	public LinkRBColumn(String caption, IDataColumn titleColumn, IDataColumn referenceColumn, int width){
		if (titleColumn == null)
			throw new IllegalArgumentException("titleColumn argument can't be null");
		if (referenceColumn == null)
			throw new IllegalArgumentException("referenceColumn argument can't be null");
		if (width < 0)
			throw new IllegalArgumentException("Width can't be lower than 0");
		this.caption = caption;
		this.referenceColumn = referenceColumn;
		this.titleColumn = titleColumn;
		this.width = width;
	}
	
	/**
	 * Constructor.
	 * 
	 * @param titleColumn binded link title column
	 * @param referenceColumn binded link column
	 * @param width
	 */
	public LinkRBColumn(IDataColumn titleColumn, IDataColumn referenceColumn, int width) {
		this(null, titleColumn, referenceColumn, width);
	}
	
	/* (non-Javadoc)
	 * @see net.pleso.framework.client.bl.rb.columns.IRBColumn#getCaption()
	 */
	public String getCaption() {
		if (this.caption == null)
			return titleColumn.getCaption();
		else
			return this.caption;
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.bl.rb.columns.IRBDataColumn#getDataColumn()
	 */
	public IDataColumn getDataColumn() {
		return this.titleColumn;
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.bl.rb.columns.IRBColumn#getWidth()
	 */
	public int getWidth() {
		return this.width;
	}
	
	/* (non-Javadoc)
	 * @see net.pleso.framework.client.bl.rb.columns.ILinkRBDataColumn#getLinkReferenceColumn()
	 */
	public IDataColumn getLinkReferenceColumn() {
		return this.referenceColumn;
	}
}
