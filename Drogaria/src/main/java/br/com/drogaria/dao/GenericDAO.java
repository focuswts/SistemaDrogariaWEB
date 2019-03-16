package br.com.drogaria.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.drogaria.util.HibernateUtil;

public class GenericDAO<Entity> {

	private Class<Entity> entityClass;

	@SuppressWarnings("unchecked")
	public GenericDAO() {
		this.entityClass = (Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	public void save(Entity entity) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = null;

		try {
			tx = session.beginTransaction(); // Inicia a transação

			session.save(entity); // Salva

			tx.commit(); // Commit

		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();// Se der algum problema a transação é desfeita
			}
			throw e;
		} finally {
			session.close();
		}

	}

	public List<Entity> list() {

		Session session = HibernateUtil.getSessionFactory().openSession();

		try {

			Criteria queries = session.createCriteria(entityClass);
			@SuppressWarnings("unchecked")
			List<Entity> resultSet = queries.list();

			return resultSet;
		} catch (RuntimeException error) {
			throw error;
		} finally {
			session.close();
		}

	}

	public List<Entity> list(String field) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		try {

			Criteria queries = session.createCriteria(entityClass);
			queries.addOrder(Order.asc(field)); //Define o critério de busca
			@SuppressWarnings("unchecked")
			List<Entity> resultSet = queries.list();

			return resultSet;
		} catch (RuntimeException error) {
			throw error;
		} finally {
			session.close();
		}

	}

	@SuppressWarnings("unchecked")
	public Entity search(long codigo) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		try {

			Criteria queries = session.createCriteria(entityClass);
			queries.add(Restrictions.idEq(codigo));
			Entity resultSet = (Entity) queries.uniqueResult();

			return resultSet;
		} catch (RuntimeException error) {
			throw error;
		} finally {
			session.close();
		}

	}

	public void delete(Entity entity) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = null;

		try {
			tx = session.beginTransaction(); // Inicia a transação

			session.delete(entity); // Deleta
			tx.commit();

		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();// Se der algum problema a transação é desfeita
			}
			throw e;
		} finally {
			session.close();
		}

	}

	public void update(Entity entity) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = null;

		try {
			tx = session.beginTransaction(); // Inicia a transação

			session.update(entity); // Deleta
			tx.commit();

		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();// Se der algum problema a transação é desfeita
			}
			throw e;
		} finally {
			session.close();
		}

	}

	@SuppressWarnings("unchecked")
	public Entity merge(Entity entity) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = null;

		try {
			tx = session.beginTransaction(); // Inicia a transação

		Entity retorno = (Entity) session.merge(entity); // Tenta Fazer O Merge
			tx.commit();
return retorno;
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();// Se der algum problema a transação é desfeita
			}
			throw e;
		} finally {
			session.close(); // Finaliza a Sessão
		}

	}

}
