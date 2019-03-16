package br.com.drogaria.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.drogaria.domain.Cidade;
import br.com.drogaria.util.HibernateUtil;

public class CidadeDAO extends GenericDAO<Cidade> {

	public List<Cidade> buscarPorEstado(Long estadoCodigo) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		try {

			Criteria queries = session.createCriteria(Cidade.class);
			queries.add(Restrictions.eq("estado.codigo", estadoCodigo));

			queries.addOrder(Order.asc("nome")); // Define o critério de busca
			@SuppressWarnings("unchecked")
			List<Cidade> resultSet = queries.list();

			return resultSet;
		} catch (RuntimeException error) {
			throw error;
		} finally {
			session.close();
		}

	}

}
