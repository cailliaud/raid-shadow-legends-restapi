package fr.cailliaud.rsl.restapi.repository;

import fr.cailliaud.rsl.restapi.domain.Champion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChampionRepository  extends MongoRepository<Champion,String> {

    Optional<Champion> findOneByName(String name);
}
