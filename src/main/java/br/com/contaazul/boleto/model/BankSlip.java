package br.com.contaazul.boleto.model;

import java.math.BigDecimal;
import java.util.Date;

public class BankSlip {

	private String id;
	private Date dueDate;
	private BigDecimal totalInCents;
	private String customer;
	private String status;
	
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Date getDueDate() {
		return dueDate;
	}
	
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	public BigDecimal getTotalInCents() {
		return totalInCents;
	}
	
	public void setTotalInCents(BigDecimal totalInCents) {
		this.totalInCents = totalInCents;
	}
	
	public String getCustomer() {
		return customer;
	}
	
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
}
