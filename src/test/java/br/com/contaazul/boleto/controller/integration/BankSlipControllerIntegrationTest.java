package br.com.contaazul.boleto.controller.integration;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.contaazul.boleto.controller.BankSlipController;
import br.com.contaazul.boleto.model.BankSlip;
import br.com.contaazul.boleto.request.RequestBankSlip;
import br.com.contaazul.boleto.service.BankSlipService;
import br.com.contaazul.boleto.util.DateUtil;
import br.com.contaazul.boleto.util.UUDIGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BankSlipControllerIntegrationTest {

	@Autowired
	private BankSlipController bankSlipController;
	
	@Autowired
	private BankSlipService bankService;
	
	private DateUtil dateUtil = new DateUtil(); 
	
	private RequestBankSlip request = null;
	
	private List<BankSlip> bankSlips = null;
	
	private BankSlip bankSlip = null;
	
	
	@Before
	public void create() throws ParseException {
		request = new RequestBankSlip();
		request.setCustomer("company teste");
		request.setTotalInCents(BigDecimal.valueOf(15000));
		request.setDueDate(dateUtil.toDate("2018-10-19"));
		
		bankSlips = new ArrayList<>();
		bankSlip =  new BankSlip();
		bankSlip.setCustomer(request.getCustomer());
		bankSlip.setDueDate(request.getDueDate());
		bankSlip.setId(UUDIGenerator.generateUniqueId());
		bankSlip.setTotalInCents(request.getTotalInCents());
		bankSlips.add(bankSlip);
		
	}
	
	@Test
	public void checkCreateNewBankSlipRequestOk() {
		ResponseEntity<BankSlip> response = bankSlipController.createBankSlip(request);
		assertNotNull(response.getBody());
		assertNotNull(bankService.getBankSlipById(response.getBody().getId()));
		assertTrue(HttpStatus.CREATED.equals(response.getStatusCode()));
	}
	
	@Test
	public void checkCreateWithRequestBodyNull() {
		RequestBankSlip request = null;
		ResponseEntity<BankSlip> response = bankSlipController.createBankSlip(request);
		assertNull(response.getBody());
		assertTrue(HttpStatus.BAD_REQUEST.equals(response.getStatusCode()));
	}
	
	@Test
	public void checkCreateWithInvalidRequest() {
		request.setTotalInCents(null); // set invalid data to test
		ResponseEntity<BankSlip> response = bankSlipController.createBankSlip(request);
		assertNull(response.getBody());
		assertTrue(HttpStatus.UNPROCESSABLE_ENTITY.equals(response.getStatusCode()));
		request.setTotalInCents(BigDecimal.valueOf(15000));
	}
	
	@Test
	public void checkDetailBankSlipsOkWithOutFine() {
		ResponseEntity<BankSlip> response = bankSlipController.getDetailBankSlipsById("84e8adbf-1a14-403b-ad73-d78ae19b59bf");
		assertNotNull(response.getBody());
		assertNull(response.getBody().getFine());
		assertTrue(HttpStatus.OK.equals(response.getStatusCode()));
	}
	
	@Test
	public void checkDetailBankSlipsOkWithFineLess10Days() {
		ResponseEntity<BankSlip> response = bankSlipController.getDetailBankSlipsById("c2dbd236-3fa5-4ccc-9c12-bd0ae1d6dd89");
		assertNotNull(response.getBody());
		assertNotNull(response.getBody().getFine());
		assertTrue(BigDecimal.valueOf(50).setScale(4).equals(response.getBody().getFine()));
		assertTrue(HttpStatus.OK.equals(response.getStatusCode()));
	}
	
}
