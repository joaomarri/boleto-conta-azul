package br.com.contaazul.boleto.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.contaazul.boleto.model.BankSlip;
import br.com.contaazul.boleto.model.BankSlipsVO;
import br.com.contaazul.boleto.repository.BankSlipRepository;

@RestController
@RequestMapping("/rest")
public class BankSlipController {

	@Autowired
	private BankSlipRepository repository;
	
	
	@GetMapping(value = "/bankslips", produces = "application/json;charset=UTF-8")
	public ResponseEntity<BankSlipsVO> getBankSlips() {
		BankSlipsVO bankSlipsVO = new BankSlipsVO();
		bankSlipsVO.addAll(repository.findAll());
		return new ResponseEntity<BankSlipsVO>(bankSlipsVO, getStatusResponse(bankSlipsVO.getBankSlip())); 
	}
	
	private HttpStatus getStatusResponse(Collection<BankSlip> list) {
		HttpStatus result = HttpStatus.OK; 
		if (list.isEmpty()) {
			return HttpStatus.NOT_FOUND; 
		}
		
		return result;
	}
	
	
}
