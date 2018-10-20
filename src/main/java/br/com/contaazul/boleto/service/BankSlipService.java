package br.com.contaazul.boleto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.contaazul.boleto.model.BankSlip;
import br.com.contaazul.boleto.repository.BankSlipRepository;

@Service
public class BankSlipService {

	@Autowired
	private BankSlipRepository repository;

	@Transactional
	public List<BankSlip> getAllBankSlip() {
		return repository.findAll();
	}
	
	@Transactional
	public BankSlip getBankSlipById(String id) {
		return repository.findById(id);
	}
	
}
