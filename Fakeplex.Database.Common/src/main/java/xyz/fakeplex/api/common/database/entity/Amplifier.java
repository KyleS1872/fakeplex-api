package xyz.fakeplex.api.common.database.entity;

import lombok.Getter;
import lombok.Setter;
import xyz.fakeplex.api.common.database.AccountTables;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = AccountTables.AMPLIFIER_TABLE_NAME)
public class Amplifier {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @Column(name = "uuid", nullable = false, length = 50)
  private String uuid;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, optional = false)
  @JoinColumn(name = "accountId", nullable = false, referencedColumnName = "id")
  private Account account;

  @Column(name = "boosterGroup", nullable = false, length = 50)
  private String boosterGroup;

  @Column(name = "duration", nullable = false)
  private Integer duration;

  @Column(name = "multiplier", nullable = false)
  private Double multiplier;

  @Column(name = "startTime", nullable = false)
  private Timestamp startTime;

  @Column(name = "endTime", nullable = false)
  private Timestamp endTime;

  @Column(name = "activationTime", nullable = false)
  private Timestamp activationTime;
}
