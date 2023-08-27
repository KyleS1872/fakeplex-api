package xyz.fakeplex.api.accounts.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.fakeplex.api.accounts.service.PlayerService;
import xyz.fakeplex.api.accounts.service.PunishmentService;
import xyz.fakeplex.api.accounts.token.ClientPunishmentToken;
import xyz.fakeplex.api.accounts.token.ClientToken;
import xyz.fakeplex.api.accounts.token.CustomBuildToken;
import xyz.fakeplex.api.accounts.token.LoginToken;
import xyz.fakeplex.api.accounts.token.donation.CurrencyRewardToken;
import xyz.fakeplex.api.accounts.token.donation.PurchaseToken;
import xyz.fakeplex.api.accounts.token.donation.UnknownPurchaseToken;
import xyz.fakeplex.api.accounts.token.punish.AddPunishmentToken;
import xyz.fakeplex.api.accounts.token.punish.RemovePunishmentToken;
import xyz.fakeplex.api.common.responses.CustomResponse;
import xyz.fakeplex.api.common.responses.PunishmentResponse;
import xyz.fakeplex.api.common.responses.TransactionResponse;
import xyz.fakeplex.api.common.types.Currency;
import xyz.fakeplex.api.common.types.SearchType;
import xyz.fakeplex.api.common.util.MinecraftUtils;
import xyz.fakeplex.api.common.util.ResourceUrls;

import java.util.ArrayList;

/**
 * @author Kyle
 */
@RestController
@RequestMapping(ResourceUrls.ACCOUNTS_PLAYER_ACCOUNT_URI)
public class PlayerAccountController {

  private final PlayerService playerService;
  private final PunishmentService punishmentService;

  public PlayerAccountController(PlayerService playerService, PunishmentService punishmentService) {
    this.playerService = playerService;
    this.punishmentService = punishmentService;
  }

  /*
      Account Related Endpoints
  */

  @PostMapping(
      value = "/Login",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ClientToken> postLogin(@RequestBody LoginToken loginToken) {

    if (!MinecraftUtils.isValidUUID(loginToken.Uuid)
        || !MinecraftUtils.isValidUsername(loginToken.Name))
      return ResponseEntity.badRequest().build();

    ClientToken clientToken = playerService.getClientLoginToken(loginToken);

    if (clientToken != null) return ResponseEntity.ok(clientToken);

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
  }

  @PostMapping(
      value = "/GetAccountByUUID",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ClientToken> postGetAccountByUUID(@RequestBody String uuid) {
    uuid = uuid.replace("\"", "");

    if (!MinecraftUtils.isValidUUID(uuid)) return ResponseEntity.badRequest().build();

    ClientToken clientToken = playerService.getClientToken(SearchType.UUID, uuid);

    if (clientToken != null) return ResponseEntity.ok(clientToken);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
  }

  @PostMapping(
      value = "/GetAccount",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ClientToken> postGetAccount(@RequestBody String name) {
    name = name.replace("\"", "");

    if (!MinecraftUtils.isValidUsername(name)) return ResponseEntity.badRequest().build();

    ClientToken clientToken = playerService.getClientToken(SearchType.USERNAME, name);

    if (clientToken != null) return ResponseEntity.ok(clientToken);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
  }

  @PostMapping(
      value = "/GetMatches",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity<String> postGetMatches(@RequestBody String name) {
    name = name.replace("\"", "");

    if (!MinecraftUtils.isValidUsername(name) || name.contains("'"))
      return ResponseEntity.ok(new ArrayList<String>().toString());

    return ResponseEntity.ok(playerService.getMatches(name).toString());
  }

  /*
      Other Account Related Endpoints
  */

  @PostMapping(
      value = "/ApplyKits",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity<String> postApplyKits(@RequestBody String name) {
    name = name.replace("\"", "");

    if (!MinecraftUtils.isValidUsername(name)) return ResponseEntity.badRequest().build();

    ClientToken clientToken = playerService.getClientToken(SearchType.USERNAME, name);

    if (clientToken == null) return ResponseEntity.ok(CustomResponse.Failed.toString());

    if (!playerService.applyKits(clientToken.AccountId))
      return ResponseEntity.ok(CustomResponse.Failed.toString());

    return ResponseEntity.ok(CustomResponse.Success.toString());
  }

  @PostMapping(
      value = "/SaveCustomBuild",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity<String> postSaveCustomBuild(
      @RequestBody CustomBuildToken customBuildToken) {
    try {
      if (!playerService.saveClass(customBuildToken))
        ResponseEntity.ok(CustomResponse.Failed.toString());
    } catch (Exception e) {
      ResponseEntity.ok(CustomResponse.Failed.toString());
    }

    return ResponseEntity.ok(CustomResponse.Success.toString());
  }

  /*
      Punishment Related Endpoints
  */

  @PostMapping(
      value = "/Punish",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity<String> postPunish(@RequestBody AddPunishmentToken addPunishmentToken) {
    try {
      if (!MinecraftUtils.isValidUsername(addPunishmentToken.Target))
        return ResponseEntity.badRequest().body(PunishmentResponse.NotPunished.toString());

      return ResponseEntity.ok(punishmentService.punishPlayer(addPunishmentToken).toString());
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(PunishmentResponse.NotPunished.toString());
    }
  }

  @PostMapping(
      value = "/RemovePunishment",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity<String> postRemovePunishment(
      @RequestBody RemovePunishmentToken removePunishmentToken) {
    if (!MinecraftUtils.isValidUsername(removePunishmentToken.Target))
      return ResponseEntity.badRequest().body(PunishmentResponse.NotPunished.toString());

    return ResponseEntity.ok(punishmentService.removePunishment(removePunishmentToken).toString());
  }

  @PostMapping(
      value = "/GetPunishClient",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ClientPunishmentToken> postGetPunishClient(@RequestBody String name) {
    name = name.replace("\"", "");

    if (!MinecraftUtils.isValidUsername(name)) return ResponseEntity.badRequest().build();

    ClientPunishmentToken punishToken = playerService.getPunishToken(SearchType.USERNAME, name);

    if (punishToken != null) return ResponseEntity.ok(punishToken);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
  }

  /*
      Donation Related Endpoints
  */

  @PostMapping(
      value = "/GemReward",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity<String> postGemReward(@RequestBody CurrencyRewardToken gemRewardToken) {
    if (!MinecraftUtils.isValidUsername(gemRewardToken.Name))
      return ResponseEntity.badRequest().body(String.valueOf(false));

    return ResponseEntity.ok(
        String.valueOf(
            playerService.giveReward(
                Currency.GEMS, gemRewardToken.Name, gemRewardToken.Source, gemRewardToken.Amount)));
  }

  @PostMapping(
      value = "/CoinReward",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity<String> postCoinReward(@RequestBody CurrencyRewardToken coinRewardToken) {
    if (!MinecraftUtils.isValidUsername(coinRewardToken.Name))
      return ResponseEntity.badRequest().body(String.valueOf(false));

    return ResponseEntity.ok(
        String.valueOf(
            playerService.giveReward(
                Currency.SHARDS,
                coinRewardToken.Name,
                coinRewardToken.Source,
                coinRewardToken.Amount)));
  }

  @PostMapping(
      value = "/PurchaseKnownSalesPackage",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity<String> postPurchaseKnownSalesPackage(
      @RequestBody PurchaseToken purchaseToken) {
    if (!MinecraftUtils.isValidUsername(purchaseToken.AccountName))
      return ResponseEntity.badRequest().body(TransactionResponse.Failed.toString());

    return ResponseEntity.ok(playerService.purchaseKnownSalesPackage(purchaseToken).toString());
  }

  @PostMapping(
      value = "/PurchaseUnknownSalesPackage",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity<String> postPurchaseUnknownSalesPackage(
      @RequestBody UnknownPurchaseToken unknownPurchaseToken) {
    if (!MinecraftUtils.isValidUsername(unknownPurchaseToken.AccountName))
      return ResponseEntity.badRequest().body(TransactionResponse.Failed.toString());

    return ResponseEntity.ok(
        playerService.purchaseUnknownSalesPackage(unknownPurchaseToken).toString());
  }
}
