package xyz.fakeplex.api.accounts.token;

import xyz.fakeplex.api.accounts.token.donation.DonorToken;
import xyz.fakeplex.api.accounts.token.punish.PunishmentToken;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kyle
 */
@SuppressWarnings("unused")
public class ClientToken {

  public int AccountId = -1;
  public String Name;
  public String Rank = "ALL";
  public boolean RankPerm;
  public String RankExpire;

  public long LastLogin;

  public DonorToken DonorToken = new DonorToken();
  public List<PunishmentToken> Punishments = new ArrayList<>();
  public List<Integer> TasksCompleted = new ArrayList<>();

  public long Time = System.currentTimeMillis();
}
