package com.spring.spittr.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.spittr.Spitter;

@Repository
public class SpitterRepositoryImpl implements SpitterRepository {
  
  private JdbcOperations jdbc;

  @Autowired
  public SpitterRepositoryImpl(JdbcOperations jdbc) {
    this.jdbc = jdbc;
  }

  public Spitter save(Spitter spitter) {
    jdbc.update(
        "insert into t_spitter(username, password, firstName, lastName, email)" +
        " values (?, ?, ?, ?, ?)",
        spitter.getUsername(),
        spitter.getPassword(),
        spitter.getFirstName(),
        spitter.getLastName(),
        spitter.getEmail());
    return spitter; // TODO: Determine value for id
  }

  public Spitter findByUsername(String username) {
    return jdbc.queryForObject(
        "select id, username, null, firstName, lastName, email from t_spitter where username=?", 
        new SpitterRowMapper(), 
        username);
  }
  
  private static class SpitterRowMapper implements RowMapper<Spitter> {
    public Spitter mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new Spitter(
          rs.getLong("id"),
          rs.getString("username"),
          null,
          rs.getString("firstName"),
          rs.getString("lastName"),
          rs.getString("email"));
    }
  }

	@Override
	public String findPassByUsername(String username) {
		String sql = "select password from t_spitter where username=?";
		 
		return (String)jdbc.queryForObject(
				sql, new Object[] { username }, String.class);
	}
}
