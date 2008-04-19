package net.pleso.demo.client.bl.bank.forms;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.pleso.demo.client.bl.bank.BankServiceLoader;
import net.pleso.demo.client.dal.BaseException;
import net.pleso.demo.client.dal.bank.Bank;
import net.pleso.demo.client.dal.bank.BankServiceAsync;
import net.pleso.demo.client.localization.Locale;
import net.pleso.framework.client.bl.forms.IAddForm;
import net.pleso.framework.client.bl.forms.IEditForm;
import net.pleso.framework.client.bl.providers.IAddFormProvider;
import net.pleso.framework.client.dal.IDataRow;

public class EditBankForm extends BankForm implements IEditForm, IAddFormProvider {
	
	private static final String editCaption = Locale.constants().edit_bank();

	public EditBankForm(IDataRow row) {
		super(row);
	}
	
	public String getCaption() {
		return EditBankForm.editCaption;
	}
	
	public void updateRow(IDataRow row, AsyncCallback callback) {
		BankServiceAsync calService = BankServiceLoader.getService();
		calService.update((Bank) row, callback);
	}
	
	public void GetData(AsyncCallback callback) {
		if (dataRow == null)
			throw new BaseException("DataRow in OperationForm is null");
		BankServiceAsync calService = BankServiceLoader.getService();
		calService.selectSingle(((Bank) dataRow).getBank_id(), callback);
	}

	public IAddForm getAddForm(IDataRow row) {
		return new AddBankForm(row);
	}

	public String getActionCaption() {
		return null;
	}
}
