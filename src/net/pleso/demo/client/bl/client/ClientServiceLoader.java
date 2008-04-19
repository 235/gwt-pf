package net.pleso.demo.client.bl.client;

import net.pleso.demo.client.dal.client.ClientService;
import net.pleso.demo.client.dal.client.ClientServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class ClientServiceLoader {
	
	public static ClientServiceAsync getService()
	{
		ClientServiceAsync calService = (ClientServiceAsync) GWT
		.create(ClientService.class);
		ServiceDefTarget target = (ServiceDefTarget) calService;
		String moduleRelativeURL = GWT.getModuleBaseURL() + "client";
		target.setServiceEntryPoint(moduleRelativeURL);
		return calService;
	}

}
