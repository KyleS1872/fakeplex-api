package xyz.fakeplex.api.amplifiers.service;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.fakeplex.api.amplifiers.token.AddBoosterToken;
import xyz.fakeplex.api.amplifiers.token.BoosterResultToken;
import xyz.fakeplex.api.amplifiers.token.BoosterToken;
import xyz.fakeplex.api.common.database.AccountDatabase;
import xyz.fakeplex.api.common.database.entity.Account;
import xyz.fakeplex.api.common.database.entity.Amplifier;
import xyz.fakeplex.api.common.redis.message.RedisMessage;
import xyz.fakeplex.api.common.util.MessageChannels;
import xyz.fakeplex.api.common.util.MinecraftUtils;

import java.sql.Timestamp;
import java.util.*;

/**
 * @author Kyle
 */
@Slf4j
@Service
public class BoosterService {

  private final AccountDatabase accountDatabase;
  private final RedisMessage redisMessage;
  private final Gson gson;

  public BoosterService(AccountDatabase accountDatabase, RedisMessage redisMessage, Gson gson) {
    this.accountDatabase = accountDatabase;
    this.redisMessage = redisMessage;
    this.gson = gson;
  }

  /**
   * Adds a Booster to a Booster Group
   *
   * @param addBoosterToken Add Booster Token
   * @param boosterGroup Booster Group
   * @return Booster Result Token
   */
  public BoosterResultToken addBooster(AddBoosterToken addBoosterToken, String boosterGroup) {
    BoosterResultToken responseToken = new BoosterResultToken();
    responseToken.success = false;

    if (!MinecraftUtils.isValidUsername(addBoosterToken.playerName))
      responseToken.error = "Invalid player name provided!";
    else if (!MinecraftUtils.isValidUUID(addBoosterToken.uuid))
      responseToken.error = "Invalid player uuid provided!";
    else if (addBoosterToken.duration == null) responseToken.error = "Invalid duration!";
    else if (addBoosterToken.accountId == null || addBoosterToken.accountId == -1)
      responseToken.error = "Invalid accountId!";
    else {
      Account account = accountDatabase.getAccountRepository().findOneByUuid(addBoosterToken.uuid);

      if (account == null) responseToken.error = "Couldn't find account!";
      else {
        Timestamp latestBoosterEnd =
            accountDatabase.getAmplifierRepository().getLatestBoosterEnd(boosterGroup);

        Timestamp startTime =
            (latestBoosterEnd == null)
                ? new Timestamp(System.currentTimeMillis())
                : new Timestamp(latestBoosterEnd.getTime() + 1000);
        Timestamp endTime = new Timestamp(startTime.getTime() + (addBoosterToken.duration * 1000));
        Timestamp activationTime = new Timestamp(System.currentTimeMillis());

        Integer success =
            accountDatabase
                .getAmplifierRepository()
                .insertBooster(
                    addBoosterToken.playerName,
                    addBoosterToken.uuid,
                    account.getId(),
                    boosterGroup,
                    addBoosterToken.duration,
                    addBoosterToken.multiplier,
                    startTime,
                    endTime,
                    activationTime);

        if (success == null || success == 0) {
          responseToken.error = "Couldn't add amplifier to database!";
          return responseToken;
        }

        responseToken.success = true;
        responseToken.startTime = startTime;

        redisMessage.publishMessage(MessageChannels.BOOSTERS, gson.toJson(getBoosters()));
      }
    }

    return responseToken;
  }

  /**
   * Retrieves All Active Boosters on the Network
   *
   * @return Map of Boosters using BoosterGroup as Key
   */
  public Map<String, List<BoosterToken>> getBoosters() {
    Map<String, List<BoosterToken>> amplifiers = new HashMap<>();

    accountDatabase
        .getAmplifierRepository()
        .getActiveBoosters()
        .forEach(
            booster -> {
              List<BoosterToken> boosterGroup =
                  amplifiers.containsKey(booster.getBoosterGroup())
                      ? amplifiers.get(booster.getBoosterGroup())
                      : new ArrayList<>();
              boosterGroup.add(generateBoosterToken(booster));
              amplifiers.put(booster.getBoosterGroup(), boosterGroup);
            });

    return amplifiers;
  }

  /**
   * Retrieves Active Boosters for a Booster Group
   *
   * @param boosterGroup Booster Group to use
   * @return Array of Active/Upcoming Boosters
   */
  public BoosterToken[] getBoosters(String boosterGroup) {
    ArrayList<BoosterToken> amplifiers = new ArrayList<>();

    accountDatabase
        .getAmplifierRepository()
        .getActiveBoosters(boosterGroup)
        .forEach(booster -> amplifiers.add(generateBoosterToken(booster)));

    return amplifiers.toArray(new BoosterToken[] {});
  }

  /**
   * Generate Booster Token from Database Object
   *
   * @param booster Database Object
   * @return Token Object
   */
  private BoosterToken generateBoosterToken(Amplifier booster) {
    BoosterToken boosterToken = new BoosterToken();
    boosterToken.id = booster.getId();
    boosterToken.playerName = booster.getName();
    boosterToken.uuid = booster.getUuid();
    boosterToken.accountId = booster.getAccount().getId();
    boosterToken.duration = booster.getDuration();
    boosterToken.multiplier = booster.getMultiplier();
    boosterToken.startTime = new Date(booster.getStartTime().getTime());
    boosterToken.endTime = new Date(booster.getEndTime().getTime());
    boosterToken.activationTime = new Date(booster.getActivationTime().getTime());
    return boosterToken;
  }
}
