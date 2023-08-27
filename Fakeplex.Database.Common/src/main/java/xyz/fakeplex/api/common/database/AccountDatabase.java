package xyz.fakeplex.api.common.database;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.fakeplex.api.common.database.repository.*;

/**
 * @author Kyle
 */
@Getter
@Component
public class AccountDatabase {

  private final AccountCoinTransactionRepository accountCoinTransactionRepository;
  private final AccountCustomBuildRepository accountCustomBuildRepository;
  private final AccountGemTransactionRepository accountGemTransactionRepository;
  private final AccountPetRepository accountPetRepository;
  private final AccountPunishmentRepository accountPunishmentRepository;
  private final AccountPurchaseRepository accountPurchaseRepository;
  private final AccountRankRepository accountRankRepository;
  private final AccountRepository accountRepository;
  private final AccountTaskRepository accountTaskRepository;
  private final AmplifierRepository amplifierRepository;
  private final GameSalesPackageRepository gameSalesPackageRepository;
  private final SkillRepository skillRepository;

  @Autowired
  public AccountDatabase(
      AccountCoinTransactionRepository accountCoinTransactionRepository,
      AccountCustomBuildRepository accountCustomBuildRepository,
      AccountGemTransactionRepository accountGemTransactionRepository,
      AccountPetRepository accountPetRepository,
      AccountPunishmentRepository accountPunishmentRepository,
      AccountPurchaseRepository accountPurchaseRepository,
      AccountRankRepository accountRankRepository,
      AccountRepository accountRepository,
      AccountTaskRepository accountTaskRepository,
      AmplifierRepository amplifierRepository,
      GameSalesPackageRepository gameSalesPackageRepository,
      SkillRepository skillRepository) {
    this.accountCoinTransactionRepository = accountCoinTransactionRepository;
    this.accountCustomBuildRepository = accountCustomBuildRepository;
    this.accountGemTransactionRepository = accountGemTransactionRepository;
    this.accountPetRepository = accountPetRepository;
    this.accountPunishmentRepository = accountPunishmentRepository;
    this.accountPurchaseRepository = accountPurchaseRepository;
    this.accountRankRepository = accountRankRepository;
    this.accountRepository = accountRepository;
    this.accountTaskRepository = accountTaskRepository;
    this.amplifierRepository = amplifierRepository;
    this.gameSalesPackageRepository = gameSalesPackageRepository;
    this.skillRepository = skillRepository;
  }
}
