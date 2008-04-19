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
import net.pleso.framework.client.ui.custom.CustomFormWindow;
import net.pleso.framework.client.ui.custom.CustomRBWindow;
import net.pleso.framework.client.ui.interfaces.IBindableDataControl;
import net.pleso.framework.client.ui.interfaces.IUpdateControlListener;
import net.pleso.framework.client.ui.interfaces.IWindow;
import net.pleso.framework.client.ui.windows.Slider;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;

/**
 * Action slider controls which wraps {@link IActionProvider} action and binds
 * its execution to slider opening.
 * 
 * @author Scater
 * 
 */
public class ActionSliderControl extends BaseActionControl implements
		ClickListener, IBindableDataControl {

	private Slider sliderDoAction;

	/**
	 * Constructs new {@link ActionButtonControl} widget with specified
	 * parameters.
	 * 
	 * @param parentWindow
	 *            parent window for widget
	 * @param actionProvider
	 *            provider of actions
	 * @param updateControlListener
	 *            listener of update control
	 */
	public ActionSliderControl(IWindow parentWindow,
			IActionProvider actionProvider,
			IUpdateControlListener updateControlListener) {
		this(parentWindow, actionProvider, updateControlListener, false);
	}

	/**
	 * Constructs new {@link ActionButtonControl} widget with specified
	 * parameters.
	 * 
	 * @param parentWindow
	 *            parent window for widget
	 * @param actionProvider
	 *            provider of actions
	 * @param updateControlListener
	 *            listener of update control
	 * @param allowCloseParentSlider
	 *            value indicationg whether this slider allow to close parent
	 *            slider
	 */
	public ActionSliderControl(IWindow parentWindow,
			IActionProvider actionProvider,
			IUpdateControlListener updateControlListener,
			boolean allowCloseParentSlider) {
		super(parentWindow, actionProvider, updateControlListener);

		sliderDoAction = new Slider("", false, allowCloseParentSlider);

		if (this.actionProvider instanceof IDeleteRowProvider)
			throw new IllegalArgumentException(
					"Slider can't use IDeleteRowProvider");

		this.sliderDoAction.addClickListener(this);

		this.panel.setWidth("100%");
		this.panel.add(this.sliderDoAction);
		
		this.setActionCaption();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.pleso.framework.client.ui.custom.controls.data.BaseActionControl#setCaption(java.lang.String)
	 */
	protected void setCaption(String caption) {
		this.sliderDoAction.setCaption(caption);
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
			CustomRBWindow rb = new CustomRBWindow(sliderDoAction,
					((IRBProvider) this.actionProvider).getRB(this.row), true);
			rb.addHideWindowListener(this);
			rb.show();
		} else if (this.actionProvider instanceof IEditFormProvider) {
			CustomFormWindow formWindow = new CustomFormWindow(sliderDoAction,
					((IEditFormProvider) this.actionProvider)
							.getEditForm(this.row));
			formWindow.addHideWindowListener(this);
			formWindow.show();
		} else if (this.actionProvider instanceof IAddFormProvider) {
			CustomFormWindow formWindow = new CustomFormWindow(sliderDoAction,
					((IAddFormProvider) this.actionProvider)
							.getAddForm(this.row));
			formWindow.addHideWindowListener(this);
			formWindow.show();
		} else if (this.actionProvider instanceof IViewFormProvider) {
			sliderDoAction.setModalAfterOpen(false);
			CustomFormWindow formWindow = new CustomFormWindow(sliderDoAction,
					((IViewFormProvider) this.actionProvider)
							.getViewForm(this.row));
			formWindow.addHideWindowListener(this);
			formWindow.show();
		} else if (this.actionProvider instanceof ISearchFormProvider) {
			CustomFormWindow formWindow = new CustomFormWindow(sliderDoAction,
					((ISearchFormProvider) this.actionProvider).getSearchForm());
			formWindow.addHideWindowListener(this);
			formWindow.show();
		} else if (this.actionProvider instanceof IDeleteRowProvider) {
			throw new IllegalArgumentException(
					"Slider can't use IDeleteRowProvider");
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
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.pleso.framework.client.ui.custom.controls.data.BaseActionControl#setEnabled(boolean)
	 */
	protected void setEnabled(boolean enabled) {
		if (enabled != this.sliderDoAction.isEnabled()) {
			this.sliderDoAction.setEnabled(enabled);
		}
	}

	public void hideChildWindow() {
		this.sliderDoAction.hideWindows();
	}
}
