package net.pleso.demo.server;

import java.sql.SQLException;
import java.sql.Types;

import net.pleso.framework.client.dal.types.BigInt;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;

public class BigIntHandlerCallback implements TypeHandlerCallback {

	public Object getResult(ResultGetter getter) throws SQLException {
		if (getter.getObject() == null)
			return null;
		else
			return new BigInt(getter.getLong());
	}

	public void setParameter(ParameterSetter setter, Object parameter)
			throws SQLException {
		Long value = null;
		if (parameter != null)
			value = ((BigInt) parameter).getAsLong();		
		
		if (value == null)
			setter.setNull(Types.BIGINT);
		else
			setter.setLong(value.longValue());
	}

	public Object valueOf(String value) {
		if (value != null)
			return new BigInt(value);
		else
			return null;
	}
}
