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
package net.pleso.framework.client.dal;


import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Represents class-utility for transfer parameters of selection data from data source (f.e. database).
 * 
 * @author Scater
 *
 */
public class SelectParams implements IsSerializable {

	/**
	 * Limit records in page
	 */
	private int limit;

	/**
	 * Current offset 
	 */
	private int offset;

	/**
	 * Column name by which datasource is sorting
	 */
	private String orderByColumnName;

	/**
	 * Order of sorting 
	 */
	private boolean orderByDirection; // true - "asc"; false - "desc" 

	/**
	 * Order of sorting like string (for SQL backends)
	 */
	private String orderByDirectionString; // true - "asc"; false - "desc" 
	
	/**
	 * Row with search parameters
	 */
	private IDataRow searchRow;
	
	public static final int defaultLimit = 25;

	/**
	 * Constructor with default parameters
	 */
	public SelectParams() {
		limit = defaultLimit;
		offset = 0;
		orderByColumnName = "";
		orderByDirection = true;
		searchRow = null;
	}

	/**
	 * Constructor with specified parameters
	 * @param limit limit of records
	 * @param offset offset number of record
	 * @param orderBy order of sorting
	 * @param orderByDirection order of sorting in string presentation 
	 * @param searchRow row with search parameters
	 */
	public SelectParams(int limit, int offset, String orderBy,
			boolean orderByDirection, IDataRow searchRow) {
		this.limit = limit;
		this.offset = offset;
		this.orderByColumnName = orderBy;
		this.orderByDirection = orderByDirection;
		this.searchRow = searchRow;
	}

	/**
	 * Gets order direction like string
	 * @return order direction like string
	 */
	public String getOrderByDirectionString() {
		if (orderByDirection)
			orderByDirectionString = "asc";
		else
			orderByDirectionString = "desc";
		return orderByDirectionString;
	}

	/**
	 * Gets order direction (true - "asc"; false - "desc")
	 * @return order direction
	 */
	public boolean getOrderByDirection() {
		return orderByDirection;
	}

	/**
	 * Sets order direction (true - "asc"; false - "desc")
	 * @param orderByDirection order direction
	 */
	public void setOrderByDirection(boolean orderByDirection) {
		this.orderByDirection = orderByDirection;
	}

	/**
	 * Gets column name by which data source is sorting
	 * @return column name
	 */
	public String getOrderByColumnName() {
		return orderByColumnName;
	}

	/**
	 * Sets column name by which data source is sorting
	 * @param orderByColumnName column name
	 */
	public void setOrderByColumnName(String orderByColumnName) {
		this.orderByColumnName = orderByColumnName;
	}

	/**
	 * Gets limit records in page
	 * @return limit number
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * Sets limit record in page
	 * @param limit limit number
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}

	/**
	 * Gets current record offset
	 * @return current offset
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * Sets current record offset
	 * @param offset current offset
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * Gets row with search parameters
	 * @return row with search parameters
	 */
	public IDataRow getSearchRow() {
		return searchRow;
	}

	/**
	 * Sets row with search parameters
	 * @param searchRow row with search parameters
	 */
	public void setSearchRow(IDataRow searchRow) {
		this.searchRow = searchRow;
	}

}
