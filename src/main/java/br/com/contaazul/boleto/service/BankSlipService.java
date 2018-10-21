package br.com.contaazul.boleto.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.contaazul.boleto.model.BankSlip;
import br.com.contaazul.boleto.model.StatusEnum;
import br.com.contaazul.boleto.repository.BankSlipRepository;
import br.com.contaazul.boleto.util.UUDIGenerator;

@Service
public class BankSlipService {

	private static final Logger logger = LoggerFactory.getLogger(BankSlipService.class);
	
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
		try {
			return repository.alterStatus(id, StatusEnum.PAID);
		} catch (Exception e) {
			logger.error("Error alter to paid status with id {}",id, e);
			return false;
		}
	}
	
	@Transactional(readOnly=false)
	public boolean cancelBankSlip(String id) {
		try {
			return repository.alterStatus(id, StatusEnum.CANCELED);
		} catch (Exception e) {
			logger.error("Error alter to canceled status with id {}",id, e);
			return false;
		}
	}
	
	@Transactional(readOnly=false)
	public boolean createBankSlip(BankSlip bankSlip) {
		try {
			if (bankSlip == null) {
				return false;
			}
		
			bankSlip.setId(UUDIGenerator.generateUniqueId());
			return repository.insert(bankSlip);
		} catch (Exception e) {
			logger.error("Error create new BankSlip",e);
			return false;
		}
	}
	
	
}
