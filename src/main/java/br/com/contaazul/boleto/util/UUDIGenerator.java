package br.com.contaazul.boleto.util;

import java.util.UUID;
import com.fasterxml.uuid.Generators;



public class UUDIGenerator {

	
	public synchronized static String generateUniqueId() {
		UUID uuid = Generators.randomBasedGenerator().generate();
		return uuid.toString();
	}
	
}
