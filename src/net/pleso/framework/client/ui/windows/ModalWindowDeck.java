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

import net.pleso.framework.client.ui.interfaces.IWindow;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckPanel;

/**
 * Wrapped {@link DeckPanel} widget for using with {@link IWindow} objects.
 */
public class ModalWindowDeck extends Composite {

	/**
	 * Wrapped {@link DeckPanel} object.
	 */
	private DeckPanel deckPanel = new DeckPanel();

	/**
	 * Constructs a newly allocated empty {@link ModalWindowDeck}.
	 */
	public ModalWindowDeck() {
		initWidget(deckPanel);
	}

	/**
	 * Adds and shows the specified {@link IWindow} object to {@link DeckPanel}.
	 * 
	 * @param window
	 *            the {@link IWindow} to be added
	 */
	public void addWindow(IWindow window) {
		this.deckPanel.add(window.getWidget());
		showTopWindow();
	}

	/**
	 * Removes the specified {@link IWindow} object from {@link DeckPanel} and
	 * shows topmost window in deck list.
	 * 
	 * @param window
	 *            the {@link IWindow} to be removed.
	 * @return <code>true</code> if {@link IWindow} was removed successfully
	 *         or <code>false</code> if there was no such {@link IWindow}
	 *         instance in deck
	 */
	public boolean removeWindow(IWindow window) {
		int index = this.deckPanel.getWidgetIndex(window.getWidget());

		if (index > -1) {
			for (int i = this.deckPanel.getWidgetCount() - 1; i >= index; i--)
				this.deckPanel.remove(i);

			showTopWindow();
		}

		return index != -1;
	}

	/**
	 * Determines whether {@link ModalWindowDeck} has specified {@link IWindow}
	 * object as top shown widget.
	 * 
	 * @param window
	 *            the {@link IWindow} object to check if it is the on top
	 * @return <code>true</code> if specified {@link IWindow} is topmost shown
	 *         deck widget
	 */
	public boolean hasTopWindow(IWindow window) {
		int index = this.deckPanel.getWidgetIndex(window.getWidget());

		return (index > -1) && (index == (this.deckPanel.getWidgetCount() - 1));
	}

	/**
	 * Determines whether {@link ModalWindowDeck} has at least one
	 * {@link IWindow} placed on it.
	 * 
	 * @return <code>true</code> if there is at least one {@link IWindow} on
	 *         deck
	 */
	public boolean hasWindows() {
		return this.deckPanel.getWidgetCount() > 0;
	}

	/**
	 * Gets the number of child {@link IWindow} in this deck.
	 * 
	 * @return the number of child {@link IWindow}
	 */
	public int getWindowCount() {
		return this.deckPanel.getWidgetCount();
	}

	/**
	 * Shows topmost {@link IWindow} on the deck.
	 */
	private void showTopWindow() {
		if (this.deckPanel.getWidgetCount() > 0)
			this.deckPanel.showWidget(this.deckPanel.getWidgetCount() - 1);
	}

	/**
	 * Hides all {@link IWindow} placed on deck applying
	 * {@link IWindow#hideWindow()} method on each of them.
	 */
	public void hideWindows() {
		while (this.deckPanel.getWidgetCount() > 0) {
			IWindow window = (IWindow) this.deckPanel.getWidget(this.deckPanel
					.getWidgetCount() - 1);
			window.hideWindow();
		}
	}

}
