package beans;

import java.util.List;

import javax.ejb.Local;

@Local
public interface CrudServiceLocal<T> {

	public T save(T t);

	public T update(T t);

	public void delete(Class<T> type, Object id);

	public T findById(Class<T> type, Object id);

	public T findByField(Class<T> type, String name, Object value);

	public List<T> findAll(Class<T> type);

}
