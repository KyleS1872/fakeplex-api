package xyz.fakeplex.api.amplifiers.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.fakeplex.api.amplifiers.service.BoosterService;
import xyz.fakeplex.api.amplifiers.token.AddBoosterToken;
import xyz.fakeplex.api.amplifiers.token.BoosterResultToken;
import xyz.fakeplex.api.common.util.ResourceUrls;

/**
 * @author Kyle
 */
@RestController
@RequestMapping(ResourceUrls.BASE_RESOURCE_URI)
public class BoosterController {

  private final BoosterService boosterService;

  private final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX").create();

  public BoosterController(BoosterService boosterService) {
    this.boosterService = boosterService;
  }

  @GetMapping(
      value = ResourceUrls.AMPLIFIERS_GET_ALL_BOOSTERS_URI,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> getBoosters() {
    return ResponseEntity.ok(gson.toJson(boosterService.getBoosters()));
  }

  @GetMapping(
      value = ResourceUrls.AMPLIFIERS_GET_BOOSTERS_URI,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> getBoosters(@PathVariable String boosterGroup) {
    return ResponseEntity.ok(gson.toJson(boosterService.getBoosters(boosterGroup)));
  }

  @PostMapping(
      value = ResourceUrls.AMPLIFIERS_POST_BOOSTERS_URI,
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BoosterResultToken> postAddBooster(
      @RequestBody AddBoosterToken addBoosterToken, @PathVariable String boosterGroup) {
    BoosterResultToken responseToken = boosterService.addBooster(addBoosterToken, boosterGroup);

    if (!responseToken.success) return ResponseEntity.badRequest().body(responseToken);
    else return ResponseEntity.ok(responseToken);
  }
}
