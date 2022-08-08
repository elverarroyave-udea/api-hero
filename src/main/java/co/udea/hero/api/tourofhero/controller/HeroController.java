package co.udea.hero.api.tourofhero.controller;

import co.udea.hero.api.tourofhero.heroDTO.HeroResponseDTO;
import co.udea.hero.api.tourofhero.heroDTO.HeroRequestDTO;
import co.udea.hero.api.tourofhero.model.Hero;
import co.udea.hero.api.tourofhero.service.HeroService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

@RestController
@RequestMapping("/heroes")
@CrossOrigin(origins = "http://localhost:4200")
public class HeroController {

  @Autowired
  private HeroService heroService;

  @GetMapping("{id}")
  @ApiOperation(value = "Get hero by ID.")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Heroe encontrado existosamente"),
          @ApiResponse(code = 400, message = "La petición es invalida"),
          @ApiResponse(code = 404, message = "Heroe no encontrado"),
          @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
  public ResponseEntity<HeroResponseDTO> getHero(@Valid @PathVariable("id") @NotNull int id){
    return ResponseEntity.ok(HeroResponseDTO.toHeroDTO(heroService.getHero(id)));
  }

  @GetMapping("")
  @ApiOperation(value = "Get all heroes.")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Heroes encontrados existosamente"),
          @ApiResponse(code = 400, message = "La petición es invalida"),
          @ApiResponse(code = 404, message = "Heroe no encontrado"),
          @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
  public ResponseEntity<List<HeroResponseDTO>> getHeroes(){
    return ResponseEntity.ok(HeroResponseDTO.toHeroesDTO(heroService.getHeroes()));
  }

  @GetMapping("/")
  @ApiOperation(value = "Get hero by term")
  public ResponseEntity<List<HeroResponseDTO>> searchHeroes(@RequestParam String name){
    return ResponseEntity.ok(HeroResponseDTO.toHeroesDTO((heroService.searchHeroes(name))));
  }

  @PostMapping
  @ApiOperation(value = "Add new hero.")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Heroes encontrados existosamente"),
          @ApiResponse(code = 400, message = "La petición es invalida"),
          @ApiResponse(code = 404, message = "Heroe no encontrado"),
          @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
  public ResponseEntity<HeroResponseDTO> addHero(@RequestBody @Valid HeroRequestDTO heroToCreate){

    Hero heroCreated = heroService.addHero(HeroRequestDTO.toHeroModel(heroToCreate));

    //El estandar de los create dice no devolver un objeto si no la localizacion
//    URI location = fromUriString("/api/v1/heroes").path("/{id}")
//            .buildAndExpand(heroCreated.getId()).toUri();

    return ResponseEntity.ok(HeroResponseDTO.toHeroDTO(heroCreated));
  }

  @PutMapping
  @ApiOperation(value = "Update a hero by ID and request body.")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Heroes encontrados existosamente"),
          @ApiResponse(code = 400, message = "La petición es invalida"),
          @ApiResponse(code = 404, message = "Heroe no encontrado"),
          @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
  public ResponseEntity<HeroResponseDTO> updateHero(@RequestBody @Valid Hero heroToUpdate){

      Hero heroUpdate = heroService.update(heroToUpdate);

      return ResponseEntity.ok(HeroResponseDTO.toHeroDTO(heroUpdate));

  }


  @DeleteMapping("/{id}")
  @ApiOperation(value = "Delete a hero by id.")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "Heroes encontrados existosamente"),
          @ApiResponse(code = 400, message = "La petición es invalida"),
          @ApiResponse(code = 404, message = "Heroe no encontrado"),
          @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
  public void deleteById(@NotNull @PathVariable("id") int id){
    heroService.deleteById(id);
  }

}
