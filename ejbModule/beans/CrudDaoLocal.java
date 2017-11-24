package beans;

import java.util.List;

import javax.ejb.Local;

@Local
public interface CrudDaoLocal<T> {

	public T save(T t);

	public T update(T t);

	public void delete(Class<T> type, Object id);

	public T findById(Class<T> type, Object id);

	public T findWithNamedQuery(String queryName, String name, Object value);

	public List<T> findWithNamedQuery(String queryName);

}
