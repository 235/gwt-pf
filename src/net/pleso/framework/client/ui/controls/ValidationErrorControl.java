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
package net.pleso.framework.client.ui.controls;

import com.google.gwt.user.client.ui.HTML;

/**
 * Widget with validation error.
 * Used in data controls.
 * 
 * <h3>CSS Style Rules</h3>
 * <ul>
 * <li>.pf-validationErrorControl {widget itself }</li>
 * </ul>
 * 
 * @author Scater
 *
 */
public class ValidationErrorControl extends HTML {
	
	public ValidationErrorControl(){
		this.setStyleName("pf-validationErrorControl");
	}

}
