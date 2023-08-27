package xyz.fakeplex.api.common.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import xyz.fakeplex.api.common.database.entity.Account;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

  @Query(nativeQuery = true, value = "SELECT * FROM Account.accounts WHERE id =:id LIMIT 1")
  Account findOneByAccountId(@Param("id") int id);

  @Query(nativeQuery = true, value = "SELECT * FROM Account.accounts WHERE uuid =:uuid LIMIT 1")
  Account findOneByUuid(@Param("uuid") String uuid);

  @Query(
      nativeQuery = true,
      value = "SELECT * FROM Account.accounts WHERE name =:name ORDER BY lastLogin DESC LIMIT 1")
  Account findOneByName(@Param("name") String name);

  @Query(
      nativeQuery = true,
      value =
          "SELECT a.* FROM Account.accounts a INNER JOIN (SELECT LOWER(name) AS name, MAX(lastLogin) AS max_last_login FROM Account.accounts WHERE name LIKE :name% GROUP BY LOWER(NAME)) b ON LOWER(a.name) = b.name AND a.lastLogin = b.max_last_login ORDER BY a.name ASC")
  List<Account> findByNameMatches(@Param("name") String name);

  @Modifying
  @Transactional
  @Query(
      nativeQuery = true,
      value =
          "INSERT INTO Account.accounts (uuid,name,gems,coins,lastLogin) VALUES (:uuid, :name, 0, 0, NOW())")
  void insertAccount(@Param("uuid") String uuid, @Param("name") String name);

  @Modifying
  @Transactional
  @Query(
      nativeQuery = true,
      value = "UPDATE Account.accounts SET gems = gems + :amount WHERE id = :id")
  int addGems(@Param("id") int id, @Param("amount") int amount);

  @Modifying
  @Transactional
  @Query(
      nativeQuery = true,
      value = "UPDATE Account.accounts SET coins = coins + :amount WHERE id = :id")
  int addShards(@Param("id") int id, @Param("amount") int amount);
}
