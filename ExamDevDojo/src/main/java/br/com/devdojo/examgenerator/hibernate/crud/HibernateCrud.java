//package br.com.devdojo.exam.hibernate.crud;
//
//import java.io.IOException;
//import java.io.Serializable;
//import java.util.List;
//
//import org.hibernate.Session;
//import org.hibernate.query.Query;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//@Component
//@Transactional
//public interface HibernateCrud<T> extends Serializable {
//
//	void save(T entity) throws IOException;
//
//	void saveOrUpdate(T entity) throws IOException;
//
//	void persist(T entity) throws IOException;
//
//	void update(T entity) throws IOException;
//
//	void delete(T entity) throws IOException;
//
//	T merge(T entity) throws IOException;
//
//	List<T> getAll(Class<T> entity) throws IOException;
//
////	Object findById(Class<T> entity, Long id) throws IOException;
//
//	T findById(Class<T> entity, Long id) throws IOException;
//
//	List<T> findListByQuery(String search) throws IOException;
//
//	List<T> findListBySQL(String search) throws IOException;
//
//	void executeUpdateQuery(String search) throws IOException;
//
//	void executeUpdateSQL(String search) throws IOException;
//
//	void clearSession() throws IOException;
//
//	void evict(T entity) throws IOException;
//
//	Session getSession() throws IOException;
//
//	JdbcTemplate getJdbcTemplate();
//
//	SimpleJdbcInsert getSimpleJdbcInsert();
//
//	NamedParameterJdbcTemplate getNamedParameterJdbcTemplate();
//
//	Long getTableDataCount(String table) throws IOException;
//
//	Query<T> getQuery(String query) throws IOException;
//
//	List<T> findListByQuery(String query, int beginIndexRegistry, int maxResult);
//
//}
