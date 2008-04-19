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
package net.pleso.framework.client.localization;

import com.google.gwt.core.client.GWT;

/**
 * Localization source class
 * 
 * @author Scater
 *
 */
public class FrameworkLocale {

	private static Constants _const = null;

	public static Constants constants(){
		if (_const == null)
			_const = (Constants)GWT.create(Constants.class);
		return _const;
	}
	
	private static Messages _mess = null;

	public static Messages messages(){
		if (_mess == null)
			_mess = (Messages)GWT.create(Messages.class);
		return _mess;
	}
} 