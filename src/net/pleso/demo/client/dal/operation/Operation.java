package net.pleso.demo.client.dal.operation;

import java.util.ArrayList;

import net.pleso.demo.client.localization.Locale;
import net.pleso.framework.client.dal.IDataColumn;
import net.pleso.framework.client.dal.IDataRow;
import net.pleso.framework.client.dal.db.IDBValue;
import net.pleso.framework.client.dal.db.types.DBBoolean;
import net.pleso.framework.client.dal.db.types.DBDate;
import net.pleso.framework.client.dal.db.types.DBInteger;
import net.pleso.framework.client.dal.db.types.DBString;
import net.pleso.framework.client.dal.impl.DataColumn;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Operation extends OperationInfo implements IDataRow, IsSerializable {

	public class operation_id_column extends Columns {

		public operation_id_column() {
			super("operation_id", Locale.constants().operation_id(), false);
		}

		public IDBValue getCell(Operation row) {
			return new DBInteger(row.getOperation_id());
		}

		public void setCell(Operation row, IDBValue value) {
			if (value instanceof DBInteger)
				row.setOperation_id(((DBInteger) value).getInteger());
		}
	}

	public class bank_id_column extends Columns {

		public bank_id_column() {
			super("bank_id", Locale.constants().bank_id(), false);
		}

		public IDBValue getCell(Operation row) {
			return new DBInteger(row.getBank_id());
		}

		public void setCell(Operation row, IDBValue value) {
			if (value instanceof DBInteger)
				row.setBank_id(((DBInteger) value).getInteger());
		}
	}

	public class bank_name_column extends Columns {

		public bank_name_column() {
			super("bank_name", Locale.constants().bank_name_verbose(), false);
		}

		public IDBValue getCell(Operation row) {
			return new DBString(row.getBank_name());
		}

		public void setCell(Operation row, IDBValue value) {
			if (value instanceof DBString)
				row.setBank_name(((DBString) value).getString());
		}
	}

	public class operation_date_column extends Columns {

		public operation_date_column() {
			super("operation_date", Locale.constants().operation_date(), false);
		}

		public IDBValue getCell(Operation row) {
			return new DBDate(row.getOperation_date());
		}

		public void setCell(Operation row, IDBValue value) {
			if (value instanceof DBDate)
				row.setOperation_date(((DBDate) value).getDate());
		}
	}

	public class operation_type_column extends Columns {

		public operation_type_column() {
			super("operation_type", Locale.constants().operation_type(), false);
		}

		public IDBValue getCell(Operation row) {
			return new DBInteger(row.getOperation_type());
		}

		public void setCell(Operation row, IDBValue value) {
			if (value instanceof DBInteger)
				row.setOperation_type(((DBInteger) value).getInteger());
		}
	}
	
	public class operation_description_column extends Columns {

		public operation_description_column() {
			super("operation_description", Locale.constants().operation_description(), true);
		}

		public IDBValue getCell(Operation row) {
			return new DBString(row.getOperation_description());
		}

		public void setCell(Operation row, IDBValue value) {
			if (value instanceof DBString)
				row.setOperation_description(((DBString) value).getString());
		}
	}
	
	public class op_cl_id_column extends Columns {

		public op_cl_id_column() {
			super("op_cl_id", Locale.constants().cl_id(), false);
		}

		public IDBValue getCell(Operation row) {
			return new DBInteger(row.getOp_cl_id());
		}

		public void setCell(Operation row, IDBValue value) {
			if (value instanceof DBInteger)
				row.setOp_cl_id(((DBInteger) value).getInteger());
		}
	}

	public class op_cl_name_column extends Columns {

		public op_cl_name_column() {
			super("op_cl_name", Locale.constants().cl_name_verbose(), false);
		}

		public IDBValue getCell(Operation row) {
			return new DBString(row.getOp_cl_name());
		}

		public void setCell(Operation row, IDBValue value) {
			if (value instanceof DBString)
				row.setOp_cl_name(((DBString) value).getString());
		}
	}
	
	public class is_confirmed_column extends Columns {

		public is_confirmed_column() {
			super("is_confirmed", Locale.constants().operation_is_confirmed(), true);
		}

		public IDBValue getCell(Operation row) {
			return new DBBoolean(row.getIs_confirmed());
		}

		public void setCell(Operation row, IDBValue value) {
			if (value instanceof DBBoolean)
				row.setIs_confirmed(((DBBoolean) value).getBoolean());
		}
	}

	public abstract static class Columns extends DataColumn {

		public Columns(String name, String caption, boolean allowNull) {
			super(name, caption, allowNull);
			columns.add(this);
			this.order = columns.size();
		}

		public abstract void setCell(Operation row, IDBValue value);
		public abstract IDBValue getCell(Operation row);

		public static final operation_id_column operation_id = instance.new operation_id_column();
		public static final bank_id_column bank_id = instance.new bank_id_column();
		public static final bank_name_column bank_name = instance.new bank_name_column();
		public static final operation_date_column operation_date = instance.new operation_date_column();
		public static final operation_type_column operation_type = instance.new operation_type_column();
		public static final operation_description_column operation_description = instance.new operation_description_column();
		public static final op_cl_id_column op_cl__id = instance.new op_cl_id_column();
		public static final op_cl_name_column op_cl_name = instance.new op_cl_name_column();
		public static final is_confirmed_column is_confirmed = instance.new is_confirmed_column();
	}

	public Operation() {}

	private static ArrayList columns = new ArrayList();
	private static final Operation instance = new Operation();

	public IDBValue getCell(IDataColumn column) {
		Columns col = (Columns) column;
		return col.getCell(this);
	}

	public IDataColumn getColumn(int index) {
		return (IDataColumn) columns.get(index);
	}

	public int getColumnCount() {
		return columns.size();
	}

	public void setCell(IDataColumn column, IDBValue value) {
		Columns col = (Columns) column;
		col.setCell(this, value);
	}
}

