package xyz.fakeplex.api.accounts.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.fakeplex.api.accounts.service.PetService;
import xyz.fakeplex.api.accounts.token.pet.PetChangeToken;
import xyz.fakeplex.api.common.util.ResourceUrls;

/**
 * @author Kyle
 */
@RestController
@RequestMapping(ResourceUrls.ACCOUNTS_PETS_URI)
public class PetsController {

  private final PetService petService;

  public PetsController(PetService petService) {
    this.petService = petService;
  }

  @PostMapping(
      value = "/AddPet",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity<String> postAddPet(@RequestBody PetChangeToken petChangeToken) {
    return ResponseEntity.ok(petService.addPet(petChangeToken).toString());
  }

  @PostMapping(
      value = "/UpdatePet",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity<String> postUpdatePet(@RequestBody PetChangeToken petChangeToken) {
    return ResponseEntity.ok(petService.updatePet(petChangeToken).toString());
  }
}
