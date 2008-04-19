package net.pleso.demo.client.bl.operation;

import net.pleso.demo.client.dal.operation.OperationService;
import net.pleso.demo.client.dal.operation.OperationServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class OperationServiceLoader {
	
	public static OperationServiceAsync getService()
	{
		OperationServiceAsync calService = (OperationServiceAsync) GWT
		.create(OperationService.class);
		ServiceDefTarget target = (ServiceDefTarget) calService;
		String moduleRelativeURL = GWT.getModuleBaseURL() + "operation";
		target.setServiceEntryPoint(moduleRelativeURL);
		return calService;
	}

}
