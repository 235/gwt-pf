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

import net.pleso.framework.client.ui.controls.tabcontrol.RemoveTabListener;
import net.pleso.framework.client.ui.controls.tabcontrol.TabPanel;

import com.google.gwt.user.client.ui.Composite;

/**
 * Wrapped {@link TabPanel} for using with {@link Slider} as content widget.
 */
public class SliderTabPanel extends Composite implements RemoveTabListener {

	/**
	 * Wrapped {@link TabPanel} widget.
	 */
	private TabPanel tabPanel = new TabPanel();

	/**
	 * Constructs empty {@link SliderTabPanel} widget.
	 */
	public SliderTabPanel() {
		tabPanel.setRemoveTabListener(this);
		initWidget(tabPanel);
	}

	/**
	 * Returns {@link Slider} on tab specified by order intex.
	 * 
	 * @param index
	 *            tab index
	 * @return {@link Slider} on specified tab
	 */
	public Slider getSlider(int index) {
		return (Slider) this.tabPanel.getWidget(index);
	}

	/**
	 * @return number of tabs with {@link Slider} on this {@link SliderTabPanel}
	 */
	public int getSliderCount() {
		return this.tabPanel.getWidgetCount();
	}

	/**
	 * Sets specified by index tab selected.
	 * 
	 * @param index
	 *            tab index to be shown
	 */
	public void selectSlider(int index) {
		this.tabPanel.selectTab(index);
	}

	/**
	 * Adds specified {@link Slider} with caption to this {@link SliderTabPanel}.
	 * 
	 * @param slider
	 *            the specified {@link Slider} to add
	 * @param caption
	 *            string caption for new tab
	 */
	public void addSlider(Slider slider, String caption) {
		this.tabPanel.add(slider, caption);
	}

	/**
	 * Removes tab by specified order index.
	 * 
	 * @param index
	 *            tab index to be removed
	 */
	public void removeSlider(int index) {
		this.tabPanel.remove(index);
	}

	/**
	 * Removes tab by specified lying on it {@link Slider} object.
	 * 
	 * @param slider the specified {@link Slider} to be removed
	 */
	public void removeSlider(Slider slider) {
		this.tabPanel.remove(slider);
	}

	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.controls.tabcontrol.RemoveTabListener#fireBeforeRemoveTab(int)
	 */
	public void fireBeforeRemoveTab(int index) {
		Slider slider = this.getSlider(index);
		if (slider != null)
			slider.hideWindows();
	}
}
