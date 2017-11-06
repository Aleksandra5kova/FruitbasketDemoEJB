package beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name="crudService")
@LocalBean
public class CrudServiceBean<T> implements CrudService<T> {

	@PersistenceContext(unitName = "FruitbasketDemoEJB")
	EntityManager em;

	public CrudServiceBean() {

	}

	@Override
	public T create(T t) {
		em.persist(t);
		em.flush();
		em.refresh(t);
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

}
