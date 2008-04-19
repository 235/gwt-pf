package net.pleso.demo.client.dal.bank;

import net.pleso.framework.client.dal.SelectParams;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface BankServiceAsync {
	
	void select(SelectParams params, AsyncCallback callback);
	void selectCount(Bank searchRow, AsyncCallback callback);
	void selectSingle(Integer bank_id, AsyncCallback callback);
	
	void insert(Bank bank, AsyncCallback callback);
	void update(Bank bank, AsyncCallback callback);
	void delete(Integer bank_id, AsyncCallback callback);
}
