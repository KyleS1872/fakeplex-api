package xyz.fakeplex.api.antispam.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.fakeplex.api.antispam.service.AnitSpamService;
import xyz.fakeplex.api.antispam.token.AntiSpamToken;
import xyz.fakeplex.api.antispam.token.ChatToken;
import xyz.fakeplex.api.common.util.ResourceUrls;

/**
 * @author Kyle
 */
@RestController
@RequestMapping(ResourceUrls.ANTISPAM_URI_BASE)
public class AntiSpamController {

  private final AnitSpamService anitSpamService;

  public AntiSpamController(AnitSpamService anitSpamService) {
    this.anitSpamService = anitSpamService;
  }

  @PostMapping(
      value = ResourceUrls.ANTISPAM_URI,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AntiSpamToken> isShadowMuted(
      @RequestBody ChatToken chatToken, @PathVariable String source) {
    return ResponseEntity.ok(new AntiSpamToken(anitSpamService.isShadowMuted(chatToken.uuid)));
  }
}
