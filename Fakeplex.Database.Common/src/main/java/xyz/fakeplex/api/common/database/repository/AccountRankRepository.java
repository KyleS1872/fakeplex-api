package xyz.fakeplex.api.common.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import xyz.fakeplex.api.common.database.entity.AccountRank;

@Repository
public interface AccountRankRepository extends JpaRepository<AccountRank, Integer> {

  @Query(
      nativeQuery = true,
      value =
          "SELECT * FROM Account.accountRanks WHERE accountId =:id AND primaryGroup = true LIMIT 1")
  AccountRank findPrimaryByAccountId(@Param("id") int id);
}
