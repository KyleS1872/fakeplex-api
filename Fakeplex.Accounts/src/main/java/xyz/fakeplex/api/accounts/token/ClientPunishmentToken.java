package xyz.fakeplex.api.accounts.token;

import xyz.fakeplex.api.accounts.token.punish.PunishmentToken;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kyle
 */
public class ClientPunishmentToken {

  public String Name;
  public List<PunishmentToken> Punishments = new ArrayList<>();
  public long Time = System.currentTimeMillis();
}
