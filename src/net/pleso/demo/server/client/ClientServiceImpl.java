package net.pleso.demo.server.client;

import java.sql.SQLException;
import java.util.List;

import net.pleso.demo.client.dal.BaseException;
import net.pleso.demo.client.dal.ServerException;
import net.pleso.demo.client.dal.client.Client;
import net.pleso.demo.client.dal.client.ClientService;
import net.pleso.demo.server.ExceptionParser;
import net.pleso.demo.server.auth.AuthRemoteServiceServlet;
import net.pleso.framework.client.dal.SelectParams;

public class ClientServiceImpl extends AuthRemoteServiceServlet implements
		ClientService {

	private static final long serialVersionUID = 1L;

	public Client[] select(SelectParams params) {
		try {	
			String queryId;
			if (params.getSearchRow() == null) {
				queryId = "selectClient";
			} else {
				queryId = "searchClient";
			}
			List li = getSqlMapClient().queryForList(queryId, params);			
			return (Client[])li.toArray(new Client[li.size()]);				
		} 
		catch (SQLException e){
			e.printStackTrace();
			throw new ServerException(ExceptionParser.parseServerException(e.getMessage()), e);			
		}
	}

	public int selectCount(Client searchRow) {
		try {	
			String queryId;
			if (searchRow == null) {
				queryId = "selectCountClient";
			} else {
				queryId = "searchCountClient";
			}
			Object result = getSqlMapClient().queryForObject(queryId, searchRow);
			return ((Integer) result).intValue();							
		} 
		catch (SQLException e){
			e.printStackTrace();
			throw new ServerException(ExceptionParser.parseServerException(e.getMessage()), e);		
		}
	}

	public void insert(Client client) {
		try {						
			getSqlMapClient().insert("insertClient", client);							
		} 
		catch (SQLException e){
			e.printStackTrace();
			throw new ServerException(ExceptionParser.parseServerException(e.getMessage()), e);		
		}		
	}

	public void delete(Integer client_id) {
		try {						
			getSqlMapClient().delete("deleteClient", client_id);							
		} 
		catch (SQLException e){
			e.printStackTrace();
			throw new ServerException(ExceptionParser.parseServerException(e.getMessage()), e);		
		}
	}

	public void update(Client client) throws BaseException {
		try {						
			getSqlMapClient().insert("updateClient", client);							
		} 
		catch (SQLException e){
			e.printStackTrace();
			throw new ServerException(ExceptionParser.parseServerException(e.getMessage()), e);		
		}
	}

	public Client selectSingle(Integer client_id) throws BaseException {
		try {						
			return (Client) getSqlMapClient().queryForObject("selectSingleClient", client_id);							
		} 
		catch (SQLException e){
			e.printStackTrace();
			throw new ServerException(ExceptionParser.parseServerException(e.getMessage()), e);			
		}
	}

}
