package br.com.contaazul.boleto.repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.contaazul.boleto.model.BankSlip;
import br.com.contaazul.boleto.model.StatusEnum;

@Repository
public class BankSlipRepository {

	private static final Logger logger = LoggerFactory.getLogger(BankSlipRepository.class);
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	
	class BankSlipRowMapper implements RowMapper<BankSlip> {
		@Override
		public BankSlip mapRow(ResultSet rs, int rowNum) throws SQLException {
			BankSlip bankSlip = new BankSlip();
			bankSlip.setId(rs.getString("id"));
			bankSlip.setCustomer(rs.getString("customer"));
			bankSlip.setDueDate(new Date(rs.getDate("due_date").getTime()));
			bankSlip.setTotalInCents(BigDecimal.valueOf(rs.getDouble("total_in_cents")));
			bankSlip.setStatus(StatusEnum.getStatusEnum(rs.getString("status")));
			return bankSlip;
		}

	}
	
	public List<BankSlip> findAll() {
		List<BankSlip> list = new ArrayList<>();
		try {
			list = jdbcTemplate.query("select * from bankslips ", new BankSlipRowMapper());
		} catch (Exception e) {
			logger.error("Error query find all bankslip in database ",e);
		}
		
		return list; 
	}
	
	public BankSlip findById(String id) {
		BankSlip result = null;
		
		try {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("id", id);
			result = jdbcTemplate.queryForObject("select * from bankslips where id = :id ", parameters, new BankSlipRowMapper());
		} catch (EmptyResultDataAccessException e) {
			logger.warn("bankslip with id {} was not found in database", id);
		} catch (Exception e) {
			logger.error("Error query bankslip by id failed in database ",e);
		}
		
		return result;
	}
	
	public boolean alterStatus(String id, StatusEnum status) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		StringBuilder sql = new StringBuilder();
		sql.append(" update bankslips set status = :status where id = :id ");
		
		parameters.addValue("id", id);
		parameters.addValue("status", status.getValue());
		
		return jdbcTemplate.update(sql.toString(), parameters) > 0;
	}
	
	public boolean alterStatus(String id, StatusEnum status, Date paymentDate) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		StringBuilder sql = new StringBuilder();
		sql.append(" update bankslips set status = :status, payment_date = :payment_date where id = :id ");
		
		parameters.addValue("id", id);
		parameters.addValue("status", status.getValue());
		parameters.addValue("payment_date", paymentDate);
		
		return jdbcTemplate.update(sql.toString(), parameters) > 0;
	}
	
	public boolean insert(BankSlip bankSlip) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		StringBuilder sql = new StringBuilder();
		sql.append("insert into bankslips (id, due_date, total_in_cents, customer, status) ");
		sql.append(" values ");
		sql.append(" (:id, :due_date, :total_in_cents, :customer, :status) ");
		
		parameters.addValue("id", bankSlip.getId());
		parameters.addValue("due_date", bankSlip.getDueDate());
		parameters.addValue("total_in_cents", bankSlip.getTotalInCents());
		parameters.addValue("customer", bankSlip.getCustomer());
		parameters.addValue("status", bankSlip.getStatus().getValue());
		
		return jdbcTemplate.update(sql.toString(), parameters) > 0;
	}
	
}
