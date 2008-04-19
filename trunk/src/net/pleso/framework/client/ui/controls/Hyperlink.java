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

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.Widget;

public class Hyperlink extends Widget implements HasHTML {

	private Element anchorElem;

	public Hyperlink() {
		setElement(DOM.createDiv());
		DOM.appendChild(getElement(), anchorElem = DOM.createAnchor());
		setStyleName("gwt-Hyperlink");
		DOM.setElementAttribute(anchorElem, "target", "_blank");
	}

	public Hyperlink(String text, boolean asHTML, String href) {
		this();
		if (asHTML) {
			setHTML(text);
		} else {
			setText(text);
		}
		setHref(href);
	}

	public Hyperlink(String text, String href) {
		this();
		setText(text);
		setHref(href);
	}

	public String getHTML() {
		return DOM.getInnerHTML(anchorElem);
	}

	public String getText() {
		return DOM.getInnerText(anchorElem);
	}

	public void setHTML(String html) {
		DOM.setInnerHTML(anchorElem, html);
	}

	public void setText(String text) {
		DOM.setInnerText(anchorElem, text);
	}
	
	public void setHref(String href) {
		DOM.setElementAttribute(anchorElem, "href", href);
	}
	
	public String getHref() {
		return DOM.getElementAttribute(anchorElem, "href");
	}
}
