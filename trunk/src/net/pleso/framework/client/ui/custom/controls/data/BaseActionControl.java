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

import net.pleso.framework.client.bl.IRowCopier;
import net.pleso.framework.client.bl.auth.AuthorizationProvider;
import net.pleso.framework.client.bl.providers.IActionProvider;
import net.pleso.framework.client.bl.providers.IAddFormProvider;
import net.pleso.framework.client.bl.providers.IAvailabilityProvider;
import net.pleso.framework.client.bl.providers.IDeleteRowProvider;
import net.pleso.framework.client.bl.providers.IEditFormProvider;
import net.pleso.framework.client.bl.providers.IRBProvider;
import net.pleso.framework.client.bl.providers.IReportProvider;
import net.pleso.framework.client.bl.providers.ISearchFormProvider;
import net.pleso.framework.client.bl.providers.IViewFormProvider;
import net.pleso.framework.client.dal.IDataColumn;
import net.pleso.framework.client.dal.IDataRow;
import net.pleso.framework.client.localization.FrameworkLocale;
import net.pleso.framework.client.ui.custom.CustomFormWindow;
import net.pleso.framework.client.ui.interfaces.HideWindowListener;
import net.pleso.framework.client.ui.interfaces.IBindableDataControl;
import net.pleso.framework.client.ui.interfaces.IUpdateControlListener;
import net.pleso.framework.client.ui.interfaces.IWindow;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;

public abstract class BaseActionControl extends Composite implements IBindableDataControl, HideWindowListener {

	protected HorizontalPanel panel = new HorizontalPanel();
	protected IActionProvider actionProvider;
	protected IAvailabilityProvider availabilityProvider = null;
	protected IDataRow row = null;
	protected IDataColumn column = null;
	protected IUpdateControlListener updateControlListener = null;
	protected IWindow parentWindow = null;
	
	/**
	 * Constructs abstract {@link BaseActionControl} with specified paremeters.
	 * 
	 * @param parentWindow control's parent {@link IWindow}
	 * @param actionProvider wrapped {@link IActionProvider}
	 * @param updateControlListener listener of update event
	 */
	public BaseActionControl(
			IWindow parentWindow,
			IActionProvider actionProvider,
			IUpdateControlListener updateControlListener) {
		
		if (actionProvider == null)
			throw new IllegalArgumentException(
					"ActionProvider argument can't be null");
		if (parentWindow == null)
			throw new IllegalArgumentException(
					"ParentWindow argument can't be null");
		
		this.actionProvider = actionProvider;
		this.parentWindow = parentWindow;

		if (actionProvider instanceof IAvailabilityProvider)
			this.availabilityProvider = (IAvailabilityProvider) actionProvider;
		
		this.updateControlListener = updateControlListener;
		
		initWidget(this.panel);
	}
	
	/**
	 * Sets control caption according to wrapped action caption.
	 */
	protected void setActionCaption() {
		if (this.actionProvider.getActionCaption() != null)
			this.setCaption(this.actionProvider
					.getActionCaption());
		else {
			// Default values for known providers.
			String caption = null;

			if (actionProvider instanceof IAddFormProvider)
				caption = FrameworkLocale.constants().insert_button_caption();
			else if (actionProvider instanceof IEditFormProvider)
				caption = FrameworkLocale.constants().edit_button_caption();
			else if (actionProvider instanceof IViewFormProvider)
				caption = FrameworkLocale.constants().show_button_caption();
			else if (actionProvider instanceof ISearchFormProvider)
				caption = FrameworkLocale.constants().search_button_caption();
			else if (actionProvider instanceof IDeleteRowProvider)
				caption = FrameworkLocale.constants().delete_button_caption();

			if (caption == null)
				throw new NullPointerException(
						"Action caption is null and there is no default caption for "
								+ GWT.getTypeName(actionProvider) + ".");

			this.setCaption(caption);
		}
	}
	
	/**
	 * Sets control caption.
	 * 
	 * @param caption {@link String} caption to be set 
	 */
	protected abstract void setCaption(String caption);
	
	/**
	 * Sets enable status.
	 * 
	 * @param enabled enable status
	 */
	protected abstract void setEnabled(boolean enabled);

	/** 
	 * Binds row and column to action control.
	 *  
	 * @param row the {@link IDataRow} to be binded
	 * @param column the {@link IDataColumn} to be binded
	 */
	public void bind(IDataRow row, IDataColumn column) {
		this.row = row;
		this.column = column;
	}
	
	/* (non-Javadoc)
	 * @see net.pleso.framework.client.ui.interfaces.IBindableDataControl#readData()
	 */
	public void readData() {
		if (this.availabilityProvider != null) {
			this.setEnabled(this.availabilityProvider.isAvailable(this.row,
					this.column));
		} else {
			// Default availability for known providers
			if (this.actionProvider instanceof IDeleteRowProvider
					|| this.actionProvider instanceof IEditFormProvider
					|| this.actionProvider instanceof IViewFormProvider) {
				this.setEnabled(this.row != null);
			}
		}

		setActionCaption();
	}
	

	/**
	 * Checks authorization rights on wrapped {@link IActionProvider}
	 * 
	 * @return <code>true</code> if user is authorized to perform action on
	 *         wrapped {@link IActionProvider}
	 */
	public boolean isAuth() {
		if (this.actionProvider instanceof IRBProvider) {
			return AuthorizationProvider
					.isObjectAuthorized((((IRBProvider) this.actionProvider)
							.getRB(this.row)).getDataSource());
		} else if (this.actionProvider instanceof IEditFormProvider) {
			return AuthorizationProvider
					.isObjectAuthorized(((IEditFormProvider) this.actionProvider)
							.getEditForm(this.row));
		} else if (this.actionProvider instanceof IAddFormProvider) {
			return AuthorizationProvider
					.isObjectAuthorized(((IAddFormProvider) this.actionProvider)
							.getAddForm(this.row));
		} else if (this.actionProvider instanceof IViewFormProvider) {
			return AuthorizationProvider
					.isObjectAuthorized(((IViewFormProvider) this.actionProvider)
							.getViewForm(this.row));
		} else if (this.actionProvider instanceof IDeleteRowProvider) {
			return AuthorizationProvider
					.isObjectAuthorized(((IDeleteRowProvider) this.actionProvider)
							.getDeleteRowExecutor());
		} else if (this.actionProvider instanceof IReportProvider) {
			return AuthorizationProvider
					.isObjectAuthorized(((IReportProvider) this.actionProvider)
							.getReport());
		} else if (this.actionProvider instanceof ISearchFormProvider) {
			return true;
		}
		return false;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see net.pleso.framework.client.ui.interfaces.HideWindowListener#onHideWindow(net.pleso.framework.client.ui.interfaces.IWindow)
	 */
	public void onHideWindow(IWindow sender) {
		if (sender instanceof CustomFormWindow) {
			// There window with rb or form was closed (row was edited or
			// selected optionally)
			CustomFormWindow customFormWindow = (CustomFormWindow) sender;

			if (customFormWindow != null && !customFormWindow.isCanceled()) {
				IDataRow editedRow = customFormWindow.getDataRow();
				// If button calls window, that return row
				if (editedRow != null) {

					// and provider has ability to copy rows, than:
					if (this.actionProvider instanceof IRowCopier) {

						((IRowCopier) this.actionProvider).CopyRow(this.row,
								editedRow);

						if (this.updateControlListener != null)
							this.updateControlListener.controlUpdated(this);
					}

					// if this is report - build it.
					if (this.actionProvider instanceof IReportProvider) {
						((IReportProvider) this.actionProvider).getReport()
								.BuildReport(editedRow);
					}

					// if parent window has ability to close window -
					// call handler.
					if (parentWindow instanceof HideWindowListener) {
						((HideWindowListener) parentWindow)
								.onHideWindow(sender);
					}
				}
			}
		}

		this.parentWindow.scrollToTop();
	}
}
