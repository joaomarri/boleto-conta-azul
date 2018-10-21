package br.com.contaazul.boleto.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.contaazul.boleto.model.BankSlip;
import br.com.contaazul.boleto.model.BankSlipsVO;
import br.com.contaazul.boleto.request.RequestBankSlip;
import br.com.contaazul.boleto.request.RequestPayment;
import br.com.contaazul.boleto.service.BankSlipService;
import br.com.contaazul.boleto.util.DateUtil;
import br.com.contaazul.boleto.util.UUDIGenerator;

@RunWith(MockitoJUnitRunner.class)
public class BankSlipControllerTest {

	@InjectMocks
	private BankSlipController bankSlipController;
	
	@Mock
	private BankSlipService bankSlipService;
	
	private DateUtil dateUtil = new DateUtil(); 
	
	private RequestBankSlip request = null;
	
	private List<BankSlip> bankSlips = null;
	
	private BankSlip bankSlip = null;
	
	@Before
	public void createMocks() throws ParseException {
		request = new RequestBankSlip();
		request.setCustomer("company teste");
		request.setTotalInCents(BigDecimal.valueOf(15000));
		request.setDueDate(dateUtil.toDate("2018-10-19"));
		
		bankSlips = new ArrayList<>();
		bankSlip =  new BankSlip();
		bankSlip.setCustomer("test1");
		bankSlip.setDueDate(request.getDueDate());
		bankSlip.setId(UUDIGenerator.generateUniqueId());
		bankSlip.setTotalInCents(request.getTotalInCents());
		bankSlips.add(bankSlip);
		
	}
	
	@Test
	public void requestBodyNullForCreate() {
		RequestBankSlip request = null;
		ResponseEntity<BankSlip> response = bankSlipController.createBankSlip(request);
		assertNull(response.getBody());
		assertTrue(HttpStatus.BAD_REQUEST.equals(response.getStatusCode()));
	}
	
	@Test
	public void requestValidForCreate() {
		when(bankSlipService.createBankSlip(any(BankSlip.class))).thenReturn(true);
		
		ResponseEntity<BankSlip> response = bankSlipController.createBankSlip(request);
		assertNotNull(response.getBody());
		assertTrue(HttpStatus.CREATED.equals(response.getStatusCode()));
	}
	
	@Test
	public void requestInvalidForCreate() {
		when(bankSlipService.createBankSlip(any(BankSlip.class))).thenReturn(false);
		
		ResponseEntity<BankSlip> response = bankSlipController.createBankSlip(request);
		assertNull(response.getBody());
		assertTrue(HttpStatus.UNPROCESSABLE_ENTITY.equals(response.getStatusCode()));
	}
	
	@Test
	public void getBankSlipsListOk() {
		when(bankSlipService.getAllBankSlip()).thenReturn(bankSlips);
		
		ResponseEntity<BankSlipsVO> response = bankSlipController.getBankSlips();
		assertFalse(response.getBody().getBankSlip().isEmpty());
		assertTrue(HttpStatus.OK.equals(response.getStatusCode()));
	}
	
	@Test
	public void getBankSlipsListNotFound() {
		when(bankSlipService.getAllBankSlip()).thenReturn(Collections.<BankSlip>emptyList());
		
		ResponseEntity<BankSlipsVO> response = bankSlipController.getBankSlips();
		assertTrue(response.getBody().getBankSlip().isEmpty());
		assertTrue(HttpStatus.NOT_FOUND.equals(response.getStatusCode()));
	}
	
	@Test
	public void getDetailBankSlipsOk() {
		bankSlip.setFine(BigDecimal.TEN);
		when(bankSlipService.getBankSlipById(any(String.class))).thenReturn(bankSlip);
		when(bankSlipService.applyBankSlipFine(any(BankSlip.class))).thenReturn(bankSlip);
		
		ResponseEntity<BankSlip> response = bankSlipController.getDetailBankSlipsById("xx11");
		assertNotNull(response.getBody());
		assertNotNull(response.getBody().getFine());
		assertTrue(HttpStatus.OK.equals(response.getStatusCode()));
	}
	
	@Test
	public void getDetailBankSlipsNotFound() {
		when(bankSlipService.getBankSlipById(any(String.class))).thenReturn(bankSlip);
		when(bankSlipService.applyBankSlipFine(any(BankSlip.class))).thenReturn(null);
		
		ResponseEntity<BankSlip> response = bankSlipController.getDetailBankSlipsById("xx11");
		assertNull(response.getBody());
		assertTrue(HttpStatus.NOT_FOUND.equals(response.getStatusCode()));
	}
	
	@Test
	public void paymentBankSlipOk() {
		RequestPayment payment = new RequestPayment();
		when(bankSlipService.paymentBankSlip(any(String.class), any(Date.class))).thenReturn(true);
		
		ResponseEntity<HttpStatus> response = bankSlipController.paymentBankSlip("xx11", payment);
		assertTrue(HttpStatus.NO_CONTENT.equals(response.getStatusCode()));
	}
	
	@Test
	public void paymentBankSlipNotOk() {
		RequestPayment payment = new RequestPayment();
		when(bankSlipService.paymentBankSlip(any(String.class), any(Date.class))).thenReturn(false);
		
		ResponseEntity<HttpStatus> response = bankSlipController.paymentBankSlip("xx11", payment);
		assertTrue(HttpStatus.NOT_FOUND.equals(response.getStatusCode()));
	}
	
	@Test
	public void cancelBankSlipOk() {
		when(bankSlipService.cancelBankSlip(any(String.class))).thenReturn(true);
		
		ResponseEntity<HttpStatus> response = bankSlipController.cancelBankSlip("x11");
		assertTrue(HttpStatus.NO_CONTENT.equals(response.getStatusCode()));
	}
	
	@Test
	public void cancelBankSlipNotOk() {
		when(bankSlipService.cancelBankSlip(any(String.class))).thenReturn(false);
		
		ResponseEntity<HttpStatus> response = bankSlipController.cancelBankSlip("x11");
		assertTrue(HttpStatus.NOT_FOUND.equals(response.getStatusCode()));
	}
	
	
}
