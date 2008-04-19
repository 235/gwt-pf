package net.pleso.demo.client.bl.bank;

import net.pleso.demo.client.dal.bank.BankService;
import net.pleso.demo.client.dal.bank.BankServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class BankServiceLoader {
	
	public static BankServiceAsync getService()
	{
		BankServiceAsync calService = (BankServiceAsync) GWT
		.create(BankService.class);
		ServiceDefTarget target = (ServiceDefTarget) calService;
		String moduleRelativeURL = GWT.getModuleBaseURL() + "bank";
		target.setServiceEntryPoint(moduleRelativeURL);
		return calService;
	}

}
