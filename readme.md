An attempt to use json/array types of [hibernate-types](https://github.com/vladmihalcea/hibernate-types) in Spring Data projections

Projection:

```java
public interface ParentProjection {
	String getName();
	List<String> getChildNames();
}
```

Query:

```java
@Query(value = "" +
        "select " +
        "  p.name as name, " +
        "  array_agg(c.name) as childNames " +
        "from " +
        "  parents p " +
        "  join children c on c.parent_id = p.id " +
        "where " +
        "  p.id = ?1 " +
        "group by " +
        "  p.name" +
        "", nativeQuery = true)
Optional<ParentProjection> getParentWithChildNamesAsArray(Integer id);
```

[Custom Postgres dialect](https://vladmihalcea.com/hibernate-no-dialect-mapping-for-jdbc-type/):

```java
public class PostgreSQLCustomDialect extends PostgreSQL95Dialect {

	public PostgreSQLCustomDialect() {
		super();
		registerHibernateType(Types.OTHER, JsonNodeStringType.class.getName());
		registerHibernateType(Types.ARRAY, StringArrayType.class.getName());
	}
}
```

When PostgreSQL aggregate function `array_agg` is used in the query above, 
then the following exception is raised:

    org.springframework.orm.jpa.JpaSystemException: Could not instantiate Type: com.vladmihalcea.hibernate.type.array.StringArrayType;
    
If `json_agg` function is used, then we have another exception:

    java.lang.IllegalArgumentException: Projection type must be an interface!  