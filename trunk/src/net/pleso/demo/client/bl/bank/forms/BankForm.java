package net.pleso.demo.client.bl.bank.forms;

import net.pleso.demo.client.dal.bank.Bank;
import net.pleso.framework.client.bl.forms.items.IFormItem;
import net.pleso.framework.client.bl.forms.items.IFormItemsGroup;
import net.pleso.framework.client.bl.forms.items.impl.EditFormItem;
import net.pleso.framework.client.bl.forms.items.impl.FormItemsGroup;
import net.pleso.framework.client.dal.IDataRow;

public class BankForm {
	
	private static IFormItem[] formColumns = new IFormItem[] {
			new EditFormItem(Bank.Columns.bank_name),
			new EditFormItem(Bank.Columns.bank_mfo)
		 };
	
	private static IFormItemsGroup[] formDataGroups = new IFormItemsGroup[] {
			new FormItemsGroup(null, formColumns)
			 };
	
	protected IDataRow dataRow = null;
	
	public BankForm(IDataRow row){
		this.dataRow = row;
	}	

	public IFormItemsGroup[] getGroups() {
		return formDataGroups;
	}
}
