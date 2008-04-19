package net.pleso.demo.client.bl.operation.forms;

import net.pleso.demo.client.bl.operation.OperationServiceLoader;
import net.pleso.demo.client.bl.operation.OperationType;
import net.pleso.demo.client.dal.client.Client;
import net.pleso.demo.client.dal.operation.Operation;
import net.pleso.demo.client.dal.operation.OperationServiceAsync;
import net.pleso.demo.client.localization.Locale;
import net.pleso.framework.client.bl.forms.IAddForm;
import net.pleso.framework.client.bl.providers.IAddFormProvider;
import net.pleso.framework.client.dal.IDataRow;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class AddOperationForm extends OperationForm implements IAddForm, IAddFormProvider {
	
	private static final String insertCaption = Locale.constants().add_operation();

	public AddOperationForm(IDataRow row) {
		super(row);
		clientRow = (Client) row;
	}
	
	private Client clientRow = null;
	
	public String getCaption() {
		return AddOperationForm.insertCaption;
	}

	public IDataRow createEmptyRow() {
		Operation op = new Operation();
		
		if (clientRow != null) {
			op.setOp_cl_id(clientRow.getCl_id());
			op.setOp_cl_name(clientRow.getCl_name());
		}
		
		return op;
	}
	
	public void addRow(IDataRow row, AsyncCallback callback) {
		
		if (((Operation) row).getOperation_type().intValue() == OperationType.Credit.getInt()) {
		   throw new IllegalArgumentException("Credit!");
		}
		
		OperationServiceAsync calService = OperationServiceLoader.getService();
		calService.insert((Operation) row, callback);
	}

	public IAddForm getAddForm(IDataRow row) {
		return new AddOperationForm(this.clientRow);
	}

	public String getActionCaption() {
		return null;
	}
}
