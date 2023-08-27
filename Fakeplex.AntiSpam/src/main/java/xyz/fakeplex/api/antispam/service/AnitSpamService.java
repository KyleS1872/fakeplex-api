package xyz.fakeplex.api.antispam.service;

import org.springframework.stereotype.Service;
import xyz.fakeplex.api.common.database.AccountDatabase;

/**
 * @author Kyle
 */
@SuppressWarnings("FieldCanBeLocal")
@Service
public class AnitSpamService {

  private final AccountDatabase accountDatabase;

  public AnitSpamService(AccountDatabase accountDatabase) {
    this.accountDatabase = accountDatabase;
  }

  public boolean isShadowMuted(String uuid) {
    return false;
  }
}
