package xyz.fakeplex.api.common.database.entity;

import lombok.Getter;
import lombok.Setter;
import xyz.fakeplex.api.common.database.AccountTables;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = AccountTables.ACCOUNTRANK_TABLE_NAME)
public class AccountRank {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, optional = false)
  @JoinColumn(name = "accountId", nullable = false, referencedColumnName = "id")
  private Account account;

  @Column(name = "rankIdentifier", length = 40)
  private String rankIdentifier;

  @Column(name = "primaryGroup")
  private Boolean primaryGroup;
}
