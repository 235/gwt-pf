package net.pleso.demo.server.bank;

import java.sql.SQLException;
import java.util.List;

import net.pleso.demo.client.dal.BaseException;
import net.pleso.demo.client.dal.ServerException;
import net.pleso.demo.client.dal.bank.Bank;
import net.pleso.demo.client.dal.bank.BankService;
import net.pleso.demo.server.ExceptionParser;
import net.pleso.demo.server.auth.AuthRemoteServiceServlet;
import net.pleso.framework.client.dal.SelectParams;

public class BankServiceImpl extends AuthRemoteServiceServlet implements
		BankService {

	private static final long serialVersionUID = 1L;

	public Bank[] select(SelectParams params) {
		try {	
			String queryId;
			if (params.getSearchRow() == null) {
				queryId = "selectBank";
			} else {
				queryId = "searchBank";
			}
			List li = getSqlMapClient().queryForList(queryId, params);			
			return (Bank[])li.toArray(new Bank[li.size()]);				
		} 
		catch (SQLException e){
			e.printStackTrace();
			throw new ServerException(ExceptionParser.parseServerException(e.getMessage()), e);			
		}
	}

	public int selectCount(Bank searchRow) {
		try {		
			String queryId;
			if (searchRow == null) {
				queryId = "selectCountBank";
			} else {
				queryId = "searchCountBank";
			}
			return ((Integer) getSqlMapClient().queryForObject(queryId, searchRow)).intValue();							
		} 
		catch (SQLException e){
			e.printStackTrace();
			throw new ServerException(ExceptionParser.parseServerException(e.getMessage()), e);		
		}
	}

	public void insert(Bank bank) {
		try {						
			getSqlMapClient().insert("insertBank", bank);							
		} 
		catch (SQLException e){
			e.printStackTrace();
			throw new ServerException(ExceptionParser.parseServerException(e.getMessage()), e);		
		}		
	}

	public void delete(Integer bank_id) {
		try {						
			getSqlMapClient().delete("deleteBank", bank_id);							
		} 
		catch (SQLException e){
			e.printStackTrace();
			throw new ServerException(ExceptionParser.parseServerException(e.getMessage()), e);		
		}
	}

	public void update(Bank bank) throws BaseException {
		try {						
			getSqlMapClient().insert("updateBank", bank);							
		} 
		catch (SQLException e){
			e.printStackTrace();
			throw new ServerException(ExceptionParser.parseServerException(e.getMessage()), e);		
		}
	}

	public Bank selectSingle(Integer bank_id) throws BaseException {
		try {						
			return (Bank) getSqlMapClient().queryForObject("selectSingleBank", bank_id);							
		} 
		catch (SQLException e){
			e.printStackTrace();
			throw new ServerException(ExceptionParser.parseServerException(e.getMessage()), e);			
		}
	}

}
