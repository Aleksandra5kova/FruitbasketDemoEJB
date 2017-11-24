package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name = "crudDao")
@LocalBean
public class CrudDao<T> implements CrudDaoLocal<T> {

	@PersistenceContext(unitName = "FruitbasketDemoEJB")
	EntityManager em;

	public CrudDao() {

	}

	@Override
	public T save(T t) {
		em.persist(t);
		return t;
	}

	@Override
	public T update(T t) {
		return em.merge(t);
	}

	@Override
	public void delete(Class<T> type, Object id) {
		Object ref = em.getReference(type, id);
		em.remove(ref);
	}

	@Override
	public T findById(Class<T> type, Object id) {
		return em.find(type, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findWithNamedQuery(String queryName) {
		return em.createNamedQuery(queryName).getResultList();
	}

	@SuppressWarnings("unchecked")
	public T findWithNamedQuery(String queryName, String name, Object value) {
		T t = null;
		Query query = em.createNamedQuery(queryName).setParameter(name, value);
		try {
			t = (T) query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("No Result Exception.");
		}
		return t;
	}

}
