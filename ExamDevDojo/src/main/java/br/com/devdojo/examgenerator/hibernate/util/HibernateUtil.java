//package br.com.devdojo.exam.hibernate.util;
//
//import java.io.Serializable;
//import java.sql.Connection;
//import java.sql.SQLException;
//
//import javax.faces.bean.ApplicationScoped;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.engine.spi.SessionFactoryImplementor;
//
//@ApplicationScoped
//public class HibernateUtil implements Serializable {
//
//	private static final long serialVersionUID = 1L;
//
//	public static final String JAVA_COM_ENV_JDBC_DATA_SOURCE = "java:/comp/env/jdbc/datasource";
//
//	private static SessionFactory mSessionFactory = buildSessionFactory();
//
//	//read configuration file of hibernate.cfg.xml
//	private static SessionFactory buildSessionFactory() {
//		try {
//			if (mSessionFactory == null) {
//				mSessionFactory = new Configuration().configure().buildSessionFactory();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new ExceptionInInitializerError("Erro ao criar conex�o");
//		}
//
//		return mSessionFactory;
//	}
//
//	public static SessionFactory getSessionFactory() {
//		return mSessionFactory;
//	}
//
//	public static Session getCurrentSession() {
//		return mSessionFactory.getCurrentSession();
//	}
//
//	public static Session openSession() {
//		if (mSessionFactory == null)
//			buildSessionFactory();
//
//		return mSessionFactory.openSession();
//	}
//
//	public static void closeSession() {
//		if (getCurrentSession().isOpen())
//			mSessionFactory.getCurrentSession().close();
//	}
//
//	public static Connection getConnectionProvider() throws SQLException {
//		return ((SessionFactoryImplementor) mSessionFactory).getJdbcServices().getBootstrapJdbcConnectionAccess()
//				.obtainConnection();
//	}
//
//	public static Connection getConnection() throws Exception{
//		InitialContext context = new InitialContext();
//		DataSource ds = (DataSource) context.lookup(JAVA_COM_ENV_JDBC_DATA_SOURCE);
//		return ds.getConnection();
//	}
//
//	public DataSource getDataSourceJndi() throws NamingException {
//		InitialContext context = new InitialContext();
//		return (DataSource) context.lookup(JAVA_COM_ENV_JDBC_DATA_SOURCE);
//	}
//
//
//
//}
