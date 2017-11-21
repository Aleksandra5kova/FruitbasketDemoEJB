package beans;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.Map.Entry;

@Stateless(name = "crudService")
@LocalBean
public class CrudServiceBean<T> implements CrudServiceLocal<T> {

	@PersistenceContext(unitName = "FruitbasketDemoEJB")
	EntityManager em;

	public CrudServiceBean() {

	}

	@Override
	public T create(T t) {
		em.persist(t);
		return t;
	}

	@Override
	public T find(Class<T> type, Object id) {
		return em.find(type, id);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findWithNamedQuery(String queryName) {
		return em.createNamedQuery(queryName).getResultList();
	}

	@SuppressWarnings("unchecked")
	public T findWithNamedQuery(String queryName, Map<String, String> parameters) {
		T t = null;
		Set<Entry<String, String>> rawParameters = parameters.entrySet();
		Query query = em.createNamedQuery(queryName);
		for (Entry<String, String> entry : rawParameters) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		try {
			t = (T) query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("No Result Exception.");
		}
		return t;
	}

}
