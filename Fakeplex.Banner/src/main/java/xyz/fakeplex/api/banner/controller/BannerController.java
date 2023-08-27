package xyz.fakeplex.api.banner.controller;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.fakeplex.api.banner.token.TriggerPriorityInfo;
import xyz.fakeplex.api.common.util.ResourceUrls;

/**
 * @author Kyle
 */
@Slf4j
@RestController
@RequestMapping(ResourceUrls.BANNER_BANNER_URI)
public class BannerController {

  @PostMapping(
      value = "/trigger/{playerName}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> postBanPlayer(
      @RequestBody TriggerPriorityInfo triggerPriorityInfo, @PathVariable String playerName) {
    log.info(
        "Priority ban triggered for "
            + playerName
            + " with cause "
            + triggerPriorityInfo.getCause());
    return ResponseEntity.ok(new JSONObject().put("error", "Not Implemented").toString());
  }
}
