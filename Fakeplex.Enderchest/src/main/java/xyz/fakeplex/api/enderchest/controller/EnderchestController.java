package xyz.fakeplex.api.enderchest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.fakeplex.api.common.util.ResourceUrls;
import xyz.fakeplex.api.enderchest.type.MapType;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

/**
 * @author Kyle
 */
@Slf4j
@RestController
@SuppressWarnings("ResultOfMethodCallIgnored")
@RequestMapping(ResourceUrls.BASE_RESOURCE_URI)
public class EnderchestController {

  private String path =
      (System.getProperty("os.name").startsWith("Windows")
              ? "C:"
              : File.separator + "home" + File.separator + "mineplex")
          + File.separator
          + "update"
          + File.separator
          + "maps"
          + File.separator;

  @GetMapping(value = ResourceUrls.ENDERCHEST_NEXT_URI)
  public ResponseEntity<Resource> getMap(@PathVariable String mapType) throws IOException {
    MapType type = MapType.getEnum(mapType);

    if (type == MapType.Unknown) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    File dir = new File(new File(path).getPath() + File.separator + type.getFolderName());
    dir.mkdirs();

    File[] files = dir.listFiles();
    if (files == null || files.length == 0)
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    Random rand = new Random();
    File file = files[rand.nextInt(files.length)];

    HttpHeaders header = new HttpHeaders();
    header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"");
    header.add("Cache-Control", "no-cache, no-store, must-revalidate");
    header.add("Pragma", "no-cache");
    header.add("Expires", "0");

    Path path = Paths.get(file.getAbsolutePath());
    ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

    return ResponseEntity.ok()
        .headers(header)
        .contentLength(file.length())
        .contentType(MediaType.parseMediaType("application/octet-stream"))
        .body(resource);
  }

  @PostMapping(value = ResourceUrls.ENDERCHEST_UPLOAD_URI)
  public ResponseEntity<String> postMap(
      HttpServletRequest request, @PathVariable String mapType, @RequestParam String name) {
    MapType type = MapType.getEnum(mapType);

    if (type == MapType.Unknown) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    File dir = new File(new File(path).getPath() + File.separator + type.getFolderName());
    dir.mkdirs();

    File temp =
        new File(
            new File(path).getPath()
                + File.separator
                + type.getFolderName()
                + File.separator
                + name);
    if (temp.exists()) return ResponseEntity.status(HttpStatus.CONFLICT).build();

    try {
      Files.copy(
          request.getInputStream(),
          Paths.get(
              new File(path).getPath()
                  + File.separator
                  + type.getFolderName()
                  + File.separator
                  + name));
    } catch (Exception e) {
      log.error("An error occurred saving a new map: " + e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
