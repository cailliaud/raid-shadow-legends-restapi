package fr.cailliaud.rsl.restapi.web.api;

import fr.cailliaud.rsl.restapi.domain.Champion;
import fr.cailliaud.rsl.restapi.repository.ChampionRepository;
import fr.cailliaud.rsl.restapi.service.ChampionService;
import fr.cailliaud.rsl.restapi.service.dto.ChampionDTO;
import fr.cailliaud.rsl.restapi.service.dto.ChampionUpdateDTO;
import fr.cailliaud.rsl.restapi.web.api.exception.ChampionAlreadyExists;
import fr.cailliaud.rsl.restapi.web.api.exception.ChampionResourceNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api")
public class ChampionResource {

    private ChampionService championService;
    private ChampionRepository championRepository;

    public ChampionResource(ChampionService championService, ChampionRepository championRepository) {
        this.championService = championService;
        this.championRepository = championRepository;
    }

    @GetMapping("/champions")
    public ResponseEntity<List<ChampionDTO>> getAllChampions() {
        final List<ChampionDTO> champions = championService.getAllManagedChampions();
        return new ResponseEntity<>(champions, HttpStatus.OK);
    }

    @PostMapping("/champions")
    public ResponseEntity<Champion> createChampion(@Valid @RequestBody ChampionDTO championDTO) throws URISyntaxException {

        log.debug("REST request to save champion : {}", championDTO);
        championRepository.findOneByName(championDTO.getName().toLowerCase()).ifPresent(champion -> {
                    throw new ChampionAlreadyExists();
                }
        );
        Champion newChampion = championService.createChampion(championDTO);
        return ResponseEntity.created(new URI("/api/champions/" + newChampion.getName()))
                .body(newChampion);
    }

    @PatchMapping("/champions/{championName}")
    public ResponseEntity<Champion> updateChampion(
            @PathVariable String championName, @Valid @RequestBody ChampionUpdateDTO championUpdateDTO) {
        log.debug("REST request to update champion {} : {}", championName, championUpdateDTO);
        Optional<Champion> optionalChampion = championRepository.findOneByName(championName.toLowerCase());
        if (optionalChampion.isEmpty()) {
            throw new ChampionResourceNotFound();
        }
        Champion champion = optionalChampion.get();
        championService.updateChampion(champion, championUpdateDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
