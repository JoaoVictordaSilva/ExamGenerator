//package br.com.devdojo.exam.hibernate.crud.injection;
//
//import java.io.Serializable;
//import java.sql.SQLException;
//
//import javax.sql.DataSource;
//
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//@Component
//@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//public class NamedParameterJdbcTemplateImpl extends NamedParameterJdbcTemplate implements Serializable{
//
//	private static final long serialVersionUID = 1L;
//
//	public NamedParameterJdbcTemplateImpl(DataSource dataSource) {
//		super(dataSource);
//	}
//
//
//}
