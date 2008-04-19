package net.pleso.demo.client.bl.client.forms;

import net.pleso.demo.client.bl.client.ClientServiceLoader;
import net.pleso.demo.client.bl.operation.OperationByClientRB;
import net.pleso.demo.client.bl.operation.forms.AddOperationForm;
import net.pleso.demo.client.dal.BaseException;
import net.pleso.demo.client.dal.client.Client;
import net.pleso.demo.client.dal.client.ClientServiceAsync;
import net.pleso.demo.client.localization.Locale;
import net.pleso.framework.client.bl.forms.IAddForm;
import net.pleso.framework.client.bl.forms.IEditForm;
import net.pleso.framework.client.bl.forms.items.IFormItem;
import net.pleso.framework.client.bl.forms.items.IFormItemsGroup;
import net.pleso.framework.client.bl.forms.items.impl.ActionFormItem;
import net.pleso.framework.client.bl.forms.items.impl.FormItemsGroup;
import net.pleso.framework.client.bl.providers.IAddFormProvider;
import net.pleso.framework.client.bl.providers.IRBProvider;
import net.pleso.framework.client.bl.rb.IRB;
import net.pleso.framework.client.dal.IDataRow;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class EditClientForm extends ClientForm implements IEditForm, IAddFormProvider {
	
	private class ClientOperationsProvider implements IRBProvider {

		public IRB getRB(IDataRow sourceRow) {
			return new OperationByClientRB((Client) sourceRow);
		}

		public String getActionCaption() {
			return Locale.constants().operations();
		}
	}
	
	private class ClientAddOperationProvider implements IAddFormProvider {

		public IAddForm getAddForm(IDataRow row) {
			return new AddOperationForm((Client) row);
		}

		public String getActionCaption() {
			return Locale.constants().add_operation();
		}
	}
	
	private static final String editCaption = Locale.constants().edit_client();

	public EditClientForm(IDataRow row) {
		super(row);
	}
	
	public String getCaption() {
		return EditClientForm.editCaption;
	}
	
	public void updateRow(IDataRow row, AsyncCallback callback) {
		ClientServiceAsync calService = ClientServiceLoader.getService();
		calService.update((Client) row, callback);
	}
	
	public void GetData(AsyncCallback callback) {
		if (dataRow == null)
			throw new BaseException("DataRow in OperationForm is null");
		ClientServiceAsync calService = ClientServiceLoader.getService();
		calService.selectSingle(((Client) dataRow).getCl_id(), callback);
	}

	public IAddForm getAddForm(IDataRow row) {
		return new AddClientForm(row);
	}

	public String getActionCaption() {
		return null;
	}
	
	private static EditClientForm instance = new EditClientForm(null);
	
	private static IFormItem[] fromColumns3 = new IFormItem[] {
		new ActionFormItem(instance.new ClientOperationsProvider()),
		new ActionFormItem(instance.new ClientAddOperationProvider())
	 };
	
	private static IFormItemsGroup[] formDataGroups = new IFormItemsGroup[] {
		new FormItemsGroup(null, formColumns),
		new FormItemsGroup(null, formColumns2),
		new FormItemsGroup(null, fromColumns3)
		 };

	public IFormItemsGroup[] getGroups() {
		return formDataGroups;
	}
}
