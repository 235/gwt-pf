package net.pleso.demo.client.bl.client.forms;

import net.pleso.demo.client.bl.bank.BankRB;
import net.pleso.demo.client.bl.bank.forms.SearchBankForm;
import net.pleso.demo.client.bl.client.SexType;
import net.pleso.demo.client.dal.client.Client;
import net.pleso.demo.client.localization.Locale;
import net.pleso.framework.client.bl.forms.IParametersForm;
import net.pleso.framework.client.bl.forms.items.IFormItem;
import net.pleso.framework.client.bl.forms.items.IFormItemsGroup;
import net.pleso.framework.client.bl.forms.items.impl.EditFormItem;
import net.pleso.framework.client.bl.forms.items.impl.EditRangeFormItem;
import net.pleso.framework.client.bl.forms.items.impl.EnumFormItem;
import net.pleso.framework.client.bl.forms.items.impl.FormItemsGroup;
import net.pleso.framework.client.bl.forms.items.impl.QuickSearchSelectorFormItem;
import net.pleso.framework.client.dal.IDataRow;

public class SearchClientForm implements IParametersForm {

	public IDataRow createEmptyRow() {
		return new Client();
	}

	public String getCaption() {
		return Locale.constants().client_search();
	}
	
	private static IFormItem[] fromColumns = new IFormItem[] {
		new EditFormItem(Client.Columns.cl_name, false),
		new QuickSearchSelectorFormItem(
				BankRB.getSelector(Client.Columns.cl_bank_id,
						Client.Columns.bank_name),
				Client.Columns.bank_name, SearchBankForm
						.getQuickSearchColumn(), BankRB
						.getQuickSearchShownColumn(), false),
		new EditRangeFormItem(Locale.constants().cl_money_period(),
				Client.Columns.cl_money_start,
				Client.Columns.cl_money_end, false)
		
	 };

	private static IFormItem[] formColumns2 = new IFormItem[] {
		new EditRangeFormItem(Locale.constants().cl_birthday_period(),
				Client.Columns.cl_birthday_start,
				Client.Columns.cl_birthday_end, false),
		new EnumFormItem(Client.Columns.cl_sex, SexType.getEnum(), false),
	 };

	private static IFormItemsGroup[] formDataGroups = new IFormItemsGroup[] {
		new FormItemsGroup(null, fromColumns),
		new FormItemsGroup(null, formColumns2)
		 };

	public IFormItemsGroup[] getGroups() {
		return formDataGroups;
	}

}
