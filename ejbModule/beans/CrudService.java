package beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import utils.QueryUtils;

@Stateless
@LocalBean
public class CrudService<T> implements CrudServiceLocal<T> {

	@EJB
	private CrudDaoLocal<T> crudDao;

	public CrudService() {

	}

	@Override
	public T save(T t) {
		return crudDao.save(t);
	}

	@Override
	public T update(T t) {
		return crudDao.update(t);
	}

	@Override
	public void delete(Class<T> type, Object id) {
		crudDao.delete(type, id);
	}

	@Override
	public T findById(Class<T> type, Object id) {
		return crudDao.findById(type, id);
	}

	@Override
	public T findByField(Class<T> type, String name, Object value) {
		String queryName = QueryUtils.generateFindByFieldQuery(type, name);
		return crudDao.findWithNamedQuery(queryName, name, value);
	}

	@Override
	public List<T> findAll(Class<T> type) {
		String queryName = QueryUtils.generateFindAllQuery(type);
		return crudDao.findWithNamedQuery(queryName);
	}

}
