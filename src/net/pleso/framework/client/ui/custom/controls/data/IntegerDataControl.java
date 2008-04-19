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
package net.pleso.framework.client.ui.custom.controls.data;

import com.google.gwt.user.client.ui.KeyboardListenerAdapter;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * Data control with text box for integer values
 * 
 * <h3>CSS Style Rules</h3>
 * <ul>
 * <li>.pf-integerDataControl { control itself }</li>
 * </ul>
 * 
 * @author Scater
 *
 */
public class IntegerDataControl extends TextBoxDataControl {
	
	public IntegerDataControl(){
		super();
	    // Let's disallow non-numeric entry in the normal text box.
	    this.textBox.addKeyboardListener(new KeyboardListenerAdapter() {
	      public void onKeyPress(Widget sender, char keyCode, int modifiers) {
	        if (!Character.isDigit(keyCode) && 
	        		keyCode != KEY_BACKSPACE &&
		        	keyCode != KEY_DELETE &&
		        	keyCode != KEY_LEFT &&
		        	keyCode != KEY_RIGHT &&
		        	!(keyCode == 'v' && modifiers == MODIFIER_CTRL) &&
		        	!(keyCode == 'V' && modifiers == MODIFIER_CTRL) &&
		        	!(keyCode == 'c' && modifiers == MODIFIER_CTRL) &&
		        	!(keyCode == 'C' && modifiers == MODIFIER_CTRL)) {
	          // TextBox.cancelKey() suppresses the current keyboard event.
	          ((TextBox)sender).cancelKey();
	        }
	      }
	    });
	    
	    this.setStyleName("pf-integerDataControl");
	}
}
