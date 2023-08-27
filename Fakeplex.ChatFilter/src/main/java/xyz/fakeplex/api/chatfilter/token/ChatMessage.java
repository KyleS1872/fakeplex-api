package xyz.fakeplex.api.chatfilter.token;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Kyle
 */
@Getter
@SuppressWarnings("unused")
public class ChatMessage {

  private Content content;

  @Getter
  public static class Content {
    private long createInstant;
    private String senderDisplayName;
    private String senderId;
    private List<Part> parts;
    private String applicationId;
  }

  @Getter
  public static class Part {
    private String type;
    private String content;
    @Setter private String replacement;
  }
}
