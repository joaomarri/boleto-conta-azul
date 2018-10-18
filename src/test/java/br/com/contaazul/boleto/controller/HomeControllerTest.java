package br.com.contaazul.boleto.controller;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {
	
	@InjectMocks
	private HomeController homeController;
	
	@Test
	public void checkApiIsStatusOk() {
		ResponseEntity<?> response = homeController.checkApi();
		Assert.assertTrue( HttpStatus.OK.equals(response.getStatusCode()) );
	}
	

}
