package xyz.fakeplex.api.common.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import xyz.fakeplex.api.common.database.entity.AccountCoinTransaction;

@Repository
public interface AccountCoinTransactionRepository
    extends JpaRepository<AccountCoinTransaction, Integer> {

  @Modifying
  @Transactional
  @Query(
      nativeQuery = true,
      value =
          "INSERT INTO Account.accountCoinTransactions (accountId,reason,coins) VALUES (:accountId, :reason, :amount)")
  void addTransaction(
      @Param("accountId") int accountId,
      @Param("reason") String reason,
      @Param("amount") int amount);
}
