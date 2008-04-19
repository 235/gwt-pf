package net.pleso.demo.server.operation;

import java.sql.SQLException;
import java.util.List;

import net.pleso.demo.client.dal.BaseException;
import net.pleso.demo.client.dal.ServerException;
import net.pleso.demo.client.dal.operation.Operation;
import net.pleso.demo.client.dal.operation.OperationService;
import net.pleso.demo.server.ExceptionParser;
import net.pleso.demo.server.auth.AuthRemoteServiceServlet;
import net.pleso.framework.client.dal.SelectParams;

public class OperationServiceImpl extends AuthRemoteServiceServlet implements OperationService {

	private static final long serialVersionUID = 1L;

	public Operation[] select(SelectParams params) {
		try {	
			List li = getSqlMapClient().queryForList("selectOperation", params);			
			return (Operation[])li.toArray(new Operation[li.size()]);				
		} 
		catch (SQLException e){
			e.printStackTrace();
			throw new ServerException(ExceptionParser.parseServerException(e.getMessage()), e);			
		}
	}
	
	public Operation[] selectByClient(SelectParams params) {
		try {	
			List li = getSqlMapClient().queryForList("selectOperationByClient", params);			
			return (Operation[])li.toArray(new Operation[li.size()]);				
		} 
		catch (SQLException e){
			e.printStackTrace();
			throw new ServerException(ExceptionParser.parseServerException(e.getMessage()), e);			
		}
	}
	
	public int selectCount() {
		try {						
			return ((Integer) getSqlMapClient().queryForObject("selectCountOperation")).intValue();							
		} 
		catch (SQLException e){
			e.printStackTrace();
			throw new ServerException(ExceptionParser.parseServerException(e.getMessage()), e);		
		}
	}
	
	public int selectCountByClient(Operation searchRow) {
		try {						
			return ((Integer) getSqlMapClient().queryForObject("selectCountOperationByClient", searchRow)).intValue();							
		} 
		catch (SQLException e){
			e.printStackTrace();
			throw new ServerException(ExceptionParser.parseServerException(e.getMessage()), e);		
		}
	}

	public void insert(Operation operation) {
		try {						
			getSqlMapClient().insert("insertOperation", operation);							
		} 
		catch (SQLException e){
			e.printStackTrace();
			throw new ServerException(ExceptionParser.parseServerException(e.getMessage()), e);		
		}		
	}

	public void delete(Integer operation_id) {
		try {						
			getSqlMapClient().delete("deleteOperation", operation_id);							
		} 
		catch (SQLException e){
			e.printStackTrace();
			throw new ServerException(ExceptionParser.parseServerException(e.getMessage()), e);		
		}
	}

	public void update(Operation operation) throws BaseException {
		try {
			getSqlMapClient().insert("updateOperation", operation);							
		} 
		catch (SQLException e){
			e.printStackTrace();
			throw new ServerException(ExceptionParser.parseServerException(e.getMessage()), e);		
		}
	}

	public Operation selectSingle(Integer operation_id) throws BaseException {
		try {						
			return (Operation) getSqlMapClient().queryForObject("selectSingleOperation", operation_id);							
		} 
		catch (SQLException e){
			e.printStackTrace();
			throw new ServerException(ExceptionParser.parseServerException(e.getMessage()), e);			
		}
	}

}
