package xyz.fakeplex.api.common.database.entity;

import lombok.Getter;
import lombok.Setter;
import xyz.fakeplex.api.common.database.AccountTables;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = AccountTables.ACCOUNTGEMTRANSACTION_TABLE_NAME)
public class AccountGemTransaction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  @JoinColumn(name = "accountId", referencedColumnName = "id")
  private Account account;

  @Column(name = "reason", length = 100)
  private String reason;

  @Column(name = "gems")
  private Integer gems;
}
