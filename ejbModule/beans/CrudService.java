package beans;

import java.util.List;

import javax.ejb.Local;

@Local
public interface CrudService<T> {
	
	public T create(T t);

	public T find(Class<T> type, Object id);

	public T update(T t);

	public void delete(Class<T> type, Object id);

	public List<T> findWithNamedQuery(String queryName);
}
