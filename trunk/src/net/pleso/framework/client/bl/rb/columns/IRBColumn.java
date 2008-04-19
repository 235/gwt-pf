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
package net.pleso.framework.client.bl.rb.columns;

/**
 * Represents abstract reference book column with caption and proportional
 * width. Other interfaces extend this interface to represent specific reference
 * book columns.
 */
public interface IRBColumn {

	/**
	 * Represents column caption which describes meaning of this column.
	 * 
	 * @return column caption
	 */
	String getCaption();

	/**
	 * Use {@link #getWidth()} to control width of each column in reference
	 * book. This width is proportional integer value specifying its size
	 * relative to other columns.
	 * 
	 * @return proportional width of column
	 */
	int getWidth();
}
