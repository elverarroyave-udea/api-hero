package co.udea.hero.api.tourofhero.repository;

import co.udea.hero.api.tourofhero.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Integer> {

    @Query("SELECT h FROM HEROES h WHERE UPPER(h.name) LIKE %:term%")
    Optional<List<Hero>> searchHeroes(String term);

}
