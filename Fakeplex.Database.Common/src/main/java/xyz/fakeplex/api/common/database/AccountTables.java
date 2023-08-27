package xyz.fakeplex.api.common.database;

/**
 * @author Kyle
 */
public class AccountTables {

  private AccountTables() {
    throw new IllegalStateException("Utility class");
  }

  public static final String ACCOUNTCLASSSLOT_TABLE_NAME = "accountClassSlots";
  public static final String ACCOUNTCOINTRANSACTION_TABLE_NAME = "accountCoinTransactions";
  public static final String ACCOUNTCUSTOMBUILD_TABLE_NAME = "accountCustomBuilds";
  public static final String ACCOUNTGEMTRANSACTION_TABLE_NAME = "accountGemTransactions";
  public static final String ACCOUNTPET_TABLE_NAME = "accountPets";
  public static final String ACCOUNTPUNISHMENT_TABLE_NAME = "punishments";
  public static final String ACCOUNTPURCHASE_TABLE_NAME = "accountPurchases";
  public static final String ACCOUNTRANK_TABLE_NAME = "accountRanks";
  public static final String ACCOUNTTASK_TABLE_NAME = "accountTasks";

  public static final String ACCOUNT_TABLE_NAME = "accounts";
  public static final String AMPLIFIER_TABLE_NAME = "amplifiers";
  public static final String GAMESALESPACKAGE_TABLE_NAME = "gameSalesPackage";
  public static final String SKILL_TABLE_NAME = "skills";
  public static final String TASK_TABLE_NAME = "tasks";
}
