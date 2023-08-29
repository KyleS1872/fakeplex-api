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
import xyz.fakeplex.api.enderchest.service.EnderchestService;
import xyz.fakeplex.api.enderchest.type.MapType;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Kyle
 */
@Slf4j
@RestController
@RequestMapping(ResourceUrls.BASE_RESOURCE_URI)
public class EnderchestController {

  private final EnderchestService enderchestService;

  public EnderchestController(EnderchestService enderchestService) {
    this.enderchestService = enderchestService;
  }

  @GetMapping(value = ResourceUrls.ENDERCHEST_NEXT_URI)
  public ResponseEntity<Resource> getMap(@PathVariable String mapType) throws IOException {
    MapType type = MapType.getEnum(mapType);

    File map = enderchestService.getMap(type);

    if (map == null) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    HttpHeaders header = new HttpHeaders();
    header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + map.getName() + "\"");
    header.add("Cache-Control", "no-cache, no-store, must-revalidate");
    header.add("Pragma", "no-cache");
    header.add("Expires", "0");

    Path path = Paths.get(map.getAbsolutePath());
    ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

    return ResponseEntity.ok()
        .headers(header)
        .contentLength(map.length())
        .contentType(MediaType.parseMediaType("application/octet-stream"))
        .body(resource);
  }

  @PostMapping(value = ResourceUrls.ENDERCHEST_UPLOAD_URI)
  public ResponseEntity<String> postMap(
      HttpServletRequest request, @PathVariable String mapType, @RequestParam String name) {
    MapType type = MapType.getEnum(mapType);

      return ResponseEntity.status(enderchestService.uploadMap(type, name, request)).build();
  }
}
