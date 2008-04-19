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
package net.pleso.framework.client.bl.providers;

/**
 * Represents abstract action provider. This inteface extends by other specific
 * action providers interfaces.
 * 
 * Action provider - is simple abstracion which provides access to some action.
 * 
 * In another words we can imagine that action provider is an action button
 * which provide access to some action.
 */
public interface IActionProvider {

	/**
	 * Returns action caption.
	 * 
	 * @return action caption
	 */
	String getActionCaption();
}
