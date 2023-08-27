package xyz.fakeplex.api.common.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import xyz.fakeplex.api.common.database.entity.AccountTask;

import java.util.List;

@Repository
public interface AccountTaskRepository extends JpaRepository<AccountTask, Integer> {

  @Query(nativeQuery = true, value = "SELECT * FROM Account.accountTasks WHERE accountId =:id")
  List<AccountTask> getAccountTasks(@Param("id") int id);
}
