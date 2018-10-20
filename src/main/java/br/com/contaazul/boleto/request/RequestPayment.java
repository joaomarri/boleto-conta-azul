package br.com.contaazul.boleto.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestPayment {

	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonProperty("payment_date")
	private Date paymentDate;

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	
	
}
