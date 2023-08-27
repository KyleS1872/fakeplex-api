package xyz.fakeplex.api.common.database.entity;

import lombok.Getter;
import lombok.Setter;
import xyz.fakeplex.api.common.database.AccountTables;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = AccountTables.ACCOUNTCLASSSLOT_TABLE_NAME)
public class AccountClassSlot {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "customBuildId", nullable = false, referencedColumnName = "id")
  private AccountCustomBuild customBuild;

  @Column(name = "slotId", nullable = false)
  private Integer slotId;

  @Column(name = "itemName", nullable = false, length = 100)
  private String itemName;

  @Column(name = "material", nullable = false, length = 100)
  private String material;

  @Column(name = "amount", nullable = false)
  private Integer amount;
}
