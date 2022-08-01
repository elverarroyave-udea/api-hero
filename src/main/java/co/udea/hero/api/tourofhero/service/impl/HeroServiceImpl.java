package co.udea.hero.api.tourofhero.service.impl;

import co.udea.hero.api.tourofhero.heroDTO.HeroRequestDTO;
import co.udea.hero.api.tourofhero.model.Hero;
import co.udea.hero.api.tourofhero.repository.HeroRepository;
import co.udea.hero.api.tourofhero.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class HeroServiceImpl implements HeroService {

  @Autowired
  private HeroRepository heroRepository;

  @Override
  public Hero getHero(Integer id) {
    return heroRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("No se encontró un HERO con con el id ingresado: " + id));
  }

  @Override
  public List<Hero> getHeroes() {
    return heroRepository.findAll();
  }

  @Override
  public List<Hero> searchHeroes(String term) {
    Optional<List<Hero>> heroes = heroRepository.searchHeroes(term.toUpperCase());
    return heroes.get();
  }

  @Override
  public Hero addHero(Hero heroToCreate) {
    return heroRepository.save(heroToCreate);
  }

  @Override
  public Hero update(int id, HeroRequestDTO heroToUpdate) {

    Hero heroToUpdateBD = getHero(id);

    Hero heroToBeUpdate = heroToUpdateBD.toBuilder()
            .name(heroToUpdate.getName())
            .build();

    return heroRepository.save(heroToBeUpdate);
  }

  @Override
  public void deleteById(int id) {
    getHero(id);
    heroRepository.deleteById(id);
  }
}
