package xyz.fakeplex.api.common.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import xyz.fakeplex.api.common.database.entity.AccountPet;

import java.util.List;

@Repository
public interface AccountPetRepository extends JpaRepository<AccountPet, Integer> {

  @Query(
      nativeQuery = true,
      value = "SELECT * FROM Account.accountPets WHERE accountId = :accountId")
  List<AccountPet> findPetsByAccountId(@Param("accountId") int accountId);

  @Query(
      nativeQuery = true,
      value =
          "SELECT * FROM Account.accountPets WHERE accountId = :accountId AND petType = :petType LIMIT 1")
  AccountPet findPetTypeByAccountId(
      @Param("accountId") int accountId, @Param("petType") String petType);

  @Modifying
  @Transactional
  @Query(
      nativeQuery = true,
      value =
          "UPDATE Account.accountPets SET petName = :petName WHERE accountId = :accountId AND petType = :petType")
  void updatePet(
      @Param("accountId") int accountId,
      @Param("petType") String petType,
      @Param("petName") String petName);

  @Modifying
  @Transactional
  @Query(
      nativeQuery = true,
      value =
          "INSERT INTO Account.accountPets (accountId, petType, petName) VALUES (:accountId, :petType, :petName)")
  void insertPet(
      @Param("accountId") int accountId,
      @Param("petType") String petType,
      @Param("petName") String petName);
}
