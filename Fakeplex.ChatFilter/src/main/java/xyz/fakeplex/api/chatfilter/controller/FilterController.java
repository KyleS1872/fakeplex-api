package xyz.fakeplex.api.chatfilter.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.fakeplex.api.chatfilter.service.FilterService;
import xyz.fakeplex.api.chatfilter.token.ChatMessage;
import xyz.fakeplex.api.common.util.ResourceUrls;

/**
 * @author Kyle
 */
@Slf4j
@RestController
@RequestMapping(ResourceUrls.CHATFILTER_URI)
public class FilterController {

  private final FilterService filterService;
  private final ObjectMapper objectMapper;

  private final String API_KEY = "oUywMpwZcIzZO5AWnfDx";

  public FilterController(FilterService filterService, ObjectMapper objectMapper) {
    this.filterService = filterService;
    this.objectMapper = objectMapper;
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
  }

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> postModerate(
      @RequestHeader(value = "Authentication", defaultValue = API_KEY) String authentication,
      @RequestBody ChatMessage chatMessage)
      throws JsonProcessingException {
    if (!authentication.equals(API_KEY))
      return ResponseEntity.badRequest()
          .body(new JSONObject().put("error", "Invalid api-key").toString());

    if (chatMessage.getContent().getParts().isEmpty())
      return ResponseEntity.badRequest()
          .body(new JSONObject().put("error", "No message provided").toString());

    for (ChatMessage.Part part : chatMessage.getContent().getParts()) {
      String originalMessage = part.getContent();
      String modifiedMessage = filterService.filterMessage(originalMessage);

      if (!originalMessage.equalsIgnoreCase(modifiedMessage)) part.setReplacement(modifiedMessage);
    }

    return ResponseEntity.ok(objectMapper.writeValueAsString(chatMessage));
  }
}
