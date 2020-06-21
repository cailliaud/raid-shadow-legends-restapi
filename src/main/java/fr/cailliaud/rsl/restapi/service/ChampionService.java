package fr.cailliaud.rsl.restapi.service;

import fr.cailliaud.rsl.restapi.domain.Champion;
import fr.cailliaud.rsl.restapi.repository.ChampionRepository;
import fr.cailliaud.rsl.restapi.service.dto.ChampionDTO;
import fr.cailliaud.rsl.restapi.service.dto.ChampionUpdateDTO;
import fr.cailliaud.rsl.restapi.service.mapper.ChampionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ChampionService {

    private ChampionRepository championRepository;
    private ChampionMapper championMapper;

    public ChampionService(ChampionRepository championRepository, ChampionMapper championMapper){
        this.championRepository=championRepository;
        this.championMapper=championMapper;
    }


    public List<ChampionDTO> getAllManagedChampions() {
        return championMapper.fromChampionList(championRepository.findAll());
    }

    public Champion createChampion(ChampionDTO championDTO) {
        championRepository.findOneByName(championDTO.getName().toLowerCase()).ifPresent(this::removeChampion);
        Champion newChampion = championMapper.fromChampionDTO(championDTO);
        championRepository.save(newChampion);
        log.debug("Created Information for User: {}", newChampion);
        return newChampion;
    }

    private void removeChampion(Champion existingUser) {
        championRepository.delete(existingUser);
    }

    public void updateChampion(Champion champion, ChampionUpdateDTO championUpdateDTO) {
        if(championUpdateDTO.getAura() != null){
            champion.setAura(championUpdateDTO.getAura());
        }
        championRepository.save(champion);
    }
}
