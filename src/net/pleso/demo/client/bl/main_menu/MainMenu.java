package net.pleso.demo.client.bl.main_menu;

import net.pleso.demo.client.bl.bank.BankRB;
import net.pleso.demo.client.bl.bank.forms.AddBankForm;
import net.pleso.demo.client.bl.client.ClientRB;
import net.pleso.demo.client.bl.client.forms.AddClientForm;
import net.pleso.demo.client.bl.operation.OperationRB;
import net.pleso.demo.client.bl.operation.forms.AddOperationForm;
import net.pleso.demo.client.localization.Locale;
import net.pleso.framework.client.bl.forms.IAddForm;
import net.pleso.framework.client.bl.providers.IActionProvider;
import net.pleso.framework.client.bl.providers.IAddFormProvider;
import net.pleso.framework.client.bl.providers.IMenu;
import net.pleso.framework.client.bl.providers.IRBProvider;
import net.pleso.framework.client.bl.providers.impl.Menu;
import net.pleso.framework.client.bl.rb.IRB;
import net.pleso.framework.client.dal.IDataRow;

public class MainMenu {

	public IMenu getMainMenu() {
		return new Menu(new IMenu[] {
				new Menu(Locale.constants().banks(), 
						new IActionProvider[] {
							new IRBProvider() {
								public IRB getRB(IDataRow sourceRow) {
									return new BankRB(false);
								}
			
								public String getActionCaption() {
									return Locale.constants().banks();
								}
							}, new IAddFormProvider() {
			
								public IAddForm getAddForm(IDataRow row) {
									return new AddBankForm(null);
								}
			
								public String getActionCaption() {
									return Locale.constants().add_bank();
								}
							}
				}),
				new Menu(Locale.constants().clients(), 
						new IActionProvider[] {
							new IRBProvider() {
								public IRB getRB(IDataRow sourceRow) {
									return new ClientRB();
								}
			
								public String getActionCaption() {
									return Locale.constants().client_search();
								}
							}, new IAddFormProvider() {
			
								public IAddForm getAddForm(IDataRow row) {
									return new AddClientForm(null);
								}
			
								public String getActionCaption() {
									return Locale.constants().add_client();
								}
							}
				}),
				new Menu(Locale.constants().operations(), 
						new IActionProvider[] {
							new IRBProvider() {
								public IRB getRB(IDataRow sourceRow) {
									return new OperationRB();
								}
			
								public String getActionCaption() {
									return Locale.constants().operations();
								}
							}, new IAddFormProvider() {
			
								public IAddForm getAddForm(IDataRow row) {
									return new AddOperationForm(null);
								}
			
								public String getActionCaption() {
									return Locale.constants().add_operation();
								}
							}
				})});
	}
}