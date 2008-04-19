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
package net.pleso.framework.client.ui.windows;

import java.util.Iterator;
import java.util.Vector;

import net.pleso.framework.client.ui.interfaces.HideWindowListener;
import net.pleso.framework.client.ui.interfaces.IWindow;

/**
 * Represents collection of {@link HideWindowListener} objects with
 * {@link #fireHide(IWindow)} method.
 */
public class HideWindowListenerCollection extends Vector {

	/**
	 * Fires {@link HideWindowListener#onHideWindow(IWindow)} method on each
	 * collection element.
	 * 
	 * @param sender
	 *            a {@link IWindow} object which being hidden and initiated
	 *            event fire.
	 */
	public void fireHide(IWindow sender) {
		for (Iterator it = iterator(); it.hasNext();) {
			HideWindowListener listener = (HideWindowListener) it.next();
			listener.onHideWindow(sender);
		}
	}
}
