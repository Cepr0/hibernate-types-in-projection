package io.github.cepr0.demo;

import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@NoArgsConstructor
@Entity
@Table(name = "parents")
@TypeDefs({
		@TypeDef(name = "string-array", typeClass = StringArrayType.class),
		@TypeDef(name = "int-array", typeClass = IntArrayType.class),
		@TypeDef(name = "json", typeClass = JsonStringType.class),
		@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class Parent {
	@Id
	@GeneratedValue(strategy = SEQUENCE)
	private Integer id;

	@Column(length = 32, nullable = false)
	private String name;

	@OneToMany(mappedBy = "parent")
	private List<Child> children;

	@Type(type = "string-array")
	@Column(columnDefinition = "text[]")
	private String[] phones;

	public Parent(String name, String... phones) {
		this.name = name;
		this.phones = phones;
	}
}
