package xyz.fakeplex.api.common.database.entity;

import lombok.Getter;
import lombok.Setter;
import xyz.fakeplex.api.common.database.AccountTables;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = AccountTables.ACCOUNTPURCHASE_TABLE_NAME)
public class AccountPurchase {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "accountId", nullable = false, referencedColumnName = "id")
  private Account account;

  @Column(name = "salesPackageName", length = 250)
  private String salesPackageName;

  @Column(name = "salesPackageId")
  private Integer salesPackageId;

  @Column(name = "cost")
  private Integer cost;

  @Column(name = "usingCredits")
  private Integer usingCredits;

  @Column(name = "source", length = 30)
  private String source;

  @Column(name = "premium")
  private Integer premium;

  @Column(name = "coinPurchase")
  private Integer coinPurchase;

  @Column(name = "known", nullable = false)
  private Integer known;
}
