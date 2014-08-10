package com.undergrowth.bean;

import java.sql.Timestamp;
import java.util.List;

/**
 * Interface for StuInfDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IStuInfDAO {
	/**
	 * Perform an initial save of a previously unsaved StuInf entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IStuInfDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            StuInf entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(StuInf entity);

	/**
	 * Delete a persistent StuInf entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IStuInfDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            StuInf entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(StuInf entity);

	/**
	 * Persist a previously saved StuInf entity and return it or a copy of it to
	 * the sender. A copy of the StuInf entity parameter is returned when the
	 * JPA persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IStuInfDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            StuInf entity to update
	 * @return StuInf the persisted StuInf entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public StuInf update(StuInf entity);

	public StuInf findById(String id);

	/**
	 * Find all StuInf entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the StuInf property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<StuInf> found by query
	 */
	public List<StuInf> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<StuInf> findByDescriptPath(Object descriptPath,
			int... rowStartIdxAndCount);

	/**
	 * Find all StuInf entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<StuInf> all StuInf entities
	 */
	public List<StuInf> findAll(int... rowStartIdxAndCount);
}