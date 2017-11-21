package beans;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

@Local
public interface CrudServiceLocal<T> {
	
	public T create(T t);

	public T find(Class<T> type, Object id);

	public T update(T t);

	public void delete(Class<T> type, Object id);

	public List<T> findWithNamedQuery(String queryName);
	
	public T findWithNamedQuery(String queryName, Map<String, String> parameters);
}
