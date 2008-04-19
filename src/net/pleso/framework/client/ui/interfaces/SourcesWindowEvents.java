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
 * A widget that implements this interface sources the events defined by the
 * {@link ChangeWindowCaptionListener} and {@link HideWindowListener}
 * interfaces.
 */
public interface SourcesWindowEvents {

	/**
	 * Adds a listener interface to receive change window caption event.
	 * 
	 * @param listener
	 *            the listener interface to add.
	 */
	void addWindowCaptionChangeListener(ChangeWindowCaptionListener listener);

	/**
	 * Removes a previously added change window caption listener.
	 * 
	 * @param listener
	 *            the listener interface to remove.
	 */
	void removeWindowCaptionChangeListener(ChangeWindowCaptionListener listener);

	/**
	 * Adds a listener interface to receive hide window event.
	 * 
	 * @param listener
	 *            the listener interface to add.
	 */
	void addHideWindowListener(HideWindowListener listener);

	/**
	 * Removes a previously added hide window listener.
	 * 
	 * @param listener
	 *            the listener interface to remove.
	 */
	void removeHideWindowListener(HideWindowListener listener);
}
