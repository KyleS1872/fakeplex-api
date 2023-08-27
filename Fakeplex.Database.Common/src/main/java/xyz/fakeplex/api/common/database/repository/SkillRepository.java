package xyz.fakeplex.api.common.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import xyz.fakeplex.api.common.database.entity.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {

  @Query(nativeQuery = true, value = "SELECT * FROM Account.skills WHERE name = :name LIMIT 1")
  Skill findSkillByName(@Param("name") String name);
}
