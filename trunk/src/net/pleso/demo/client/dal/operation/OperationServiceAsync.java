package net.pleso.demo.client.dal.operation;

import net.pleso.framework.client.dal.SelectParams;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface OperationServiceAsync {
	
	void select(SelectParams params, AsyncCallback callback);
	void selectCount(AsyncCallback callback);
	void selectByClient(SelectParams params, AsyncCallback callback);
	void selectCountByClient(Operation searchRow, AsyncCallback callback);
	void selectSingle(Integer operation_id, AsyncCallback callback);
	
	void insert(Operation operation, AsyncCallback callback);
	void update(Operation operation, AsyncCallback callback);
	void delete(Integer operation_id, AsyncCallback callback);
}
