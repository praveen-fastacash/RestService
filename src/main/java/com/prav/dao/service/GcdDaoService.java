package com.prav.dao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.prav.dao.model.Numbers;

@Service
public class GcdDaoService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insertValues(int i1, int i2) {
		jdbcTemplate.update("insert into numbers_tbl(number1, number2) values (?,?)", i1, i2);
	}

	public List<Numbers> selectValues() {
		String sql = "select number1, number2 from numbers_tbl order by id";
		List<Numbers> numbersList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Numbers>(Numbers.class));
		return numbersList;
	}

}
