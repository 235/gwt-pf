package net.pleso.demo.server;

import java.sql.SQLException;
import java.sql.Time;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.pleso.framework.client.dal.types.StringTime;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;

public class StringTimeHandlerCallback implements TypeHandlerCallback {

	public Object getResult(ResultGetter getter) throws SQLException {
		if (getter.getObject() == null)
			return null;
		else
			return new StringTime(getter.getTime());
	}

	public void setParameter(ParameterSetter setter, Object parameter)
			throws SQLException {
		Date value = null;
		if (parameter != null)
		{
			DateFormat df = new SimpleDateFormat("HH:mm");
			String s = ((StringTime) parameter).getAsString();
			try {
				value = df.parse(s);
			} catch (ParseException e) {
				value = null;
			}
		}
		
		if (value == null)
			setter.setNull(Types.NULL);
		else
			setter.setTime(new Time(value.getHours(), value.getMinutes(), value.getSeconds()));
	}

	public Object valueOf(String value) {
		if (value != null)
			return new StringTime(value);
		else
			return null;
	}
}
