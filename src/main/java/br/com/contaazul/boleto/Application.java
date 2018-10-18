package br.com.contaazul.boleto;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements ApplicationRunner {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	
	
	@Value("${accesslog}")
	private String accesslog;
	
	@Value("${startDate}")
	private String startDate;

	@Value("${duration}")
	private String duration;

	@Value("${threshold}")
	private Integer threshold;
	
	
	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
}
