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

/**
 * Represents window manager based on {@link SliderTabPanel} and {@link Slider}
 * widgets.
 */
public class TabbedSliderManager extends Composite {

	private SliderTabPanel tabPanel = new SliderTabPanel();

	/**
	 * Static global {@link TabbedSliderManager} instance.
	 */
	private static TabbedSliderManager instance = new TabbedSliderManager();

	/**
	 * Constructs new {@link TabbedSliderManager}.
	 */
	private TabbedSliderManager() {

		initWidget(this.tabPanel);
	}

	/**
	 * Removes specified {@link Slider} from tab pab panel.
	 * 
	 * @param slider
	 *            the specified {@link Slider} to be hidden
	 */
	public void HideSlider(Slider slider) {
		tabPanel.removeSlider(slider);
	}

	/**
	 * Shows specified {@link IWindow} on new tab panel and makes it's tab
	 * selected.
	 * 
	 * @param window the specified {@link IWindow} to be shown
	 */
	public void showWindow(IWindow window) {
		Slider slider = new Slider(this);
		window.setParentSlider(slider);
		slider.ShowModalWindow(window);

		this.tabPanel.addSlider(slider, window.getCaption());

		selectLastDeck();
	}

	/**
	 * Makes last tab selected.
	 */
	private void selectLastDeck() {

		if (this.tabPanel.getSliderCount() > 0)
			this.tabPanel.selectSlider(this.tabPanel.getSliderCount() - 1);
	}

	/**
	 * @return static {@link TabbedSliderManager} object instance
	 */
	public static TabbedSliderManager getInstance() {
		return instance;
	}

}
