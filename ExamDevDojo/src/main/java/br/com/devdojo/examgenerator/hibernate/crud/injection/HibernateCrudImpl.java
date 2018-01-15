//package br.com.devdojo.exam.hibernate.crud.injection;
//
//import java.io.IOException;
//import java.util.List;
//
//import javax.faces.bean.ViewScoped;
//
//import br.com.devdojo.exam.hibernate.crud.HibernateCrud;
//import br.com.devdojo.exam.hibernate.util.HibernateUtil;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.query.Query;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//
//@Component
//@Transactional
//public class HibernateCrudImpl<T> implements HibernateCrud<T> {
//
//	private static final long serialVersionUID = 1L;
//
//	private static SessionFactory mSessionFactory = HibernateUtil.getSessionFactory();
//
//	@Autowired
//	private JdbcTemplateImpl mJdbcTemplateImpl;
//
//	@Autowired
//	private SimpleJdbcInsertImpl mSimpleJdbcInsertImpl;
//
//	@Autowired
//	private NamedParameterJdbcTemplateImpl mNameParameterJdbcTemplateImpl;
//
//	@Autowired
//	private SimpleJdbcClassImpl mSimpleJdbcClassImpl;
//
//	private Transaction mTransaction = mSessionFactory.getCurrentSession().getTransaction();
//
//	@Override
//	public void save(T entity) throws IOException {
//		mSessionFactory.getCurrentSession().save(entity);
//		executeFlushSession();
//	}
//
//	@Override
//	public void saveOrUpdate(T entity) throws IOException {
//		mSessionFactory.getCurrentSession().saveOrUpdate(entity);
//		executeFlushSession();
//	}
//
//	@Override
//	public void persist(T entity) throws IOException {
//		mSessionFactory.getCurrentSession().persist(entity);
//		executeFlushSession();
//	}
//
//	@Override
//	public void update(T entity) throws IOException {
//		mSessionFactory.getCurrentSession().update(entity);
//		executeFlushSession();
//	}
//
//	@Override
//	public void delete(T entity) throws IOException {
//		mSessionFactory.getCurrentSession().delete(entity);
//		executeFlushSession();
//	}
//
//	@Override
//	public T merge(T entity) throws IOException {
//		entity = (T) mSessionFactory.getCurrentSession().merge(entity);
//		executeFlushSession();
//		return entity;
//	}
//
//	@Override
//	public List<T> getAll(Class<T> entity) throws IOException {
//		StringBuilder builderQuery = new StringBuilder();
//		builderQuery.append("SELECT DISTINCT(entity) FROM").append(entity.getSimpleName()).append("entity");
//
//		return mSessionFactory.getCurrentSession().createQuery(builderQuery.toString()).list();
//
//	}
//
//	@Override
//	public T findById(Class<T> entity, Long id) throws IOException {
//		return mSessionFactory.getCurrentSession().load(entity, id);
//	}
//
//	@Override
//	public List<T> findListByQuery(String search) throws IOException {
//		return mSessionFactory.getCurrentSession().createQuery(search).list();
//	}
//
//	@Override
//	public List<T> findListBySQL(String search) throws IOException {
//		return mSessionFactory.getCurrentSession().createNativeQuery(search).list();
//	}
//
//	@Override
//	public void executeUpdateQuery(String search) throws IOException {
//		mSessionFactory.getCurrentSession().createQuery(search).executeUpdate();
//		executeFlushSession();
//	}
//
//	@Override
//	public void executeUpdateSQL(String search) throws IOException {
//		mSessionFactory.getCurrentSession().createNativeQuery(search).executeUpdate();
//		executeFlushSession();
//	}
//
//	@Override
//	public void clearSession() throws IOException {
//		mSessionFactory.getCurrentSession().clear();
//	}
//
//	@Override
//	public void evict(T entity) throws IOException {
//		mSessionFactory.getCurrentSession().evict(entity);
//	}
//
//	@Override
//	public Session getSession() throws IOException {
//		return mSessionFactory.getCurrentSession();
//	}
//
//	@Override
//	public JdbcTemplate getJdbcTemplate() {
//		return mJdbcTemplateImpl;
//	}
//
//	@Override
//	public SimpleJdbcInsert getSimpleJdbcInsert() {
//		return mSimpleJdbcInsertImpl;
//	}
//
//	@Override
//	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
//		return mNameParameterJdbcTemplateImpl;
//	}
//
//	@Override
//	public Long getTableDataCount(String table) throws IOException {
//		StringBuilder builderSql = new StringBuilder();
//		builderSql.append("SELECT COUNT(1) FROM").append(table);
//
//		return mJdbcTemplateImpl.queryForObject(builderSql.toString(), Long.class);
//	}
//
//	@Override
//	public Query<T> getQuery(String query) throws IOException {
//		return mSessionFactory.getCurrentSession().createQuery(query);
//	}
//
//	@Override
//	public List<T> findListByQuery(String query, int beginIndexRegistry, int maxResult) {
//		return mSessionFactory.getCurrentSession().createQuery(query)
//				.setFirstResult(beginIndexRegistry)
//				.setMaxResults(maxResult)
//				.list();
//	}
//
//	public List<Object[]> findListArray(String search) {
//		return mSessionFactory.getCurrentSession().createNativeQuery(search).list();
//	}
//
//	private void validateTransaction() {
//		if (!mTransaction.isActive())
//			mTransaction.begin();
//	}
//
//	private void commitAjax() {
//		mTransaction.commit();
//	}
//
//	private void rollbackAjax() {
//		mTransaction.rollback();
//	}
//
//	// Instantly execute SQL statements
//	private void executeFlushSession() {
//		mSessionFactory.getCurrentSession().flush();
//	}
//
//}
