package net.pleso.framework.client.ui.custom.controls;

import java.util.ArrayList;
import java.util.Iterator;

import net.pleso.framework.client.ui.interfaces.IFocusControl;

/**
 * Represents managed collection of {@link FormItemsGroupWidget}. 
 */
public class FormItemsGroupWidgetCollection {

	private ArrayList groups = new ArrayList();

	/**
	 * Constructs empty {@link FormItemsGroupWidgetCollection}.
	 */
	public FormItemsGroupWidgetCollection() {
	}

	/**
	 * Adds to collection specified {@link FormItemsGroupWidget}.
	 * 
	 * @param formItemsGroupWidget
	 */
	public void addFormItemsGroupWidget(
			FormItemsGroupWidget formItemsGroupWidget) {

		this.groups.add(formItemsGroupWidget);
	}

	/**
	 * Updates binded {@link #dataRow} by values from all collection groups
	 * controls.
	 */
	public void updateAllData() {
		for (Iterator i = this.groups.iterator(); i.hasNext();) {
			FormItemsGroupWidget group = (FormItemsGroupWidget) i.next();

			group.updateAllData();
		}
	}

	/**
	 * Forces bindable controls to read binded data.
	 */
	public void reReadBindableControls() {
		for (Iterator i = this.groups.iterator(); i.hasNext();) {
			FormItemsGroupWidget group = (FormItemsGroupWidget) i.next();

			group.reReadBindableControls();
		}
	}

	/**
	 * Hides all windows in child sliders.
	 */
	public void hideWindows() {
		for (Iterator i = this.groups.iterator(); i.hasNext();) {
			FormItemsGroupWidget group = (FormItemsGroupWidget) i.next();

			group.hideWindows();
		}
	}

	/**
	 * Makes first {@link IFocusControl} controls in all controlls list to be
	 * focused.
	 */
	public void focusOnFirstControl() {
		boolean focused = false;
		for (Iterator i = this.groups.iterator(); i.hasNext() && !focused;) {
			FormItemsGroupWidget group = (FormItemsGroupWidget) i.next();

			focused = group.focusOnFirstControl();
		}
	}

	/**
	 * Validates form input controls and returns validation success result.
	 * 
	 * @return <code>true</code> if all form controls' values are valid
	 */
	public boolean validate() {
		for (Iterator i = this.groups.iterator(); i.hasNext();) {
			FormItemsGroupWidget group = (FormItemsGroupWidget) i.next();

			group.validate();
		}

		for (Iterator i = this.groups.iterator(); i.hasNext();) {
			FormItemsGroupWidget group = (FormItemsGroupWidget) i.next();

			if (!group.isValid())
				return false;
		}

		return true;
	}
}
