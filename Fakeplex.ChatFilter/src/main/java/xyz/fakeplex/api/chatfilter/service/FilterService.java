package xyz.fakeplex.api.chatfilter.service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kyle
 */
@Slf4j
@Service
public class FilterService {

  @SuppressWarnings("FieldCanBeLocal")
  private final String SHEET_ID = "1KPF4i9pHsMDscoLLIQ6Jchd-V07fQ_TTLCn188FPk1c";

  @Getter private final Map<String, String[]> bannedWords = new HashMap<>();
  @Getter private int largestBannedWordLength = 0;

  public FilterService() {

    int readCounter = 0;
    try {
      BufferedReader reader =
          new BufferedReader(
              new InputStreamReader(
                  new URL(
                          "https://docs.google.com/spreadsheets/d/"
                              + SHEET_ID
                              + "/export?format=csv")
                      .openConnection()
                      .getInputStream()));

      String currentLine;
      while ((currentLine = reader.readLine()) != null) {
        readCounter++;

        try {
          if (readCounter == 1) {
            continue;
          }

          String[] content = currentLine.split(",");
          if (content.length == 0) {
            continue;
          }

          String word = content[0];

          if (word.startsWith("-----")) {
            continue;
          }

          if (word.length() > largestBannedWordLength) {
            largestBannedWordLength = word.length();
          }

          String[] ignoreInCombinationWithWords =
              (content.length > 1) ? content[1].split("_") : new String[0];

          bannedWords.put(word.replace(" ", "").toLowerCase(), ignoreInCombinationWithWords);
        } catch (Exception e) {
          log.debug(e.getMessage());
        }
      }
    } catch (IOException e) {
      log.debug(e.getMessage());
    }
  }

  public String filterMessage(String originalMessage) {

    if (originalMessage == null) return "";

    String modifiedInput = originalMessage;

    modifiedInput =
        modifiedInput
            .replace("1", "i")
            .replace("!", "i")
            .replace("3", "e")
            .replace("4", "a")
            .replace("@", "a")
            .replace("5", "s")
            .replace("7", "t")
            .replace("0", "o")
            .replace("9", "g");

    modifiedInput = modifiedInput.toLowerCase().replaceAll("[^a-zA-Z]", "");

    ArrayList<String> badWordsFound = new ArrayList<>();

    for (int start = 0; start < modifiedInput.length(); start++)
      for (int offset = 1;
          offset < (modifiedInput.length() + 1 - start) && offset < getLargestBannedWordLength();
          offset++) {
        String wordToCheck = modifiedInput.substring(start, start + offset);
        if (getBannedWords().containsKey(wordToCheck)) {
          String[] ignoreCheck = getBannedWords().get(wordToCheck);
          boolean ignore = false;
          for (String s : ignoreCheck) {
            if (modifiedInput.contains(s)) {
              ignore = true;
              break;
            }
          }

          if (!ignore) badWordsFound.add(wordToCheck);
        }
      }

    String modifiedMessage = originalMessage;
    for (String swearWord : badWordsFound) {
      char[] charsStars = new char[swearWord.length()];
      Arrays.fill(charsStars, '*');
      String stars = new String(charsStars);

      modifiedMessage = modifiedMessage.replaceAll("(?i)" + swearWord, stars);
    }

    return modifiedMessage;
  }
}
