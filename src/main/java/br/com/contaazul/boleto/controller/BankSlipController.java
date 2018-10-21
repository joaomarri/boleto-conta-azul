package br.com.contaazul.boleto.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.contaazul.boleto.model.BankSlip;
import br.com.contaazul.boleto.model.BankSlipsVO;
import br.com.contaazul.boleto.request.RequestBankSlip;
import br.com.contaazul.boleto.request.RequestPayment;
import br.com.contaazul.boleto.service.BankSlipService;

@RestController
@RequestMapping("/rest")
public class BankSlipController {

	@Autowired
	private BankSlipService bankService;
	
	
	@PostMapping(value = "/bankslips", produces = "application/json;charset=UTF-8")
	public ResponseEntity<BankSlip> createBankSlip(@RequestBody RequestBankSlip request) {
		if (request == null) {
			return new ResponseEntity<BankSlip>(HttpStatus.BAD_REQUEST); 
		}
		
		BankSlip bankSlip = new BankSlip();
		bankSlip.setDueDate(request.getDueDate());
		bankSlip.setTotalInCents(request.getTotalInCents());
		bankSlip.setCustomer(request.getCustomer());
		boolean created = bankService.createBankSlip(bankSlip);
		
		if (created) {
			return new ResponseEntity<BankSlip>(bankSlip, HttpStatus.CREATED); 
		} else {
			return new ResponseEntity<BankSlip>(HttpStatus.UNPROCESSABLE_ENTITY); 
		}
	}
	
	@GetMapping(value = "/bankslips", produces = "application/json;charset=UTF-8")
	public ResponseEntity<BankSlipsVO> getBankSlips() {
		BankSlipsVO bankSlipsVO = new BankSlipsVO();
		bankSlipsVO.addAll(bankService.getAllBankSlip());
		return new ResponseEntity<BankSlipsVO>(bankSlipsVO, getStatusResponse(bankSlipsVO.getBankSlip())); 
	}

	@GetMapping(value = "/bankslips/{id}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<BankSlip> getBankSlipsById(@PathVariable String id) {
		BankSlip bankSlip = bankService.getBankSlipById(id);
		bankSlip = bankService.applyBankSlipFine(bankSlip);
		return new ResponseEntity<BankSlip>(bankSlip, getStatusResponse(bankSlip)); 
	}
	
	@PostMapping(value = "/bankslips/{id}/payments", produces = "application/json;charset=UTF-8")
	public ResponseEntity<HttpStatus> paymentBankSlip(@PathVariable String id, @RequestBody RequestPayment payment) {
		boolean paid = bankService.paymentBankSlip(id, payment.getPaymentDate());
		HttpStatus httpStatus = getResponse(paid);
		return new ResponseEntity<HttpStatus>(httpStatus); 
	}

	@DeleteMapping(value = "/bankslips/{id}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<HttpStatus> cancelBankSlip(@PathVariable String id) {
		boolean canceled = bankService.cancelBankSlip(id);
		HttpStatus httpStatus = getResponse(canceled);
		return new ResponseEntity<HttpStatus>(httpStatus); 
	}
	
	private HttpStatus getResponse(boolean paid) {
		HttpStatus result = HttpStatus.NO_CONTENT; 
		if (!paid) {
			result = HttpStatus.NOT_FOUND; 
		}
		
		return result;
	}
	
	private HttpStatus getStatusResponse(Collection<BankSlip> list) {
		HttpStatus result = HttpStatus.OK; 
		if (list.isEmpty()) {
			return HttpStatus.NOT_FOUND; 
		}
		
		return result;
	}

	private HttpStatus getStatusResponse(BankSlip bankSlip) {
		HttpStatus result = HttpStatus.OK; 
		if (bankSlip == null) {
			return HttpStatus.NOT_FOUND; 
		}
		
		return result;
	}
	
	
}
