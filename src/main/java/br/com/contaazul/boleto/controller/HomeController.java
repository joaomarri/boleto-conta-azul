package br.com.contaazul.boleto.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/home")
public class HomeController {
	
	
	@GetMapping(value = "/check", produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> checkApi(){
		return new ResponseEntity<String>("Ok", HttpStatus.OK);
	}

}
