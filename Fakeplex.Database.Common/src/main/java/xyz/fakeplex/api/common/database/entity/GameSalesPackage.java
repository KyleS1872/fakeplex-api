package xyz.fakeplex.api.common.database.entity;

import lombok.Getter;
import lombok.Setter;
import xyz.fakeplex.api.common.database.AccountTables;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = AccountTables.GAMESALESPACKAGE_TABLE_NAME)
public class GameSalesPackage {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "gems", nullable = false)
  private Integer gems;

  @Column(name = "economy", nullable = false)
  private Integer economy;

  @Column(name = "free", nullable = false)
  private Boolean free;
}
