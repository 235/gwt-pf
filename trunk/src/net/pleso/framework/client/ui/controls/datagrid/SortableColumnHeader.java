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
package net.pleso.framework.client.ui.controls.datagrid;

import net.pleso.framework.client.ui.controls.datagrid.interfaces.ISortableColumnHeaderListener;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * Column header widget with sort ability
 * Used on {@link DataGridWrapper} and {@link DataGrid}
 * 
 * <h3>CSS Style Rules</h3>
 * <ul>
 * <li>.pf-sortableHeader { the sortable header itself }</li>
 * <li>.pf-sortableHeader-text { text in header (column name) }</li>
 * <li>.pf-sortableHeader-orderby-none {HTML order status widget - NO ORDER }</li>
 * <li>.pf-sortableHeader-orderby-up {HTML order status widget - ORDER UP }</li>
 * <li>.pf-sortableHeader-orderby-down {HTML order status widget - ORDER DOWN }</li>
 * </ul>
 * @author Scater
 * 
 */
public class SortableColumnHeader extends Composite implements ClickListener {
	
	/**
	 * Main widget for this composite widget
	 */
	HorizontalPanel headerPanel = new HorizontalPanel();
	
	/**
	 * HTML widget with order visibility (up-down arrows for example)
	 */
	private final HTML orderHTML = new HTML();
	
	/**
	 * Label for display column name
	 */
	private final Label columnName = new Label();
	
	/**
	 * Constructor
	 * @param columnIndex index of column that use this header
	 * @param columnText text in header (column name)
	 * @param orderDirection initial order direction
	 * @param sortableColumnHeaderListener listener for handle events of header (change order direction)
	 */
	public SortableColumnHeader(int columnIndex, String columnText, Boolean orderDirection, ISortableColumnHeaderListener sortableColumnHeaderListener) {		
		initWidget(headerPanel);
		setOrderDirection(orderDirection);
		
		setStyleName("pf-sortableHeader");
		columnName.setStyleName("pf-sortableHeader-text");
		columnName.setText(columnText);
		
		columnName.addClickListener(this);
		orderHTML.addClickListener(this);
		
		headerPanel.add(columnName);			
		headerPanel.add(orderHTML);
		
		headerPanel.setCellVerticalAlignment(columnName, HorizontalPanel.ALIGN_MIDDLE);
		headerPanel.setCellVerticalAlignment(orderHTML, HorizontalPanel.ALIGN_MIDDLE);
		headerPanel.setVerticalAlignment(HorizontalPanel.ALIGN_MIDDLE);
		
		this.columnIndex = columnIndex;
		this.sortableColumnHeaderListener = sortableColumnHeaderListener;
	}

	public void onClick(Widget sender) {
		if (sortableColumnHeaderListener != null){
			sortableColumnHeaderListener.fireOrder(this);
		}
	}
	
	/**
	 * Order direction.
	 * Can be:
	 * - null - no order
	 * - true - order up
	 * - false - order down
	 */
	private Boolean orderDirection = null;
	
	/**
	 * Gets order direction.
	 * @return order direction
	 */
	public Boolean getOrderDirection()
	{		
		return orderDirection;
	}
	
	/**
	 * Sets order direction. Changes css-class for orderHTML widget.
	 * @param orderDirection order direction
	 */
	public void setOrderDirection(Boolean orderDirection) {
		this.orderDirection = orderDirection;
		if (this.orderDirection == null) {
			orderHTML.setStyleName("pf-sortableHeader-orderby-none");
		} else {
			// user sees current operation
			if (orderDirection.booleanValue() == true) {
				orderHTML.setStyleName("pf-sortableHeader-orderby-up");
			} else {
				orderHTML.setStyleName("pf-sortableHeader-orderby-down");
			}
		}
	}
	
	/**
	 * Column index of this header
	 */
	private int columnIndex;

	/**
	 * Gets column index.
	 * @return column index.
	 */
	public int getColumnIndex() {
		return columnIndex;
	}		
	
	/**
	 * Events listener.
	 */
	private ISortableColumnHeaderListener sortableColumnHeaderListener;

	/**
	 * Gets events listener.
	 * @return events listener
	 */
	public ISortableColumnHeaderListener getSortableColumnHeaderListener() {
		return sortableColumnHeaderListener;
	}

	/**
	 * Sets events listener.
	 * @param sortableColumnHeaderListener events listener
	 */
	public void setSortableColumnHeaderListener(
			ISortableColumnHeaderListener sortableColumnHeaderListener) {
		this.sortableColumnHeaderListener = sortableColumnHeaderListener;
	} 

}
