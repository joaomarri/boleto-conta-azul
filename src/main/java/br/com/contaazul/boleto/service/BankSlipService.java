package br.com.contaazul.boleto.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.contaazul.boleto.model.BankSlip;
import br.com.contaazul.boleto.model.StatusEnum;
import br.com.contaazul.boleto.repository.BankSlipRepository;

@Service
public class BankSlipService {

	@Autowired
	private BankSlipRepository repository;

	@Transactional(readOnly=true)
	public List<BankSlip> getAllBankSlip() {
		return repository.findAll();
	}
	
	@Transactional(readOnly=true)
	public BankSlip getBankSlipById(String id) {
		return repository.findById(id);
	}
	
	@Transactional(readOnly=false)
	public boolean paymentBankSlip(String id) {
		return repository.alterStatus(id, StatusEnum.PAID);
	}
	
	@Transactional(readOnly=false)
	public boolean cancelBankSlip(String id) {
		return repository.alterStatus(id, StatusEnum.CANCELED);
	}
	
}
