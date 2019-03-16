package br.com.drogaria.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static SessionFactory connectionFactory = buildSessionFactory();

	public static SessionFactory getSessionFactory() {
		return connectionFactory;
	}

	public static Connection getConexao() {

		Session sessao = connectionFactory.openSession();

		Connection conexao = sessao.doReturningWork(new ReturningWork<Connection>() {

			@Override
			public Connection execute(Connection conn) throws SQLException {
				return conn;
			}

		});
		return conexao;

	}

	private static SessionFactory buildSessionFactory() {

		try {
			// Recebe A Configuração do Hibernate.cfg(lê as configurações)
			Configuration config = new Configuration().configure();

			// Registra O Serviço recebendo o valor da variável config
			ServiceRegistry register = new StandardServiceRegistryBuilder().applySettings(config.getProperties())
					.build();

			// Cria a fábrica de sessão através do registro
			SessionFactory factory = config.buildSessionFactory(register);

			// Retorna o valor para a variável global
			return factory;

		} catch (Throwable ex) {
			System.err.println("Cannot Create ConnectionFactory." + ex);
			throw new ExceptionInInitializerError(ex);
		}

	}

}
