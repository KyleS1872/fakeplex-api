package xyz.fakeplex.api.common.database.entity;

import lombok.Getter;
import lombok.Setter;
import xyz.fakeplex.api.common.database.AccountTables;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = AccountTables.ACCOUNTPUNISHMENT_TABLE_NAME)
public class AccountPunishment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "target", nullable = false, referencedColumnName = "id")
  private Account target;

  @Column(name = "category", nullable = false, length = 100)
  private String category;

  @Column(name = "sentence", nullable = false, length = 100)
  private String sentence;

  @Column(name = "reason", nullable = false)
  private String reason;

  @Column(name = "duration", nullable = false)
  private Double duration;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "admin", nullable = false, referencedColumnName = "id")
  private Account admin;

  @Column(name = "severity", nullable = false)
  private Integer severity;

  @Column(name = "time", nullable = false)
  private Long time;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "removeAdmin", referencedColumnName = "id")
  private Account removeAdmin;

  @Column(name = "removeReason")
  private String removeReason;

  @Column(name = "removeTime")
  private Long removeTime;
}
