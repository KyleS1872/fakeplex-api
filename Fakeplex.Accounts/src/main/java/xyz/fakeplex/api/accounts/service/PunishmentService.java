package xyz.fakeplex.api.accounts.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.fakeplex.api.accounts.token.punish.AddPunishmentToken;
import xyz.fakeplex.api.accounts.token.punish.RemovePunishmentToken;
import xyz.fakeplex.api.common.database.AccountDatabase;
import xyz.fakeplex.api.common.database.entity.Account;
import xyz.fakeplex.api.common.database.entity.AccountPunishment;
import xyz.fakeplex.api.common.responses.PunishmentResponse;

import java.util.Optional;

/**
 * @author Kyle
 */
@Slf4j
@Service
public class PunishmentService {

  private final AccountDatabase accountDatabase;

  public PunishmentService(AccountDatabase accountDatabase) {
    this.accountDatabase = accountDatabase;
  }

  /**
   * Add a Punishment to an Account
   *
   * @param punishToken AddPunishmentToken
   * @return PunishmentResponse
   */
  public PunishmentResponse punishPlayer(AddPunishmentToken punishToken) {
    try {
      Account target = accountDatabase.getAccountRepository().findOneByName(punishToken.Target);
      if (target == null || target.getId() == -1) return PunishmentResponse.AccountDoesNotExist;

      Account admin = accountDatabase.getAccountRepository().findOneByName(punishToken.Admin);
      if (admin == null || admin.getId() == -1) return PunishmentResponse.NotPunished;

      long time = System.currentTimeMillis() - punishToken.Time;

      AccountPunishment accountPunishment = new AccountPunishment();
      accountPunishment.setTarget(target);
      accountPunishment.setCategory(punishToken.Category);
      accountPunishment.setReason(punishToken.Reason);
      accountPunishment.setAdmin(admin);
      accountPunishment.setTime(time);
      accountPunishment.setDuration(punishToken.Duration);
      accountPunishment.setSentence(punishToken.Sentence);
      accountPunishment.setSeverity(punishToken.Severity);
      accountDatabase.getAccountPunishmentRepository().save(accountPunishment);
    } catch (Exception e) {
      log.error(
          "There was an error punishing " + punishToken.Target + " by " + punishToken.Admin + "!");
      log.error("An error occurred in punishPlayer: {}", e.getMessage(), e);
      return PunishmentResponse.NotPunished;
    }
    return PunishmentResponse.Punished;
  }

  /**
   * Remove an Accounts Punishment
   *
   * @param removePunishToken RemovePunishmentToken
   * @return PunishmentResponse
   */
  public PunishmentResponse removePunishment(RemovePunishmentToken removePunishToken) {
    try {
      Optional<AccountPunishment> punishment =
          accountDatabase.getAccountPunishmentRepository().findById(removePunishToken.PunishmentId);
      if (punishment.isEmpty()) return PunishmentResponse.NotPunished;

      Account admin = accountDatabase.getAccountRepository().findOneByName(removePunishToken.Admin);
      if (admin == null) return PunishmentResponse.NotPunished;

      AccountPunishment removedPunishment = punishment.get();
      removedPunishment.setRemoveAdmin(admin);
      removedPunishment.setRemoveReason(removePunishToken.Reason);
      removedPunishment.setRemoveTime(System.currentTimeMillis());
      accountDatabase.getAccountPunishmentRepository().save(removedPunishment);
    } catch (Exception e) {
      log.error(
          "There was an error removing punishment for "
              + removePunishToken.Target
              + " by "
              + removePunishToken.Admin
              + "!");
      log.error("An error occurred in removePunishment: {}", e.getMessage(), e);
      return PunishmentResponse.NotPunished;
    }
    return PunishmentResponse.PunishmentRemoved;
  }
}
