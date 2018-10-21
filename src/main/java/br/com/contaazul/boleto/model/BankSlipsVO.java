package br.com.contaazul.boleto.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonValue;

public class BankSlipsVO {

	private List<BankSlip> bankSlip = new ArrayList<>();

	
	@JsonValue
	public List<BankSlip> getBankSlip() {
		return bankSlip;
	}
	
	public void addAll(List<BankSlip> bankSlips) {
		this.bankSlip.addAll(bankSlips);
	}
	
	public void add(BankSlip bankSlip) {
		this.bankSlip.add(bankSlip);
	}
	
}
