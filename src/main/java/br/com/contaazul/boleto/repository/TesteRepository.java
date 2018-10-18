package br.com.contaazul.boleto.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.contaazul.boleto.model.Teste;

@Repository
public class TesteRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	
	class TesteRowMapper implements RowMapper<Teste> {
		@Override
		public Teste mapRow(ResultSet rs, int rowNum) throws SQLException {
			Teste teste = new Teste();
			teste.setId(rs.getLong("id"));
			teste.setName(rs.getString("name"));
			return teste;
		}

	}
	
	public List<Teste> findAll() {
		return jdbcTemplate.query("select * from teste", new TesteRowMapper());
	}
	
}
