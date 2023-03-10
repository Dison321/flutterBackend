package com.crud.test.rest.languageData;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class languageDAOImpl implements languageDAO {
    @Autowired
	JdbcTemplate jdbcTemplate;
	
    @Override
	public List<language> findAll() {
		return jdbcTemplate.query("SELECT * FROM language_type", new BeanPropertyRowMapper<language>(language.class));
	}
	@Override
	public language langTypeID(language l) {
		System.out.println(l.getLanguage_name());
		
		return jdbcTemplate.queryForObject("SELECT language_type_id FROM language_type WHERE language_name=?", new BeanPropertyRowMapper<language>(language.class),l.getLanguage_name());
	}

	@Override
	public language langProfID(language l) {
		System.out.println(l.getLanguage_proficient_level());
		
		return jdbcTemplate.queryForObject("SELECT language_proficient_id FROM language_proficient WHERE language_proficient_level=?", new BeanPropertyRowMapper<language>(language.class),l.getLanguage_proficient_level());
	}

    @Override
	public int saveLang(language l) {
		
		return jdbcTemplate.update("INSERT INTO language (seller_id,language_type_id,language_proficient_id) VALUES ( ?, ?,?)", new Object[] {l.getSeller_id(),l.getLanguage_type_id(),l.getLanguage_proficient_id()});

	}

	@Override
	public List<language> findLanguage(language l) {
		System.out.println(l.getSeller_id());
		
		return jdbcTemplate.query("SELECT language.*, language_type.language_name, language_proficient.language_proficient_level FROM language JOIN language_type ON language.language_type_id = language_type.language_type_id JOIN language_proficient ON language.language_proficient_id = language_proficient.language_proficient_id WHERE language.seller_id = ?;", new BeanPropertyRowMapper<language>(language.class),l.getSeller_id());
	}

	@Override
	public int deleteLang(language l) {
		System.out.println(l.getLanguage_id());
		return jdbcTemplate.update("DELETE from language where language_id = ?", l.getLanguage_id());

	}


	@Override
	public int editLang(language l) {

		return jdbcTemplate.update("UPDATE language SET language_type_id = ? ,language_proficient_id = ? WHERE language_id = ?", l.getLanguage_type_id(),l.getLanguage_proficient_id(),l.getLanguage_id());

	}

}
