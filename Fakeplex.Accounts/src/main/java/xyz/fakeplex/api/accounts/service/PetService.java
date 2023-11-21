package xyz.fakeplex.api.accounts.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.fakeplex.api.accounts.token.pet.PetChangeToken;
import xyz.fakeplex.api.accounts.token.pet.PetToken;
import xyz.fakeplex.api.common.database.AccountDatabase;
import xyz.fakeplex.api.common.database.entity.Account;
import xyz.fakeplex.api.common.database.entity.AccountPet;
import xyz.fakeplex.api.common.responses.CustomResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kyle
 */
@Slf4j
@Service
public class PetService {

  private final AccountDatabase accountDatabase;

  public PetService(AccountDatabase accountDatabase) {
    this.accountDatabase = accountDatabase;
  }

  /**
   * Retrieves List of Accounts Pets
   *
   * @param account Players Account
   * @return List of PetToken
   */
  public List<PetToken> getAccountPets(Account account) {
    List<AccountPet> pets =
        accountDatabase.getAccountPetRepository().findPetsByAccountId(account.getId());

    List<PetToken> petTokens = new ArrayList<>();

    for (AccountPet accountPets : pets) {
      PetToken petToken = new PetToken();
      petToken.PetName = accountPets.getPetName();
      petToken.PetType = accountPets.getPetType();
      petTokens.add(petToken);
    }

    return petTokens;
  }

  /**
   * Add or Update a Pet to an Account
   *
   * @param petChangeToken Pet Token
   * @return Custom Response
   */
  public CustomResponse changePet(PetChangeToken petChangeToken) {
    Account account =
        accountDatabase.getAccountRepository().findOneByAccountId(petChangeToken.AccountId);

    if (account == null) return CustomResponse.Failed;

    AccountPet pet =
        accountDatabase
            .getAccountPetRepository()
            .findPetTypeByAccountId(account.getId(), petChangeToken.PetType);

    try {
      if (pet == null)
        accountDatabase
            .getAccountPetRepository()
            .insertPet(account.getId(), petChangeToken.PetType, petChangeToken.PetName);
      else
        accountDatabase
            .getAccountPetRepository()
            .updatePet(petChangeToken.AccountId, petChangeToken.PetType, petChangeToken.PetName);
    } catch (Exception e) {
      log.debug(e.getMessage());
      return CustomResponse.Failed;
    }

    return CustomResponse.Success;
  }
}
