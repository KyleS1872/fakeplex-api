package xyz.fakeplex.api.accounts.controller;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.fakeplex.api.accounts.service.DominateService;
import xyz.fakeplex.api.accounts.token.dominate.SkillToken;
import xyz.fakeplex.api.common.util.ResourceUrls;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kyle
 */
@Slf4j
@RestController
@RequestMapping(ResourceUrls.ACCOUNTS_DOMINATE_URI)
public class DominateController {

  private final DominateService dominateService;

  public DominateController(DominateService dominateService) {
    this.dominateService = dominateService;
  }

  @PostMapping(
      value = "/GetSkills",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity<String> postGetSkills(@RequestBody List<SkillToken> skillTokenList) {
    ArrayList<JSONObject> skillTokens = new ArrayList<>();

    dominateService
        .getSkills(skillTokenList)
        .forEach(
            item -> {
              try {
                skillTokens.add(new JSONObject(new Gson().toJson(item)));
              } catch (Exception e) {
                log.debug(e.getMessage());
              }
            });

    return ResponseEntity.ok(skillTokens.toString());
  }
}
