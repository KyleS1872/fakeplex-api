package xyz.fakeplex.api.accounts.token.donation;

import xyz.fakeplex.api.accounts.token.CustomBuildToken;
import xyz.fakeplex.api.accounts.token.pet.PetToken;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kyle
 */
@SuppressWarnings("unused")
public class DonorToken {

  public int Gems;
  public int Coins;
  public boolean Donated;
  public List<Integer> SalesPackages = new ArrayList<>();
  public List<String> UnknownSalesPackages = new ArrayList<>();
  public List<TransactionToken> Transactions = new ArrayList<>();
  public List<CoinTransactionToken> CoinRewards = new ArrayList<>();
  public List<CustomBuildToken> CustomBuilds = new ArrayList<>();
  public List<PetToken> Pets = new ArrayList<>();
  public int PetNameTagCount;
}
