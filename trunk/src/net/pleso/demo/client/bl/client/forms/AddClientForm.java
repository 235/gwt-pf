package net.pleso.demo.client.bl.client.forms;

import net.pleso.demo.client.bl.client.ClientServiceLoader;
import net.pleso.demo.client.dal.client.Client;
import net.pleso.demo.client.dal.client.ClientServiceAsync;
import net.pleso.demo.client.localization.Locale;
import net.pleso.framework.client.bl.forms.IAddForm;
import net.pleso.framework.client.bl.forms.items.IFormItemsGroup;
import net.pleso.framework.client.bl.forms.items.impl.FormItemsGroup;
import net.pleso.framework.client.bl.providers.IAddFormProvider;
import net.pleso.framework.client.dal.IDataRow;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class AddClientForm extends ClientForm implements IAddForm, IAddFormProvider {
	
	private static final String insertCaption = Locale.constants().add_client() ;

	public AddClientForm(IDataRow row) {
		super(row);
	}
	
	public String getCaption() {
		return AddClientForm.insertCaption;
	}

	public IDataRow createEmptyRow() {
		return new Client();
	}
	
	public void addRow(IDataRow row, AsyncCallback callback) {
		ClientServiceAsync calService = ClientServiceLoader.getService();
		calService.insert((Client) row, callback);
	}

	public IAddForm getAddForm(IDataRow row) {
		return new AddClientForm(row);
	}

	public String getActionCaption() {
		return null;
	}
	
	private static IFormItemsGroup[] formDataGroups = new IFormItemsGroup[] {
		new FormItemsGroup(null, formColumns),
		new FormItemsGroup(null, formColumns2)
		 };

	public IFormItemsGroup[] getGroups() {
		return formDataGroups;
	}
}
