package net.pleso.demo.client.dal.bank;

import java.util.ArrayList;
import com.google.gwt.user.client.rpc.IsSerializable;

import net.pleso.demo.client.localization.Locale;
import net.pleso.framework.client.dal.IDataColumn;
import net.pleso.framework.client.dal.IDataRow;
import net.pleso.framework.client.dal.db.IDBValue;
import net.pleso.framework.client.dal.db.types.DBInteger;
import net.pleso.framework.client.dal.db.types.DBString;
import net.pleso.framework.client.dal.impl.DataColumn;

public class Bank extends BankInfo implements IDataRow, IsSerializable {
	
	public Bank() {
	}
	
	public class bank_id_column extends Columns {
		public bank_id_column() {
			super("bank_id", "ID", false);
		}

		public IDBValue getCell(Bank row) {
			return new DBInteger(row.getBank_id());
		}

		public void setCell(Bank row, IDBValue value) {
			if (value instanceof DBInteger)
				row.setBank_id(((DBInteger) value).getInteger());
		}
	}
	
	public class bank_name_column extends Columns {
		public bank_name_column() {
			super("bank_name", Locale.constants().bank_name(), false);
		}

		public IDBValue getCell(Bank row) {
			return new DBString(row.getBank_name());
		}

		public void setCell(Bank row, IDBValue value) {
			if (value instanceof DBString)
				row.setBank_name(((DBString) value).getString());
		}
	}
	
	public class bank_mfo_column extends Columns {
		public bank_mfo_column() {
			super("bank_mfo", Locale.constants().bank_mfo(), false);
		}

		public IDBValue getCell(Bank row) {
			return new DBInteger(row.getBank_mfo());
		}

		public void setCell(Bank row, IDBValue value) {
			if (value instanceof DBInteger)
				row.setBank_mfo(((DBInteger) value).getInteger());			
		}
	}
	
	private static ArrayList columns = new ArrayList();
	private static final Bank instance = new Bank();
	
	public abstract static class Columns extends DataColumn {
		
		public Columns(String name, String caption, boolean allowNull) {
			super(name, caption, allowNull);
			columns.add(this);
			this.order = columns.size(); 
		}
		
		public abstract void setCell(Bank row, IDBValue value);
		public abstract IDBValue getCell(Bank row);
		
		public static final bank_id_column bank_id = instance.new bank_id_column();
		public static final bank_name_column bank_name = instance.new bank_name_column();
		public static final bank_mfo_column bank_mfo = instance.new bank_mfo_column();
	}
	
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
