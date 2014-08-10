package com.undergrowth.bean;

import java.sql.Timestamp;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.Level;

import com.undergrowth.utils.EntityManagerHelper;

/**
 * A data access object (DAO) providing persistence and search support for
 * StuInf entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see com.undergrowth.bean.StuInf
 * @author MyEclipse Persistence Tools
 */
public class StuInfDAO implements IStuInfDAO {
	// property constants
	public static final String DESCRIPT_PATH = "descriptPath";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

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
	 * StuInfDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            StuInf entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(StuInf entity) {
		EntityManagerHelper.log("saving StuInf instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.INFO, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent StuInf entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * StuInfDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            StuInf entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(StuInf entity) {
		EntityManagerHelper.log("deleting StuInf instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(StuInf.class,
					entity.getId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.INFO, re);
			throw re;
		}
	}

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
	 * entity = StuInfDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            StuInf entity to update
	 * @return StuInf the persisted StuInf entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public StuInf update(StuInf entity) {
		EntityManagerHelper.log("updating StuInf instance", Level.INFO, null);
		try {
			StuInf result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.INFO, re);
			throw re;
		}
	}

	public StuInf findById(String id) {
		EntityManagerHelper.log("finding StuInf instance with id: " + id,
				Level.INFO, null);
		try {
			StuInf instance = getEntityManager().find(StuInf.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.INFO, re);
			throw re;
		}
	}

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
	 *            number of results to return.
	 * @return List<StuInf> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<StuInf> findByProperty(String propertyName, final Object value,
			final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding StuInf instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from StuInf model where model."
					+ propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.INFO, re);
			throw re;
		}
	}

	public List<StuInf> findByDescriptPath(Object descriptPath,
			int... rowStartIdxAndCount) {
		return findByProperty(DESCRIPT_PATH, descriptPath, rowStartIdxAndCount);
	}

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
	@SuppressWarnings("unchecked")
	public List<StuInf> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all StuInf instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from StuInf model";
			Query query = getEntityManager().createQuery(queryString);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.INFO, re);
			throw re;
		}
	}

}