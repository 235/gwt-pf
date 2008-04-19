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

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Represents data source which allows to get data as array of {@link IDataRow}.
 * {@link SelectParams} parameter allows using paging and additional search
 * parameters.
 * 
 * This interface used everyvere where table data representation is required.
 */
public interface IDataSource {

	/**
	 * Asynchronous returns array of {@link IDataRow} via {@link AsyncCallback}
	 * interface. Paging and search parameters can be set by
	 * {@link SelectParams} parameter.
	 * 
	 * @param params
	 *            specifies paging and search parameters
	 * @param callback
	 *            implementation of callback handler
	 */
	void select(SelectParams params, AsyncCallback callback);

	/**
	 * Asynchronous returns count of rows via {@link AsyncCallback} which can be
	 * retrieved by specified parameters.
	 * 
	 * @param params
	 *            specifies paging and search parameters
	 * @param callback
	 *            implementation of callback handler
	 */
	void selectCount(SelectParams params, AsyncCallback callback);
}
