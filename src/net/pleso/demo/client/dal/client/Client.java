package net.pleso.demo.client.dal.client;

import java.util.ArrayList;

import net.pleso.demo.client.localization.Locale;
import net.pleso.framework.client.dal.IDataColumn;
import net.pleso.framework.client.dal.IDataRow;
import net.pleso.framework.client.dal.db.IDBValue;
import net.pleso.framework.client.dal.db.types.DBDate;
import net.pleso.framework.client.dal.db.types.DBFloat;
import net.pleso.framework.client.dal.db.types.DBInteger;
import net.pleso.framework.client.dal.db.types.DBString;
import net.pleso.framework.client.dal.impl.DataColumn;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Client extends ClientInfo implements IDataRow, IsSerializable {

	public class cl_id_column extends Columns {

		public cl_id_column() {
			super("cl_id", Locale.constants().cl_id(), false);
		}

		public IDBValue getCell(Client row) {
			return new DBInteger(row.getCl_id());
		}

		public void setCell(Client row, IDBValue value) {
			if (value instanceof DBInteger)
				row.setCl_id(((DBInteger) value).getInteger());
		}
	}

	public class cl_name_column extends Columns {

		public cl_name_column() {
			super("cl_name", Locale.constants().cl_name(), false);
		}

		public IDBValue getCell(Client row) {
			return new DBString(row.getCl_name());
		}

		public void setCell(Client row, IDBValue value) {
			if (value instanceof DBString)
				row.setCl_name(((DBString) value).getString());
		}
	}

	public class cl_bank_id_column extends Columns {

		public cl_bank_id_column() {
			super("cl_bank_id", Locale.constants().cl_bank_id(), false);
		}

		public IDBValue getCell(Client row) {
			return new DBInteger(row.getCl_bank_id());
		}

		public void setCell(Client row, IDBValue value) {
			if (value instanceof DBInteger)
				row.setCl_bank_id(((DBInteger) value).getInteger());
		}
	}

	public class bank_name_column extends Columns {

		public bank_name_column() {
			super("bank_name", Locale.constants().bank_name_verbose(), false);
		}

		public IDBValue getCell(Client row) {
			return new DBString(row.getBank_name());
		}

		public void setCell(Client row, IDBValue value) {
			if (value instanceof DBString)
				row.setBank_name(((DBString) value).getString());
		}
	}

	public class cl_birthday_column extends Columns {

		public cl_birthday_column() {
			super("cl_birthday", Locale.constants().cl_birthday(), false);
		}

		public IDBValue getCell(Client row) {
			return new DBDate(row.getCl_birthday());
		}

		public void setCell(Client row, IDBValue value) {
			if (value instanceof DBDate)
				row.setCl_birthday(((DBDate) value).getDate());
		}
	}

	public class cl_birthday_start_column extends Columns {

		public cl_birthday_start_column() {
			super("cl_birthday_start", Locale.constants().cl_birthday_start(), false);
		}

		public IDBValue getCell(Client row) {
			return new DBDate(row.getCl_birthday_start());
		}

		public void setCell(Client row, IDBValue value) {
			if (value instanceof DBDate)
				row.setCl_birthday_start(((DBDate) value).getDate());
		}
	}

	public class cl_birthday_end_column extends Columns {

		public cl_birthday_end_column() {
			super("cl_birthday_end", Locale.constants().cl_birthday_end(), false);
		}

		public IDBValue getCell(Client row) {
			return new DBDate(row.getCl_birthday_end());
		}

		public void setCell(Client row, IDBValue value) {
			if (value instanceof DBDate)
				row.setCl_birthday_end(((DBDate) value).getDate());
		}
	}

	public class cl_money_column extends Columns {

		public cl_money_column() {
			super("cl_money", Locale.constants().cl_money(), false);
		}

		public IDBValue getCell(Client row) {
			return new DBFloat(row.getCl_money());
		}

		public void setCell(Client row, IDBValue value) {
			if (value instanceof DBFloat)
				row.setCl_money(((DBFloat) value).getFloat());
		}
	}

	public class cl_money_start_column extends Columns {

		public cl_money_start_column() {
			super("cl_money_start", Locale.constants().cl_money_start(), false);
		}

		public IDBValue getCell(Client row) {
			return new DBFloat(row.getCl_money_start());
		}

		public void setCell(Client row, IDBValue value) {
			if (value instanceof DBFloat)
				row.setCl_money_start(((DBFloat) value).getFloat());
		}
	}

	public class cl_money_end_column extends Columns {

		public cl_money_end_column() {
			super("cl_money_end", Locale.constants().cl_money_end(), false);
		}

		public IDBValue getCell(Client row) {
			return new DBFloat(row.getCl_money_end());
		}

		public void setCell(Client row, IDBValue value) {
			if (value instanceof DBFloat)
				row.setCl_money_end(((DBFloat) value).getFloat());
		}
	}
	
	public class cl_sex_column extends Columns {

		public cl_sex_column() {
			super("cl_sex", Locale.constants().cl_sex(), false);
		}

		public IDBValue getCell(Client row) {
			return new DBInteger(row.getCl_sex());
		}

		public void setCell(Client row, IDBValue value) {
			if (value instanceof DBInteger)
				row.setCl_sex(((DBInteger) value).getInteger());
		}
	}

	public abstract static class Columns extends DataColumn {

		public Columns(String name, String caption, boolean allowNull) {
			super(name, caption, allowNull);
			columns.add(this);
			this.order = columns.size();
		}

		public abstract void setCell(Client row, IDBValue value);
		public abstract IDBValue getCell(Client row);

		public static final cl_id_column cl_id = instance.new cl_id_column();
		public static final cl_name_column cl_name = instance.new cl_name_column();
		public static final cl_bank_id_column cl_bank_id = instance.new cl_bank_id_column();
		public static final bank_name_column bank_name = instance.new bank_name_column();
		public static final cl_birthday_column cl_birthday = instance.new cl_birthday_column();
		public static final cl_birthday_start_column cl_birthday_start = instance.new cl_birthday_start_column();
		public static final cl_birthday_end_column cl_birthday_end = instance.new cl_birthday_end_column();
		public static final cl_money_column cl_money = instance.new cl_money_column();
		public static final cl_money_start_column cl_money_start = instance.new cl_money_start_column();
		public static final cl_money_end_column cl_money_end = instance.new cl_money_end_column();
		public static final cl_sex_column cl_sex = instance.new cl_sex_column();
	}

	public Client() {}

	private static ArrayList columns = new ArrayList();
	private static final Client instance = new Client();

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

