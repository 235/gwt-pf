package net.pleso.demo.client.bl.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.pleso.framework.client.dal.IDataColumn;
import net.pleso.framework.client.dal.IDataRow;
import net.pleso.framework.client.dal.SelectParams;
import net.pleso.framework.client.bl.IAuthDataSource;
import net.pleso.framework.client.bl.ISelector;
import net.pleso.framework.client.bl.actions.IDeleteRowExecutor;
import net.pleso.framework.client.bl.forms.IAddForm;
import net.pleso.framework.client.bl.forms.IEditForm;
import net.pleso.framework.client.bl.forms.IParametersForm;
import net.pleso.framework.client.bl.providers.IActionProvider;
import net.pleso.framework.client.bl.providers.IActionProviders;
import net.pleso.framework.client.bl.providers.IAddFormProvider;
import net.pleso.framework.client.bl.providers.IDeleteRowProvider;
import net.pleso.framework.client.bl.providers.IEditFormProvider;
import net.pleso.framework.client.bl.providers.ISearchFormProvider;
import net.pleso.framework.client.bl.rb.IRB;
import net.pleso.framework.client.bl.rb.columns.IRBColumn;
import net.pleso.framework.client.bl.rb.columns.impl.EnumRBColumn;
import net.pleso.framework.client.bl.rb.columns.impl.RBColumn;
import net.pleso.framework.client.dal.db.types.DBInteger;
import net.pleso.framework.client.dal.db.types.DBString;
import net.pleso.framework.client.dal.impl.DataColumn;
import net.pleso.demo.client.bl.client.forms.AddClientForm;
import net.pleso.demo.client.bl.client.forms.EditClientForm;
import net.pleso.demo.client.bl.client.forms.SearchClientForm;
import net.pleso.demo.client.bl.operation.forms.AddOperationForm;
import net.pleso.demo.client.dal.client.Client;
import net.pleso.demo.client.dal.client.ClientServiceAsync;
import net.pleso.demo.client.localization.Locale;

public class ClientRB implements IRB, IActionProviders {
	
	private class AddFormProvider implements IAddFormProvider {
		
		public IAddForm getAddForm(IDataRow row) {
			return new AddClientForm(null);
		}

		public String getActionCaption() {
			return null;
		}	
	}
	
	private class EditFormProvider implements IEditFormProvider {
		
		public IEditForm getEditForm(IDataRow row) {
			return new EditClientForm(row);
		}
		
		public String getActionCaption() {
			return null;
		}
	}
	
	private class SearchFormProvider implements ISearchFormProvider {
		
		public IParametersForm getSearchForm() {
			return new SearchClientForm();
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
			ClientServiceAsync calService = ClientServiceLoader.getService();
			calService.delete(((Client) row).getCl_id(), callback);
		}
	}
	
	private class ClientRBDataSource implements IAuthDataSource {
		
		public void select(SelectParams params, AsyncCallback callback) {
			ClientServiceAsync calService = ClientServiceLoader.getService();
			calService.select(params, callback);
		}

		public void selectCount(SelectParams params, AsyncCallback callback) {
			ClientServiceAsync calService = ClientServiceLoader.getService();
			calService.selectCount((Client) params.getSearchRow(), callback);
		}
	}
	
	private class ClientAddOperationProvider implements IAddFormProvider {

		public IAddForm getAddForm(IDataRow row) {
			return new AddOperationForm((Client) row);
		}

		public String getActionCaption() {
			return Locale.constants().add_operation();
		}
	}
	
	private class ClientSelector implements ISelector {
		
		private IDataColumn idColumn;
		private IDataColumn nameColumn;		
		
		public ClientSelector(IDataColumn idColumn, IDataColumn nameColumn) {
			this.idColumn = idColumn;
			this.nameColumn = nameColumn;
		}

		public void CopyRow(IDataRow sourceRow, IDataRow selectedRow) {
			Client row = (Client) selectedRow;
			
			sourceRow.setCell(idColumn, new DBInteger(row.getCl_id()));			
			sourceRow.setCell(nameColumn, new DBString(row.getCl_name()));
		}

		public IRB getRB(IDataRow sourceRow) {
			return new ClientRB();
		}
		
		public void setNull(IDataRow sourceRow) {
			DataColumn.setNull(sourceRow, idColumn);
			DataColumn.setNull(sourceRow, nameColumn);
		}

		public String getActionCaption() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	private String caption = Locale.constants().clients();
	private static IRBColumn[] rbColumns = new IRBColumn[] 
	        {
	        	new RBColumn(Client.Columns.cl_name, 10),
	        	new RBColumn(Client.Columns.bank_name, 10),
	        	new RBColumn(Client.Columns.cl_birthday, 10),
	        	new RBColumn(Client.Columns.cl_money, 10),
	        	new EnumRBColumn(Client.Columns.cl_sex, 10, SexType.getEnum())
	        };
	
	public String getCaption() {
		return caption;
	}

	public IRBColumn[] getColumns() {
		return rbColumns;
	}

	public IAuthDataSource getDataSource() {
		return new ClientRBDataSource();
	}
	
	public static ISelector getSelector(IDataColumn idColumn, IDataColumn nameColumn) {
		return instance.new ClientSelector(idColumn, nameColumn);
	}
	
	private static ClientRB instance = new ClientRB();
	
	public IActionProvider[] getActionProviders() {
		return new IActionProvider[] {
				new SearchFormProvider(),
				new AddFormProvider(),
				new EditFormProvider(),
				new RowDeleter(), 
				new ClientAddOperationProvider()
			};
	}
}
