package xyz.fakeplex.api.common.database.entity;

import lombok.Getter;
import lombok.Setter;
import xyz.fakeplex.api.common.database.AccountTables;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = AccountTables.ACCOUNTCOINTRANSACTION_TABLE_NAME)
public class AccountCoinTransaction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "accountId", nullable = false, referencedColumnName = "id")
  private Account account;

  @Column(name = "reason", length = 100)
  private String reason;

  @Column(name = "coins")
  private Integer coins;
}
