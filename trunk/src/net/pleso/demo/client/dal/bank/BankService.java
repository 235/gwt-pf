package net.pleso.demo.client.dal.bank;

import net.pleso.demo.client.dal.BaseException;
import net.pleso.demo.client.dal.bank.Bank;
import net.pleso.framework.client.dal.SelectParams;
import com.google.gwt.user.client.rpc.RemoteService;

public interface BankService extends RemoteService {
	
	Bank[] select(SelectParams params) throws BaseException;
	int selectCount(Bank searchRow) throws BaseException;
	Bank selectSingle(Integer bank_id) throws BaseException;
	
	void insert(Bank bank) throws BaseException;
	void update(Bank bank) throws BaseException;
	void delete(Integer bank_id) throws BaseException;
}
