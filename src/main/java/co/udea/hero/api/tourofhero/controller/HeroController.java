package co.udea.hero.api.tourofhero.controller;

import co.udea.hero.api.tourofhero.heroDTO.HeroResponseDTO;
import co.udea.hero.api.tourofhero.heroDTO.HeroRequestDTO;
import co.udea.hero.api.tourofhero.model.Hero;
import co.udea.hero.api.tourofhero.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

@RestController
@RequestMapping("/heroes")
public class HeroController {

  @Autowired
  private HeroService heroService;

  @GetMapping("{id}")
  public ResponseEntity<HeroResponseDTO> getHero(@Valid @PathVariable("id") @NotNull int id){
    return ResponseEntity.ok(HeroResponseDTO.toHeroDTO(heroService.getHero(id)));
  }

  @GetMapping
  public ResponseEntity<List<HeroResponseDTO>> getHeroes(){
    return ResponseEntity.ok(HeroResponseDTO.toHeroesDTO(heroService.getHeroes()));
  }

  @GetMapping("term/{term}")
  public ResponseEntity<List<HeroResponseDTO>> searchHeroes(@Valid @PathVariable("term") @NotNull String term){
    return ResponseEntity.ok(HeroResponseDTO.toHeroesDTO((heroService.searchHeroes(term))));
  }

  @PostMapping
  public ResponseEntity<Void> addHero(@RequestBody @Valid HeroRequestDTO heroToCreate){

    Hero heroCreated = heroService.addHero(HeroRequestDTO.toHeroModel(heroToCreate));

    //El estandar de los create dice no devolver un objeto si no la localizacion
    URI location = fromUriString("/api/v1/heroes").path("/{id}")
            .buildAndExpand(heroCreated.getId()).toUri();

    return ResponseEntity.created(location).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<HeroResponseDTO> updateHero(@PathVariable int id,
                                                    @RequestBody @Valid HeroRequestDTO heroToUpdate){

      Hero heroUpdate = heroService.update(id, heroToUpdate);

      return ResponseEntity.ok(HeroResponseDTO.toHeroDTO(heroUpdate));

  }


  @DeleteMapping("/{id}")
  public void deleteById(@NotNull int id){
    heroService.deleteById(id);
  }

}
