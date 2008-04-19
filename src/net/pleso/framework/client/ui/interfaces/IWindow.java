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

import net.pleso.framework.client.ui.windows.Slider;

import com.google.gwt.user.client.ui.Widget;

/**
 * Represents abstract window.
 */
public interface IWindow extends SourcesWindowEvents {
	
	/**
	 * @return window caption
	 */
	String getCaption();
	
	/**
	 * @return window widget
	 */
	Widget getWidget();
	
	/**
	 * Sets parent window {@link Slider} object.
	 * 
	 * @param slider parent {@link Slider}
	 */
	void setParentSlider(Slider slider);
	
	/**
	 * @return parent window {@link Slider}
	 */
	Slider getParentSlider();
	
	/**
	 * Scrolls browser window to make window be in visible for user area.
	 */
	void scrollToTop();
	
	/**
	 * Hides window. 
	 */
	void hideWindow();
	
	/**
	 * @return <code>true</code> if window was hidden
	 */
	boolean isHidden();
}