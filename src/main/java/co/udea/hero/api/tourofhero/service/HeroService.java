package co.udea.hero.api.tourofhero.service;

import co.udea.hero.api.tourofhero.heroDTO.HeroRequestDTO;
import co.udea.hero.api.tourofhero.model.Hero;

import java.util.List;
import java.util.Optional;

public interface HeroService {
    Hero getHero(Integer id);

    List<Hero> getHeroes();

    List<Hero> searchHeroes(String term);

    Hero addHero(Hero heroToCreated);

    Hero update(Hero heroToUpdate);

    void deleteById(int id);
}
