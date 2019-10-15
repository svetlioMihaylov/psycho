//package entities;
//
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.EntityManager;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Table;
//import javax.persistence.Version;
//import javax.validation.constraints.NotNull;
//
//@Entity
//@Table(name = "users")
//public class Users {
//
//	/**
//     */
//	@NotNull
//	@Column(name = "username", unique = true)
//	private String userName;
//
//	/**
//     */
//	@NotNull
//	@Column(name = "password")
//	private String password;
//
//	/**
//     */
//	@NotNull
//	@Column(name = "fullName")
//	private String fullName;
//
//	/**
//     */
//	@NotNull
//	@Column(name = "email", unique = true)
//	private String email;
//
//	/**
//     */
//	@NotNull
//	@Column(name = "id", unique = true)
//	private int id;
//
//	@PersistenceContext
//	transient EntityManager entityManager;
//
//	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays
//			.asList("userName", "password", "fullName", "email", "id");
//
//	public static final EntityManager entityManager() {
//		EntityManager em = new Users().entityManager;
//		if (em == null)
//			throw new IllegalStateException(
//					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
//		return em;
//	}
//
//	public static long countUserses() {
//		return entityManager().createQuery("SELECT COUNT(o) FROM Users o",
//				Long.class).getSingleResult();
//	}
//
//	public static List<Users> findAllUserses() {
//		return entityManager()
//				.createQuery("SELECT o FROM Users o", Users.class)
//				.getResultList();
//	}
//
//	public static List<Users> findAllUserses(String sortFieldName,
//			String sortOrder) {
//		String jpaQuery = "SELECT o FROM Users o";
//		if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
//			jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
//			if ("ASC".equalsIgnoreCase(sortOrder)
//					|| "DESC".equalsIgnoreCase(sortOrder)) {
//				jpaQuery = jpaQuery + " " + sortOrder;
//			}
//		}
//		return entityManager().createQuery(jpaQuery, Users.class)
//				.getResultList();
//	}
//
//	public static Users findUsers(Long id_) {
//		if (id_ == null)
//			return null;
//		return entityManager().find(Users.class, id_);
//	}
//
//	public static List<Users> findUsersEntries(int firstResult, int maxResults) {
//		return entityManager()
//				.createQuery("SELECT o FROM Users o", Users.class)
//				.setFirstResult(firstResult).setMaxResults(maxResults)
//				.getResultList();
//	}
//
//	public static List<Users> findUsersEntries(int firstResult, int maxResults,
//			String sortFieldName, String sortOrder) {
//		String jpaQuery = "SELECT o FROM Users o";
//		if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
//			jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
//			if ("ASC".equalsIgnoreCase(sortOrder)
//					|| "DESC".equalsIgnoreCase(sortOrder)) {
//				jpaQuery = jpaQuery + " " + sortOrder;
//			}
//		}
//		return entityManager().createQuery(jpaQuery, Users.class)
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
//			Users attached = Users.findUsers(this.id_);
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
//	public Users merge() {
//		if (this.entityManager == null)
//			this.entityManager = entityManager();
//		Users merged = this.entityManager.merge(this);
//		this.entityManager.flush();
//		return merged;
//	}
//
//	public String getUserName() {
//		return this.userName;
//	}
//
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//
//	public String getPassword() {
//		return this.password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String getFullName() {
//		return this.fullName;
//	}
//
//	public void setFullName(String fullName) {
//		this.fullName = fullName;
//	}
//
//	public String getEmail() {
//		return this.email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
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
