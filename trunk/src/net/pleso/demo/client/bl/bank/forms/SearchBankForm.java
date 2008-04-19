package net.pleso.demo.client.bl.bank.forms;

import net.pleso.demo.client.dal.bank.Bank;
import net.pleso.framework.client.bl.forms.IParametersForm;
import net.pleso.framework.client.bl.forms.items.IEditColumnFormItem;
import net.pleso.framework.client.bl.forms.items.IFormItem;
import net.pleso.framework.client.bl.forms.items.IFormItemsGroup;
import net.pleso.framework.client.bl.forms.items.impl.EditFormItem;
import net.pleso.framework.client.bl.forms.items.impl.FormItemsGroup;
import net.pleso.framework.client.dal.IDataRow;

public class SearchBankForm implements IParametersForm {
	
	public IDataRow createEmptyRow() {
		return new Bank();
	}

	public String getCaption() {
		return "";
	}
	
	private static EditFormItem bank_name = new EditFormItem(Bank.Columns.bank_name, false);
	
	private static IFormItem[] fromColumns = new IFormItem[] {
		bank_name
	 };

	private static IFormItemsGroup[] formDataGroups = new IFormItemsGroup[] {
		new FormItemsGroup(null, fromColumns)
	};

	public IFormItemsGroup[] getGroups() {
		return formDataGroups;
	}
	
	public static IEditColumnFormItem getQuickSearchColumn(){
		return bank_name;
	}

}
