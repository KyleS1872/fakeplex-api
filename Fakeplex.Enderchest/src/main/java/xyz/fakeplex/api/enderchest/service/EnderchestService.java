package xyz.fakeplex.api.enderchest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import xyz.fakeplex.api.enderchest.type.MapType;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

/**
 * @author Kyle
 */
@Slf4j
@Service
@SuppressWarnings("ResultOfMethodCallIgnored")
public class EnderchestService {

  private final String mapsFolder;

  public EnderchestService(@Value("${maps}") String mapsFolder) {
    this.mapsFolder = mapsFolder;
  }

  public File getMap(MapType type) {
    if (type == MapType.Unknown) return null;

    File dir = new File(new File(mapsFolder).getPath() + File.separator + type.getFolderName());
    dir.mkdirs();

    File[] files = dir.listFiles();
    if (files == null || files.length == 0) return null;

    Random rand = new Random();
    return files[rand.nextInt(files.length)];
  }

  public HttpStatus uploadMap(MapType type, String name, HttpServletRequest request) {
    if (type == MapType.Unknown) return HttpStatus.BAD_REQUEST;

    File dir = new File(new File(mapsFolder).getPath() + File.separator + type.getFolderName());
    dir.mkdirs();

    File temp =
        new File(
            new File(mapsFolder).getPath()
                + File.separator
                + type.getFolderName()
                + File.separator
                + name);
    if (temp.exists()) return HttpStatus.CONFLICT;

    try {
      Files.copy(
          request.getInputStream(),
          Paths.get(
              new File(mapsFolder).getPath()
                  + File.separator
                  + type.getFolderName()
                  + File.separator
                  + name));
    } catch (Exception e) {
      log.error("An error occurred saving a new map: " + e.getMessage());
      return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    return HttpStatus.OK;
  }
}
