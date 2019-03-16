package br.com.drogaria.util;

import org.hibernate.Session;
import org.junit.Test;

public class HibernateUtilTest {

	@Test
	public void connect() {

		// Inicia A Comunicação com o banco e armaneza na variável o valor
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.close();
		System.out.println("Fechando Fábrica De Sessão!");
		HibernateUtil.getSessionFactory().close();
		if (HibernateUtil.getSessionFactory().isClosed() == true) {
			System.out.println("Encerrado!");
		}

	}

}
