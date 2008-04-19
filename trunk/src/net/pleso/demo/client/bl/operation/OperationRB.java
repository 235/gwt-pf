package net.pleso.demo.client.bl.operation;

import net.pleso.demo.client.bl.operation.forms.AddOperationForm;
import net.pleso.demo.client.bl.operation.forms.EditOperationForm;
import net.pleso.demo.client.dal.operation.Operation;
import net.pleso.demo.client.dal.operation.OperationServiceAsync;
import net.pleso.demo.client.localization.Locale;
import net.pleso.framework.client.bl.IAuthDataSource;
import net.pleso.framework.client.bl.actions.IDeleteRowExecutor;
import net.pleso.framework.client.bl.forms.IAddForm;
import net.pleso.framework.client.bl.forms.IEditForm;
import net.pleso.framework.client.bl.providers.IActionProvider;
import net.pleso.framework.client.bl.providers.IActionProviders;
import net.pleso.framework.client.bl.providers.IAddFormProvider;
import net.pleso.framework.client.bl.providers.IDeleteRowProvider;
import net.pleso.framework.client.bl.providers.IEditFormProvider;
import net.pleso.framework.client.bl.rb.IRB;
import net.pleso.framework.client.bl.rb.columns.IRBColumn;
import net.pleso.framework.client.bl.rb.columns.impl.EnumRBColumn;
import net.pleso.framework.client.bl.rb.columns.impl.RBColumn;
import net.pleso.framework.client.dal.IDataRow;
import net.pleso.framework.client.dal.SelectParams;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class OperationRB implements IRB, IActionProviders {
	
	private class AddFormProvider implements IAddFormProvider {
		
		public IAddForm getAddForm(IDataRow row) {
			return new AddOperationForm(null);
		}
		
		public String getActionCaption() {
			return null;
		}
	}
	
	private class EdirFormProvider implements IEditFormProvider {
		
		public IEditForm getEditForm(IDataRow row) {
			return new EditOperationForm(row);
		}	
		
		public String getActionCaption() {
			return null;
		}
	}
	
	private class RowDeleter implements IDeleteRowProvider, IDeleteRowExecutor {

		public IDeleteRowExecutor getDeleteRowExecutor() {
			return this;
		}

		public String getActionCaption() {
			return null;
		}

		public void deleteRow(IDataRow row, AsyncCallback callback) {
			OperationServiceAsync calService = OperationServiceLoader.getService();
			calService.delete(((Operation) row).getOperation_id(), callback);
		}
	}
	
	private class OperationRBDataSource implements IAuthDataSource {
		
		public void select(SelectParams params, AsyncCallback callback) {
			OperationServiceAsync calService = OperationServiceLoader.getService();
			calService.select(params, callback);
		}

		public void selectCount(SelectParams params, AsyncCallback callback) {
			OperationServiceAsync calService = OperationServiceLoader.getService();
			calService.selectCount(callback);
		}
	}
	
	private String caption = Locale.constants().operations();
	private static IRBColumn[] rbColumns = new IRBColumn[] 
	        {
	        	new RBColumn(Operation.Columns.bank_name, 20),
	        	new RBColumn(Operation.Columns.op_cl_name, 20),
	            new RBColumn(Operation.Columns.operation_date, 16),
	            new EnumRBColumn(Operation.Columns.operation_type, 16, OperationType.getEnum()),
	            new RBColumn(Operation.Columns.operation_description, 16),
	            new RBColumn(Operation.Columns.is_confirmed, 12)
	        };
	
	public String getCaption() {
		return caption;
	}

	public IRBColumn[] getColumns() {
		return rbColumns;
	}

	public IAuthDataSource getDataSource() {
		return new OperationRBDataSource();
	}	

	public IActionProvider[] getActionProviders() {
		return new IActionProvider[] 
           {
				new AddFormProvider(),
				new EdirFormProvider(),
				new RowDeleter()
			};
	}
}
