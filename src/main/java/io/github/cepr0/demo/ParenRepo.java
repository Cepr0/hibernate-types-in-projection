package io.github.cepr0.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ParenRepo extends JpaRepository<Parent, Integer> {

	@Query(value = "" +
			"select " +
			"  p.name as name, " +
			"  json_agg(c.name) as childNames " +
			"from " +
			"  parents p " +
			"  join children c on c.parent_id = p.id " +
			"where " +
			"  p.id = ?1 " +
			"group by " +
			"  p.name" +
			"", nativeQuery = true)
	Optional<ParentProjection> getParentWithChildNamesAsJson(Integer id);

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
}
