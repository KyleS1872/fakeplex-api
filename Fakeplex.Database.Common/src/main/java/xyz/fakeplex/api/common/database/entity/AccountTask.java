package xyz.fakeplex.api.common.database.entity;

import lombok.Getter;
import lombok.Setter;
import xyz.fakeplex.api.common.database.AccountTables;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = AccountTables.ACCOUNTTASK_TABLE_NAME)
public class AccountTask {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, optional = false)
  @JoinColumn(name = "accountId", nullable = false, referencedColumnName = "id")
  private Account account;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, optional = false)
  @JoinColumn(name = "taskId", nullable = false, referencedColumnName = "id")
  private Task task;
}
