package br.com.contaazul.boleto.repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.contaazul.boleto.model.BankSlip;
import br.com.contaazul.boleto.model.StatusEnum;

@Repository
public class BankSlipRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	
	class BankSlipRowMapper implements RowMapper<BankSlip> {
		@Override
		public BankSlip mapRow(ResultSet rs, int rowNum) throws SQLException {
			BankSlip bankSlip = new BankSlip();
			bankSlip.setId(rs.getString("id"));
			bankSlip.setCustomer(rs.getString("customer"));
			bankSlip.setDueDate(rs.getDate("due_date"));
			bankSlip.setTotalInCents(BigDecimal.valueOf(rs.getDouble("total_in_cents")));
			bankSlip.setStatus(StatusEnum.getStatusEnum(rs.getString("status")));
			return bankSlip;
		}

	}
	
	public List<BankSlip> findAll() {
		return jdbcTemplate.query("select * from bankslips ", new BankSlipRowMapper());
	}
	
}
