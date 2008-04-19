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

import net.pleso.framework.client.bl.actions.IReportBuilder;
import net.pleso.framework.client.bl.exceptions.AsyncCallbackFailureException;
import net.pleso.framework.client.bl.forms.IParametersForm;
import net.pleso.framework.client.bl.providers.IActionProvider;
import net.pleso.framework.client.bl.providers.IAddFormProvider;
import net.pleso.framework.client.bl.providers.IDeleteRowProvider;
import net.pleso.framework.client.bl.providers.IEditFormProvider;
import net.pleso.framework.client.bl.providers.IParametersFormProvider;
import net.pleso.framework.client.bl.providers.IRBProvider;
import net.pleso.framework.client.bl.providers.IReportProvider;
import net.pleso.framework.client.bl.providers.ISearchFormProvider;
import net.pleso.framework.client.bl.providers.IViewFormProvider;
import net.pleso.framework.client.localization.FrameworkLocale;
import net.pleso.framework.client.ui.NotImplementedFeatureException;
import net.pleso.framework.client.ui.custom.CustomFormWindow;
import net.pleso.framework.client.ui.custom.CustomRBWindow;
import net.pleso.framework.client.ui.interfaces.IBindableDataControl;
import net.pleso.framework.client.ui.interfaces.IUpdateControlListener;
import net.pleso.framework.client.ui.interfaces.IWindow;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;

/**
 * Action button control which wraps {@link IActionProvider} action and binds
 * its execution to button click event.
 * 
 * @author Scater
 * 
 */
public class ActionButtonControl extends BaseActionControl implements
		ClickListener, IBindableDataControl {

	/**
	 * Internal callback handler for delete action.
	 * 
	 * @author Scater
	 * 
	 */
	private class DeleteCallback implements AsyncCallback {

		private IBindableDataControl instance;

		public DeleteCallback(IBindableDataControl instance) {
			this.instance = instance;
		}

		public void onFailure(Throwable caught) {
			btnDoAction.setEnabled(true);
			throw new AsyncCallbackFailureException(FrameworkLocale.messages()
					.asyncerror_delete(), caught);
		}

		public void onSuccess(Object result) {
			// Reload data. Deleted row must disappear
			if (updateControlListener != null)
				updateControlListener.controlUpdated(instance);
			btnDoAction.setEnabled(true);
		}
	}

	private Button btnDoAction = new Button("", this);

	/**
	 * Constructs new {@link ActionButtonControl} widget with specified
	 * parameters.
	 * 
	 * @param parentWindow
	 *            parent window for widget
	 * @param actionProvider
	 *            provider of actions
	 * @param updateControlListener
	 *            listener for update control event
	 */
	public ActionButtonControl(IWindow parentWindow,
			IActionProvider actionProvider,
			IUpdateControlListener updateControlListener) {
		super(parentWindow, actionProvider, updateControlListener);

		this.panel.add(this.btnDoAction);
		
		this.setActionCaption();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.user.client.ui.ClickListener#onClick(com.google.gwt.user.client.ui.Widget)
	 */
	public void onClick(Widget sender) {
		// From this.actionProvider gets action.
		// By action opens reference book or form etc.
		if (this.actionProvider instanceof IRBProvider) {
			CustomRBWindow rb = new CustomRBWindow(this.parentWindow
					.getParentSlider(), ((IRBProvider) this.actionProvider)
					.getRB(this.row), true);
			rb.addHideWindowListener(this);
			rb.show();
		} else if (this.actionProvider instanceof IEditFormProvider) {
			CustomFormWindow formWindow = new CustomFormWindow(
					this.parentWindow.getParentSlider(),
					((IEditFormProvider) this.actionProvider)
							.getEditForm(this.row));
			formWindow.addHideWindowListener(this);
			formWindow.show();
		} else if (this.actionProvider instanceof IAddFormProvider) {
			CustomFormWindow formWindow = new CustomFormWindow(
					this.parentWindow.getParentSlider(),
					((IAddFormProvider) this.actionProvider)
							.getAddForm(this.row));
			formWindow.addHideWindowListener(this);
			formWindow.show();
		} else if (this.actionProvider instanceof IViewFormProvider) {
			CustomFormWindow formWindow = new CustomFormWindow(
					this.parentWindow.getParentSlider(),
					((IViewFormProvider) this.actionProvider)
							.getViewForm(this.row));
			formWindow.addHideWindowListener(this);
			formWindow.show();
		} else if (this.actionProvider instanceof ISearchFormProvider) {
			CustomFormWindow formWindow = new CustomFormWindow(
					this.parentWindow.getParentSlider(),
					((ISearchFormProvider) this.actionProvider).getSearchForm());
			formWindow.addHideWindowListener(this);
			formWindow.show();
		} else if (this.actionProvider instanceof IDeleteRowProvider) {
			if (com.google.gwt.user.client.Window.confirm(FrameworkLocale
					.messages().delete_confirmation())) {
				this.btnDoAction.setEnabled(false);
				((IDeleteRowProvider) this.actionProvider)
						.getDeleteRowExecutor().deleteRow(this.row,
								new DeleteCallback(this));
			}
		} else if (this.actionProvider instanceof IReportProvider) {
			IReportBuilder report = ((IReportProvider) this.actionProvider)
					.getReport();
			if (report instanceof IParametersFormProvider) {
				IParametersForm parametersForm = ((IParametersFormProvider) report)
						.getParametersForm(this.row);
				CustomFormWindow formWindow = new CustomFormWindow(
						this.parentWindow.getParentSlider(), parametersForm);
				formWindow.addHideWindowListener(this);
				formWindow.show();
			} else {
				// if report has no parameters form, then build without
				// parameters
				((IReportProvider) this.actionProvider).getReport()
						.BuildReport(this.row);
			}
		} else
			throw new NotImplementedFeatureException(
					"Not realized type in ActionButtonControl");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.pleso.framework.client.ui.custom.controls.data.BaseActionControl#setEnabled(boolean)
	 */
	protected void setEnabled(boolean enabled) {
		if (enabled != this.btnDoAction.isEnabled()) {
			this.btnDoAction.setEnabled(enabled);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.pleso.framework.client.ui.custom.controls.data.BaseActionControl#setCaption(java.lang.String)
	 */
	protected void setCaption(String caption) {
		this.btnDoAction.setHTML(caption);
	}

}
