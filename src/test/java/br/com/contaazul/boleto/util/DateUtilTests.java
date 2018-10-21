package br.com.contaazul.boleto.util;

import java.text.ParseException;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class DateUtilTests {

	private DateUtil dtUtil = new DateUtil();
	
	@Test
	public void daysBetween() throws ParseException {
		Date dueDate = dtUtil.toDate("2018-10-19");
		Date paymentDate = dtUtil.toDate("2018-10-21");
		long days = dtUtil.getDaysBetween(paymentDate, dueDate);
		Assert.assertTrue(days == 2);
		
	}

}
