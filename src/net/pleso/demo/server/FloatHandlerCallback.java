package net.pleso.demo.server;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Types;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;

//The recommended Java mapping for the DECIMAL and NUMERIC types is java.math.BigDecimal. 
//The java.math.BigDecimal type provides math operations to allow BigDecimal types to be added, 
//subtracted, multiplied, and divided with other BigDecimal types, with integer types, and with floating point types.
//http://java.sun.com/j2se/1.3/docs/guide/jdbc/getstart/mapping.html
public class FloatHandlerCallback implements TypeHandlerCallback {
	
	public Object getResult(ResultGetter getter) throws SQLException {
		if (getter.getObject() == null)
			return null;
		else
			return new Float(getter.getFloat());
	}

	public void setParameter(ParameterSetter setter, Object parameter)
			throws SQLException {
		BigDecimal value = null;
		if (parameter != null)
			value =  new BigDecimal(((Float) parameter).floatValue());		
		
		if (value == null)
			setter.setNull(Types.NUMERIC);
		else
			setter.setBigDecimal(value);
	}

	public Object valueOf(String value) {
		if (value != null)
			return new Float(value);
		else
			return null;
	}

}
