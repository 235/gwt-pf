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
package net.pleso.framework.client.ui.custom;

import net.pleso.framework.client.bl.actions.IReportBuilder;
import net.pleso.framework.client.bl.auth.AuthorizationProvider;
import net.pleso.framework.client.bl.forms.IAddForm;
import net.pleso.framework.client.bl.forms.IEditForm;
import net.pleso.framework.client.bl.forms.IParametersForm;
import net.pleso.framework.client.bl.forms.IViewForm;
import net.pleso.framework.client.bl.providers.IActionProvider;
import net.pleso.framework.client.bl.providers.IAddFormProvider;
import net.pleso.framework.client.bl.providers.IEditFormProvider;
import net.pleso.framework.client.bl.providers.IMenu;
import net.pleso.framework.client.bl.providers.IParametersFormProvider;
import net.pleso.framework.client.bl.providers.IRBProvider;
import net.pleso.framework.client.bl.providers.IReportProvider;
import net.pleso.framework.client.bl.providers.IViewFormProvider;
import net.pleso.framework.client.bl.rb.IRB;
import net.pleso.framework.client.dal.IDataRow;
import net.pleso.framework.client.ui.interfaces.HideWindowListener;
import net.pleso.framework.client.ui.interfaces.IWindow;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.MenuBar;

/**
 * Custom menu.
 * Builds menu by {@link IMenu} implementation.
 * 
 * @author Scater
 *
 */
public class CustomMenu extends MenuBar {
	
	private IMenu menu;
	
	private class RBCommand implements Command {
		
		private IRB rb;
		
		public RBCommand(IRB rb) {
			this.rb = rb;
		}

		public void execute() {
			new CustomRBWindow(null, rb, true).show();
		}
	}
	
	private class AddFormCommand implements Command {

		private IAddForm form;
		
		public AddFormCommand(IAddForm form) {
			this.form = form;
		}
		
		public void execute() {
			new CustomFormWindow(null, form).show();
		}
	}
	
	private class EditFormCommand implements Command {

		private IEditForm form;
		
		public EditFormCommand(IEditForm form) {
			this.form = form;
		}
		
		public void execute() {
			new CustomFormWindow(null, form).show();
		}
	}
	
	private class ShowFormCommand implements Command {

		private IViewForm form;
		
		public ShowFormCommand(IViewForm form) {
			this.form = form;
		}
		
		public void execute() {
			new CustomFormWindow(null, form).show();
		}
	}
	
	private class ReportCommand implements Command, HideWindowListener  {
		
		private IReportBuilder report;

		public ReportCommand(IReportBuilder report) {
			super();
			this.report = report;
		}

		public void execute() {
			if (report instanceof IParametersFormProvider) {
				IParametersForm form = ((IParametersFormProvider)report).getParametersForm(null);
				CustomFormWindow formWindow = new CustomFormWindow(null, form);
				formWindow.addHideWindowListener(this);
				formWindow.show();
			}
			else
			{
				//if report do not return parameters form, then build without parameters.
				report.BuildReport(null);
			}
		}

		public void onHideWindow(IWindow sender) {
			if (sender instanceof CustomFormWindow) {
				CustomFormWindow customFormWindow = (CustomFormWindow) sender;
				
				if (customFormWindow != null && !customFormWindow.isCanceled()) {
					IDataRow editedRow = customFormWindow.getDataRow();
					if (editedRow != null) { 
						report.BuildReport(editedRow);
					}				
				}
			}
		}
	}

	/**
	 * Constructs custom menu by specified IMenu implementation.
	 * 
	 * @param menu
	 */
	public CustomMenu(IMenu menu) {
		super();
		this.menu = menu;
		
		this.buildMenu(this, this.menu);
	}
	
	private boolean buildMenu(MenuBar parentMenu, IMenu menu) {
		
		boolean someItemAdded = false;
		
		IActionProvider[] actions = menu.getActionProviders();
		for(int i = 0; i < actions.length; i++) {
			if (actions[i] instanceof IMenu) {
				MenuBar subMenu = new MenuBar(true);
				
				if (this.buildMenu(subMenu, (IMenu)actions[i]))
				{
					parentMenu.addItem(actions[i].getActionCaption(), subMenu);
					someItemAdded = true;
				}
			}
			else {
				Command cmd = null;
				if (actions[i] instanceof IAddFormProvider) {
					IAddForm form = ((IAddFormProvider)actions[i]).getAddForm(null);
					
					if (AuthorizationProvider.isObjectAuthorized(form))
						cmd = new AddFormCommand(form);
				}
				else
					if (actions[i] instanceof IEditFormProvider) {
						IEditForm form = ((IEditFormProvider)actions[i]).getEditForm(null);
						
						if (AuthorizationProvider.isObjectAuthorized(form))
							cmd = new EditFormCommand(form);
					}
					else
						if (actions[i] instanceof IViewFormProvider) {
							IViewForm form = ((IViewFormProvider)actions[i]).getViewForm(null);
							
							if (AuthorizationProvider.isObjectAuthorized(form))
								cmd = new ShowFormCommand(form);
						}
						else
							if (actions[i] instanceof IRBProvider) {
								IRB rb = ((IRBProvider)actions[i]).getRB(null);
								
								if (AuthorizationProvider.isObjectAuthorized(rb.getDataSource()))
									cmd = new RBCommand(rb);
							}
							else
								if (actions[i] instanceof IReportProvider) {
									IReportBuilder report = ((IReportProvider)actions[i]).getReport();
									
									if (AuthorizationProvider.isObjectAuthorized(report))
										cmd = new ReportCommand(report);
								}
								else
									new IllegalArgumentException("Menu contains not recognizable: " + GWT.getTypeName(actions[i]));
				
				if (cmd != null)
				{
					parentMenu.addItem(actions[i].getActionCaption(), cmd);
					someItemAdded = true;
				}
			}
		}
		
		return someItemAdded;
	}
		
}

