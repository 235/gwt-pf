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

package net.pleso.framework.client.ui.interfaces;

/**
 * Event listener interface for control update event. This event is fired when
 * control has updated some values possibly binded to other controls. This
 * allows control owner to make other controls reread their binded values.
 */
public interface IUpdateControlListener {

	/**
	 * Fired when control updated some unamanaged by it data.
	 * 
	 * @param sender the {@link IBindableDataControl} which caused data update
	 */
	void controlUpdated(IBindableDataControl sender);
}
