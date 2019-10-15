//package entities;
//
//import org.springframework.beans.factory.annotation.Configurable;
//import org.springframework.transaction.annotation.Transactional;
//import java.util.List;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.EntityManager;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Version;
//import javax.validation.constraints.NotNull;
//
//@Configurable
//@Entity
//public class History {
//
//	/**
//     */
//	@NotNull
//	@Column(name = "columnName")
//	private String columnName;
//
//	/**
//     */
//	@NotNull
//	@Column(name = "oldValue")
//	private String oldValue;
//
//	/**
//     */
//	@NotNull
//	@Column(name = "newValue")
//	private String newValue;
//
//	// public String toString() {
//	// return ReflectionToStringBuilder.toString(this,
//	// ToStringStyle.SHORT_PREFIX_STYLE);
//	// }
//
//	public String getColumnName() {
//		return this.columnName;
//	}
//
//	public void setColumnName(String columnName) {
//		this.columnName = columnName;
//	}
//
//	public String getOldValue() {
//		return this.oldValue;
//	}
//
//	public void setOldValue(String oldValue) {
//		this.oldValue = oldValue;
//	}
//
//	public String getNewValue() {
//		return this.newValue;
//	}
//
//	public void setNewValue(String newValue) {
//		this.newValue = newValue;
//	}
//
//	@PersistenceContext
//	transient EntityManager entityManager;
//
//	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays
//			.asList("columnName", "oldValue", "newValue");
//
//	public static final EntityManager entityManager() {
//		EntityManager em = new History().entityManager;
//		if (em == null)
//			throw new IllegalStateException(
//					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
//		return em;
//	}
//
//	public static long countHistorys() {
//		return entityManager().createQuery("SELECT COUNT(o) FROM History o",
//				Long.class).getSingleResult();
//	}
//
//	public static List<History> findAllHistorys() {
//		return entityManager().createQuery("SELECT o FROM History o",
//				History.class).getResultList();
//	}
//
//	public static List<History> findAllHistorys(String sortFieldName,
//			String sortOrder) {
//		String jpaQuery = "SELECT o FROM History o";
//		if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
//			jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
//			if ("ASC".equalsIgnoreCase(sortOrder)
//					|| "DESC".equalsIgnoreCase(sortOrder)) {
//				jpaQuery = jpaQuery + " " + sortOrder;
//			}
//		}
//		return entityManager().createQuery(jpaQuery, History.class)
//				.getResultList();
//	}
//
//	public static History findHistory(Long id) {
//		if (id == null)
//			return null;
//		return entityManager().find(History.class, id);
//	}
//
//	public static List<History> findHistoryEntries(int firstResult,
//			int maxResults) {
//		return entityManager()
//				.createQuery("SELECT o FROM History o", History.class)
//				.setFirstResult(firstResult).setMaxResults(maxResults)
//				.getResultList();
//	}
//
//	public static List<History> findHistoryEntries(int firstResult,
//			int maxResults, String sortFieldName, String sortOrder) {
//		String jpaQuery = "SELECT o FROM History o";
//		if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
//			jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
//			if ("ASC".equalsIgnoreCase(sortOrder)
//					|| "DESC".equalsIgnoreCase(sortOrder)) {
//				jpaQuery = jpaQuery + " " + sortOrder;
//			}
//		}
//		return entityManager().createQuery(jpaQuery, History.class)
//				.setFirstResult(firstResult).setMaxResults(maxResults)
//				.getResultList();
//	}
//
//	@Transactional
//	public void persist() {
//		if (this.entityManager == null)
//			this.entityManager = entityManager();
//		this.entityManager.persist(this);
//	}
//
//	@Transactional
//	public void remove() {
//		if (this.entityManager == null)
//			this.entityManager = entityManager();
//		if (this.entityManager.contains(this)) {
//			this.entityManager.remove(this);
//		} else {
//			History attached = History.findHistory(this.id);
//			this.entityManager.remove(attached);
//		}
//	}
//
//	@Transactional
//	public void flush() {
//		if (this.entityManager == null)
//			this.entityManager = entityManager();
//		this.entityManager.flush();
//	}
//
//	@Transactional
//	public void clear() {
//		if (this.entityManager == null)
//			this.entityManager = entityManager();
//		this.entityManager.clear();
//	}
//
//	@Transactional
//	public History merge() {
//		if (this.entityManager == null)
//			this.entityManager = entityManager();
//		History merged = this.entityManager.merge(this);
//		this.entityManager.flush();
//		return merged;
//	}
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "id")
//	private Long id;
//
//	@Version
//	@Column(name = "version")
//	private Integer version;
//
//	public Long getId() {
//		return this.id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public Integer getVersion() {
//		return this.version;
//	}
//
//	public void setVersion(Integer version) {
//		this.version = version;
//	}
// }
