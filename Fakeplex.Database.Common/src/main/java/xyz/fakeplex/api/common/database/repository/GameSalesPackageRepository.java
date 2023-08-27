package xyz.fakeplex.api.common.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.fakeplex.api.common.database.entity.GameSalesPackage;

@Repository
public interface GameSalesPackageRepository extends JpaRepository<GameSalesPackage, Integer> {}
