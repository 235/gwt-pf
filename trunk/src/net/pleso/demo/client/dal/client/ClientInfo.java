package net.pleso.demo.client.dal.client;

import java.util.Date;

import net.pleso.framework.client.dal.db.types.DBDate;
import net.pleso.framework.client.dal.db.types.DBFloat;
import net.pleso.framework.client.dal.db.types.DBInteger;
import net.pleso.framework.client.dal.db.types.DBString;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ClientInfo implements IsSerializable{
	
	private Integer cl_id = DBInteger.nullValue;
	private String cl_name = DBString.nullValue;
	private Integer cl_bank_id = DBInteger.nullValue;
	private String bank_name = DBString.nullValue;
	private Date cl_birthday = DBDate.nullValue;
	private Date cl_birthday_start = DBDate.nullValue;
	private Date cl_birthday_end = DBDate.nullValue;
	private Float cl_money = DBFloat.nullValue;
	private Float cl_money_start = DBFloat.nullValue;
	private Float cl_money_end = DBFloat.nullValue;
	private Integer cl_sex = DBInteger.nullValue;
	
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public Integer getCl_bank_id() {
		return cl_bank_id;
	}
	public void setCl_bank_id(Integer cl_bank_id) {
		this.cl_bank_id = cl_bank_id;
	}
	public Date getCl_birthday() {
		return cl_birthday;
	}
	public void setCl_birthday(Date cl_birthday) {
		this.cl_birthday = cl_birthday;
	}
	public Date getCl_birthday_end() {
		return cl_birthday_end;
	}
	public void setCl_birthday_end(Date cl_birthday_end) {
		this.cl_birthday_end = cl_birthday_end;
	}
	public Date getCl_birthday_start() {
		return cl_birthday_start;
	}
	public void setCl_birthday_start(Date cl_birthday_start) {
		this.cl_birthday_start = cl_birthday_start;
	}
	public Integer getCl_id() {
		return cl_id;
	}
	public void setCl_id(Integer cl_id) {
		this.cl_id = cl_id;
	}
	public String getCl_name() {
		return cl_name;
	}
	public void setCl_name(String cl_name) {
		this.cl_name = cl_name;
	}
	public Integer getCl_sex() {
		return cl_sex;
	}
	public void setCl_sex(Integer cl_sex) {
		this.cl_sex = cl_sex;
	}
	public Float getCl_money() {
		return cl_money;
	}
	public void setCl_money(Float cl_money) {
		this.cl_money = cl_money;
	}
	public Float getCl_money_end() {
		return cl_money_end;
	}
	public void setCl_money_end(Float cl_money_end) {
		this.cl_money_end = cl_money_end;
	}
	public Float getCl_money_start() {
		return cl_money_start;
	}
	public void setCl_money_start(Float cl_money_start) {
		this.cl_money_start = cl_money_start;
	}
}
