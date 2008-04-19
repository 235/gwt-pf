package net.pleso.demo.client.dal.client;

import net.pleso.framework.client.dal.SelectParams;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ClientServiceAsync {
	
	void select(SelectParams params, AsyncCallback callback);
	void selectCount(Client searchRow, AsyncCallback callback);
	void selectSingle(Integer cl_id, AsyncCallback callback);
	
	void insert(Client operation, AsyncCallback callback);
	void update(Client operation, AsyncCallback callback);
	void delete(Integer cl_id, AsyncCallback callback);
}
