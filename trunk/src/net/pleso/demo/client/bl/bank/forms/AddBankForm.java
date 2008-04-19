package net.pleso.demo.client.bl.bank.forms;

import net.pleso.demo.client.bl.bank.BankServiceLoader;
import net.pleso.demo.client.dal.bank.Bank;
import net.pleso.demo.client.dal.bank.BankServiceAsync;
import net.pleso.demo.client.localization.Locale;
import net.pleso.framework.client.bl.forms.IAddForm;
import net.pleso.framework.client.bl.providers.IAddFormProvider;
import net.pleso.framework.client.dal.IDataRow;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class AddBankForm extends BankForm implements IAddForm, IAddFormProvider {
	
	private static final String insertCaption = Locale.constants().add_bank();

	public AddBankForm(IDataRow row) {
		super(row);
	}
	
	public String getCaption() {
		return AddBankForm.insertCaption;
	}

	public IDataRow createEmptyRow() {
		return new Bank();
	}
	
	public void addRow(IDataRow row, AsyncCallback callback) {
		BankServiceAsync calService = BankServiceLoader.getService();
		calService.insert((Bank) row, callback);
	}

	public IAddForm getAddForm(IDataRow row) {
		return new AddBankForm(row);
	}

	public String getActionCaption() {
		return null;
	}
}
