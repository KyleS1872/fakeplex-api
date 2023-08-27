package xyz.fakeplex.api.common.database.entity;

import lombok.Getter;
import lombok.Setter;
import xyz.fakeplex.api.common.database.AccountTables;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = AccountTables.SKILL_TABLE_NAME)
public class Skill {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "name", nullable = false, length = 100)
  private String name;

  @Column(name = "level", nullable = false)
  private Integer level;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "gameSalesPackageId", referencedColumnName = "id", nullable = false)
  private GameSalesPackage gameSalesPackage;
}
