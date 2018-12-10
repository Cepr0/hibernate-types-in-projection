package io.github.cepr0.demo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@NoArgsConstructor
@Entity
@Table(name = "children")
public class Child implements Serializable {

	@Id
	@GeneratedValue(strategy = SEQUENCE)
	private Integer id;

	@Column(length = 32, nullable = false)
	private String name;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Parent parent;

	public Child(String name, Parent parent) {
		this.name = name;
		this.parent = parent;
	}
}
