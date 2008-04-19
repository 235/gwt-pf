package net.pleso.demo.client.bl.bank;

import net.pleso.demo.client.bl.bank.forms.AddBankForm;
import net.pleso.demo.client.bl.bank.forms.EditBankForm;
import net.pleso.demo.client.bl.bank.forms.SearchBankForm;
import net.pleso.demo.client.dal.bank.Bank;
import net.pleso.demo.client.dal.bank.BankServiceAsync;
import net.pleso.demo.client.localization.Locale;
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
import net.pleso.framework.client.bl.rb.columns.IRBDataColumn;
import net.pleso.framework.client.bl.rb.columns.impl.RBColumn;
import net.pleso.framework.client.dal.IDataColumn;
import net.pleso.framework.client.dal.IDataRow;
import net.pleso.framework.client.dal.SelectParams;
import net.pleso.framework.client.dal.db.types.DBInteger;
import net.pleso.framework.client.dal.db.types.DBString;
import net.pleso.framework.client.dal.impl.DataColumn;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class BankRB implements IRB, IActionProviders {
	
	private class AddFormProvider implements IAddFormProvider {
		public IAddForm getAddForm(IDataRow row) {
			return new AddBankForm(null);
		}
		
		public String getActionCaption() {
			return null;
		}
	}
	
	private class EditFormProvider implements IEditFormProvider {
		public IEditForm getEditForm(IDataRow row) {
			return new EditBankForm(row);
		}

		public String getActionCaption() {
			return null;
		}
	}
	
	private class SearchFormProvider implements ISearchFormProvider {
		
		public IParametersForm getSearchForm() {
			return new SearchBankForm();
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
			BankServiceAsync calService = BankServiceLoader.getService();
			calService.delete(((Bank) row).getBank_id(), callback);
		}
	}
	
	private class BankRBDataSource implements IAuthDataSource {
		
		public void select(SelectParams params, AsyncCallback callback) {
			BankServiceAsync calService = BankServiceLoader.getService();
			calService.select(params, callback);
		}

		public void selectCount(SelectParams params, AsyncCallback callback) {
			BankServiceAsync calService = BankServiceLoader.getService();
			if (params.getSearchRow() != null)
				calService.selectCount((Bank)params.getSearchRow(), callback);
			else
				calService.selectCount(null, callback);
		}
	}
	
	private class BankSelector implements ISelector {
		
		private IDataColumn idColumn;
		private IDataColumn nameColumn;		
		
		public BankSelector(IDataColumn idColumn, IDataColumn nameColumn) {
			this.idColumn = idColumn;
			this.nameColumn = nameColumn;
		}

		public void CopyRow(IDataRow sourceRow, IDataRow selectedRow) {
			Bank row = (Bank) selectedRow;
			
			sourceRow.setCell(idColumn, new DBInteger(row.getBank_id()));			
			sourceRow.setCell(nameColumn, new DBString(row.getBank_name()));
		}

		public IRB getRB(IDataRow sourceRow) {
			return new BankRB();
		}
		
		public void setNull(IDataRow sourceRow) {
			DataColumn.setNull(sourceRow, idColumn);
			DataColumn.setNull(sourceRow, nameColumn);
		}

		public String getActionCaption() {
			return null;
		}
	}

	private String caption = Locale.constants().banks();
	private static RBColumn bank_name = new RBColumn(Bank.Columns.bank_name, 30);
	
	private static RBColumn[] rbColumns = new RBColumn[] 
	        {
				bank_name,
	            new RBColumn(Bank.Columns.bank_mfo, 70)
	        };
	
	public String getCaption() {
		return caption;
	}

	public IRBColumn[] getColumns() {
		return rbColumns;
	}

	public IAuthDataSource getDataSource() {
		return new BankRBDataSource();
	}
	
	public static ISelector getSelector(IDataColumn idColumn, IDataColumn nameColumn) {
		return instance.new BankSelector(idColumn, nameColumn);
	}
	
	private static BankRB instance = new BankRB();

	public IActionProvider[] getActionProviders() {
		if (showSearch)
			return new IActionProvider[] {
					new AddFormProvider(),
					new EditFormProvider(),
					new SearchFormProvider(),
					new RowDeleter()
				};
		else
			return new IActionProvider[] {
				new AddFormProvider(),
				new EditFormProvider(),
				new RowDeleter()
			};
	}
	
	public static IRBDataColumn getQuickSearchShownColumn(){
		return bank_name;
	}
	
	private boolean showSearch;

	public BankRB(boolean showSearch) {
		this.showSearch = showSearch;
	}
	
	public BankRB() {
		this(true);
	}
	
}
