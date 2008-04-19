package net.pleso.demo.client.bl.operation.forms;

import net.pleso.demo.client.bl.operation.OperationServiceLoader;
import net.pleso.demo.client.dal.BaseException;
import net.pleso.demo.client.dal.operation.Operation;
import net.pleso.demo.client.dal.operation.OperationServiceAsync;
import net.pleso.demo.client.localization.Locale;
import net.pleso.framework.client.bl.forms.IAddForm;
import net.pleso.framework.client.bl.forms.IEditForm;
import net.pleso.framework.client.bl.providers.IAddFormProvider;
import net.pleso.framework.client.dal.IDataRow;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class EditOperationForm extends OperationForm implements IEditForm, IAddFormProvider {
	
	private static final String editCaption = Locale.constants().edit_operation();

	public EditOperationForm(IDataRow row) {
		super(row);
	}
	
	public String getCaption() {
		return EditOperationForm.editCaption;
	}
	
	public void updateRow(IDataRow row, AsyncCallback callback) {
		OperationServiceAsync calService = OperationServiceLoader.getService();
		calService.update((Operation) row, callback);
	}
	
	public void GetData(AsyncCallback callback) {
		if (dataRow == null)
			throw new BaseException("DataRow in OperationForm is null");
		OperationServiceAsync calService = OperationServiceLoader.getService();
		calService.selectSingle(((Operation) dataRow).getOperation_id(), callback);
	}

	public IAddForm getAddForm(IDataRow row) {
		return new AddOperationForm(row);
	}

	public String getActionCaption() {
		return null;
	}
}
