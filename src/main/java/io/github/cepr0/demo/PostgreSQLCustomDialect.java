package io.github.cepr0.demo;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import com.vladmihalcea.hibernate.type.json.JsonNodeStringType;
import org.hibernate.dialect.PostgreSQL95Dialect;

import java.sql.Types;

public class PostgreSQLCustomDialect extends PostgreSQL95Dialect {

	public PostgreSQLCustomDialect() {
		super();
		registerHibernateType(Types.OTHER, JsonNodeStringType.class.getName());
		registerHibernateType(Types.ARRAY, StringArrayType.class.getName());
	}
}