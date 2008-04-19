package net.pleso.demo.client.dal.client;

import net.pleso.demo.client.dal.BaseException;
import net.pleso.framework.client.dal.SelectParams;

import com.google.gwt.user.client.rpc.RemoteService;

public interface ClientService extends RemoteService {
	
	Client[] select(SelectParams params) throws BaseException;
	int selectCount(Client searchRow) throws BaseException;
	Client selectSingle(Integer cl_id) throws BaseException;
	
	void insert(Client operation) throws BaseException;
	void update(Client operation) throws BaseException;
	void delete(Integer cl_id) throws BaseException;

}
