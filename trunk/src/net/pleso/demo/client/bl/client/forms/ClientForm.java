package net.pleso.demo.client.bl.client.forms;

import net.pleso.demo.client.bl.bank.BankRB;
import net.pleso.demo.client.bl.bank.forms.SearchBankForm;
import net.pleso.demo.client.bl.client.SexType;
import net.pleso.demo.client.dal.client.Client;
import net.pleso.framework.client.bl.forms.items.IFormItem;
import net.pleso.framework.client.bl.forms.items.impl.EditFormItem;
import net.pleso.framework.client.bl.forms.items.impl.EnumFormItem;
import net.pleso.framework.client.bl.forms.items.impl.QuickSearchSelectorFormItem;
import net.pleso.framework.client.dal.IDataRow;

public class ClientForm {
	
	protected static IFormItem[] formColumns = new IFormItem[] {
			new EditFormItem(Client.Columns.cl_name),
			new QuickSearchSelectorFormItem(
					BankRB.getSelector(Client.Columns.cl_bank_id,
							Client.Columns.bank_name),
					Client.Columns.bank_name, SearchBankForm
							.getQuickSearchColumn(), BankRB
							.getQuickSearchShownColumn()),
			new EditFormItem(Client.Columns.cl_money)
		 };
	
	protected static IFormItem[] formColumns2 = new IFormItem[] {
		new EditFormItem(Client.Columns.cl_birthday),
		new EnumFormItem(Client.Columns.cl_sex, SexType.getEnum()),
	 };
	
	protected IDataRow dataRow = null;
	
	public ClientForm(IDataRow row){
		this.dataRow = row;
	}
	
}
