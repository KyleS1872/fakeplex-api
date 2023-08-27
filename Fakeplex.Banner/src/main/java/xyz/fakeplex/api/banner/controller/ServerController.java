package xyz.fakeplex.api.banner.controller;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.fakeplex.api.banner.token.PlayerInfo;
import xyz.fakeplex.api.common.util.ResourceUrls;

/**
 * @author Kyle
 */
@Slf4j
@RestController
@RequestMapping(ResourceUrls.BANNER_SERVER_URI)
public class ServerController {

  @PostMapping(
      value = "/login/{playerName}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> postLoginPlayer(
      @RequestBody PlayerInfo playerInfo, @PathVariable String playerName) {
    log.info(playerName + " joined the server!");
    return ResponseEntity.ok(new JSONObject().put("error", "Not Implemented").toString());
  }
}
