package my.pack;

import java.io.Serializable;

/**
 * Interface that describes the CRUD operations that should be provided by all
 * DAO implementations.
 * 
 * @author kaleksandrov
 * 
 * @param <T>
 *            The type of the managed class (DTO)
 */
public interface Dao<T> extends Serializable {

	/**
	 * Loads the entity with the given identifier.
	 * 
	 * @param id
	 * @return The loaded entity.
	 */
	T read(final int id);

	/**
	 * Removes the given entity.
	 * 
	 * @param entity
	 *            The entity to be removed.
	 */
	void delete(final T entity);

	/**
	 * Updates the given entity to the remote repository. The entity is fully
	 * replaced.
	 * 
	 * @param entity
	 *            The entity to be updated.
	 * @return The updated entity.
	 */
	T update(final T entity);

	/**
	 * Persist the given object to the remote repository.
	 * 
	 * @param nonPersistentObject
	 *            The object to be persisted.
	 * @return The persisted object.
	 */
	T create(final T nonPersistentObject);
}
