package net.pleso.demo.client.dal.operation;

import java.util.Date;

import net.pleso.framework.client.dal.db.types.DBBoolean;
import net.pleso.framework.client.dal.db.types.DBDate;
import net.pleso.framework.client.dal.db.types.DBInteger;
import net.pleso.framework.client.dal.db.types.DBString;

import com.google.gwt.user.client.rpc.IsSerializable;

public class OperationInfo implements IsSerializable{

	private Integer operation_id = DBInteger.nullValue;
	private Integer bank_id = DBInteger.nullValue;
	private String bank_name = DBString.nullValue;
	private Date operation_date = DBDate.nullValue;
	private Integer operation_type = DBInteger.nullValue;
	private String operation_description = DBString.nullValue;
	private Integer op_cl_id = DBInteger.nullValue;
	private String op_cl_name = DBString.nullValue;
	private Boolean is_confirmed = DBBoolean.nullValue;
	
	public String getOperation_description() {
		return operation_description;
	}
	public void setOperation_description(String operation_description) {
		this.operation_description = operation_description;
	}
	public Integer getBank_id() {
		return bank_id;
	}
	public void setBank_id(Integer bank_id) {
		this.bank_id = bank_id;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public Date getOperation_date() {
		return operation_date;
	}
	public void setOperation_date(Date operation_date) {
		this.operation_date = operation_date;
	}
	public Integer getOperation_id() {
		return operation_id;
	}
	public void setOperation_id(Integer operation_id) {
		this.operation_id = operation_id;
	}
	public Integer getOperation_type() {
		return operation_type;
	}
	public void setOperation_type(Integer operation_type) {
		this.operation_type = operation_type;
	}
	public Integer getOp_cl_id() {
		return op_cl_id;
	}
	public void setOp_cl_id(Integer op_cl_id) {
		this.op_cl_id = op_cl_id;
	}
	public String getOp_cl_name() {
		return op_cl_name;
	}
	public void setOp_cl_name(String op_cl_name) {
		this.op_cl_name = op_cl_name;
	}
	public Boolean getIs_confirmed() {
		return is_confirmed;
	}
	public void setIs_confirmed(Boolean is_confirmed) {
		this.is_confirmed = is_confirmed;
	}	
}
