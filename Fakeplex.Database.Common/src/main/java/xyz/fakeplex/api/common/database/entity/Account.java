package xyz.fakeplex.api.common.database.entity;

import lombok.Getter;
import lombok.Setter;
import xyz.fakeplex.api.common.database.AccountTables;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = AccountTables.ACCOUNT_TABLE_NAME)
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "uuid", nullable = false, length = 100)
  private String uuid;

  @Column(name = "name", nullable = false, length = 40)
  private String name;

  @Column(name = "gems", nullable = false)
  private Integer gems;

  @Column(name = "coins", nullable = false)
  private Integer coins;

  @Column(name = "lastLogin")
  private Instant lastLogin;

  @Column(name = "totalPlayTime")
  private String totalPlayTime;
}
