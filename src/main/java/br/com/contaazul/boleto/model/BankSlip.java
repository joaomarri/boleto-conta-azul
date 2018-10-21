package br.com.contaazul.boleto.model;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BankSlip {

	@JsonProperty("id")
	private String id;
	
	@JsonProperty("due_date")
	private Date dueDate;
	
	@JsonProperty("total_in_cents")
	private BigDecimal totalInCents;
	
	@JsonProperty("customer")
	private String customer;
	
	@JsonProperty("status")
	private StatusEnum status;
	
	@JsonInclude(Include.NON_NULL)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonProperty("payment_date")
	private Date paymentDate;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("fine")
	private BigDecimal fine;
	
	
	public BankSlip() {
		this.status = StatusEnum.PENDING;
	}
	
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

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public BigDecimal getFine() {
		return fine;
	}

	public void setFine(BigDecimal fine) {
		this.fine = fine;
	}
	
	
	
	
}
