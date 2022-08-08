package co.udea.hero.api.tourofhero.service.impl;

import co.udea.hero.api.tourofhero.config.exception.NotFoundException;
import co.udea.hero.api.tourofhero.heroDTO.HeroRequestDTO;
import co.udea.hero.api.tourofhero.model.Hero;
import co.udea.hero.api.tourofhero.repository.HeroRepository;
import co.udea.hero.api.tourofhero.service.HeroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeroServiceImpl implements HeroService {

  @Autowired
  private HeroRepository heroRepository;

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public Hero getHero(Integer id) {

    logger.info("Begin getHero by id: id={}", id);

    return heroRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("No se encontró un HERO con con el id ingresado: " + id));
  }

  @Override
  public List<Hero> getHeroes() {
    logger.info("Begin get all heroes");
    return heroRepository.findAll();
  }

  @Override
  public List<Hero> searchHeroes(String term) {

    logger.info("Begin get heroes by term: term={}", term);

    Optional<List<Hero>> heroes = heroRepository.searchHeroes(term.toUpperCase());
    return heroes.get();
  }

  @Override
  public Hero addHero(Hero heroToCreate) {

    logger.info("Begin add hero");
    return heroRepository.save(heroToCreate);
  }

  @Override
  public Hero update(Hero heroToUpdate) {

    logger.info("Begin update hero.");

    Hero heroToUpdateBD = getHero(heroToUpdate.getId());

    Hero heroToBeUpdate = heroToUpdateBD.toBuilder()
            .name(heroToUpdate.getName())
            .build();

    return heroRepository.save(heroToBeUpdate);
  }

  @Override
  public void deleteById(int id) {
    logger.info("Begin delete hero by id: id={}", id);
    getHero(id);
    heroRepository.deleteById(id);
  }
}
