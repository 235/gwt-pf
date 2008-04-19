package net.pleso.demo.client.bl.operation.forms;

import net.pleso.demo.client.bl.bank.BankRB;
import net.pleso.demo.client.bl.client.ClientRB;
import net.pleso.demo.client.bl.operation.OperationType;
import net.pleso.demo.client.dal.operation.Operation;
import net.pleso.framework.client.bl.forms.items.IFormItem;
import net.pleso.framework.client.bl.forms.items.IFormItemsGroup;
import net.pleso.framework.client.bl.forms.items.impl.EditFormItem;
import net.pleso.framework.client.bl.forms.items.impl.EnumFormItem;
import net.pleso.framework.client.bl.forms.items.impl.FormItemsGroup;
import net.pleso.framework.client.bl.forms.items.impl.MultilineEditFormItem;
import net.pleso.framework.client.bl.forms.items.impl.SelectorFormItem;
import net.pleso.framework.client.dal.IDataRow;

public class OperationForm {

	private static IFormItem[] formColumns = new IFormItem[] {
			new SelectorFormItem(BankRB.getSelector(Operation.Columns.bank_id,
					Operation.Columns.bank_name), Operation.Columns.bank_name),
			new SelectorFormItem(ClientRB.getSelector(Operation.Columns.op_cl__id,
					Operation.Columns.op_cl_name), Operation.Columns.op_cl_name),
			new MultilineEditFormItem(Operation.Columns.operation_description, 10) };
	
	private static IFormItem[] fromColumns2 = new IFormItem[] {
		new EditFormItem(Operation.Columns.operation_date),
		new EnumFormItem(Operation.Columns.operation_type, OperationType
				.getEnum()),
		new EditFormItem(Operation.Columns.is_confirmed)};

	private static IFormItemsGroup[] formDataGroups = new IFormItemsGroup[] { 
		new FormItemsGroup("Info", formColumns),
		new FormItemsGroup("Additional", fromColumns2)};

	protected IDataRow dataRow = null;

	public OperationForm(IDataRow row) {
		this.dataRow = row;
	}

	public IFormItemsGroup[] getGroups() {
		return formDataGroups;
	}
}
