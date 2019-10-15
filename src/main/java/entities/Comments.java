//package entities;
//
//import org.springframework.transaction.annotation.Transactional;
//import javax.persistence.Column;
//import javax.validation.constraints.NotNull;
//import java.util.Calendar;
//import java.util.List;
//import javax.persistence.Entity;
//import javax.persistence.EntityManager;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//import javax.persistence.Version;
//import org.springframework.beans.factory.annotation.Configurable;
//import org.springframework.format.annotation.DateTimeFormat;
//import javax.persistence.ManyToOne;
//
//@Entity
//@Configurable
//public class Comments {
//
//	/**
//     */
//	@NotNull
//	@Column(name = "content")
//	private String content;
//
//	/**
//     */
//	@NotNull
//	@Column(name = "shortName")
//	private String shortName;
//
//	/**
//     */
//	@NotNull
//	@Column(name = "creationDate")
//	@Temporal(TemporalType.TIMESTAMP)
//	@DateTimeFormat(style = "M-")
//	private Calendar creationDate;
//
//	/**
//     */
//	@NotNull
//	@Column(name = "id", unique = true)
//	private int id;
//
//	/**
//     */
//	@ManyToOne
//	private Users author;
//
//	// public String toString() {
//	// return ReflectionToStringBuilder.toString(this,
//	// ToStringStyle.SHORT_PREFIX_STYLE);
//	// }
//
//	public String getContent() {
//		return this.content;
//	}
//
//	public void setContent(String content) {
//		this.content = content;
//	}
//
//	public String getShortName() {
//		return this.shortName;
//	}
//
//	public void setShortName(String shortName) {
//		this.shortName = shortName;
//	}
//
//	public Calendar getCreationDate() {
//		return this.creationDate;
//	}
//
//	public void setCreationDate(Calendar creationDate) {
//		this.creationDate = creationDate;
//	}
//
//	public int getId() {
//		return this.id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public Users getAuthor() {
//		return this.author;
//	}
//
//	public void setAuthor(Users author) {
//		this.author = author;
//	}
//
//	@PersistenceContext
//	transient EntityManager entityManager;
//
//	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays
//			.asList("content", "shortName", "creationDate", "id", "author");
//
//	public static final EntityManager entityManager() {
//		EntityManager em = new Comments().entityManager;
//		if (em == null)
//			throw new IllegalStateException(
//					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
//		return em;
//	}
//
//	public static long countCommentses() {
//		return entityManager().createQuery("SELECT COUNT(o) FROM Comments o",
//				Long.class).getSingleResult();
//	}
//
//	public static List<Comments> findAllCommentses() {
//		return entityManager().createQuery("SELECT o FROM Comments o",
//				Comments.class).getResultList();
//	}
//
//	public static List<Comments> findAllCommentses(String sortFieldName,
//			String sortOrder) {
//		String jpaQuery = "SELECT o FROM Comments o";
//		if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
//			jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
//			if ("ASC".equalsIgnoreCase(sortOrder)
//					|| "DESC".equalsIgnoreCase(sortOrder)) {
//				jpaQuery = jpaQuery + " " + sortOrder;
//			}
//		}
//		return entityManager().createQuery(jpaQuery, Comments.class)
//				.getResultList();
//	}
//
//	public static Comments findComments(Long id_) {
//		if (id_ == null)
//			return null;
//		return entityManager().find(Comments.class, id_);
//	}
//
//	public static List<Comments> findCommentsEntries(int firstResult,
//			int maxResults) {
//		return entityManager()
//				.createQuery("SELECT o FROM Comments o", Comments.class)
//				.setFirstResult(firstResult).setMaxResults(maxResults)
//				.getResultList();
//	}
//
//	public static List<Comments> findCommentsEntries(int firstResult,
//			int maxResults, String sortFieldName, String sortOrder) {
//		String jpaQuery = "SELECT o FROM Comments o";
//		if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
//			jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
//			if ("ASC".equalsIgnoreCase(sortOrder)
//					|| "DESC".equalsIgnoreCase(sortOrder)) {
//				jpaQuery = jpaQuery + " " + sortOrder;
//			}
//		}
//		return entityManager().createQuery(jpaQuery, Comments.class)
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
//			Comments attached = Comments.findComments(this.id_);
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
//	public Comments merge() {
//		if (this.entityManager == null)
//			this.entityManager = entityManager();
//		Comments merged = this.entityManager.merge(this);
//		this.entityManager.flush();
//		return merged;
//	}
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "id_")
//	private Long id_;
//
//	@Version
//	@Column(name = "version")
//	private Integer version;
//
//	public Long getId_() {
//		return this.id_;
//	}
//
//	public void setId_(Long id) {
//		this.id_ = id;
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
