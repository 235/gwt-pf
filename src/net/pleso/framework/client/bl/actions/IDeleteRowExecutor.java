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
package net.pleso.framework.client.bl.actions;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.pleso.framework.client.bl.auth.IAuth;
import net.pleso.framework.client.dal.IDataRow;

/**
 * Represents delete row executor which can delete specified row from some data
 * set.
 * 
 * This interface represents operation which requires authorization check, so it
 * extends {@link IAuth}.
 */
public interface IDeleteRowExecutor extends IAuth {

	/**
	 * Deletes specified row from some data set known by instance. Asynchronous
	 * returns operation result through {@link AsyncCallback} instance.
	 * 
	 * @param row
	 *            data row to delete
	 * @param callback
	 *            result callback
	 */
	void deleteRow(IDataRow row, AsyncCallback callback);
}