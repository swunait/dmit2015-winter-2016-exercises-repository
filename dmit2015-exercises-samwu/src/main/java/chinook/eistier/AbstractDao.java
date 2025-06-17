package chinook.eistier;

import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

@TransactionAttribute(TransactionAttributeType.REQUIRED)
public abstract class AbstractDao<T extends Serializable> implements Serializable {
	private static final long serialVersionUID = 1L;

	private final Class<T> clazz;

    @Inject
    private EntityManager em;

    public AbstractDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T find(Object id) {
        return em.find(clazz, id);
    }

    public void remove(T entity) {
        em.remove(em.merge(entity));
        em.flush();
    }

    public T edit(T entity) {
        T t = em.merge(entity);
        em.flush();
        return t;
    }

    public void persist(final T entity) {
        em.persist(entity);
    }

    public List<T> findAll() {
        final CriteriaQuery<T> criteriaQuery = em.getCriteriaBuilder().createQuery(clazz);
        criteriaQuery.select(criteriaQuery.from(clazz));
        return em.createQuery(criteriaQuery).getResultList();
    }

    public void deleteAll() {
        final CriteriaDelete<T> criteriaDelete = em.getCriteriaBuilder().createCriteriaDelete(clazz);
        criteriaDelete.from(clazz);
        em.createQuery(criteriaDelete).executeUpdate();
    }

    protected EntityManager getEntityManager() {
        return em;
    }

    protected Class<T> getClazz() {
        return clazz;
    }
}
