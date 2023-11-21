package xyz.fakeplex.api.common.database.entity;

import lombok.Getter;
import lombok.Setter;
import xyz.fakeplex.api.common.database.AccountTables;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = AccountTables.ACCOUNTPET_TABLE_NAME)
public class AccountPet {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "petId", nullable = false)
  private Integer id; // TODO: Use Type and Account as Key

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, optional = false)
  @JoinColumn(name = "accountId", nullable = false, referencedColumnName = "id")
  private Account account;

  @Column(name = "petType", nullable = false, length = 50)
  private String petType;

  @Column(name = "petName", length = 50)
  private String petName;
}
