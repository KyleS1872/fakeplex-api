package xyz.fakeplex.api.accounts.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.fakeplex.api.accounts.token.ClientPunishmentToken;
import xyz.fakeplex.api.accounts.token.ClientToken;
import xyz.fakeplex.api.accounts.token.CustomBuildToken;
import xyz.fakeplex.api.accounts.token.LoginToken;
import xyz.fakeplex.api.accounts.token.donation.DonorToken;
import xyz.fakeplex.api.accounts.token.donation.PurchaseToken;
import xyz.fakeplex.api.accounts.token.donation.UnknownPurchaseToken;
import xyz.fakeplex.api.accounts.token.punish.PunishmentToken;
import xyz.fakeplex.api.common.database.AccountDatabase;
import xyz.fakeplex.api.common.database.entity.*;
import xyz.fakeplex.api.common.responses.TransactionResponse;
import xyz.fakeplex.api.common.types.Currency;
import xyz.fakeplex.api.common.types.SearchType;
import xyz.fakeplex.api.common.wrappers.Purchases;
import xyz.fakeplex.api.common.wrappers.Rank;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Kyle
 */
@Slf4j
@Service
public class PlayerService {

  private final AccountDatabase accountDatabase;

  private final PetService petService;

  public PlayerService(AccountDatabase accountDatabase, PetService petService) {
    this.accountDatabase = accountDatabase;
    this.petService = petService;
  }

  /**
   * Login Player
   *
   * @param loginToken Login Token
   * @return Client Token
   */
  public ClientToken getClientLoginToken(LoginToken loginToken) {
    ClientToken clientToken = getClientToken(SearchType.UUID, loginToken.Uuid);

    if (clientToken == null) {
      Account account = new Account();
      account.setUuid(loginToken.Uuid);
      account.setName(loginToken.Name);
      account.setGems(0);
      account.setCoins(0);
      account.setLastLogin(Instant.now());
      accountDatabase.getAccountRepository().save(account);

      clientToken = new ClientToken();
      clientToken.AccountId = account.getId();
      clientToken.Name = loginToken.Name;
      return clientToken;
    }

    return clientToken;
  }

  /**
   * Retrieves Client Token associated to an Account
   *
   * @param searchType Type of Search
   * @param value Search Value
   * @return Client Token
   */
  public ClientToken getClientToken(SearchType searchType, String value) {
    ClientToken clientToken = new ClientToken();

    Optional<Account> searchAccount = searchAccount(searchType, value);
    if (searchAccount.isEmpty()) return null;
    Account account = searchAccount.get();

    clientToken.AccountId = account.getId();
    clientToken.Name = account.getName();

    AccountRank accountRank =
        accountDatabase.getAccountRankRepository().findPrimaryByAccountId(account.getId());
    Rank rank = new Rank();

    if (accountRank != null) {
      rank.RankId = accountRank.getId();
      rank.Name = accountRank.getRankIdentifier();
    }

    clientToken.Rank = rank.Name;
    clientToken.RankPerm = false;

    clientToken.LastLogin =
        account.getLastLogin() == null
            ? System.currentTimeMillis()
            : account.getLastLogin().getEpochSecond();

    clientToken.DonorToken = getDonorToken(account);
    clientToken.Punishments = getAccountPunishments(account);
    clientToken.TasksCompleted = getAccountTasksCompleted(account);

    clientToken.Time = System.currentTimeMillis();

    return clientToken;
  }

  /**
   * Retrieves Donor Token associated to an Account
   *
   * @param account Players Account
   * @return Donor Token
   */
  public DonorToken getDonorToken(Account account) {
    DonorToken donorToken = new DonorToken();

    donorToken.Gems = account.getGems();
    donorToken.Coins = account.getCoins();
    donorToken.Donated = false;

    Purchases purchases = getAccountPurchases(account);
    donorToken.SalesPackages = purchases.getKnown();
    donorToken.UnknownSalesPackages = purchases.getUnknown();

    donorToken.CustomBuilds = getCustomBuilds(account);
    donorToken.Pets = petService.getAccountPets(account);
    donorToken.PetNameTagCount = donorToken.Pets.size();

    return donorToken;
  }

  /**
   * Retrieves Client Punishments associated to an Account
   *
   * @param searchType Type of Search
   * @param value Search Value
   * @return Client Token
   */
  public ClientPunishmentToken getPunishToken(SearchType searchType, String value) {
    ClientPunishmentToken punishToken = new ClientPunishmentToken();

    Optional<Account> searchAccount = searchAccount(searchType, value);
    if (searchAccount.isEmpty()) return null;
    Account account = searchAccount.get();

    punishToken.Name = account.getName();

    punishToken.Punishments = getAccountPunishments(account);

    punishToken.Time = System.currentTimeMillis();

    return punishToken;
  }

  /**
   * Search for Accounts with a similar name
   *
   * @param name Name to search for
   * @return List of similar Account names
   */
  public List<String> getMatches(String name) {
    List<String> list = new ArrayList<>();

    List<Account> accounts =
        accountDatabase.getAccountRepository().findByNameMatches(name.toLowerCase());

    accounts.forEach(accountsRecord -> list.add(accountsRecord.getName()));

    return list;
  }

  /**
   * Retrieves Known and Unknown Purchases associated to an Account
   *
   * @param account Players Account
   * @return Purchases
   */
  private Purchases getAccountPurchases(Account account) {
    List<AccountPurchase> purchases =
        accountDatabase.getAccountPurchaseRepository().findPurchasesByAccountId(account.getId());
    Iterator<AccountPurchase> purchasesIterator = purchases.iterator();

    ArrayList<Integer> known = new ArrayList<>();
    ArrayList<String> unknown = new ArrayList<>();

    while (purchasesIterator.hasNext()) {
      AccountPurchase accountPurchases = purchasesIterator.next();
      String salesPackageName = accountPurchases.getSalesPackageName();
      Object salesPackageId = accountPurchases.getSalesPackageId();
      boolean isKnown = accountPurchases.getKnown() == 1;

      try {
        if (isKnown) known.add((int) salesPackageId);
        else unknown.add(salesPackageName);
      } catch (Exception e) {
        log.debug(e.getMessage());
      }
    }

    return new Purchases(known, unknown);
  }

  /**
   * Adds purchase to a Players Account
   *
   * @param purchaseToken Purchase Details
   * @return Transaction Response
   */
  public TransactionResponse purchaseKnownSalesPackage(PurchaseToken purchaseToken) {
    Optional<Account> searchAccount = searchAccount(SearchType.USERNAME, purchaseToken.AccountName);
    if (searchAccount.isEmpty()) return TransactionResponse.Failed;
    Account account = searchAccount.get();

    try {
      if (!accountDatabase
          .getAccountPurchaseRepository()
          .getOwnedByPackageId(account.getId(), purchaseToken.SalesPackageId)
          .isEmpty()) return TransactionResponse.AlreadyOwns;

      accountDatabase
          .getAccountPurchaseRepository()
          .insertPurchase(
              account.getId(), purchaseToken.SalesPackageId, purchaseToken.UsingCredits ? 1 : 0, 1);
    } catch (Exception e) {
      log.debug(e.getMessage());
      return TransactionResponse.Failed;
    }

    return TransactionResponse.Success;
  }

  /**
   * Adds unknown purchase to a Players Account
   *
   * @param unknownPurchaseToken Purchase Details
   * @return Transaction Response
   */
  public TransactionResponse purchaseUnknownSalesPackage(
      UnknownPurchaseToken unknownPurchaseToken) {
    Optional<Account> searchAccount =
        searchAccount(SearchType.USERNAME, unknownPurchaseToken.AccountName);
    if (searchAccount.isEmpty()) return TransactionResponse.Failed;
    Account account = searchAccount.get();

    if ((unknownPurchaseToken.CoinPurchase && (unknownPurchaseToken.Cost > account.getCoins()))
        || (!unknownPurchaseToken.CoinPurchase && (unknownPurchaseToken.Cost > account.getGems())))
      return TransactionResponse.InsufficientFunds;

    try {
      if (!accountDatabase
          .getAccountPurchaseRepository()
          .getOwnedByPackageName(account.getId(), unknownPurchaseToken.SalesPackageName)
          .isEmpty()) return TransactionResponse.AlreadyOwns;

      if (unknownPurchaseToken.CoinPurchase)
        accountDatabase
            .getAccountRepository()
            .addShards(account.getId(), -unknownPurchaseToken.Cost);
      else
        accountDatabase.getAccountRepository().addGems(account.getId(), -unknownPurchaseToken.Cost);

      accountDatabase
          .getAccountPurchaseRepository()
          .insertUnknownPurchase(
              account.getId(),
              unknownPurchaseToken.SalesPackageName,
              unknownPurchaseToken.Cost,
              unknownPurchaseToken.Premium ? 1 : 0,
              unknownPurchaseToken.CoinPurchase ? 1 : 0,
              0);
    } catch (Exception e) {
      log.debug(e.getMessage());
      return TransactionResponse.Failed;
    }

    return TransactionResponse.Success;
  }

  /**
   * Retrieves Punishments associated to an Account
   *
   * @param account Players Account
   * @return List of Punishments
   */
  private List<PunishmentToken> getAccountPunishments(Account account) {
    List<AccountPunishment> punishments =
        accountDatabase.getAccountPunishmentRepository().findPunishmentsById(account.getId());

    List<PunishmentToken> punishmentTokens = new ArrayList<>();

    for (AccountPunishment accountPunishment : punishments) {
      PunishmentToken punishmentToken = new PunishmentToken();
      punishmentToken.PunishmentId = accountPunishment.getId();
      punishmentToken.Admin = accountPunishment.getAdmin().getName();
      punishmentToken.Time = accountPunishment.getTime();
      punishmentToken.Sentence = accountPunishment.getSentence();
      punishmentToken.Category = accountPunishment.getCategory();
      punishmentToken.Reason = accountPunishment.getReason();
      punishmentToken.Severity = accountPunishment.getSeverity();
      punishmentToken.Duration = accountPunishment.getDuration();
      punishmentToken.Removed =
          accountPunishment.getRemoveAdmin() != null
              && !accountPunishment.getRemoveAdmin().getName().equalsIgnoreCase("");
      punishmentToken.RemoveAdmin =
          accountPunishment.getRemoveAdmin() != null
              ? accountPunishment.getRemoveAdmin().getName()
              : null;
      punishmentToken.RemoveReason = accountPunishment.getRemoveReason();

      boolean active = true;
      long time = accountPunishment.getTime();
      double duration = accountPunishment.getDuration();

      if (((time + (duration * 3600000)) < System.currentTimeMillis()) && duration != -1)
        active = false;

      if (accountPunishment.getRemoveAdmin() != null
          && !accountPunishment.getRemoveAdmin().getName().equalsIgnoreCase("")) active = false;

      punishmentToken.Active = active;

      punishmentTokens.add(punishmentToken);
    }

    return punishmentTokens;
  }

  /**
   * Retrieves Completed Task IDs associated to an Account
   *
   * @param account Players Account
   * @return List of Completed Task IDs
   */
  private List<Integer> getAccountTasksCompleted(Account account) {
    return accountDatabase.getAccountTaskRepository().getAccountTasks(account.getId()).stream()
        .map(AccountTask::getId)
        .collect(Collectors.toList());
  }

  /**
   * Adds Currency to Players Account
   *
   * @param type Currency Type
   * @param name Players Name
   * @param source Source of Reward
   * @param amount Amount to Reward
   * @return Successfully Rewarded
   */
  public boolean giveReward(Currency type, String name, String source, int amount) {

    ClientToken clientToken = getClientToken(SearchType.USERNAME, name);

    if (clientToken == null) return false;

    int res = 0;

    switch (type) {
      case GEMS:
        res = accountDatabase.getAccountRepository().addGems(clientToken.AccountId, amount);
        break;
      case SHARDS:
        res = accountDatabase.getAccountRepository().addShards(clientToken.AccountId, amount);
        break;
    }

    if (res != 1) return false;

    if (source == null
        || source.contains("Earned")
        || source.contains("Tutorial")
        || source.contains("Parkour")) return true;

    switch (type) {
      case GEMS:
        accountDatabase
            .getAccountGemTransactionRepository()
            .addTransaction(clientToken.AccountId, source, amount);
        break;
      case SHARDS:
        accountDatabase
            .getAccountCoinTransactionRepository()
            .addTransaction(clientToken.AccountId, source, amount);
        break;
    }

    return true;
  }

  /**
   * Adds Kits to a Players Account
   *
   * @param accountId Players Account ID
   * @return Successfully Given
   */
  public boolean applyKits(int accountId) {
    ArrayList<String> kits = getAllKits();

    try {
      kits.forEach(
          kit -> {
            if (accountDatabase
                .getAccountPurchaseRepository()
                .getOwnedByPackageName(accountId, kit)
                .isEmpty())
              accountDatabase
                  .getAccountPurchaseRepository()
                  .insertUnknownPurchase(accountId, kit, 0, 0, 0, 0);
          });
    } catch (Exception e) {
      log.debug(e.getMessage());
      return false;
    }

    return true;
  }

  /**
   * Retrieves kit names used in the database
   *
   * @return List of Kits Names
   */
  private ArrayList<String> getAllKits() {
    ArrayList<String> kits = new ArrayList<>();
    kits.add("Bacon Brawl Bebe Piggles");
    kits.add("Bacon Brawl `Pig`");
    kits.add("A Barbarians Life Barbarian Archer");
    kits.add("A Barbarians Life Bomber");
    kits.add("The Bridges Archer");
    kits.add("The Bridges Bomber");
    kits.add("The Bridges Brawler");
    kits.add("The Bridges Miner");
    kits.add("Castle Siege Castle Assassin");
    kits.add("Castle Siege Castle Brawler");
    kits.add("Castle Siege Castle Knight");
    kits.add("Castle Siege Undead Archer");
    kits.add("Castle Siege Undead Zombie");
    kits.add("Death Tag Runner Archer");
    kits.add("Death Tag Runner Traitor");
    kits.add("Dragon Escape Disruptor");
    kits.add("Dragon Escape Warper");
    kits.add("Dragons Marksman");
    kits.add("Dragons Pyrotechnic");
    kits.add("Block Hunt Instant Hider");
    kits.add("Block Hunt Shocking Hider");
    kits.add("Block Hunt Radar Hunter");
    kits.add("Block Hunt TNT Hunter");
    kits.add("Super Paintball Machine Gun");
    kits.add("Super Paintball Shotgun");
    kits.add("One in the Quiver Brawler");
    kits.add("One in the Quiver Enchanter");
    kits.add("Runner Archer");
    kits.add("Runner Frosty");
    kits.add("Sheep Quest Archer");
    kits.add("Sheep Quest Brute");
    kits.add("Super Smash Mobs Blaze");
    kits.add("Super Smash Mobs Chicken");
    kits.add("Super Smash Mobs Mad Cow");
    kits.add("Super Smash Mobs Creeper");
    kits.add("Super Smash Mobs Enderman");
    kits.add("Super Smash Mobs Undead Knight");
    kits.add("Super Smash Mobs Magma Cube");
    kits.add("Super Smash Mobs Pig");
    kits.add("Super Smash Mobs Skeletal Horse");
    kits.add("Super Smash Mobs Sky Squid");
    kits.add("Super Smash Mobs Snowman");
    kits.add("Super Smash Mobs Witch");
    kits.add("Super Smash Mobs Wither");
    kits.add("Super Smash Mobs Wither Skeleton");
    kits.add("Super Smash Mobs Wolf");
    kits.add("Snake Super Snake");
    kits.add("Snake Other Snake");
    kits.add("Sneaky Assassins Ranged Assassin");
    kits.add("Sneaky Assassins Revealer");
    kits.add("Super Spleef Archer");
    kits.add("Super Spleef Brawler");
    kits.add("Squid Shooter Squid Blaster");
    kits.add("Squid Shooter Squid Sniper");
    kits.add("Survival Games Archer");
    kits.add("Survival Games Assassin");
    kits.add("Survival Games Beastmaster");
    kits.add("Survival Games Bomber");
    kits.add("Survival Games Brawler");
    kits.add("Survival Games Necromancer");
    kits.add("Turf Wars Infiltrator");
    kits.add("Turf Wars Shredder");
    kits.add("Zombie Survival Survivor Archer");
    kits.add("Zombie Survival Survivor Rogue");
    return kits;
  }

  /**
   * Retrieves Custom Builds associated to an Account
   *
   * @param account Players Account
   * @return List of Custom Builds
   */
  public List<CustomBuildToken> getCustomBuilds(Account account) {
    List<AccountCustomBuild> builds =
        accountDatabase.getAccountCustomBuildRepository().getCustomBuilds(account.getId());

    if (builds.isEmpty()) return new ArrayList<>();

    List<CustomBuildToken> customBuildTokens = new ArrayList<>();
    builds.forEach(customBuild -> customBuildTokens.add(generateCustomBuildToken(customBuild)));

    return customBuildTokens;
  }

  /**
   * Generate Custom Build Token from Database Object
   *
   * @param customBuild Database Object
   * @return Token Object
   */
  private CustomBuildToken generateCustomBuildToken(AccountCustomBuild customBuild) {
    CustomBuildToken customBuildToken = new CustomBuildToken();
    customBuildToken.CustomBuildId = customBuild.getId();
    customBuildToken.PlayerName = customBuild.getAccount().getName();
    customBuildToken.Name = customBuild.getClassName();
    customBuildToken.Active = customBuild.getActive();
    customBuildToken.CustomBuildNumber = customBuild.getBuildNumber();
    customBuildToken.PvpClass = customBuild.getPvpClass();
    customBuildToken.SwordSkill = customBuild.getSwordSkill();
    customBuildToken.SwordSkillLevel = customBuild.getSwordSkillLevel();
    customBuildToken.AxeSkill = customBuild.getAxeSkill();
    customBuildToken.AxeSkillLevel = customBuild.getAxeSkillLevel();
    customBuildToken.BowSkill = customBuild.getBowSkill();
    customBuildToken.BowSkillLevel = customBuild.getBowSkillLevel();
    customBuildToken.ClassPassiveASkill = customBuild.getClassPassiveASkill();
    customBuildToken.ClassPassiveASkillLevel = customBuild.getClassPassiveASkillLevel();
    customBuildToken.ClassPassiveBSkill = customBuild.getClassPassiveBSkill();
    customBuildToken.ClassPassiveBSkillLevel = customBuild.getClassPassiveBSkillLevel();
    customBuildToken.GlobalPassiveSkill = customBuild.getGlobalPassiveSkill();
    customBuildToken.GlobalPassiveSkillLevel = customBuild.getGlobalPassiveSkillLevel();
    customBuildToken.SkillTokens = customBuild.getSkillTokens();
    customBuildToken.ItemTokens = customBuild.getItemTokens();

    List<CustomBuildToken.SlotToken> slots = new ArrayList<>(9);

    try {
      for (int i = 1; i <= 9; i++) {
        String slotName =
            (String) customBuild.getClass().getMethod("getSlot" + i + "Name").invoke(customBuild);
        String slotMaterial =
            (String)
                customBuild.getClass().getMethod("getSlot" + i + "Material").invoke(customBuild);
        int slotAmount =
            (int) customBuild.getClass().getMethod("getSlot" + i + "Amount").invoke(customBuild);

        slots.add(new CustomBuildToken.SlotToken(slotName, slotMaterial, slotAmount));
      }
    } catch (Exception e) {
      log.debug(e.getMessage());
    }

    customBuildToken.Slots = slots;

    return customBuildToken;
  }

  /**
   * Save a Custom Build
   *
   * @param cb Custom Build
   * @return Successful
   */
  public boolean saveClass(CustomBuildToken cb) {
    if (cb == null) return false;

    Account account = accountDatabase.getAccountRepository().findOneByName(cb.PlayerName);

    if (account == null) return false;

    AccountCustomBuild classRecord =
        accountDatabase
            .getAccountCustomBuildRepository()
            .getCustomBuild(account.getId(), cb.CustomBuildNumber, cb.PvpClass);

    if (classRecord != null) {
      accountDatabase
          .getAccountCustomBuildRepository()
          .updateCustomBuild(
              account.getId(),
              cb.Name,
              cb.Active,
              cb.CustomBuildNumber,
              cb.PvpClass,
              cb.SwordSkill,
              cb.SwordSkillLevel,
              cb.AxeSkill,
              cb.AxeSkillLevel,
              cb.BowSkill,
              cb.BowSkillLevel,
              cb.ClassPassiveASkill,
              cb.ClassPassiveASkillLevel,
              cb.ClassPassiveBSkill,
              cb.ClassPassiveBSkillLevel,
              cb.GlobalPassiveSkill,
              cb.GlobalPassiveSkillLevel,
              cb.SkillTokens,
              cb.ItemTokens,
              cb.Slots.get(0).Name,
              cb.Slots.get(0).Material,
              cb.Slots.get(0).Amount,
              cb.Slots.get(1).Name,
              cb.Slots.get(1).Material,
              cb.Slots.get(1).Amount,
              cb.Slots.get(2).Name,
              cb.Slots.get(2).Material,
              cb.Slots.get(2).Amount,
              cb.Slots.get(3).Name,
              cb.Slots.get(3).Material,
              cb.Slots.get(3).Amount,
              cb.Slots.get(4).Name,
              cb.Slots.get(4).Material,
              cb.Slots.get(4).Amount,
              cb.Slots.get(5).Name,
              cb.Slots.get(5).Material,
              cb.Slots.get(5).Amount,
              cb.Slots.get(6).Name,
              cb.Slots.get(6).Material,
              cb.Slots.get(6).Amount,
              cb.Slots.get(7).Name,
              cb.Slots.get(7).Material,
              cb.Slots.get(7).Amount,
              cb.Slots.get(8).Name,
              cb.Slots.get(8).Material,
              cb.Slots.get(8).Amount);
    } else {
      accountDatabase
          .getAccountCustomBuildRepository()
          .insertCustomBuild(
              account.getId(),
              cb.Name,
              cb.Active,
              cb.CustomBuildNumber,
              cb.PvpClass,
              cb.SwordSkill,
              cb.SwordSkillLevel,
              cb.AxeSkill,
              cb.AxeSkillLevel,
              cb.BowSkill,
              cb.BowSkillLevel,
              cb.ClassPassiveASkill,
              cb.ClassPassiveASkillLevel,
              cb.ClassPassiveBSkill,
              cb.ClassPassiveBSkillLevel,
              cb.GlobalPassiveSkill,
              cb.GlobalPassiveSkillLevel,
              cb.SkillTokens,
              cb.ItemTokens,
              cb.Slots.get(0).Name,
              cb.Slots.get(0).Material,
              cb.Slots.get(0).Amount,
              cb.Slots.get(1).Name,
              cb.Slots.get(1).Material,
              cb.Slots.get(1).Amount,
              cb.Slots.get(2).Name,
              cb.Slots.get(2).Material,
              cb.Slots.get(2).Amount,
              cb.Slots.get(3).Name,
              cb.Slots.get(3).Material,
              cb.Slots.get(3).Amount,
              cb.Slots.get(4).Name,
              cb.Slots.get(4).Material,
              cb.Slots.get(4).Amount,
              cb.Slots.get(5).Name,
              cb.Slots.get(5).Material,
              cb.Slots.get(5).Amount,
              cb.Slots.get(6).Name,
              cb.Slots.get(6).Material,
              cb.Slots.get(6).Amount,
              cb.Slots.get(7).Name,
              cb.Slots.get(7).Material,
              cb.Slots.get(7).Amount,
              cb.Slots.get(8).Name,
              cb.Slots.get(8).Material,
              cb.Slots.get(8).Amount);
    }

    return true;
  }

  /**
   * Search for an Account
   *
   * @param searchType Type of Search
   * @param value Search Value
   * @return Account
   */
  private Optional<Account> searchAccount(SearchType searchType, String value) {
    Optional<Account> account = Optional.empty();
    switch (searchType) {
      case UUID:
        account = Optional.ofNullable(accountDatabase.getAccountRepository().findOneByUuid(value));
        break;
      case USERNAME:
        account = Optional.ofNullable(accountDatabase.getAccountRepository().findOneByName(value));
        break;
      default:
        log.debug("Unknown searchType provided: " + searchType);
        break;
    }
    return account;
  }
}
