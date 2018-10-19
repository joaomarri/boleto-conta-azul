package br.com.contaazul.boleto.model;

import java.util.ArrayList;
import java.util.List;

public class BankSlipsVO {

	private List<BankSlip> bankSlip = new ArrayList<>();

	public List<BankSlip> getBankSlip() {
		return bankSlip;
	}
	
	public void addAll(List<BankSlip> bankSlips) {
		this.bankSlip.addAll(bankSlips);
	}
	
	
	
}
